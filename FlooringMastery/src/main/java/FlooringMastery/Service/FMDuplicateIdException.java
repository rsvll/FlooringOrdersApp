/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlooringMastery.Service;

/**
 *
 * @author svlln
 */
public class FMDuplicateIdException extends Exception {
        public FMDuplicateIdException(String message){
        super(message);
    }
    public FMDuplicateIdException(String message, Throwable cause){
        super(message,cause);
    }
    
}
