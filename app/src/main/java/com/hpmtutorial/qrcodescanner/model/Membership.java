package com.hpmtutorial.qrcodescanner.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Membership {

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("startDate")
    @Expose
    private String startDate;
    @SerializedName("endDate")
    @Expose
    private String endDate;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("price")
    @Expose
    private double price;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("nextBilling")
    @Expose
    private String nextBilling;
    @SerializedName("entries")
    @Expose
    private int entries;
    @SerializedName("photoUrl")
    @Expose
    private String photoUrl;

    private long daysLeft;

    /**
     * No args constructor for use in serialization
     */
    public Membership() {
    }

    /**
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
    public Membership(long id, String name, String startDate, String endDate, String status, double price, String currency, String nextBilling, int entries, String photoUrl) {
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

    public String getStartDate() {
        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date(Long.parseLong(startDate)*1000);
        return sf.format(date);
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date(Long.parseLong(endDate)*1000);
        return sf.format(date);
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getNextBilling() {
        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date(Long.parseLong(nextBilling)*1000);
        return sf.format(date);
    }

    public void setNextBilling(String nextBilling) {
        this.nextBilling = nextBilling;
    }

    public int getEntries() {
        return entries;
    }

    public void setEntries(int entries) {
        this.entries = entries;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public long getDaysLeft(){
        long diffInMillies = Math.abs(Long.parseLong(endDate) - Long.parseLong(startDate));
        this.daysLeft = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return daysLeft;
    }

}