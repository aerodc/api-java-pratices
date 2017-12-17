package controllers;

import actions.IPStrict;
import play.mvc.Result;

import static play.mvc.Results.ok;

@IPStrict
public class PingController {

    public Result ping(){
        return ok();
    }
}
