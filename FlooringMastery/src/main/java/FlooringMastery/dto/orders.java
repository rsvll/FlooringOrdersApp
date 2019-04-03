/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlooringMastery.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author svlln
 */
public class orders {
    private int OrderNumber;
    private String CustomerName;
    private Taxes myTaxesClass;
    private Products myProductsClass;
    private BigDecimal materialCost;
    private BigDecimal laborCost;
    private double Area;
    private BigDecimal Tax;
    private BigDecimal Total;
    private LocalDate localDate;

    public orders(int OrderNumber) {
        this.OrderNumber = OrderNumber;
        this.myProductsClass = new Products();
        this.myTaxesClass = new Taxes();
    }

    public int getOrderNumber() {
        return OrderNumber;
    }

    public void setOrderNumber(int OrderNumber) {
        this.OrderNumber = OrderNumber;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    public Taxes getMyTaxesClass() {
        return myTaxesClass;
    }

    public void setMyTaxesClass(Taxes myTaxesClass) {
        this.myTaxesClass = myTaxesClass;
    }

    public Products getMyProductsClass() {
        return myProductsClass;
    }

    public void setMyProductsClass(Products myProductsClass) {
        this.myProductsClass = myProductsClass;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    public double getArea() {
        return Area;
    }

    public void setArea(double Area) {
        this.Area = Area;
    }

    public BigDecimal getTax() {
        return Tax;
    }

    public void setTax(BigDecimal Tax) {
        this.Tax = Tax;
    }

    public BigDecimal getTotal() {
        return Total;
    }

    public void setTotal(BigDecimal Total) {
        this.Total = Total;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }


    
}
