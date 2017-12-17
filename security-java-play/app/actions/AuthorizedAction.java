package actions;

import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import services.AuthService;

import javax.inject.Inject;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class AuthorizedAction extends Action<Authorization> {

    private final AuthService authService;

    @Inject
    public AuthorizedAction(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public CompletionStage<Result> call(Http.Context ctx) {

        boolean need = configuration.need();

        if (need) {
            Optional<String> accessTokenOptional = ctx.request().header("Authorization");
            if (accessTokenOptional.isPresent() && accessTokenOptional.get().startsWith("Bearer ")) {
                String accessToken = accessTokenOptional.get().replace("Bearer ", "");
                if (!authService.accessTokenValidation(accessToken)) {
                    return CompletableFuture.completedFuture(Results.forbidden());
                }
            } else {
                return CompletableFuture.completedFuture(Results.forbidden());
            }

        }
        return delegate.call(ctx);
    }
}
