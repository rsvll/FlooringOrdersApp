/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlooringMastery.dao;

/**
 *
 * @author svlln
 */
public class PersistanceException extends Exception{

    public PersistanceException (String message){
        super(message);
    }
    
    public PersistanceException(String message, Throwable cause) {
        super(message, cause);
    }
    
    
}
