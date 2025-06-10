public class ATMSystem11 {
    public static void main(String[] args) {
        Account acc = new Account("user1", "1234", 1000.0);
        javax.swing.SwingUtilities.invokeLater(() -> new LoginFrame(acc));
    }
}
