package com.example.cityapp.Model;

import java.io.Serializable;

public class Item implements Serializable {
    int placeID;
    String placeAddress;
    int locationID;
    int userID;
    String description;
    String category;
    String placeName;
    String latitude;
    String lagtitude;
    String imgPath;

    public Item(int placeID, String placeAddress, int locationID, int userID, String description, String category, String placeName, String latitude, String lagtitude, String imgPath) {
        this.placeID = placeID;
        this.placeAddress = placeAddress;
        this.locationID = locationID;
        this.userID = userID;
        this.description = description;
        this.category = category;
        this.placeName = placeName;
        this.latitude = latitude;
        this.lagtitude = lagtitude;
        this.imgPath = imgPath;
    }


    public int getPlaceID() {
        return placeID;
    }

    public void setPlaceID(int placeID) {
        this.placeID = placeID;
    }

    public String getPlaceAddress() {
        return placeAddress;
    }

    public void setPlaceAddress(String placeAddress) {
        this.placeAddress = placeAddress;
    }

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLagtitude() {
        return lagtitude;
    }

    public void setLagtitude(String lagtitude) {
        this.lagtitude = lagtitude;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}