package com.simploo.simplooapp.DataModel;

/**
 * Created by Jerry Tsai on 16-09-23.
 */
public class Washroom {

    private long id;
    private String name;
    private String description;
    private float lat;
    private float lng;
    private boolean hasWheelchairAccess;

    private float overallRating;

    public Washroom() {
        this.id = -1;
        this.name = null;
        this.description = null;
        this.lat = -1;
        this.lng = -1;
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
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
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
