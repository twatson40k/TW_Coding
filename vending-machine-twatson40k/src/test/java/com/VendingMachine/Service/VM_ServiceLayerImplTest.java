/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.VendingMachine.Service;

import com.VendingMachine.DAO.VM_AuditDAO;
import com.VendingMachine.DAO.VM_DAO;
import com.VendingMachine.DTO.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Thomas Watson
 */
public class VM_ServiceLayerImplTest {
    private VM_ServiceLayer service;
    public VM_ServiceLayerImplTest() {
        VM_DAO dao = new VM_DAOStubImpl();
        VM_AuditDAO auditDao = new VM_AuditDAOStubImpl();

        service = new VM_ServiceLayerImpl(dao, auditDao);
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }
    

    @Test
    public void testGetAllItems() throws Exception {
        // ARRANGE
        Item testClone = new Item("A1");
        testClone.setItemName("Mars Bar");
        testClone.setPriceTag(BigDecimal.valueOf(1.20));
        testClone.setNoOfItem(25);
        List<Item> itemList = service.getAllItems();
        // ACT & ASSERT
        assertEquals(1, itemList.size(),
                "Should only have one Item.");

        boolean testItem = false;
        for (Item itemTest : itemList) {
            if (itemTest.getItemId().equalsIgnoreCase(testClone.getItemId())) {
                if (itemTest.getItemName().equalsIgnoreCase(testClone.getItemName()) &&
                        itemTest.getPriceTag() == testClone.getPriceTag() &&
                        itemTest.getNoOfItem() == testClone.getNoOfItem()) {
                    testItem = true;
                }
            }
        }
        assertTrue(testItem,
                "The one Item should be Mars Bar.");
    }

    @Test
    public void testGetItem() throws Exception {
        // ARRANGE
        Item testClone = new Item("A1");
        testClone.setItemName("Mars Bar");
        testClone.setPriceTag(BigDecimal.valueOf(1.20));
        testClone.setNoOfItem(25);

        // ACT & ASSERT
        String shouldBeA1 = service.buyItem("A1");
        assertNotNull(shouldBeA1, "Getting Mars Bar should be not null.");
        assertEquals( testClone, shouldBeA1,
                "Student stored under a1 should be Mars Bar.");

        String shouldBeNull = service.buyItem("A42");
        assertNull( shouldBeNull, "Getting A42 should be null.");

    }
}
