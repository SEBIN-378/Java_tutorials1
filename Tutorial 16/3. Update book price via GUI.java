import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UpdatePriceGUI extends JFrame implements ActionListener {
    JTextField tfId, tfNewPrice;
    JButton btnUpdate;

    public UpdatePriceGUI() {
        setTitle("Update Book Price");
        setSize(320, 180);
        setLayout(new GridLayout(3, 2, 10, 10));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // UI Components
        add(new JLabel("Book ID:"));
        tfId = new JTextField();
        add(tfId);

        add(new JLabel("New Price:"));
        tfNewPrice = new JTextField();
        add(tfNewPrice);

        btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(this);
        add(new JLabel("")); // Placeholder
        add(btnUpdate);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String url = "jdbc:mysql://localhost:3306/library_db";
        String user = "root";
        String password = "password";

        String query = "UPDATE books SET price = ? WHERE book_id = ?";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {

            double newPrice = Double.parseDouble(tfNewPrice.getText().trim());
            int bookId = Integer.parseInt(tfId.getText().trim());

            pst.setDouble(1, newPrice);
            pst.setInt(2, bookId);

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Price updated successfully");
                tfId.setText("");
                tfNewPrice.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Book not found", "Not Found", JOptionPane.WARNING_MESSAGE);
            }

        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new UpdatePriceGUI();
    }
}
