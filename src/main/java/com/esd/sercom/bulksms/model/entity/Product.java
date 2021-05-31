package com.esd.sercom.bulksms.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    private String transactionId;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private Double productAmount;
    @NotBlank(message = "Product code cannot be blank")
    private String productCode;
    @NotNull
    private int productQuantity;
    private LocalDateTime dateTime;
    @NotBlank(message = "VAT code cannot be blank")
    private String vatCode;
    @NotNull
    private Double vatAmount;
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "transactionId", nullable = false)
//    private ErpInvoiceRequest2 invoice;

    public Product() {
    }

    public Product(@NotNull Double productAmount,
                   @NotBlank(message = "Product code cannot be blank") String productCode,
                   @NotNull int productQuantity, String transactionId,
                   @NotBlank(message = "VAT code cannot be blank") String vatCode,
                   @NotNull Double vatAmount) {
        this.productAmount = productAmount;
        this.productCode = productCode;
        this.productQuantity = productQuantity;
        this.transactionId = transactionId;
        this.vatCode = vatCode;
        this.vatAmount = vatAmount;
    }

    public Double getProductAmount() {
        return productAmount * getProductQuantity();
    }

    public void setProductAmount(Double productAmount) {
        this.productAmount = productAmount;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    public String getVatCode() {
        return vatCode;
    }

    public void setVatCode(String vatCode) {
        this.vatCode = vatCode;
    }

    public Double getVatAmount() {
        return vatAmount;
    }

    public void setVatAmount(Double vatAmount) {
        this.vatAmount = vatAmount;
    }

    @Override
    public String toString() {
        return "Product{" +
                "transactionId='" + transactionId + '\'' +
                ", id=" + id +
                ", productAmount=" + productAmount +
                ", productCode='" + productCode + '\'' +
                ", productQuantity=" + productQuantity +
                ", dateTime=" + dateTime +
                ", vatCode='" + vatCode + '\'' +
                ", vatAmount=" + vatAmount +
                '}';
    }
}
