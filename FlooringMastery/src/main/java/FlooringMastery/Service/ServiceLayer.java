/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlooringMastery.Service;

import FlooringMastery.dao.PersistanceException;
import FlooringMastery.dto.Products;
import FlooringMastery.dto.Taxes;
import FlooringMastery.dto.orders;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author svlln
 */
public interface ServiceLayer {
    
   void addOrder(orders prod) 
           throws DataValidationException, 
                  PersistanceException,
                  FMDuplicateIdException;
   
   List<orders> getAllOrders();
   
   List<orders> getOrderByDate(LocalDate date) throws DateValidationException;
   
   List<orders> checkDateOrderNumExist(LocalDate date, int OrderNumber)
           throws DateValidationException;
   
   orders editOrder(orders order);
   
   void removeOrder(int order);
   
   void saveCurrentWork();
   void validateOrderData(orders ord) throws DataValidationException;
//   void loadOrders();
   
   Taxes getMyTaxR(String State) throws PersistanceException;
   
   Products getProdList(String ProductType) throws PersistanceException;
   
   int getOrderNum();    
}
