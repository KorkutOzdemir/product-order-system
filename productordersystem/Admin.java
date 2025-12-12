/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.productordersystem;

/**
 *
 * @author Korkut
 */
import java.util.List;

public class Admin {
    private static final String DEFAULT_USERNAME = "admin";
    private static final String DEFAULT_PASSWORD = "1234";

    private String username;
    private String password;

    public Admin() {
        this.username = DEFAULT_USERNAME;
        this.password = DEFAULT_PASSWORD;
    }

    public boolean login(String inputUsername, String inputPassword) {
        return this.username.equals(inputUsername) && this.password.equals(inputPassword);
    }

    public void addProduct(List<Product> products, Product newProduct) {
        products.add(newProduct);
        System.out.println("Product added: " + newProduct.getProduct());
    }

//    public void removeProduct(List<Product> products, Product productToRemove) {
//        if (products.remove(productToRemove)) {
//            System.out.println("Product deleted: " + productToRemove.getProduct());
//        } else {
//            System.out.println("Product couldn't find.");
//        }
//    }

//    public void viewUsers(List<User> users) {
//        System.out.println("Kayıtlı Kullanıcılar:");
//        for (User user : users) {
//            System.out.println("- " + user.getName() + " (" + user.getEmail() + ")");
//        }
//    }
}
