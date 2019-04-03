/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlooringMastery;

import FlooringMastery.Service.ServiceLayer;
import FlooringMastery.Service.ServiceLayerImpl;
import FlooringMastery.controller.controlla;
import FlooringMastery.dao.AuditDao;
import FlooringMastery.dao.AuditDaoImpl;
import FlooringMastery.dao.DaoImpl;
import FlooringMastery.ui.userIO;
import FlooringMastery.ui.userIOConsoleImpl;
import FlooringMastery.ui.view;
import FlooringMastery.dao.ProductionFlooringMasteryDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author svlln
 */
public class App {
    public static void main(String[] args) {
//     userIO myIo = new userIOConsoleImpl();
//     //userIOConsoleImpl myIO = new userIOConsoleImpl();
//     view myView = new view(myIo);
//     ProductionFlooringMasteryDao myDao = new DaoImpl();
//     AuditDao auditDao = new AuditDaoImpl();
//     ServiceLayer mySL = new ServiceLayerImpl(myDao, auditDao);
//     controlla myControlla = new controlla(myView, mySL);
//     myControlla.run();
     
     ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
     controlla myControlla = ctx.getBean("controlla", controlla.class);
     myControlla.run();
    }
    
}
