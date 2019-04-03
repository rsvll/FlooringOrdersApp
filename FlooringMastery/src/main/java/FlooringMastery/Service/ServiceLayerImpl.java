/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlooringMastery.Service;

import FlooringMastery.dao.AuditDao;
import FlooringMastery.dao.PersistanceException;
import FlooringMastery.dto.Products;
import FlooringMastery.dto.Taxes;
import FlooringMastery.dto.orders;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import FlooringMastery.dao.ProductionFlooringMasteryDao;
import java.time.LocalDate;
import java.util.stream.Collectors;

/**
 *
 * @author svlln
 */
public class ServiceLayerImpl implements ServiceLayer {
    
    ProductionFlooringMasteryDao dao;
    private AuditDao auditDao;

    public ServiceLayerImpl(ProductionFlooringMasteryDao dao, AuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }
    
    

    @Override
    public void addOrder(orders prod) 
            throws DataValidationException, 
                   PersistanceException,
                   FMDuplicateIdException{
        Products newProd = getProdList(prod.getMyProductsClass().getProductType());
        Taxes newTaxR = getMyTaxR(prod.getMyTaxesClass().getState());
        prod.setMyProductsClass(newProd);
        prod.setMyTaxesClass(newTaxR);
        
        CalcululateCost(prod);
        
        validateOrderData(prod);
        dao.addOrder(prod);
        
        auditDao.writeAuditEntry(
                "Order " +  prod.getOrderNumber() + " Created.");
        
 }
    private void CalcululateCost(orders order){
       BigDecimal costPerSqft =  order.getMyProductsClass().getCostPerSquareFoot()
               , laborCostperSqft = order.getMyProductsClass().getLaborCostPerSquareFootCarpet(),
               materialCost, laborCost, totalTax, totalAmount;
       
       materialCost =  costPerSqft.multiply(BigDecimal.valueOf(order.getArea()));
       laborCost = laborCostperSqft.multiply(BigDecimal.valueOf(order.getArea()));    
       totalTax = materialCost.multiply(BigDecimal.valueOf(order.getMyTaxesClass().getTaxRate()))
               .add(laborCost.multiply(BigDecimal.valueOf(order.getMyTaxesClass().getTaxRate())));
       totalAmount = materialCost.add(laborCost).add(totalTax);
       
       order.setMaterialCost(materialCost.setScale(2, RoundingMode.HALF_UP));
       order.setLaborCost(laborCost.setScale(2, RoundingMode.HALF_UP));
       order.setTax(totalTax.setScale(2, RoundingMode.HALF_UP));
       order.setTotal(totalAmount.setScale(2, RoundingMode.HALF_UP));
    }
    
    
    @Override
    public int getOrderNum() {
        return dao.getAllOrders().size() + 1;
    }
    
    @Override
    public List<orders> getAllOrders() {
       return dao.getAllOrders();
    }
    // returns orders - by date and order number
    
    @Override
    public List<orders> getOrderByDate(LocalDate date) 
            throws DateValidationException{ 
//    List<orders> userDate = null;    
//       try{ 
//    userDate = dao.getOrderByDate(date);
//    
//       } catch(Exception e){
//           System.out.println("Error");
//       }
    return dao.getOrderByDate(date);
    //return userDate;
    }

    @Override
    public orders editOrder(orders order) {
        return dao.editOrder(order);
    }

    @Override
    public void removeOrder(int order) {
         dao.removeOrder(order);
         
    }

    @Override
    public void saveCurrentWork() {
        dao.saveCurrentWork();
        
    }

    @Override
    public Taxes getMyTaxR(String State) throws PersistanceException {
        return dao.getMyTaxR(State);
    }

    @Override
    public Products getProdList(String ProductType) throws PersistanceException {
        return dao.getProdList(ProductType);
    }
    
    @Override
    public void validateOrderData(orders ord) throws DataValidationException{
        
        if(ord.getCustomerName() == null
                || ord.getCustomerName().trim().length() == 0
                || ord.getMyTaxesClass().getState() == null
                || ord.getMyTaxesClass().getState().trim().length() == 0
                || ord.getMyProductsClass().getProductType() == null
                || ord.getMyProductsClass().getProductType().trim().length() == 0
                ){
            throw new DataValidationException(
            "ERROR: All Fields [Customer Name,State, Product Type, Area]");
        }
        
            
        }

    @Override
    public List<orders> checkDateOrderNumExist(LocalDate date, int OrderNumber) throws DateValidationException{
        
        List<orders> tempList = dao.getOrderByDate(date)
                .stream()
                .filter(s -> s.getLocalDate().equals(date))
                .collect(Collectors.toList());
        if(tempList.isEmpty()){
            throw new DateValidationException("THERE IS NO ORDERS ON THIS DAY!");
        } else if(!tempList.isEmpty()){
            tempList.stream()
                    .filter(s -> s.getOrderNumber() == OrderNumber)
                    .collect(Collectors.toList());
        }
        return tempList;
    }


    }
    

