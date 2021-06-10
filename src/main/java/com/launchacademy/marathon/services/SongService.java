package com.launchacademy.marathon.services;

import com.launchacademy.marathon.models.*;
import com.launchacademy.marathon.repositories.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class SongService {
    private SongRepository songRepository;

    @Autowired
    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public Page<Song> findAll(Pageable pageable) {
        return songRepository.findAll(pageable);
    }

    public boolean save(Song song) {
        try {
            songRepository.save(song);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("\n\n******** Exception in save ******\n\n");
            return false;
        }
        return true;
    }

    public Optional<Song> findById(Integer id) {
        return songRepository.findById(id);
    }
}
