package com.esd.sercom.bulksms.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BulkSmsPayments implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String transactionReference;
    private String paymentVendorReference;
    @NotNull
    @Column(nullable = false)
    private String amount;
    private LocalDateTime transactionDate;
    @NotNull
    @Column(nullable = false)
    private String phoneNumber;
    @Email
    @NotNull
    @Column(nullable = false,name="Email_Address")
    private String email;
    private String rechargeType;
    @Transient
    private List<Product> products;
    private String eventType;
    private String callback_url ;
    private LocalDateTime responseTime;
    private String responseCode;
    private String responseMessage;
    private String transactionStatus;
    private String redirectUrl;
    @NotNull
    @Column(nullable = false)
    private String paymentVendor;
    private String firstName;
    private String lastName;
    private String paymentOptions;
    private String title;
    private String description;
    private String logo;
    private String accountCode;
    private String serviceType;
    private String erpReference;
    private String customerClass;
    private String model;
    private String currency;
    @Column(nullable = true)
    private boolean erpVerified;
    @Column(nullable = true)
    private String narration;
    @Column(nullable = true)
    private boolean payWithBankTransfer;

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public boolean isPayWithBankTransfer() {
        return payWithBankTransfer;
    }

    public void setPayWithBankTransfer(boolean payWithBankTransfer) {
        this.payWithBankTransfer = payWithBankTransfer;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPaymentVendorReference() {
        return paymentVendorReference;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @JsonIgnore
    public String getFullName(){
        StringBuilder sb  = new StringBuilder(getFirstName());
        sb.append(" ");
        sb.append(getLastName());
        return sb.toString();
    }
    public void setPaymentVendorReference(String paymentVendorReference) {
        this.paymentVendorReference = paymentVendorReference;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getPaymentOptions() {
        return paymentOptions;
    }

    public void setPaymentOptions(String paymentOptions) {
        this.paymentOptions = paymentOptions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getTransactionReference() {
        return transactionReference;
    }

    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRechargeType() {
        return rechargeType;
    }

    public void setRechargeType(String rechargeType) {
        this.rechargeType = rechargeType;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getCallBackUrl() {
        return callback_url ;
    }

    public void setCallBackUrl(String callBackUrl) {
        this.callback_url  = callBackUrl;
    }

    public LocalDateTime getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(LocalDateTime responseTime) {
        this.responseTime = responseTime;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getCallback_url() {
        return callback_url;
    }

    public void setCallback_url(String callback_url) {
        this.callback_url = callback_url;
    }

    public String getPaymentVendor() {
        return paymentVendor;
    }

    public void setPaymentVendor(String paymentVendor) {
        this.paymentVendor = paymentVendor;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getErpReference() {
        return erpReference;
    }

    public void setErpReference(String erpReference) {
        this.erpReference = erpReference;
    }

    public String getCustomerClass() {
        return customerClass;
    }

    public void setCustomerClass(String customerClass) {
        this.customerClass = customerClass;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public boolean isErpVerified() {
        return erpVerified;
    }

    public void setErpVerified(boolean erpVerified) {
        this.erpVerified = erpVerified;
    }

    @Override
    public String toString() {
        return "PaymentRequest{" +
                "id=" + id +
                ", transactionReference='" + transactionReference + '\'' +
                ", paymentVendorReference='" + paymentVendorReference + '\'' +
                ", amount='" + amount + '\'' +
                ", transactionDate=" + transactionDate +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", rechargeType='" + rechargeType + '\'' +
                ", products=" + products +
                ", eventType='" + eventType + '\'' +
                ", callback_url='" + callback_url + '\'' +
                ", responseTime=" + responseTime +
                ", responseCode='" + responseCode + '\'' +
                ", responseMessage='" + responseMessage + '\'' +
                ", transactionStatus='" + transactionStatus + '\'' +
                ", redirectUrl='" + redirectUrl + '\'' +
                ", paymentVendor='" + paymentVendor + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", paymentOptions='" + paymentOptions + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", logo='" + logo + '\'' +
                ", accountCode='" + accountCode + '\'' +
                ", serviceType='" + serviceType + '\'' +
                ", erpReference='" + erpReference + '\'' +
                ", customerClass='" + customerClass + '\'' +
                ", model='" + model + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }
}