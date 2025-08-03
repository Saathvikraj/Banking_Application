package banking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Deposit extends JFrame implements ActionListener {
    JTextField amountField;
    JButton depositBtn;
    String accountNumber;

    public Deposit(String accountNumber) {
        this.accountNumber = accountNumber;
        setTitle("Deposit");

        setLayout(new GridLayout(2, 2, 10, 10));
        add(new JLabel("Amount:"));
        amountField = new JTextField();
        add(amountField);

        depositBtn = new JButton("Deposit");
        depositBtn.addActionListener(this);
        add(depositBtn);

        setSize(300, 150);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            double amount = Double.parseDouble(amountField.getText());
            Connection con = connection.getConnection();

            String updateBal = "UPDATE users SET balance = balance + ? WHERE account_number = ?";
            PreparedStatement pst1 = con.prepareStatement(updateBal);
            pst1.setDouble(1, amount);
            pst1.setString(2, accountNumber);
            pst1.executeUpdate();

            String insertTxn = "INSERT INTO transactions(account_number, type, amount) VALUES (?, 'deposit', ?)";
            PreparedStatement pst2 = con.prepareStatement(insertTxn);
            pst2.setString(1, accountNumber);
            pst2.setDouble(2, amount);
            pst2.executeUpdate();

            JOptionPane.showMessageDialog(null, "Amount Deposited!");
            dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
