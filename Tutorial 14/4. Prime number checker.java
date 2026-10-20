import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrimeCheckerGUI extends JFrame implements ActionListener {
    private JTextField txtNumber;
    private JLabel lblStatus;
    private JButton btnCheck;

    public PrimeCheckerGUI() {
        setTitle("Prime Number Checker");
        setSize(360, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1, 10, 10));

        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Enter Number:"));
        txtNumber = new JTextField(12);
        inputPanel.add(txtNumber);
        add(inputPanel);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        btnCheck = new JButton("Check Prime");
        btnCheck.addActionListener(this);
        buttonPanel.add(btnCheck);
        add(buttonPanel);

        lblStatus = new JLabel("Result will appear here", SwingConstants.CENTER);
        lblStatus.setFont(new Font("Arial", Font.BOLD, 13));
        add(lblStatus);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private boolean isPrime(long n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;

        for (long i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            long number = Long.parseLong(txtNumber.getText().trim());
            if (isPrime(number)) {
                lblStatus.setText(number + " is a Prime Number.");
                lblStatus.setForeground(new Color(0, 128, 0));
            } else {
                lblStatus.setText(number + " is NOT a Prime Number.");
                lblStatus.setForeground(Color.RED);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid integer.", 
                                          "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PrimeCheckerGUI::new);
    }
}
