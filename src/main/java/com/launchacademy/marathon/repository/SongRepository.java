package com.launchacademy.marathon.repository;

import com.launchacademy.marathon.models.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends PagingAndSortingRepository<Song, Integer> {
  Page<Song> findAll(Pageable pageable);

}
