package actions;

import play.cache.NamedCache;
import play.cache.SyncCacheApi;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import ratelimit.Ratelimit;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class RatelimitAction extends Action<Ratelimited> {

    private static final String APP_GLOBAL_RATELIMIT = "app-global-ratelimit";

    @Inject
    @NamedCache("ratelimit-cache")
    private SyncCacheApi cache;

    @Override
    public CompletionStage<Result> call(Http.Context ctx) {
        int limit = configuration.limit();
        int period = configuration.period();
        Ratelimit ratelimit = cache.getOrElseUpdate(APP_GLOBAL_RATELIMIT, () -> new Ratelimit(limit, period));

        if (ratelimit.reached()) {
            return CompletableFuture.completedFuture(Results.status(429, "too may requests"));
        }

        if (ratelimit.expired()) {
            ratelimit = new Ratelimit(limit, period);
            cache.set(APP_GLOBAL_RATELIMIT, ratelimit);
        }

        ratelimit.decrease();

        return delegate.call(ctx);
    }
}
