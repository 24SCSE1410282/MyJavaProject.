import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Account {
    private String userId, pin;
    private double balance;

    public Account(String userId, String pin, double initBalance) {
        this.userId = userId;
        this.pin = pin;
        this.balance = initBalance;
    }

    public boolean authenticate(String u, String p) {
        return userId.equals(u) && pin.equals(p);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amt) throws IOException {
        balance += amt;
        record("DEPOSIT", amt);
    }

    public boolean withdraw(double amt) throws IOException {
        if (amt <= balance) {
            balance -= amt;
            record("WITHDRAW", amt);
            return true;
        }
        return false;
    }

    private void record(String type, double amt) throws IOException {
        String entry = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                        .format(new Date()) + " | " + type 
                        + " | " + amt + " | bal: " + balance;
        try (FileWriter fw = new FileWriter("transaction_history.txt", true)) {
            fw.write(entry + "\n");
        }
    }
}
