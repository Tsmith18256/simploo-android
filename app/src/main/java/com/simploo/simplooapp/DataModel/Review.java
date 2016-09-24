package com.simploo.simplooapp.DataModel;

/**
 * Created by user on 16-09-23.
 */
public class Review {
    //All ID's used.
    private long id;
    private long userId;
    private long washroomId;

    //Body of the review
    private String reviewDescription;

    //totalRating = (cleaninessRating + privacyRating + safetyRating + accessibiltyRating)/4
    private int cleaninessRating;
    private int privacyRating;
    private int safetyRating;
    private int accessibiltyRating;

    //BEGINNING OF GETTERS AND SETTERS

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getWashroomId() {
        return washroomId;
    }

    public void setWashroomId(long washroomId) {
        this.washroomId = washroomId;
    }

    public String getReviewDescription() {
        return reviewDescription;
    }

    public void setReviewDescription(String reviewDescription) {
        this.reviewDescription = reviewDescription;
    }

    public int getCleaninessRating() {
        return cleaninessRating;
    }

    public void setCleaninessRating(int cleaninessRating) {
        this.cleaninessRating = cleaninessRating;
    }

    public int getPrivacyRating() {
        return privacyRating;
    }

    public void setPrivacyRating(int privacyRating) {
        this.privacyRating = privacyRating;
    }

    public int getSafetyRating() {
        return safetyRating;
    }

    public void setSafetyRating(int safetyRating) {
        this.safetyRating = safetyRating;
    }

    public int getAccessibiltyRating() {
        return accessibiltyRating;
    }

    public void setAccessibiltyRating(int accessibiltyRating) {
        this.accessibiltyRating = accessibiltyRating;
    }

    //END OF GETTERS AND SETTERS

    
}
