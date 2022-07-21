package com.VendingMachine.DAO;

import com.VendingMachine.DTO.Item;
import java.util.*;


public interface VM_DAO {

    List<Item> getAllItems() throws VM_PersistenceException;

    Item buyItem(String ItemId) throws VM_PersistenceException;

    void updateItemAmount(Item editItem) throws VM_PersistenceException;

    Item getItem(String itemIDNo) throws VM_PersistenceException;


}
