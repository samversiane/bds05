package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("SELECT DISTINCT movie " +
            "FROM Movie movie " +
            "WHERE (COALESCE(:genreId) = 0 OR genre.id = :genreId) " +
            "ORDER BY movie.title ASC")
    Page<Movie> findByGenreId(Long genreId, Pageable pageable);

    @Query("SELECT movie " +
            "FROM Movie movie " +
            "JOIN FETCH movie.genre genre ")
    List<Movie> findWithGenreId();
}