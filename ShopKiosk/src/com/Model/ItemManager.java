package com.Model;

import com.Model.data.Item;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    public static final String STOCK_FILE_PATH = "Resources/Stock.csv";

    private static List<Item> items;

    private static void loadItems()  {

        items = new ArrayList<>();

        List<String> lines = null;
        try {
            lines = Files.readAllLines(Path.of(STOCK_FILE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String line: lines) {
            String[] data = line.split(",");
            String stockName = data[0];
            String stockId = data[1];
            double stockPrice = Double.parseDouble(data[2]);
            int stockAmount = Integer.parseInt(data[3]);
            items.add(new Item(stockName, stockId, stockPrice, stockAmount));
            System.out.printf("Product: %s, ID: %s, Price: %s Amount: %s\n", stockName, stockId, stockPrice, stockAmount);
        }
    }

    private static void saveItems() {
        //write out data, overwrite existing file
        try {
            File stockFile = new File(STOCK_FILE_PATH);
            FileWriter fw = new FileWriter(stockFile);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            for(Item item : items) {
                String line = generateCsvLine(item.getName(), item.getId(), String.valueOf(item.getPrice()), String.valueOf(item.getAmount()));
                pw.println(line);
            }
            pw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String generateCsvLine(String... strings){
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < strings.length; i++) {
            builder.append(strings[i]);

            if(i != strings.length -1){
                builder.append(",");
            }
        }
        return builder.toString();
    }

    public static List<Item> getAllItems(){
        if (items == null) {
            loadItems();
        }
        return  items;
    }

    public static void removeItem(Item item)  {
        items.remove(item);
        saveItems();
    }

    public static void addItem(Item item)  {
        items.add(item);
        saveItems();
    }

    public static Item getItemById(String id) throws IOException {
        if(items == null){
            loadItems();
        }
        for(Item item : items){
            if(item.getId().equals(id)){
                return item;
            }
        }
        return null;
    }
}
