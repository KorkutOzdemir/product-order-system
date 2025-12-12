/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.productordersystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class User {

    private String email, password;
    private boolean discountApplied;
    public static User currentUser;

    static ArrayList<User> userList = new ArrayList<>();

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        userList.add(this);
        this.discountApplied = false;
    }

    public static List<User> loadUsersFromFile() {
        List<User> userList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("user.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Email:")) {
                    String[] parts = line.split(", ");
                    String email = parts[0].substring("Email: ".length());
                    String password = parts[1].substring("Password: ".length());
                    userList.add(new User(email, password));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading user file: " + e.getMessage());
        }
        return userList;
    }

    public static void insertUserToDatabase(User user) {
        String sql = "INSERT INTO users (email, password) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getPassword());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }
    }

    public boolean isDiscountApplied() {
        return discountApplied;
    }

    public void setDiscountApplied(boolean discountApplied) {
        this.discountApplied = discountApplied;
    }

    public static void addUser(User user) {
        userList.add(user);
    }

    public static ArrayList<User> getUserList() {
        return userList;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    String getName() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
