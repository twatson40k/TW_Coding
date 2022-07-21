/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.VendingMachine.DAO;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.VendingMachine.DTO.Item;

import javax.swing.*;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Thomas Watson
 */
public class VM_DAOFileImplTest {

    VM_DAOFileImpl testDao;

    public VM_DAOFileImplTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws Exception{
        String testFile = "testMachine.txt";
        // Use the FileWriter to quickly blank the file
        new FileWriter(testFile);
        testDao = new VM_DAOFileImpl(testFile);
    }
    
    @AfterEach
    public void tearDown() {
    }

   @Test
    public void testGetItem() throws Exception {
    // Create our method test inputs
       String itemId = "A1";
       Item item = new Item(itemId);
        item.setItemName("Mars Bar");
        item.setPriceTag(BigDecimal.valueOf(1.20));
        item.setNoOfItem(25);

       testDao.getItem(itemId);
        Item retrievedItem = testDao.getItem(itemId);
    
        // Check the data is equal
        assertEquals(item.getItemId(),
                    retrievedItem.getItemId(),
                    "Checking Item id.");
        assertEquals(item.getItemName(),
                    retrievedItem.getItemName(),
                    "Checking Item name.");
        assertEquals(item.getPriceTag(), 
                    retrievedItem.getPriceTag(),
                    "Checking Price Tag.");
        assertEquals(item.getNoOfItem(), 
                    retrievedItem.getNoOfItem(),
                    "Checking No of Items.");
}

@Test
public void testGetAllItems() throws Exception {
    // Create our first student
    Item firstItem = new Item("A1");
    firstItem.setItemName("Mars Bar");
    firstItem.setPriceTag(BigDecimal.valueOf(1.20));
    firstItem.setNoOfItem(25);

    // Create our second student
    Item secondItem = new Item("A2");
    secondItem.setItemName("Twix");
    secondItem.setPriceTag(BigDecimal.valueOf(1.50));
    secondItem.setNoOfItem(25);

    // Add both our students to the DAO
    testDao.getItem(firstItem.getItemId());
    testDao.getItem(secondItem.getItemId());

    // Retrieve the list of all students within the DAO
    List<Item> allItems = testDao.getAllItems();

    // First check the general contents of the list
    assertNotNull(allItems, "The list of items must not null");
    assertEquals(2, allItems.size(),"List of Items should have 2 items.");

    // Then the specifics
    assertTrue(testDao.getAllItems().contains(firstItem),
                "The list of items should include .");
    assertTrue(testDao.getAllItems().contains(secondItem),
            "The list of items should include .");

}
}
