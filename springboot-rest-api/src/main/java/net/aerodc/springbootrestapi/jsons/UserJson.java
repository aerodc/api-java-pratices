package net.aerodc.springbootrestapi.jsons;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserJson {

    @JsonProperty(value = "uuid")
    private String uuid;

    @JsonProperty(value = "name")
    private String name;

    public UserJson(String uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }
}
