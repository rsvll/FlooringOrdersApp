/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlooringMastery.dao;

import FlooringMastery.dto.Products;
import FlooringMastery.dto.Taxes;
import FlooringMastery.dto.orders;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author svlln
 */
public interface ProductionFlooringMasteryDao {
    
   orders addOrder(orders prod);
   
   List<orders> getAllOrders();
   
   List<orders> getOrderByDate(LocalDate date);
   
   orders editOrder(orders order);
   
   void removeOrder(int order);
   //void LoadOrders() throws PersistanceException;
   void saveCurrentWork();
   
   Taxes getMyTaxR(String State) throws PersistanceException;
   
   Products getProdList(String ProductType) throws PersistanceException;
   
}
