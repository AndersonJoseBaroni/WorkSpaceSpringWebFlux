package com.restfull.webflux.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.restfull.webflux.document.Playlist;

public interface PlaylistRespository extends ReactiveMongoRepository<Playlist, String>{

}
