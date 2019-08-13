package com.hpmtutorial.qrcodescanner.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("firstName")
    @Expose
    private Object firstName;
    @SerializedName("id")
    @Expose
    private Object id;
    @SerializedName("email")
    @Expose
    private Object email;
    @SerializedName("photoUrl")
    @Expose
    private Object photoUrl;
    @SerializedName("lastName")
    @Expose
    private Object lastName;
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
    public User(Object firstName, Object id, Object email, Object photoUrl, Object lastName, Membership membership) {
        super();
        this.firstName = firstName;
        this.id = id;
        this.email = email;
        this.photoUrl = photoUrl;
        this.lastName = lastName;
        this.membership = membership;
    }

    public Object getFirstName() {
        return firstName;
    }

    public void setFirstName(Object firstName) {
        this.firstName = firstName;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public Object getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(Object photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Object getLastName() {
        return lastName;
    }

    public void setLastName(Object lastName) {
        this.lastName = lastName;
    }

    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

}