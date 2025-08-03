package banking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Dashboard extends JFrame implements ActionListener {
    JButton depositBtn, withdrawBtn, balanceBtn, accDetailsBtn, txnHistoryBtn, logoutBtn;
    String accountNumber;

    public Dashboard(String accountNumber) {
        this.accountNumber = accountNumber;

        setTitle("Dashboard - " + accountNumber);
        setLayout(new GridLayout(3, 2, 10, 10));

        depositBtn = new JButton("Deposit");
        withdrawBtn = new JButton("Withdraw");
        balanceBtn = new JButton("Check Balance");
        accDetailsBtn = new JButton("Account Details");
        txnHistoryBtn = new JButton("Transaction History");
        logoutBtn = new JButton("Logout");

        depositBtn.addActionListener(this);
        withdrawBtn.addActionListener(this);
        balanceBtn.addActionListener(this);
        accDetailsBtn.addActionListener(this);
        txnHistoryBtn.addActionListener(this);
        logoutBtn.addActionListener(this);

        add(depositBtn); add(withdrawBtn);
        add(balanceBtn); add(accDetailsBtn);
        add(txnHistoryBtn); add(logoutBtn);

        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == depositBtn) new Deposit(accountNumber);
        else if (ae.getSource() == withdrawBtn) new Withdraw(accountNumber);
        else if (ae.getSource() == balanceBtn) new Balance(accountNumber);
        else if (ae.getSource() == accDetailsBtn) new AccountDetails(accountNumber);
        else if (ae.getSource() == txnHistoryBtn) new TransactionHistory(accountNumber);
        else if (ae.getSource() == logoutBtn) {
            new Login();
            dispose();
        }
    }
}
