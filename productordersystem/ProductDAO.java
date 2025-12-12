/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.productordersystem;

/**
 *
 * @author Korkut
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public static ArrayList<Product> getAllProducts() {
        ArrayList<Product> productList = new ArrayList<>();
        String sql = "SELECT name, price, quantity FROM products";

        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String productName = rs.getString("name");  // product yerine name
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");

                Product product = new Product(productName, price, quantity);
                productList.add(product);
            }

        } catch (SQLException e) {
            System.out.println("An error occurred while retrieving products from the database: " + e.getMessage());
        }

        return productList;
    }

    public static boolean addProduct(Product product) {
        String sql = "INSERT INTO products (name, price, quantity) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, product.getProduct());
            pstmt.setDouble(2, product.getPrice());
            pstmt.setInt(3, product.getQuantity());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.out.println("An error occurred while adding the product: " + e.getMessage());
            return false;
        }
    }

    public static boolean deleteProduct(String productName) {
        String sql = "DELETE FROM products WHERE name = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, productName);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.out.println("An error occurred while deleting the product: " + e.getMessage());
            return false;
        }
    }

    public static boolean productExists(String name) {
        String sql = "SELECT * FROM products WHERE name = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // varsa true döner
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateProduct(Product product) {
        String sql = "UPDATE products SET price = ?, quantity = ? WHERE name = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, product.getPrice());
            pstmt.setInt(2, product.getQuantity());
            pstmt.setString(3, product.getProduct());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.out.println("An error occurred while updating the product: " + e.getMessage());
            return false;
        }
    }
}
