package banking;

import javax.swing.*;
import java.sql.*;

public class TransactionHistory extends JFrame {
    public TransactionHistory(String accountNumber) {
        setTitle("Transaction History");

        StringBuilder history = new StringBuilder();
        try {
            Connection con = connection.getConnection();
            String query = "SELECT * FROM transactions WHERE account_number = ? ORDER BY date DESC";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, accountNumber);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                history.append(rs.getString("type"))
                       .append(" - ₹").append(rs.getDouble("amount"))
                       .append(" on ").append(rs.getTimestamp("date")).append("\n");
            }

            if (history.length() == 0) history.append("No transactions yet.");

            JOptionPane.showMessageDialog(null, history.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        dispose();
    }
}
