package com.coffeecart.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelReader {

    public Object[][] getTestData(String fileName) {
        List<String[]> dataList = new ArrayList<>();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

            String line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                dataList.add(line.split(","));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Object[][] data = new Object[dataList.size()][];
        for (int i = 0; i < dataList.size(); i++) {
            data[i] = dataList.get(i);
        }
        return data;
    }

    public Map<String, String> getCoffeeItems() {
        Map<String, String> coffeeItems = new HashMap<>();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("testdata.csv");
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

            String line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    coffeeItems.put(parts[0], parts[2]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return coffeeItems;
    }
}