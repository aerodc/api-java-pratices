package filters;

import akka.stream.Materializer;
import play.mvc.Filter;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;

import javax.inject.Inject;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;

public class GlobalRatelimitFilter extends Filter {

    private static final long MAX_REQUESTS = 10;
    private static final long PERIOD = 60;
    private AtomicLong remainingRequests;
    private Instant expirationTime;

    @Inject
    public GlobalRatelimitFilter(Materializer mat) {
        super(mat);
        this.remainingRequests = new AtomicLong(MAX_REQUESTS);
        this.expirationTime = Instant.now().plusSeconds(PERIOD);
    }

    @Override
    public CompletionStage<Result> apply(Function<Http.RequestHeader, CompletionStage<Result>> next, Http.RequestHeader rh) {

        if (Instant.now().isAfter(this.expirationTime)) {
            this.remainingRequests = new AtomicLong(MAX_REQUESTS);
            this.expirationTime = Instant.now().plusSeconds(PERIOD);
        }
        if (this.remainingRequests.get() > 0L) {
            this.remainingRequests.getAndDecrement();
            return next.apply(rh).thenApply(result -> result.withHeader("X-Remaining-Ratelimit", String.valueOf(this.remainingRequests.get())));
        } else {
            return CompletableFuture.completedFuture(Results.status(429, "too many requests globally"));
        }
    }
}
