package controllers;

import play.mvc.Result;

import static play.mvc.Results.ok;

public class PingController {

    public Result ping(){
        return ok();
    }
}
