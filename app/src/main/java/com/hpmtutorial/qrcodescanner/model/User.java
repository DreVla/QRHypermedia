package com.hpmtutorial.qrcodescanner.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("photoUrl")
    @Expose
    private String photoUrl;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("membership")
    @Expose
    private Membership membership;

    /**
     * No args constructor for use in serialization
     *
     */
    public User() {
    }

    /**
     *
     * @param id
     * @param lastName
     * @param membership
     * @param email
     * @param photoUrl
     * @param firstName
     */
    public User(String firstName, long id, String email, String photoUrl, String lastName, Membership membership) {
        this.firstName = firstName;
        this.id = id;
        this.email = email;
        this.photoUrl = photoUrl;
        this.lastName = lastName;
        this.membership = membership;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }
}