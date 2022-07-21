package com.VendingMachine.VM;

import com.VendingMachine.Controller.VM_Controller;
import com.VendingMachine.DAO.VM_AuditDAO;
import com.VendingMachine.DAO.VM_AuditDAOFileImpl;
import com.VendingMachine.DAO.VM_DAO;
import com.VendingMachine.DAO.VM_DAOFileImpl;
import com.VendingMachine.Service.VM_ServiceLayer;
import com.VendingMachine.Service.VM_ServiceLayerImpl;
import com.VendingMachine.UI.UserIO;
import com.VendingMachine.UI.UserIOConsoleImpl;
import com.VendingMachine.UI.VM_View;

public class VM_App {

    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImpl();
        VM_View myView = new VM_View(myIo);
        VM_DAO myDao = new VM_DAOFileImpl();
        VM_AuditDAO myAuditDao = new VM_AuditDAOFileImpl();
        VM_ServiceLayer myService = new VM_ServiceLayerImpl(myDao, myAuditDao);
        VM_Controller controller = new VM_Controller(myService, myView);
        controller.run();
    }   
}