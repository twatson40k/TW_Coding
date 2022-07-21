/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.VendingMachine.Service;

import com.VendingMachine.DAO.VM_AuditDAO;
import com.VendingMachine.DAO.VM_PersistenceException;

/**
 *
 * @author Thomas Watson
 */
public class VM_AuditDAOStubImpl implements VM_AuditDAO {
    @Override
    public void writeAuditEntry(String entry) throws VM_PersistenceException {
        //do nothing . . .
    }
    
}
