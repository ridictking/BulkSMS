package com.esd.sercom.bulksms.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class BulkSmsPricing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private long id;
    private long lowerBoundUnit;
    private long upperBoundUnit;
    private float promotionalPricePerSms;
    private float transactionalPricePerSms;
    @Column(nullable = true)
    private String rank;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLowerBoundUnit() {
        return lowerBoundUnit;
    }

    public void setLowerBoundUnit(long lowerBoundUnit) {
        this.lowerBoundUnit = lowerBoundUnit;
    }

    public long getUpperBoundUnit() {
        return upperBoundUnit;
    }

    public void setUpperBoundUnit(long upperBoundUnit) {
        this.upperBoundUnit = upperBoundUnit;
    }

    public float getPromotionalPricePerSms() {
        return promotionalPricePerSms;
    }

    public void setPromotionalPricePerSms(float promotionalPricePerSms) {
        this.promotionalPricePerSms = promotionalPricePerSms;
    }

    public float getTransactionalPricePerSms() {
        return transactionalPricePerSms;
    }

    public void setTransactionalPricePerSms(float transactionalPricePerSms) {
        this.transactionalPricePerSms = transactionalPricePerSms;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
