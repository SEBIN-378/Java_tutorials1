import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ViewBooksGUI extends JFrame implements ActionListener {
    JTable table;
    DefaultTableModel model;
    JButton btnLoad;

    public ViewBooksGUI() {
        setTitle("View All Books");
        setSize(500, 350);
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Table Setup
        String[] columns = {"Book ID", "Title", "Author", "Price"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Button Setup
        btnLoad = new JButton("Load Books");
        btnLoad.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnLoad);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String url = "jdbc:mysql://localhost:3306/library_db";
        String user = "root";
        String password = "password";

        String query = "SELECT * FROM books";

        // Clear existing rows before loading
        model.setRowCount(0);

        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                int id = rs.getInt("book_id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                double price = rs.getDouble("price");

                model.addRow(new Object[]{id, title, author, price});
            }

            if (!hasData) {
                JOptionPane.showMessageDialog(this, "The table is empty. No books found.");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new ViewBooksGUI();
    }
}
