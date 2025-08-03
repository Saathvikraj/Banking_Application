package banking;

import javax.swing.*;
import java.sql.*;

public class AccountDetails extends JFrame {
    public AccountDetails(String accountNumber) {
        setTitle("Account Details");

        try {
            Connection con = connection.getConnection();
            String query = "SELECT * FROM users WHERE account_number = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, accountNumber);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String details = "Username: " + rs.getString("username") +
                                 "\nAccount Number: " + rs.getString("account_number") +
                                 "\nBalance: ₹" + rs.getDouble("balance");
                JOptionPane.showMessageDialog(null, details);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        dispose();
    }
}
