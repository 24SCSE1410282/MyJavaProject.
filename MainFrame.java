import javax.swing.*;
import java.awt.*;
import java.io.*;

public class MainFrame extends JFrame {
    private Account acc;
    private JLabel balLabel, status;

    public MainFrame(Account acc) {
        this.acc = acc;
        setTitle("ATM Dashboard");
        setSize(400, 300);
        setLayout(new GridLayout(6, 1, 5, 5)); // 6 rows
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        balLabel = new JLabel("Balance: ₹" + acc.getBalance());
        status = new JLabel(" ");

        JButton btnDep = new JButton("Deposit");
        JButton btnW = new JButton("Withdraw");
        JButton btnHist = new JButton("History");
        JButton btnExit = new JButton("Exit");

        add(balLabel);
        add(btnDep);
        add(btnW);
        add(btnHist);
        add(btnExit);
        add(status);

        btnDep.addActionListener(e -> doTrans(true));
        btnW.addActionListener(e -> doTrans(false));
        btnHist.addActionListener(e -> showHistory());
        btnExit.addActionListener(e -> System.exit(0));

        setLocationRelativeTo(null);
        setVisible(true);
    } // ← constructor बंद

    private void doTrans(boolean isDep) {
        String s = JOptionPane.showInputDialog(this, "Enter amount:");
        try {
            double amt = Double.parseDouble(s);
            boolean ok;
            if (isDep) {
                acc.deposit(amt);
                ok = true;
            } else {
                ok = acc.withdraw(amt);
            }
            if (!ok) {
                status.setText("Insufficient funds.");
                return;
            }
            balLabel.setText("Balance: ₹" + acc.getBalance());
            status.setText((isDep ? "Deposited " : "Withdrew ") + "₹" + amt);
        } catch (NumberFormatException ex) {
            status.setText("Invalid amount.");
        } catch (IOException e) {
            status.setText("Error saving transaction.");
        }
    } // ← doTrans बंद

    private void showHistory() {
        try (BufferedReader br = new BufferedReader(new FileReader("transaction_history.txt"))) {
            JTextArea area = new JTextArea();
            String line;
            while ((line = br.readLine()) != null) {
                area.append(line + "\n");
            }
            area.setEditable(false);
            JScrollPane scroll = new JScrollPane(area);
            scroll.setPreferredSize(new Dimension(350, 200));
            JOptionPane.showMessageDialog(this, scroll, "Transaction History", JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException e) {
            status.setText("No transaction history file found.");
        } catch (IOException e) {
            status.setText("Error reading history.");
        }
    } // ← showHistory बंद

} // ← ← ← यह आख़िरी brace MainFrame class को बंद करता है
