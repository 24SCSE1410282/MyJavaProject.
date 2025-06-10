import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {
    public LoginFrame(Account acc) {
        setTitle("ATM Login");
        setSize(300, 150);
        setLayout(new GridLayout(3, 2));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(new JLabel("User ID:")); JTextField idF = new JTextField(); add(idF);
        add(new JLabel("PIN:")); JPasswordField pinF = new JPasswordField(); add(pinF);
        JButton btn = new JButton("Login"); add(btn);
        JLabel msg = new JLabel(); add(msg);

        btn.addActionListener(e -> {
            if (acc.authenticate(idF.getText(), new String(pinF.getPassword()))) {
                dispose();
                new MainFrame(acc);
            } else {
                msg.setText("Invalid credentials!");
            }
        });
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
