package com.esd.sercom.bulksms.model.entity;

import com.esd.sercom.bulksms.model.DTO.UagTransactionModificationDTO;
import com.esd.sercom.bulksms.util.Status;
import org.springframework.data.domain.Persistable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
public class UagTransactionEntity implements Serializable, Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String accountName;
    @NotNull
    private Integer validityDuration;
    @NotNull
    private Integer numberOfSms;
    @NotNull
    private Boolean rollOver;
    @NotNull
    private String correlationId;
    @NotNull
    private LocalDateTime subscriptionDate;
    private Status status;
    private Long amount;

    public UagTransactionEntity of(UagTransactionModificationDTO dto){
        return this.setAccountName(dto.getAccountName())
                .setNumberOfSms(dto.getNumberOfSms())
                .setValidityDuration(dto.getValidity())
                .setAmount(dto.getAmount())
                .setSubscriptionDate(LocalDateTime.now())
                .setCorrelationId(UUID.randomUUID().toString())
                .setRollOver(false);
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public UagTransactionEntity setAccountName(String accountName) {
        this.accountName = accountName;
        return this;
    }

    public int getValidityDuration() {
        return validityDuration;
    }

    public UagTransactionEntity setValidityDuration(int validityDuration) {
        this.validityDuration = validityDuration;
        return this;
    }

    public int getNumberOfSms() {
        return numberOfSms;
    }

    public UagTransactionEntity setNumberOfSms(int numberOfSms) {
        this.numberOfSms = numberOfSms;
        return this;
    }

    public boolean isRollOver() {
        return rollOver;
    }

    public UagTransactionEntity setRollOver(boolean rollOver) {
        this.rollOver = rollOver;
        return this;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public UagTransactionEntity setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
        return this;
    }

    public LocalDateTime getSubscriptionDate() {
        return subscriptionDate;
    }

    public UagTransactionEntity setSubscriptionDate(LocalDateTime subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public UagTransactionEntity setStatus(Status status) {
        this.status = status;
        return this;
    }

    public Long getAmount() {
        return amount;
    }

    public UagTransactionEntity setAmount(Long amount) {
        this.amount = amount;
        return this;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return null == getId();
    }

    @Override
    public String toString() {
        return "UagTransactionEntity{" +
                "id=" + id +
                ", accountName='" + accountName + '\'' +
                ", validityDuration=" + validityDuration +
                ", numberOfSms=" + numberOfSms +
                ", rollOver=" + rollOver +
                ", correlationId='" + correlationId + '\'' +
                ", subscriptionDate=" + subscriptionDate +
                ", status=" + status +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UagTransactionEntity)) return false;
        UagTransactionEntity that = (UagTransactionEntity) o;
        return getValidityDuration() == that.getValidityDuration() &&
                getNumberOfSms() == that.getNumberOfSms() &&
                isRollOver() == that.isRollOver() &&
                Objects.equals(getId(), that.getId()) &&
                getAccountName().equals(that.getAccountName()) &&
                getCorrelationId().equals(that.getCorrelationId()) &&
                getSubscriptionDate().equals(that.getSubscriptionDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAccountName(), getValidityDuration(), getNumberOfSms(), isRollOver(), getCorrelationId(), getSubscriptionDate());
    }
}
