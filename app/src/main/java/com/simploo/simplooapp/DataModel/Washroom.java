package com.simploo.simplooapp.DataModel;

/**
 * Created by Jerry Tsai on 16-09-23.
 */
public class Washroom {

    private long id;
    private String name;
    private String description;
    private float latitude;
    private float longitude;
    private boolean hasWheelchairAccess;
    private float overallRating;

    public Washroom() {
        this.id = -1;
        this.name = null;
        this.description = null;
        this.latitude = -1;
        this.longitude = -1;
        this.hasWheelchairAccess = false;
        this.overallRating = -1;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getLat() {
        return latitude;
    }

    public void setLat(float latitude) {
        this.latitude = latitude;
    }

    public float getLng() {
        return longitude;
    }

    public void setLng(float longitude) {
        this.longitude = longitude;
    }

    public boolean hasWheelchairAccess() {
        return hasWheelchairAccess;
    }

    public void gettHasWheelchairAccess(boolean hasWheelchairAccess) {
        this.hasWheelchairAccess = hasWheelchairAccess;
    }

    public float getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(float overallRating) {
        this.overallRating = overallRating;
    }
}
