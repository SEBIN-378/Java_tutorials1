import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FibonacciListGUI extends JFrame implements ActionListener {
    private JTextField txtLimit;
    private DefaultListModel<String> listModel;
    private JList<String> fibList;
    private JButton btnGenerate, btnClear;

    public FibonacciListGUI() {
        setTitle("Fibonacci Generator");
        setSize(350, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Top input panel
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Enter Limit:"));
        txtLimit = new JTextField(10);
        inputPanel.add(txtLimit);
        add(inputPanel, BorderLayout.NORTH);

        // Center list display
        listModel = new DefaultListModel<>();
        fibList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(fibList);
        add(scrollPane, BorderLayout.CENTER);

        // Bottom button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        btnGenerate = new JButton("Generate");
        btnClear = new JButton("Clear");
        btnGenerate.addActionListener(this);
        btnClear.addActionListener(this);
        buttonPanel.add(btnGenerate);
        buttonPanel.add(btnClear);
        add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGenerate) {
            listModel.clear();
            try {
                long limit = Long.parseLong(txtLimit.getText().trim());
                if (limit < 0) {
                    JOptionPane.showMessageDialog(this, "Please enter a positive limit.", 
                                                  "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                long a = 0, b = 1;
                while (a <= limit) {
                    listModel.addElement(String.valueOf(a));
                    long next = a + b;
                    a = b;
                    b = next;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid integer.", 
                                              "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == btnClear) {
            txtLimit.setText("");
            listModel.clear();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FibonacciListGUI::new);
    }
      }
