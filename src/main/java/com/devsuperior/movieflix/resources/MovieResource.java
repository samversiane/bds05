package com.devsuperior.movieflix.resources;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.MovieGenreDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.services.MovieService;
import com.devsuperior.movieflix.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @Autowired
    private MovieService movieService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> findById(@PathVariable Long id) {
        MovieDTO movieDTO = movieService.findById(id);
        return ResponseEntity.ok().body(movieDTO);
    }

    @GetMapping
    public ResponseEntity<Page<MovieGenreDTO>> findByGenre(
            @RequestParam(name = "genreId", defaultValue = "0") Long genreId
            , Pageable pageable) {
        Page<MovieGenreDTO> page = movieService.findByGenre(genreId, pageable);
        return ResponseEntity.ok().body(page);
    }

    @GetMapping("/{id}/reviews")
    public ResponseEntity<List<ReviewDTO>> findMovieByReviews(@PathVariable Long id) {
        List<ReviewDTO> reviewsDTO = movieService.findMovieByReviews(id);
        return ResponseEntity.ok().body(reviewsDTO);
    }
}
