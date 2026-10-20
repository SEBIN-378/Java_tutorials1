import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrevNextGUI extends JFrame implements ActionListener {
    private JLabel lblInput, lblPrev, lblNext;
    private JTextField tfInput, tfPrev, tfNext;
    private JButton btnCompute;

    public PrevNextGUI() {
        setTitle("Previous and Next Number");
        setSize(400, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 10, 10));

        // Three Labels and Three Text Fields
        lblInput = new JLabel("  Enter Number:");
        tfInput = new JTextField();

        lblPrev = new JLabel("  Previous Number:");
        tfPrev = new JTextField();
        tfPrev.setEditable(false);

        lblNext = new JLabel("  Next Number:");
        tfNext = new JTextField();
        tfNext.setEditable(false);

        // One Button
        btnCompute = new JButton("Display Neighbors");
        btnCompute.addActionListener(this);

        add(lblInput);
        add(tfInput);
        add(lblPrev);
        add(tfPrev);
        add(lblNext);
        add(tfNext);
        add(new JLabel("")); // Spacer
        add(btnCompute);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            long value = Long.parseLong(tfInput.getText().trim());
            tfPrev.setText(String.valueOf(value - 1));
            tfNext.setText(String.valueOf(value + 1));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid integer.", 
                                          "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PrevNextGUI::new);
    }
}
