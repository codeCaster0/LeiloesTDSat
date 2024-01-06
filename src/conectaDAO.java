
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Adm
 */
public class conectaDAO {

    public Connection connectDB() {
        Connection conn = null;
        try {

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/uc11?autoReconnect=true&useSSL=false", "user", "password");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Error, class 'ConectaDAO' " + erro.getMessage() + "\nCheck for typos or a wrong user/password on 'DriverManager.getConnection'", "Connection error", JOptionPane.ERROR_MESSAGE);
        }
        return conn;
    }

}
