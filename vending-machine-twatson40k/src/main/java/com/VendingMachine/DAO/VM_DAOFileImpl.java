package com.VendingMachine.DAO;
import com.VendingMachine.DTO.Item;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
public class VM_DAOFileImpl implements VM_DAO {
    private final Map<String, Item> items = new HashMap<>();
    public static String VM_Items_FILE;
    public static final String DELIMITER = "::";
    public VM_DAOFileImpl() {
        VM_Items_FILE = "VM_Items.txt";
    }
    public VM_DAOFileImpl(String testFile) {
        VM_Items_FILE = testFile;
    }

    @Override
    public List<Item> getAllItems() throws VM_PersistenceException {
        loadItem();
        return new ArrayList<>(items.values());
    }

    @Override
    public Item buyItem(String itemId) throws VM_PersistenceException {
        loadItem();
        return items.get(itemId);
    }

    @Override
    public void updateItemAmount(Item editItem) throws VM_PersistenceException {
        loadItem();
        items.put(editItem.getItemId(), editItem);
        writeItem();
    }
    @Override
    public Item getItem(String itemIDNo) throws VM_PersistenceException {
        loadItem();
        return items.get(itemIDNo);

    }

    private Item unmarshallItems(String itemAsText){

        String[] itemTokens = itemAsText.split(DELIMITER);
        String itemId = itemTokens[0];

        Item itemFromFile = new Item(itemId);
        itemFromFile.setItemName(itemTokens[1]);
        itemFromFile.setPriceTag(new BigDecimal(itemTokens[2]));
        itemFromFile.setNoOfItem(Integer.parseInt(itemTokens[3]));

        return itemFromFile;
    }
    private void loadItem() throws VM_PersistenceException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(VM_Items_FILE)));
        } catch (FileNotFoundException e) {
            throw new VM_PersistenceException(
                    "-_- Could not load roster data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentItem holds the most recent student unmarshalled
        Item currentItem;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentItem = unmarshallItems(currentLine);

            items.put(currentItem.getItemId(), currentItem);
        }
        // close scanner
        scanner.close();
    }

    private String marshallItem(Item aItem){

        return String.format("%s%s%s%s%s%s%s",
                aItem.getItemId(),
                DELIMITER,
                aItem.getItemName(),
                DELIMITER,
                aItem.getPriceTag(),
                DELIMITER,
                aItem.getNoOfItem());

    }

    private void writeItem() throws VM_PersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(VM_Items_FILE));
        } catch (IOException e) {
            throw new VM_PersistenceException(
                    "Could not save student data.", e);
        }

        String itemAsText;
        List<Item> itemList = this.getAllItems();
        for (Item currentItem : itemList) {
            itemAsText = marshallItem(currentItem);
            out.println(itemAsText);
        }
        out.flush();
        out.close();
    }
}
