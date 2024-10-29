package com.exercise.publicapi.dto;

public class CreateListingResponseDto {

    private boolean success;
    private ListingDto listing;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ListingDto getListing() {
        return listing;
    }

    public void setListing(ListingDto listing) {
        this.listing = listing;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
