package com.samsoft.weddings263;

public class DataObject {
    private String venueName;

    private String venueLocation;

    DataObject (String text1, String text2){
        venueName = text1;
        venueLocation = text2;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getVenueLocation() {
        return venueLocation;
    }

    public void setVenueLocation(String venueLocation) {
        this.venueLocation = venueLocation;
    }
}