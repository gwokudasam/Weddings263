package com.samsoft.weddings263;

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

    public VenueItem() {
    }

    public VenueItem(String venueName, String email, String phone, String address, String reviews, String description, int venuePic) {

        this.venueName = venueName;
        this.email = email;
        this.phone = phone;
        this.venueAddress = address;
        this.reviews = reviews;
        this.description = description;
        this.venuePic = venuePic;

    }

    public String getVenueAddress() {
        return venueAddress;
    }

    public void setVenueAddress(String venueAddress) {
        this.venueAddress = venueAddress;
    }

    public int getVenuePic() {
        return venuePic;
    }

    public void setVenuePic(int venuePic) {
        this.venuePic = venuePic;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
