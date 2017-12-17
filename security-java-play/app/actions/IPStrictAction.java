package actions;

import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class IPStrictAction extends Action<IPStrict> {

    @Override
    public CompletionStage<Result> call(Http.Context ctx) {

        String[] whiteListIPs = configuration.whiteListIPs();

        //String clientIP = ctx.request().remoteAddress();

        String clientHost = ctx.request().host().split(":")[0];

        if(!iPwhitelisted(whiteListIPs, clientHost)){
            CompletableFuture.completedFuture(Results.status(403, "IP not allowed"));
        }

        return delegate.call(ctx);
    }

    private boolean iPwhitelisted(String[] whiteListIPs, String clientHost) {
        return Arrays.asList(whiteListIPs).contains(clientHost);
    }
}
