package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.MovieGenreDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Transactional(readOnly = true)
    public MovieDTO findById(Long id) {
        Optional<Movie> obj = movieRepository.findById(id);
        Movie movie = obj.orElseThrow(() -> new ResourceNotFoundException("Movie not found"));
        return new MovieDTO(movie);
    }

    @Transactional(readOnly = true)
    public Page<MovieGenreDTO> findByGenre(Long genreId, Pageable pageable) {
        movieRepository.findWithGenreId();
        Page<Movie> page = movieRepository.findByGenreId(genreId, pageable);
        return page.map(x -> new MovieGenreDTO(x));
    }

    @Transactional(readOnly = true)
    public List<ReviewDTO> findMovieByReviews(Long id) {
        List<Review> reviews = reviewRepository.findByMovieId(id);
        return reviews.stream().map(x -> new ReviewDTO(x)).collect(Collectors.toList());
    }
}
