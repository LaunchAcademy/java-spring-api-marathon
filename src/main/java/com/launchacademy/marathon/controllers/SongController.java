package com.launchacademy.marathon.controllers;

import com.launchacademy.marathon.models.*;
import com.launchacademy.marathon.services.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/songs")
public class SongController {
    private SongService songService;

    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping
    public String getSongs(Model model, Pageable pageable) {
        model.addAttribute("songs", songService.findAll(pageable));
        return "songs/index";
    }

    @GetMapping("/new")
    public String getNewSong(@ModelAttribute Song song){
        return "songs/new";
    }

    @PostMapping
    public String postSong (@ModelAttribute Song song){
        if(songService.save(song))
            return ("redirect:/songs");
        else
            return "/songs/new";
    }

}
