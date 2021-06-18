package com.esd.sercom.bulksms.model.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UagTransactionModificationDTO {
    private String accountName;
    private Integer validity;
    private Integer numberOfSms;
    private Long amount;
    private String correlationId;
    private String dateCreated;
    private String rechargeSuccess;
    private String accountType;
    private int rollOverFlag;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Integer getValidity() {
        return validity;
    }

    public void setValidity(Integer validity) {
        this.validity = validity;
    }

    public Integer getNumberOfSms() {
        return numberOfSms;
    }

    public void setNumberOfSms(Integer numberOfSms) {
        this.numberOfSms = numberOfSms;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getRechargeSuccess() {
        return rechargeSuccess;
    }

    public void setRechargeSuccess(String rechargeSuccess) {
        this.rechargeSuccess = rechargeSuccess;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public int getRollOverFlag() {
        return rollOverFlag;
    }

    public void setRollOverFlag(int rollOverFlag) {
        this.rollOverFlag = rollOverFlag;
    }

    @Override
    public String toString() {
        return "UagTransactionModificationDTO{" +
                "accountName='" + accountName + '\'' +
                ", validity=" + validity +
                ", numberOfSms=" + numberOfSms +
                ", amount=" + amount +
                ", correlationId='" + correlationId + '\'' +
                ", dateCreated='" + dateCreated + '\'' +
                ", rechargeSuccess='" + rechargeSuccess + '\'' +
                ", accountType='" + accountType + '\'' +
                ", rollOverFlag=" + rollOverFlag +
                '}';
    }
}
