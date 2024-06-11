package com.uaspborestoran.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class connection extends Service<Boolean> {
    public static Connection conn;

    @Override
    protected Task<Boolean> createTask() {
        return new Task<Boolean>() {
            @Override
            protected Boolean call() {
                try {
                    Thread.sleep(1000);
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbuasrestoran", "root", "");
                    if (conn == null) {
                        System.err.println("koneksi null ajg");
                        return false;
                    }
                    return true;
                } catch (Exception e) {
                    System.out.println(e);
                    return false;
                }
            }
        };
    }

    public static void initializeConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbuasrestoran", "root", "");
        }
    }
}
