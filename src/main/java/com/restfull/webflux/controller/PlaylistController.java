package com.restfull.webflux.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.restfull.webflux.document.Playlist;
import com.restfull.webflux.services.PlaylistService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

//@CrossOrigin(origins = "*")
// deixar comentado quando for testar o Handler para não ter conflito.
@RestController
public class PlaylistController {

	@Autowired
	PlaylistService service;

	@GetMapping(value = "/playlist")
	public Flux<Playlist> getPlaylist() {
		return service.findAll();
	}

	@GetMapping(value = "/playlist/{id}")
	public Mono<Playlist> getPlaylistId(@PathVariable String id) {
		return service.findById(id);
	}

	@PostMapping(value = "/playlist")
	public Mono<Playlist> save(@RequestBody Playlist playlist) {
		return service.save(playlist);
	}

	@GetMapping(value = "/playlist/webflux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Tuple2<Long, Playlist>> getPlaylistByWebflux() {
        // Esta criando um intervalo de tempo para conseguirmos verificar o funcionamento da aplicação reativa
		// Abrir duas abas no browser e fazer duas chamadas vai iniciar a segunda antes mesmo de terminar a primeira
		// Isso mostra o programação reativa assincrona não fica travado a segunda ate terminar a primeira
        // Quando não usa reativo é sincrono a seginda precisa aguardar ate a primeira ser executada
		System.out.println("---Start get Playlists by WEBFLUX--- " + LocalDateTime.now());
		Flux<Long> interval = Flux.interval(Duration.ofSeconds(5));
		Flux<Playlist> playlistFlux = service.findAll();

		return Flux.zip(interval, playlistFlux);

	}

	@GetMapping(value = "/playlist/mvc")
	public List<String> getPlaylistByMvc() throws InterruptedException {

		System.out.println("---Start get Playlists by MVC--- " + LocalDateTime.now());

		List<String> playlistList = new ArrayList<>();
		playlistList.add("Java 8");
		playlistList.add("Spring Security");
		playlistList.add("Github");
		playlistList.add("Deploy de uma aplicação java no IBM Cloud");
		playlistList.add("Bean no Spring Framework");
		TimeUnit.SECONDS.sleep(15);

		return playlistList;

	}

}
