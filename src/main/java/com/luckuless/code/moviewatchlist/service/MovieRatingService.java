package com.luckuless.code.moviewatchlist.service;

import java.util.Optional;

public interface MovieRatingService {
    public Optional<String> getMovieRating(String title);
}
