package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import services.AuthService;

import javax.inject.Inject;

public class AuthenticationController extends Controller {

    private final AuthService authService;

    @Inject
    public AuthenticationController(AuthService authService) {
        this.authService = authService;
    }


    public Result authenticate(String username, String password) {

        if (authService.authenticate(username, password)) {
            ObjectNode result = Json.newObject();
            result.put("accessToken", "accesstokenadmintest123");
            return ok(result);
        }else{
            return Results.forbidden();
        }
    }
}
