import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

public class FactorialGUI extends JFrame implements ActionListener {
    private JTextField txtInput, txtResult;
    private JButton btnCalculate;

    public FactorialGUI() {
        setTitle("Factorial Calculator");
        setSize(400, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel("  Enter a Number:"));
        txtInput = new JTextField();
        add(txtInput);

        add(new JLabel("  Factorial Result:"));
        txtResult = new JTextField();
        txtResult.setEditable(false);
        add(txtResult);

        btnCalculate = new JButton("Calculate");
        btnCalculate.addActionListener(this);
        add(new JLabel("")); // Empty placeholder for grid balance
        add(btnCalculate);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int n = Integer.parseInt(txtInput.getText().trim());
            if (n < 0) {
                JOptionPane.showMessageDialog(this, "Number must be non-negative.", 
                                              "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            BigInteger fact = BigInteger.ONE;
            for (int i = 2; i <= n; i++) {
                fact = fact.multiply(BigInteger.valueOf(i));
            }

            txtResult.setText(fact.toString());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid number entered.", 
                                          "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FactorialGUI::new);
    }
}
