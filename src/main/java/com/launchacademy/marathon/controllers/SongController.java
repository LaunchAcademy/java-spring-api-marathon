package com.launchacademy.marathon.controllers;

import com.launchacademy.marathon.models.Song;
import com.launchacademy.marathon.repositories.SongRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/songs")
public class SongController {
  private SongRepository songRepo;

  @Autowired
  public SongController(SongRepository songRepo) {
    this.songRepo = songRepo;
  }
  @GetMapping
  public String listSongs(Pageable pageable, Model model) {
    model.addAttribute("songs", songRepo.findAll(pageable));
    return "songs/index";
  }

  @GetMapping("/new")
  public String newSong(@ModelAttribute("song") Song song){
    return "songs/new";
  }
  @PostMapping
  public String addSong(@ModelAttribute("song") @Valid Song song, BindingResult bindingResult){
    if (bindingResult.hasErrors()) {
      return "songs/new";
    } else {
      songRepo.save(song);
      return "redirect:/songs";
    }
  }

}
