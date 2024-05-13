package org.adjole.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {

    static Connection conn = null;

    public Connexion() {
    }

    public final Connection makeConnection() throws SQLException {
        if (conn == null) {

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("EX: " + ex.getMessage());

            }
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_tppoo_hadjole", "root", "");
        }
        return conn;
    }
}

