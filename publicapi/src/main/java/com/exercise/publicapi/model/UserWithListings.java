package com.exercise.publicapi.model;

import lombok.Data;
import java.util.List;

@Data
public class UserWithListings {
	private User user;
    private List<Listing> listings;
    
 // Constructor
    public UserWithListings(User user, List<Listing> listings) {
        this.user = user;
        this.listings = listings;
    }

    // Getters and setters
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Listing> getListings() {
        return listings;
    }

    public void setListings(List<Listing> listings) {
        this.listings = listings;
    }
}

    

