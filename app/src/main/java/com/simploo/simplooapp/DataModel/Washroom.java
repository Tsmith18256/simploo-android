package com.simploo.simplooapp.DataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jerry Tsai on 16-09-23.
 */
public class Washroom implements Serializable {
    @SerializedName("id")
    @Expose
    private long id;

    /**
     * @return The id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(long id) {
        this.id = id;
    }

    @SerializedName("name")
    @Expose
    private String name;

    /**
     * @return The area name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The area name
     */
    public void setName(String name) {
        this.name = name;
    }

    @SerializedName("description")
    @Expose
    private String description;

    /**
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @SerializedName("latitude")
    @Expose
    private float latitude;

    /**
     * @return The latitude of washroom location
     */
    public float getLatitude() {
        return latitude;
    }

    /**
     * @param latitude The latitude
     */
    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    @SerializedName("longitude")
    @Expose
    private float longitude;

    /**
     * @return The longitude of washroom location
     */
    public float getLongitude() {
        return longitude;
    }

    /**
     * @param longitude The longitude
     */
    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    @SerializedName("hasWheelchairAccess")
    @Expose
    private boolean hasWheelchairAccess;

    /**
     * @return Wheelchair accessibility
     */
    public boolean isHasWheelchairAccess() {
        return hasWheelchairAccess;
    }

    /**
     * @param hasWheelchairAccess Wheelchair accessibility
     */
    public void setHasWheelchairAccess(boolean hasWheelchairAccess) {
        this.hasWheelchairAccess = hasWheelchairAccess;
    }

    @SerializedName("washrooms")
    @Expose
    private List<Washroom> washrooms = new ArrayList<Washroom>();

    /**
     * @return The list of all washrooms
     */
    public List<Washroom> getWashrooms() {
        return washrooms;
    }

    /**
     * @param washrooms The all the washrooms
     */
    public void setWashrooms(List<Washroom> washrooms) {
        this.washrooms = washrooms;
    }

    @SerializedName("rating")
    @Expose
    private float rating;

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
