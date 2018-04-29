package net.aerodc.springbootreactiveapi.handlers;

import net.aerodc.springbootreactiveapi.jsons.UserJson;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class UserHandler {

    public Mono<ServerResponse> getUser(ServerRequest request){
            try{
                String uuid = request.pathVariable("uuid");
                return ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromObject(new UserJson(uuid, "bob")));
            }catch (IllegalArgumentException i){
                return ServerResponse.badRequest()
                        .contentType(MediaType.TEXT_PLAIN)
                        .body(BodyInserters.fromObject(i.getMessage()));
            }
    }
}
