package com.luckuless.code.moviewatchlist.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.luckuless.code.moviewatchlist.domain.WatchlistItem;
import com.luckuless.code.moviewatchlist.exception.DuplicateTitleException;
import com.luckuless.code.moviewatchlist.repository.WatchlistRepository;

@Service
public class WatchlistService {

	private final WatchlistRepository watchlistRepository;
	private final MovieRatingService movieRatingService;

	public WatchlistService(WatchlistRepository watchlistRepository, MovieRatingService movieRatingService) {
		this.watchlistRepository = watchlistRepository;
		this.movieRatingService = movieRatingService;
	}

	public List<WatchlistItem> getWatchlistItems() {
		List<WatchlistItem> watchlistItems = watchlistRepository.getList();
				
		for (WatchlistItem watchlistItem : watchlistItems) {
			Optional<String> movieRating = movieRatingService.getMovieRating(watchlistItem.getTitle());
			if (movieRating.isPresent()) {
				watchlistItem.setRating(movieRating.get());
			}
		}
		return watchlistItems;
	}
	
	public int getWatchlistItemsSize() {
		return watchlistRepository.getList().size();
	}
	
	public WatchlistItem findWatchlistItemById(Integer id) {
		return watchlistRepository.findById(id);
	}
	
	public void addOrUpdateWatchlistItem(WatchlistItem watchlistItem) throws DuplicateTitleException {
		
		WatchlistItem existingItem = findWatchlistItemById(watchlistItem.getId());
		
		if (existingItem == null) {
			if (watchlistRepository.findByTitle(watchlistItem.getTitle())!=null) {
				throw new DuplicateTitleException();
			}
			watchlistRepository.addItem(watchlistItem);
		} else {
			existingItem.setComment(watchlistItem.getComment());
			existingItem.setPriority(watchlistItem.getPriority());
			existingItem.setRating(watchlistItem.getRating());
			existingItem.setTitle(watchlistItem.getTitle());  
		}
	}
}
