package controllers;

import actions.Authorization;
import actions.IPStrict;
import play.mvc.Controller;
import play.mvc.Result;

@IPStrict
@Authorization(need = true)
public class UserController extends Controller {

    public Result getUserInfo(String uuid){

        return ok(uuid);
    }
}
