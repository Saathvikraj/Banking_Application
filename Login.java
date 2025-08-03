package banking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginBtn, registerBtn;

    public Login() {
        setTitle("Login");
        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        loginBtn = new JButton("Login");
        loginBtn.addActionListener(this);
        add(loginBtn);

        registerBtn = new JButton("Register");
        registerBtn.addActionListener(this);
        add(registerBtn);

        setSize(300, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == loginBtn) {
            try {
                Connection con = connection.getConnection();
                String query = "SELECT * FROM users WHERE username=? AND password=?";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, usernameField.getText());
                pst.setString(2, new String(passwordField.getPassword()));
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    String accNum = rs.getString("account_number");
                    new Dashboard(accNum);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid credentials!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == registerBtn) {
            new Register();
            dispose();
        }
    }
}
