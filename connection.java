package banking;

import java.sql.*;

public class connection {
    static Connection con;
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bankapp_db", "root", "Saathvik@22");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
