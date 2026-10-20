import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DeleteBookGUI extends JFrame implements ActionListener {
    JTextField tfId;
    JButton btnDelete;

    public DeleteBookGUI() {
        setTitle("Delete Book");
        setSize(300, 140);
        setLayout(new GridLayout(2, 2, 10, 10));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // UI Components
        add(new JLabel("Book ID:"));
        tfId = new JTextField();
        add(tfId);

        btnDelete = new JButton("Delete");
        btnDelete.addActionListener(this);
        add(new JLabel("")); // Placeholder
        add(btnDelete);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String url = "jdbc:mysql://localhost:3306/library_db";
        String user = "root";
        String password = "password";

        String query = "DELETE FROM books WHERE book_id = ?";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {

            int bookId = Integer.parseInt(tfId.getText().trim());
            pst.setInt(1, bookId);

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Book deleted successfully");
                tfId.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Book not found", "Not Found", JOptionPane.WARNING_MESSAGE);
            }

        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Please enter a valid Book ID.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new DeleteBookGUI();
    }
}
