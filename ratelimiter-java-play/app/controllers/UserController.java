package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import actions.Ratelimited;

public class UserController extends Controller {

    @Ratelimited(limit = 2, period = 10)
    public Result getUserInfo(String uuid){

        return ok(uuid);
    }
}
