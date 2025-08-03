package banking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Register extends JFrame implements ActionListener {
    JTextField usernameField, accNumField;
    JPasswordField passwordField;
    JButton registerBtn, backBtn;

    public Register() {
        setTitle("Register");
        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        add(new JLabel("Account Number:"));
        accNumField = new JTextField();
        add(accNumField);

        registerBtn = new JButton("Register");
        registerBtn.addActionListener(this);
        add(registerBtn);

        backBtn = new JButton("Back");
        backBtn.addActionListener(this);
        add(backBtn);

        setSize(300, 250);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == registerBtn) {
            try {
                Connection con = connection.getConnection();
                String query = "INSERT INTO users(username, password, account_number, balance) VALUES (?, ?, ?, ?)";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, usernameField.getText());
                pst.setString(2, new String(passwordField.getPassword()));
                pst.setString(3, accNumField.getText());
                pst.setDouble(4, 0.0);
                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "Registered successfully!");
                new Login();
                dispose();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == backBtn) {
            new Login();
            dispose();
        }
    }
}
