package com.restfull.webflux;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.restfull.webflux.document.Playlist;
import com.restfull.webflux.services.PlaylistService;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;

import reactor.core.publisher.Mono;
//Manter comentado para o Spring não instaciar ela quando estamos testando o controller
//@Component
public class PlaylistHandler {

	@Autowired
	PlaylistService service;

	// ServerResponse faz parte da biblioteca reativa do Spring framework
	// ServerRequest faz parte da biblioteca reativa do Spring framework
	// ok()faz parte da biblioteca reativa do Spring framework
	// Mapeamento usando estilo funcional de programação
	public Mono<ServerResponse> findAll(ServerRequest request) {
		return ok().contentType(MediaType.APPLICATION_JSON).body(service.findAll(), Playlist.class);
	}

	public Mono<ServerResponse> findById(ServerRequest request) {
		String id = request.pathVariable("id");
		return ok().contentType(MediaType.APPLICATION_JSON).body(service.findById(id), Playlist.class);
	}
    // service::save chamada de metodo no java 8
	public Mono<ServerResponse> save(ServerRequest request) {
		final Mono<Playlist> playlist = request.bodyToMono(Playlist.class);
		return ok().contentType(MediaType.APPLICATION_JSON)
				.body(fromPublisher(playlist.flatMap(service::save), Playlist.class));
	}

}
