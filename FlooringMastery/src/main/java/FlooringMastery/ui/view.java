/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlooringMastery.ui;

import FlooringMastery.Service.DataValidationException;
import FlooringMastery.dto.orders;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author svlln
 */
public class view {
    
    userIO io;

    public view(userIO io) {
         this.io = io;
    }
    
    public int printMenuAndSelection(){
        
        io.print("============================================");
        io.print("|         SWG CORP. FLOORING                |");
        io.print("============================================");
        io.print("|******************MENU*********************|");
        io.print("| ( 1 ) Display Order's                     |");
        io.print("| ( 2 ) Add Order                           |");
        io.print("| ( 3 ) Edit Order                          |");
        io.print("| ( 4 ) Remove Order                        |");
        io.print("| ( 5 ) Save Current Work                   |");
        io.print("| ( 6 ) Quit                                |");
        io.print("|*******************************************|");
        
        return io.readInt("Please select from the" + " above choices.", 1, 6);
    }
    
    public orders addOrderInfo(List<orders> List,int orderNumber) 
                    throws DataValidationException{
    //public orders addOrderInfo(int orderNumber) throws DataValidationException{
       LocalDate date = LocalDate.now();
       //DateTimeFormatter format = DateTimeFormatter.ofPattern("MM-dd-yyyy");
       //String orderDate = date.format(format);
       //int orderNumber = List.size()+1; // need to change this
       
       String CustomerName = io.readString("Enter customer Name: ");
       String State = io.readString("Enter state(OH,PA,MI,TN): ");
       String ProductType = io.readString("Enter product type(carpet, laminate,tile, wood): ");
       double Area;
       try{
        Area = io.readDouble("Enter Area to purchase: ");          
       } catch(NumberFormatException e){
           throw new DataValidationException("Enter a Number", e);
       }
        
       
       orders newOrder = new orders(orderNumber);
       newOrder.setCustomerName(CustomerName);       
       newOrder.getMyTaxesClass().setState(State);
       newOrder.getMyProductsClass().setProductType(ProductType);
       newOrder.setArea(Area);
       newOrder.setLocalDate(date);
       return newOrder;
    }
    public String keepOrderingOrNah(){
        String input = io.readString("Continue ordering? [y/n]");
        return input;
    }
    public void displayOrders(List<orders> List){
        
     for(orders display : List){
            io.print("Order Number: "
            + display.getOrderNumber()
            + " | Date: " + display.getLocalDate()
            + " | Customer Name: "
            + display.getCustomerName()
            + " | State: " + display.getMyTaxesClass().getState()
            + " | Area: " + display.getArea()
            + " | Product Type: "
            + display.getMyProductsClass().getProductType()
            + " | Material Cost: "
            + display.getMyProductsClass().getCostPerSquareFoot()
            + " | Tax: " + display.getTax()
            + " | Total Cost: " + display.getTotal()
            );        
        }
    }
        public LocalDate enterDate(){       
        LocalDate date = io.readLocalDate("Enter Date: [MM/dd/yyyy]");
        return date;   
    }
        public int enterOrderNum(){
            int userOrderNum = io.readInt("Enter OrderNumber: ");
            return userOrderNum;
        }
        
        public orders setOrdersForEdit(orders order){
            
            io.readString("Type new data or hit enter: ");
            io.readString("ass");
            String customerName = (io.readString("Current customer: " + order.getCustomerName()));
            String area = (io.readString("Current area: " + order.getArea()));
            String state = (io.readString("current state: " + order.getMyTaxesClass().getState()));
            String product = (io.readString("Current product: " + order.getMyProductsClass().getProductType()));
            
            // ask if they want to set/change data
            io.readString(customerName + " change or nah? ");
            if(customerName.trim().length()!=0){
                order.setCustomerName(customerName);
            }else{ order.setCustomerName(order.getCustomerName()); }
            io.readString(area + " change area or nah?");
            if(area.trim().length()!=0){
                order.setArea(Double.parseDouble(area));
            } else { order.setArea(order.getArea()); }
            
            if(state.trim().length()!=0){
                order.getMyTaxesClass().setState(state);
            } else { order.getMyTaxesClass().setState(order.getMyTaxesClass().getState());}
            
            if(product.trim().length()!=0){
                order.getMyProductsClass().setProductType(product);
            } else { order.getMyProductsClass().setProductType(order.getMyProductsClass().getProductType());}
            
            return order;
        }
        
//    public String enterDate(){
//        String date = io.readString("Enter Date: (MM-DD-YYYY)");
//        return date;   
//    }
//    public void displayOrderByDate(String date){
//       
//        
//    }
    
    public boolean saveData(){
        boolean saveOrder = true;
        String input = io.readString("Would you like to commit this order? [y/n]" );
        if(input.equalsIgnoreCase("y")){
            saveOrder = true;
        } else if (input.equalsIgnoreCase("n")){
            saveOrder = false;
        }
        return saveOrder;
    }
  

    
    public void displayCreateOrderBanner(){
        io.print("***** CREATE ORDER *****");
    }
    
    public void displayCreateSuccessBanner(){
        io.readString(
                "ORDER successfully added. Hit enter to continue");
    }
    
    public void displayAllOrdersBanner(){
        io.print("**** DISPLAY ORDERS ****");
    }
    public void displayAllOrdersSuccessBanner(){
        io.readString(
                "Order successfully displayed. Hit enter to continue");
    }
    public void saveToTxtBanner(){
        io.print("*** SAVE ORDER ***");
    }
    public void saveToTxtSuccessBanner(){
        io.readString(" Successfully saved. Hit Enter to continue");
    }
    
    public void DisplayError(String message){
        io.print("=== ERROR ===");
        io.print(message);
    }
    
}
