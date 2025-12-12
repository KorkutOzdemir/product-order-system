/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.productordersystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Korkut
 */
public class LogHelper {
    public static void writeLog(String logEntry) {
        System.out.println("LogHelper triggered with: " + logEntry);
        String sql = "INSERT INTO purchase_history (message) VALUES (?)";

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, logEntry);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Log error: " + e.getMessage());
        }
    }
}
