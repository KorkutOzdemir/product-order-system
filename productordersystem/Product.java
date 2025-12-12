/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.productordersystem;

import java.util.ArrayList;

/**
 *
 * @author Korkut
 */
public class Product {

    private String name;
    private double price;
    private int quantity;

    public static ArrayList<Product> productList = new ArrayList<>();

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public static void loadFromDB() {
        productList.clear();
        productList.addAll(ProductDAO.getAllProducts());
    }

    public static void addProduct(Product p) {
        productList.add(p);
    }

    public static ArrayList<Product> getProductList() {
        return productList;
    }

    public String getProduct() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public static void loadSampleTable() {
        productList.clear();

        Product p1 = new Product("Headphone", 300, 10);
        Product p2 = new Product("Iphone", 1000, 3);
        Product p3 = new Product("TV", 2500, 2);

        addAndSave(p1);
        addAndSave(p2);
        addAndSave(p3);

        Computer c1 = new Computer("Casper", 2500, 1);
        Computer c2 = new Computer("Lenovo", 3000, 1);
        Computer c3 = new Computer("Macbook", 5000, 1);
        Computer c4 = new Computer("Monster", 4000, 1);
        Computer c5 = new Computer("Toshiba", 2000, 1);

        Phone ph1 = new Phone("Iphone", 1500, 1);
        Phone ph2 = new Phone("Samsung", 700, 1);
        Phone ph3 = new Phone("Nokia", 300, 1);
        Phone ph4 = new Phone("Huawei", 400, 1);
        Phone ph5 = new Phone("Xiaomi", 500, 1);

        addAndSave(c1.toProduct());
        addAndSave(c2.toProduct());
        addAndSave(c3.toProduct());
        addAndSave(c4.toProduct());
        addAndSave(c5.toProduct());

        addAndSave(ph1.toProduct());
        addAndSave(ph2.toProduct());
        addAndSave(ph3.toProduct());
        addAndSave(ph4.toProduct());
        addAndSave(ph5.toProduct());
    }


    private static void addAndSave(Product product) {
        productList.add(product); 
        if (!ProductDAO.productExists(product.getProduct())) {
            ProductDAO.addProduct(product);
        }
    }
}
