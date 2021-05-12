package com.launchacademy.marathon.controllers;

import com.launchacademy.marathon.models.Song;
import com.launchacademy.marathon.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ApiV1Controller {

  private class SongNotFoundException extends RuntimeException {};
  private SongRepository songRepo;

  @ControllerAdvice
  private class SongNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(SongNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String songNotFoundHandler(SongNotFoundException e) {
      return e.getMessage();
    }
  }

  @Autowired
  public ApiV1Controller(SongRepository songRepo) {
    this.songRepo = songRepo;
  }

  @GetMapping("/songs")
  public Page<Song> getSongs(Pageable pageable) {
    return this.songRepo.findAll(pageable);
  }

  @GetMapping("/songs/{id}")
  public Song getSong(@PathVariable Integer id) {
    return songRepo.findById(id).orElseThrow(() -> new SongNotFoundException());
  }
  @PostMapping("/api/v1/songs")
  public Song createSong(@RequestBody Song song) {
    return songRepo.save(song);

  }
}
