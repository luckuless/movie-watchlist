package com.luckuless.code.moviewatchlist.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.luckuless.code.moviewatchlist.domain.WatchlistItem;

@Repository
public class WatchlistRepository {
	private List<WatchlistItem> watchlistItems = new ArrayList<WatchlistItem>();
	private static int index = 1;
	
	public List<WatchlistItem> getList(){
		return watchlistItems;
	}
	
	public void addItem(WatchlistItem watchlistItem) {
		watchlistItem.setId(index++);
		watchlistItems.add(watchlistItem);
	}
	
	public WatchlistItem findById(Integer id) {
		for (WatchlistItem watchlistItem : watchlistItems) {
			if (watchlistItem.getId().equals(id)) {
				return watchlistItem;
			}
		}
		return null;
	}
	
	public WatchlistItem findByTitle(String title) {
		for (WatchlistItem watchlistItem : watchlistItems) {
			if (watchlistItem.getTitle().equals(title)) {
				return watchlistItem;
			}
		}
		return null;		
	}
}