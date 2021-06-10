package com.launchacademy.marathon.repositories;

import com.launchacademy.marathon.models.*;
import org.springframework.data.repository.*;

public interface SongRepository extends PagingAndSortingRepository<Song,Integer> {
}
