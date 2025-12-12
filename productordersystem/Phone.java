/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.productordersystem;

/**
 *
 * @author Korkut
 */
public class Phone {
    private String name;
    private double price;

    public Phone(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
    
   public Product toProduct() {
        return new Product(name, price, 1); // quantity = 1 varsayıldı
    }
}
