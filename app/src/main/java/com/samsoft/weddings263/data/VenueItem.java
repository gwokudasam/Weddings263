package com.samsoft.weddings263.data;

/**
 * Created by gwokudasam on 08-Oct-2015.
 */
public class VenueItem {
    private String venueName;
    private String email;
    private String phone;
    private String reviews;
    private int venuePic;
    private String venueAddress;
    private String description;
    private double latitude;
    private double longitude;

    public VenueItem(String venueName, String email, String phone, String address,
                     String reviews, String description, int venuePic, double latitude, double longitude) {

        this.venueName = venueName;
        this.email = email;
        this.phone = phone;
        this.venueAddress = address;
        this.reviews = reviews;
        this.description = description;
        this.venuePic = venuePic;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getVenueAddress() {
        return venueAddress;
    }

    public int getVenuePic() {
        return venuePic;
    }

    public String getDescription() {
        return description;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getReviews() {
        return reviews;
    }

    public String getVenueName() {
        return venueName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }


}
