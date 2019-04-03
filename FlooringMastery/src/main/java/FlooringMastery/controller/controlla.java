/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlooringMastery.controller;

import FlooringMastery.Service.DataValidationException;
import FlooringMastery.Service.DateValidationException;
import FlooringMastery.Service.ServiceLayer;
import FlooringMastery.dao.PersistanceException;
import FlooringMastery.dto.orders;
import FlooringMastery.ui.view;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author svlln
 */
public class controlla {

    view myView;
    ServiceLayer mySL;

    public controlla(view myView, ServiceLayer mySL) {
        this.myView = myView;
        this.mySL = mySL;
    }

    public void run() {

        boolean keepGoing = true;
        int menuSelection = 0;

        while (keepGoing) {

            try {

                menuSelection = getMenu();

                switch (menuSelection) {
                    case 1:                                          
                        listOrders();
                        break;
                    case 2:
                    
                        createOrder();
                        break;
                    case 3:
                        editOrder();
                        break;
                    case 4:
                        removeOrder();
                        break;
                    case 5:
                        saveOrder();
                        break;
                    case 6:
                        keepGoing = false; // quit           
                        break;
                    default:
                        System.out.println("UNKNOW COMMAND, TRY AGAIN!");
                }
            
                System.out.println("Thank you");
            
            } catch (DataValidationException 
                    | DateValidationException 
                    | PersistanceException e) {
                myView.DisplayError(e.getMessage());
                
            }
        }
    }
    

    private int getMenu() {
        return myView.printMenuAndSelection();
    }

    private void createOrder() 
            throws DataValidationException,
            PersistanceException{
        myView.displayCreateOrderBanner();
        boolean hasErrors = false;
        boolean keepOrdering = true;
    
        do {
            do{
            orders neworder = myView.addOrderInfo(mySL.getAllOrders(), mySL.getOrderNum()); 
            try {
                mySL.addOrder(neworder); 
                List<orders> ordList = new ArrayList<>();
                ordList.add(neworder);
                myView.displayOrders(ordList);
                
                boolean SaveData = myView.saveData();
                if (SaveData == false){
                    
                    mySL.removeOrder(neworder.getOrderNumber());
                }
                 //code to continue ordering
                String input = myView.keepOrderingOrNah();
                if(input.equalsIgnoreCase("y")){
                    keepOrdering = true;
                } else if(input.equalsIgnoreCase("n")){
                    keepOrdering = false;
                    
                }
                

            } catch (Exception e) {
                hasErrors = true;
                myView.DisplayError(e.getMessage());
            }
            } while(keepOrdering);
        } while (hasErrors);
        myView.displayCreateSuccessBanner();
    }
    
    
    
    private void listOrders() throws DateValidationException{
        LocalDate userDate = myView.enterDate();
        myView.displayAllOrdersBanner();
        List<orders> myOrder = mySL.getOrderByDate(userDate);
        myView.displayOrders(myOrder);
        
        
        //mySL.getOrderByDate(date);
        //mySL.getAllOrders();
    }

    
    private void saveOrder() throws DataValidationException{
        myView.saveToTxtBanner();
        try {
        mySL.saveCurrentWork();
        myView.saveToTxtSuccessBanner();
        } catch (Exception ex) {
            Logger.getLogger(controlla.class.getName()).log(Level.SEVERE, null, ex);
        }
//        mySL.saveCurrentWork();
//        myView.saveToTxtSuccessBanner();
    }
    
//    private orders removeOrders(){
//        return mySL.removeOrder();
//    }

    private void removeOrder() throws DateValidationException {
        LocalDate userDate = myView.enterDate();
        int userOrder = myView.enterOrderNum();        
        mySL.checkDateOrderNumExist(userDate, userOrder);
        mySL.removeOrder(userOrder);
        
    }
 
    private void editOrder() throws DateValidationException {
       
        LocalDate userDate = myView.enterDate();
        int userOrderNum = myView.enterOrderNum();
        System.out.println("fuck life");
        //List<orders> getOrderToEdit = mySL.checkDateOrderNumExist(userDate, userOrderNum);
        orders currentOrder = null;
        

        try{
           // do{
                currentOrder = myView.setOrdersForEdit(currentOrder);
           // }while(!noNull(currentOrder));
        }catch(Exception e){
            System.out.println("error message ");
        }
        
        myView.displayOrders((List<orders>) currentOrder);
        
        //banner that is sucessfull blah
        
    }
    public void noNull(orders ord) throws DataValidationException{
        mySL.validateOrderData(ord);
    }
}
