
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Bank Account class to store the account balance
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }
}

// ATM class representing the ATM machine
class ATM extends JFrame implements ActionListener {
    private BankAccount account;
    private JLabel balanceLabel;

    public ATM(BankAccount account) {
        this.account = account;

        setTitle("ATM");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createUI();
    }

    private void createUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(this);
        panel.add(withdrawButton);

        JButton depositButton = new JButton("Deposit");
        depositButton.addActionListener(this);
        panel.add(depositButton);

        JButton balanceButton = new JButton("Check Balance");
        balanceButton.addActionListener(this);
        panel.add(balanceButton);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(this);
        panel.add(exitButton);

        balanceLabel = new JLabel("Current balance: $1000");
        panel.add(balanceLabel);

        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();

        switch (action) {
            case "Withdraw":
                String withdrawInput = JOptionPane.showInputDialog(this, "Enter amount to withdraw:");
                if (withdrawInput != null) {
                    try {
                        double withdrawAmount = Double.parseDouble(withdrawInput);
                        withdraw(withdrawAmount);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a valid number.");
                    }
                }
                break;
            case "Deposit":
                String depositInput = JOptionPane.showInputDialog(this, "Enter amount to deposit:");
                if (depositInput != null) {
                    try {
                        double depositAmount = Double.parseDouble(depositInput);
                        deposit(depositAmount);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a valid number.");
                    }
                }
                break;
            case "Check Balance":
                checkBalance();
                break;
            case "Exit":
                System.exit(0);
                break;
        }
    }

    private void withdraw(double amount) {
        if (amount <= 0) {
            JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a positive value.");
            return;
        }

        if (amount > account.getBalance()) {
            JOptionPane.showMessageDialog(this, "Insufficient balance.");
            return;
        }

        account.withdraw(amount);
        JOptionPane.showMessageDialog(this, "Successfully withdrew $" + amount);

        updateBalanceLabel();
    }

    private void deposit(double amount) {
        if (amount <= 0) {
            JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a positive value.");
            return;
        }

        account.deposit(amount);
        JOptionPane.showMessageDialog(this, "Successfully deposited $" + amount);

        updateBalanceLabel();
    }

    private void checkBalance() {
        double balance = account.getBalance();
        JOptionPane.showMessageDialog(this, "Current balance: $" + balance);
    }

    private void updateBalanceLabel() {
        double balance = account.getBalance();
        balanceLabel.setText("Current balance: $" + balance);
    }
}

public class task4 {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000.0); // Initial balance of $1000
        ATM atm = new ATM(account);
        atm.setVisible(true);
    }
}
