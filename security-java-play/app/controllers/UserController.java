package controllers;

import actions.IPStrict;
import play.mvc.Controller;
import play.mvc.Result;

@IPStrict
public class UserController extends Controller {

    public Result getUserInfo(String uuid){

        return ok(uuid);
    }
}
