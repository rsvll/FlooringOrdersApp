/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlooringMastery.dao;

import FlooringMastery.dto.Products;
import FlooringMastery.dto.Taxes;
import FlooringMastery.dto.orders;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author svlln
 */
public class DaoImpl  implements ProductionFlooringMasteryDao{
    
    private Map<Integer, orders> ordCollection = new HashMap<>();
    private Map<String, Taxes> StateAndT = new HashMap<>();
    private Map<String, Products> prodNprice = new HashMap<>();
    public static final String ORDER_FILE = "orderList.txt";
    public static final String TAXES_FILE = "taxesList.txt";
    public static final String PRODUCTS_FILE = "productsList.txt";
    public static final String DELIMITER = "::";

    public DaoImpl() {
        try {
            LoadOrders();
        } catch (PersistanceException ex) {
            Logger.getLogger(DaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Override
    public orders addOrder(orders prod) {
        orders newOrder = ordCollection.put(prod.getOrderNumber(), prod);       
        return newOrder;
    }

    @Override
    public List<orders> getAllOrders() {
        return new ArrayList<>(ordCollection.values());        
    }
    
    public List<orders> getOrderByDate(LocalDate date){
        return ordCollection.values()
                .stream()
                .filter(s -> s.getLocalDate().equals(date))
                .collect(Collectors.toList());
    }
    
    @Override
    public orders editOrder(orders order) {
        return ordCollection.put(order.getOrderNumber(), order);
    }

    @Override
    public void removeOrder(int orderNumber) {
        
        orders userSelectOrderNum = ordCollection.remove(orderNumber);
        
    }

    @Override
    public void saveCurrentWork() {
        orders write;
        try{
            writeOrders();
        }catch(PersistanceException ex){
            System.out.println("Could not Save!");
        }
        
    }
    
    @Override
    public Taxes getMyTaxR(String State) throws PersistanceException{
        LoadTaxes();
       return  StateAndT.get(State);
    }

    @Override
    public Products getProdList(String ProductType) throws PersistanceException{
       LoadProducts();
       return prodNprice.get(ProductType);
    }
    
    
        private void LoadOrders() throws PersistanceException {
	    Scanner scanner;

	    try {

	        scanner = new Scanner(
	                new BufferedReader(
	                        new FileReader(ORDER_FILE)));
	    } catch (FileNotFoundException e) {
	        throw new PersistanceException(
	                "-_- Could not load Orders data into memory.", e);
	    }

	    String currentLine;

	    String[] currentTokens;

	    while (scanner.hasNextLine()) {
	        // get the next line in the file
	        currentLine = scanner.nextLine();
	        // break up the line into tokens
	        currentTokens = currentLine.split(DELIMITER);
                
	        orders currentOrder = new orders(parseInt(currentTokens[1]));
	        // Set the remaining vlaues on current manually
                currentOrder.setLocalDate(LocalDate.parse(currentTokens[0]));
	        currentOrder.setCustomerName(currentTokens[2]);                
                currentOrder.getMyTaxesClass().setState(currentTokens[3]);
                currentOrder.getMyTaxesClass().setTaxRate(parseDouble(currentTokens[4]));
                currentOrder.getMyProductsClass().setProductType(currentTokens[5]);
                currentOrder.getMyProductsClass().setCostPerSquareFoot(new BigDecimal(currentTokens[6]));
                currentOrder.getMyProductsClass().setLaborCostPerSquareFootCarpet(new BigDecimal(currentTokens[7]));
                currentOrder.setMaterialCost(new BigDecimal(currentTokens[8]));
                currentOrder.setLaborCost(new BigDecimal(currentTokens[9]));
                currentOrder.setTax(new BigDecimal(currentTokens[10]));
                currentOrder.setTotal(new BigDecimal(currentTokens[11]));
                	        
	        // Put currentTitle into the map using title as the key
	        ordCollection.put(currentOrder.getOrderNumber(), currentOrder);
	    }
	    // close scanner
	    scanner.close();
	}
    

	private void writeOrders() throws PersistanceException{

	    PrintWriter out;
	    
	    try {
	        out = new PrintWriter(new FileWriter(ORDER_FILE));
	    } catch (IOException e) {
	        throw new PersistanceException(
	                "Could not save student data.", e);
	    }
	    

	    List<orders> ordList = this.getAllOrders();
	    for (orders currentOrder : ordList) {

	        out.println(currentOrder.getLocalDate() + DELIMITER
                        + currentOrder.getOrderNumber() + DELIMITER
	                + currentOrder.getCustomerName()+ DELIMITER 
	                + currentOrder.getMyTaxesClass().getState() + DELIMITER
                        + currentOrder.getMyTaxesClass().getTaxRate() + DELIMITER
                        + currentOrder.getMyProductsClass().getProductType() + DELIMITER
                        + currentOrder.getMyProductsClass().getCostPerSquareFoot() + DELIMITER
                        + currentOrder.getMyProductsClass().getLaborCostPerSquareFootCarpet() + DELIMITER
                        + currentOrder.getMaterialCost() + DELIMITER
                        + currentOrder.getLaborCost() + DELIMITER
                        + currentOrder.getTax() + DELIMITER
                        + currentOrder.getTotal());


	        out.flush();
	    }

	    out.close();
	}
        
        private void LoadTaxes() throws PersistanceException {
	    Scanner scanner;

	    try {

	        scanner = new Scanner(
	                new BufferedReader(
	                        new FileReader(TAXES_FILE)));
	    } catch (FileNotFoundException e) {
	        throw new PersistanceException(
	                "-_- Could not load Taxes data into memory.", e);
	    }

	    String currentLine;
	    String[] currentTokens;

	    while (scanner.hasNextLine()) {
                
	        currentLine = scanner.nextLine();
	        currentTokens = currentLine.split(DELIMITER);
               
	        Taxes taxNewOrder = new Taxes();
	        taxNewOrder.setState(currentTokens[0]);
                taxNewOrder.setTaxRate(parseDouble(currentTokens[1]));
                	                      
	        StateAndT.put(currentTokens[0], taxNewOrder);
	    }
	    scanner.close();
	}
        
        
        private void LoadProducts() throws PersistanceException {
	    Scanner scanner;

	    try {

	        scanner = new Scanner( new BufferedReader (new FileReader(PRODUCTS_FILE)));
	    } catch (FileNotFoundException e) {
	        throw new PersistanceException(
	                "-_- Could not load Products data into memory.", e);
	    }

	    String currentLine;
	    String[] currentTokens;

	    while (scanner.hasNextLine()) {
	        currentLine = scanner.nextLine();
	        currentTokens = currentLine.split(DELIMITER);
               
	        Products newProducts = new Products();
                newProducts.setProductType(currentTokens[0]);
                newProducts.setCostPerSquareFoot(new BigDecimal(currentTokens[1]));
                newProducts.setLaborCostPerSquareFootCarpet(new BigDecimal(currentTokens[2]));
                	                      
	        prodNprice.put(currentTokens[0], newProducts);
	    }
	    scanner.close();
	}


        
    

}
