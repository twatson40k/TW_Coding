package com.VendingMachine.Service;

public class VM_InsufficientFundsException extends Exception{
    public VM_InsufficientFundsException(String message) {
        super(message);
    }
    public VM_InsufficientFundsException(String message, Throwable cause) {
        super(message, cause);
    }
}
