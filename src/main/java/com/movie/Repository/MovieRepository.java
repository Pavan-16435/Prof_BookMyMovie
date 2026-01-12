package com.movie.Repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.Entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findByNameIgnoreCase(String name);
}

