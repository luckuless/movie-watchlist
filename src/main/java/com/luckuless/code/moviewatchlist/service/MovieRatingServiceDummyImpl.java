package com.luckuless.code.moviewatchlist.service;

import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile({"dev"})
@Service("omdbService")
public class MovieRatingServiceDummyImpl implements MovieRatingService {

    @Override
    public Optional<String> getMovieRating(String title) {
        return Optional.of("9.99");
    }
    
}
