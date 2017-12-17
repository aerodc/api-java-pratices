package controllers;

import actions.Authorization;
import actions.IPStrict;
import play.mvc.Controller;
import play.mvc.Result;

@IPStrict
@Authorization(need = false)
public class PingController extends Controller {

    public Result ping(){
        return ok();
    }
}
