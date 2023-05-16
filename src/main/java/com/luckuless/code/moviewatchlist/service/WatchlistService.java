package com.luckuless.code.moviewatchlist.service;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.luckuless.code.moviewatchlist.domain.WatchlistItem;
import com.luckuless.code.moviewatchlist.exception.DuplicateTitleException;
import com.luckuless.code.moviewatchlist.repository.WatchlistRepository;

public class WatchlistService {

	WatchlistRepository watchlistRepository = new WatchlistRepository();
	
	public List<WatchlistItem> getWatchlistItems(){
		return watchlistRepository.getList();
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
