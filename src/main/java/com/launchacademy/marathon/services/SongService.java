package com.launchacademy.marathon.services;

import com.launchacademy.marathon.models.Song;
import com.launchacademy.marathon.repository.SongRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

  public void save(Song song) {
    songRepository.save(song);
  }

  public Optional<Song> findById(Integer id) {
    return songRepository.findById(id);
  }
}
