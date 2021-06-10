package com.launchacademy.marathon.controllers;

import com.launchacademy.marathon.models.Song;
import com.launchacademy.marathon.services.SongService;
import java.util.HashMap;
import java.util.Map;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/songs")
public class ApiV1SongController {

  private final SongService songService;

  @Autowired
  public ApiV1SongController(SongService songService) {
    this.songService = songService;
  }

  @GetMapping
  public Map<String, Iterable<Song>> getSongsList(Pageable pageable) {
    Map<String, Iterable<Song>> songs = new HashMap<>();
    songs.put("songs", songService.findAll(pageable));
    return songs;
  }

  @GetMapping("/{id}")
  public Map<String, Song> getSong(@PathVariable Integer id) throws SongNotFoundException {
    Map<String, Song> song = new HashMap<>();
    song.put("song", songService.findById(id).orElseThrow(() -> new SongNotFoundException()));
    return song;
  }

  @NoArgsConstructor
  private class SongNotFoundException extends RuntimeException {};

  @ControllerAdvice
  private class SongNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(SongNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String songNotFoundHandler(SongNotFoundException ex) {
      return ex.getMessage();
    }
  }
}
