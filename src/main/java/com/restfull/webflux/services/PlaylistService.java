package com.restfull.webflux.services;

import com.restfull.webflux.document.Playlist;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlaylistService {

	//Programação Assincrona reativa retorna dois tipos Flux e Mono por isso não retorna uma lista
	Flux<Playlist> findAll();
	// Quando for apenas 0 ou 1 elemento o retorno deve ser Mono
	Mono<Playlist> findById(String id);
	Mono<Playlist> save(Playlist playlist);

}
