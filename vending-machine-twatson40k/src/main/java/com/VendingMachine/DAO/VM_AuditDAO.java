package com.VendingMachine.DAO;

public interface VM_AuditDAO {
    void writeAuditEntry(String entry) throws VM_PersistenceException;
}
