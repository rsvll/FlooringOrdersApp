/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlooringMastery.dto;

import java.math.BigDecimal;

/**
 *
 * @author svlln
 */
public class Products {
    private String ProductType;
    private BigDecimal CostPerSquareFoot;
    private BigDecimal LaborCostPerSquareFootCarpet;

    public String getProductType() {
        return ProductType;
    }

    public void setProductType(String ProductType) {
        this.ProductType = ProductType;
    }

    public BigDecimal getCostPerSquareFoot() {
        return CostPerSquareFoot;
    }

    public void setCostPerSquareFoot(BigDecimal CostPerSquareFoot) {
        this.CostPerSquareFoot = CostPerSquareFoot;
    }

    public BigDecimal getLaborCostPerSquareFootCarpet() {
        return LaborCostPerSquareFootCarpet;
    }

    public void setLaborCostPerSquareFootCarpet(BigDecimal LaborCostPerSquareFootCarpet) {
        this.LaborCostPerSquareFootCarpet = LaborCostPerSquareFootCarpet;
    }
}
