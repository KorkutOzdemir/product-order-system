/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.productordersystem;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Korkut
 */
public class PurchaseHistory {
    private static List<String> historyList = new ArrayList<>();

    public static void addHistory(String productName, double price) {
        String entry = productName + " - $" + price;
        historyList.add(entry);
    }

    public static List<String> getHistoryList() {
        return historyList;
    }

    public static void clearHistory() {
        historyList.clear();
    }
}
