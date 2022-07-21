package com.VendingMachine.Service;

public class VM_NoItemInventoryException extends Exception {

    public VM_NoItemInventoryException(String message) {
        super(message);
    }
    public VM_NoItemInventoryException(String message, Throwable cause) {
        super(message, cause);
    }

}
