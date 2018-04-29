package net.aerodc.springbootreactiveapi.routers;

import net.aerodc.springbootreactiveapi.handlers.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class UserRouter {

    @Bean
    public RouterFunction<ServerResponse> route(UserHandler userHandler){
        return RouterFunctions.route(RequestPredicates.GET("/users/{uuid}").
                and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
                userHandler::getUser);
    }
}
