package banking;

import javax.swing.*;
import java.sql.*;

public class Balance extends JFrame {
    public Balance(String accountNumber) {
        setTitle("Check Balance");

        try {
            Connection con = connection.getConnection();
            String query = "SELECT balance FROM users WHERE account_number = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, accountNumber);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Current Balance: ₹" + rs.getDouble("balance"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        dispose();
    }
}
