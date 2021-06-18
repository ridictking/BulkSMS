package com.esd.sercom.bulksms.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BulkSmsPayment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Account code is mandatory")
    private String accountCode;
    @Column(unique = true)
    @NotBlank(message = "Transaction Id is mandatory")
    private String transactionId;
    @NotBlank(message = "Email is mandatory")
    private String emailAddress;
    @NotBlank(message = "Customer class is mandatory")
    private String customerClass;
    @NotBlank(message = "Event type is mandatory")
    private String eventType;
    @NotBlank(message = "Model is mandatory")
    private String model;
    @NotBlank(message = "Invoice date is mandatory")
    private String invoiceDate;
    @NotBlank(message = "Create date is mandatory")
    private String createDate;
    @NotBlank(message = "Service type cannot be is mandatory")
    private String serviceType;

    @Transient
    private List<Product> products;

    public BulkSmsPayment() {
    }

    public BulkSmsPayment(@NotBlank(message = "Account code is mandatory") String accountCode,
                          @NotBlank(message = "Transaction Id is mandatory") String transactionId,
                          @NotBlank(message = "Email is mandatory") String emailAddress,
                          @NotBlank(message = "Customer class is mandatory") String customerClass,
                          @NotBlank(message = "Event type is mandatory") String eventType,
                          @NotBlank(message = "Model cannot be is mandatory") String model,
                          @NotBlank(message = "Invoice date is mandatory") String invoiceDate,
                          @NotBlank(message = "Create date is mandatory") String createDate,
                          @NotBlank(message = "Service type cannot be is mandatory") String serviceType) {
        this.accountCode = accountCode;
        this.transactionId = transactionId;
        this.emailAddress = emailAddress;
        this.customerClass = customerClass;
        this.eventType = eventType;
        this.model = model;
        this.invoiceDate = invoiceDate;
        this.createDate = createDate;
        this.serviceType = serviceType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getCustomerClass() {
        return customerClass;
    }

    public void setCustomerClass(String customerClass) {
        this.customerClass = customerClass;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }


    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "ErpInvoiceRequest2{" +
                "id=" + id +
                ", accountCode='" + accountCode + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", customerClass='" + customerClass + '\'' +
                ", eventType='" + eventType + '\'' +
                ", model='" + model + '\'' +
                ", invoiceDate='" + invoiceDate + '\'' +
                ", createDate='" + createDate + '\'' +
                ", serviceType='" + serviceType + '\'' +
                ", products=" + products +
                '}';
    }
}
