package net.aerodc.springbootrestapi.controllers;

import net.aerodc.springbootrestapi.jsons.UserJson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @RequestMapping(value = "/users/{uuid}", method = RequestMethod.GET)
    public ResponseEntity<UserJson> index(@PathVariable("uuid") String uuid) {
        UserJson userJson = new UserJson(uuid, "bob");
        return new ResponseEntity<>(userJson, HttpStatus.OK);
    }
}
