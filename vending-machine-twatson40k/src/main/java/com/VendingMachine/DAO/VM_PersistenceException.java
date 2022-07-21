package com.VendingMachine.DAO;

public class VM_PersistenceException extends Exception{
    public VM_PersistenceException(String message){super(message);}
    public VM_PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
