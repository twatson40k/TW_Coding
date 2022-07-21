/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.VendingMachine.Service;

import com.VendingMachine.DAO.VM_DAO;
import com.VendingMachine.DAO.VM_PersistenceException;
import com.VendingMachine.DTO.Item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thomas Watson
 */
    public class VM_DAOStubImpl implements VM_DAO {

        public Item onlyItem;

        public VM_DAOStubImpl() {
            onlyItem = new Item("A1");
            onlyItem.setItemName("Mars Bar");
            onlyItem.setPriceTag(BigDecimal.valueOf(1.20));
            onlyItem.setNoOfItem(25);
        }

        public VM_DAOStubImpl(Item testItem){
            this.onlyItem = testItem;
        }


        @Override
        public List<Item> getAllItems()
                throws VM_PersistenceException {
            List<Item> itemList = new ArrayList<>();
            itemList.add(onlyItem);
            return itemList;
        }

    @Override
    public Item buyItem(String itemId) throws VM_PersistenceException {
        return null;
    }

    @Override
    public void updateItemAmount(Item editItem) throws VM_PersistenceException {

    }

    @Override
        public Item getItem(String itemId)
                throws VM_PersistenceException {
            if (itemId.equals(onlyItem.getItemId())) {
                return onlyItem;
            } else {
                return null;
            }
        }
    }

