/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.productordersystem;

/**
 *
 * @author Korkut
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Transaction {

    private static final ArrayList<Product> cartList = new ArrayList<>();
    private static final ArrayList<Product> favList = new ArrayList<>();
    private static final ArrayList<String> transactionLog = new ArrayList<>();

    private static final String DISCOUNT_CODE = "FSM10";
    private static boolean discountApplied = false;

    public static ArrayList<Product> getCartList() {
        return cartList;
    }

    public static ArrayList<Product> getFavList() {
        return favList;
    }

    public static ArrayList<String> getTransactionLog() {
        return transactionLog;
    }

    public static void addToCart(Product product) {
        cartList.add(product);
        log("Product added to cart: " + product.getProduct() + ", Price: " + product.getPrice());
        discountApplied = false;
    }

    public static void addToFavorites(Product product) {
        favList.add(product);
        log("Product added to favorites: " + product.getProduct());
    }

    public static void removeFromCart(int index) {
        if (index >= 0 && index < cartList.size()) {
            Product removed = cartList.remove(index);
            log("Product removed from cart: " + removed.getProduct());
            discountApplied = false;
        }
    }

    public static int getCartTotalPrice() {
        int sum = 0;
        for (Product p : cartList) {
            sum += p.getPrice();
        }
        return sum;
    }

    public static int getDiscountedPrice(String code) {
        if (discountApplied) {
            log("Attempted to reapply discount code.");
            return getCartTotalPrice();
        }

        if (DISCOUNT_CODE.equalsIgnoreCase(code)) {
            int discounted = (int) (getCartTotalPrice() * 0.9);
            log("Discount applied with code: " + code + ", Discounted Total: " + discounted);
            discountApplied = true;
            return discounted;
        } else {
            log("Invalid discount code used: " + code);
            return getCartTotalPrice();
        }
    }

    public static void resetDiscount() {
        discountApplied = false;
        log("Discount reset due to cart change.");
    }

    private static void log(String message) {
        String timestampedMessage = "[" + new Date().toString() + "] " + message;
        transactionLog.add(timestampedMessage);
        writeToFile(timestampedMessage);
    }

    public static void writeToFile(String logEntry) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("transaction_log.txt", true))) {
            writer.write(logEntry);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }

    public static void clearAll() {
        cartList.clear();
        favList.clear();
        discountApplied = false;
        log("All lists cleared.");
    }

    public static void writeDiscountedTotalToFile(double total) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("transaction_log.txt", true))) {
            writer.write("[" + new java.util.Date() + "] Discounted Total Updated: $" + total + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static double readDiscountedTotalFromFile() {
        double lastDiscountedTotal = 0.0;
        try (BufferedReader reader = new BufferedReader(new FileReader("transaction_log.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Discounted Total Updated")) {
                    int idx = line.lastIndexOf("$");
                    if (idx != -1) {
                        String amountStr = line.substring(idx + 1);
                        lastDiscountedTotal = Double.parseDouble(amountStr.trim());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lastDiscountedTotal;
    }
}
