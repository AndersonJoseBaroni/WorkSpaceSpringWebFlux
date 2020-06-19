package com.restfull.webflux;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
// Manter comentado para o Spring não instaciar ela quando estamos testando o controller
//@Configuration
public class PlaylistRouter {
    // Esse metodo retorna um Router Function
	//@Bean
	public RouterFunction<ServerResponse> route(PlaylistHandler handler) {
		return RouterFunctions.route(GET("/playlist").and(accept(MediaType.APPLICATION_JSON)), handler::findAll)
				.andRoute(GET("/playlist/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::findById)
				.andRoute(POST("/playlist").and(accept(MediaType.APPLICATION_JSON)), handler::save);

	}

}
