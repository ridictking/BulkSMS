package com.esd.sercom.bulksms.model.entity;

import com.esd.sercom.bulksms.model.DTO.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Bulksms_Users")
public class UserEntity {

    @NotNull
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String userState;
    private LocalDateTime registrationDate;
    private String correlationId;
    private String userGroup;
    private String password;
    private String accountCode;
    private String eventType;
    private String transactionId;
    private String operationType;
    private String msisdn;
    @Column(columnDefinition = "varchar(155) default 'BULKSMS'")
    private String serviceType;

    public UserEntity(UserDetails details) {
        this.id = details.getId();
        this.firstName = details.getFirstName();
        this.lastName = details.getLastName();
        this.email = details.getEmail();
        this.userState = details.getUserState();
        this.registrationDate = details.getRegistrationDate();
        this.correlationId = details.getCorrelationId();
        this.userGroup = details.getUserGroup();
        this.password = details.getPassword();
        this.accountCode = details.getAccountCode();
        this.eventType = details.getEventType();
        this.transactionId = details.getTransactionId();
        this.operationType = details.getOperationType();
        this.msisdn = details.getMsisdn();
        this.serviceType = details.getServiceType();
    }

    public UserEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }
}
