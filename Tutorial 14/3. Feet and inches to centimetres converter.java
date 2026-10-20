import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LengthConverterGUI extends JFrame implements ActionListener {
    private JTextField txtFeet, txtInches, txtCentimeters;
    private JButton btnConvert;

    public LengthConverterGUI() {
        setTitle("Length Converter");
        setSize(380, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel("  Feet:"));
        txtFeet = new JTextField();
        add(txtFeet);

        add(new JLabel("  Inches:"));
        txtInches = new JTextField();
        add(txtInches);

        add(new JLabel("  Centimeters (cm):"));
        txtCentimeters = new JTextField();
        txtCentimeters.setEditable(false);
        add(txtCentimeters);

        btnConvert = new JButton("Convert");
        btnConvert.addActionListener(this);
        add(new JLabel("")); 
        add(btnConvert);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double feet = Double.parseDouble(txtFeet.getText().trim());
            double inches = Double.parseDouble(txtInches.getText().trim());

            if (feet < 0 || inches < 0) {
                JOptionPane.showMessageDialog(this, "Measurements cannot be negative.", 
                                              "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double totalInches = (feet * 12.0) + inches;
            double cm = totalInches * 2.54;

            txtCentimeters.setText(String.format("%.2f", cm));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values.", 
                                          "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LengthConverterGUI::new);
    }
}
