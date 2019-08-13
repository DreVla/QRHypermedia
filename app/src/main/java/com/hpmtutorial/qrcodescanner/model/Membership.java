package com.hpmtutorial.qrcodescanner.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Membership {

    @SerializedName("id")
    @Expose
    private Object id;
    @SerializedName("name")
    @Expose
    private Object name;
    @SerializedName("startDate")
    @Expose
    private Object startDate;
    @SerializedName("endDate")
    @Expose
    private Object endDate;
    @SerializedName("status")
    @Expose
    private Object status;
    @SerializedName("price")
    @Expose
    private Object price;
    @SerializedName("currency")
    @Expose
    private Object currency;
    @SerializedName("nextBilling")
    @Expose
    private Object nextBilling;
    @SerializedName("entries")
    @Expose
    private Object entries;
    @SerializedName("photoUrl")
    @Expose
    private Object photoUrl;

    /**
     * No args constructor for use in serialization
     *
     */
    public Membership() {
    }

    /**
     *
     * @param id
     * @param startDate
     * @param price
     * @param nextBilling
     * @param status
     * @param name
     * @param entries
     * @param endDate
     * @param photoUrl
     * @param currency
     */
    public Membership(Object id, Object name, Object startDate, Object endDate, Object status, Object price, Object currency, Object nextBilling, Object entries, Object photoUrl) {
        super();
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.price = price;
        this.currency = currency;
        this.nextBilling = nextBilling;
        this.entries = entries;
        this.photoUrl = photoUrl;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public Object getStartDate() {
        return startDate;
    }

    public void setStartDate(Object startDate) {
        this.startDate = startDate;
    }

    public Object getEndDate() {
        return endDate;
    }

    public void setEndDate(Object endDate) {
        this.endDate = endDate;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public Object getPrice() {
        return price;
    }

    public void setPrice(Object price) {
        this.price = price;
    }

    public Object getCurrency() {
        return currency;
    }

    public void setCurrency(Object currency) {
        this.currency = currency;
    }

    public Object getNextBilling() {
        return nextBilling;
    }

    public void setNextBilling(Object nextBilling) {
        this.nextBilling = nextBilling;
    }

    public Object getEntries() {
        return entries;
    }

    public void setEntries(Object entries) {
        this.entries = entries;
    }

    public Object getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(Object photoUrl) {
        this.photoUrl = photoUrl;
    }

}