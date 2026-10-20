CREATE DATABASE library_db;
USE library_db;

CREATE TABLE books (
    book_id INT PRIMARY KEY,
    title VARCHAR(100),
    author VARCHAR(100),
    price DOUBLE
);

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class InsertBookGUI extends JFrame implements ActionListener {
    JTextField tfId, tfTitle, tfAuthor, tfPrice;
    JButton btnSubmit;

    public InsertBookGUI() {
        setTitle("Add New Book");
        setSize(350, 250);
        setLayout(new GridLayout(5, 2, 10, 10));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // UI Components
        add(new JLabel("Book ID:"));
        tfId = new JTextField();
        add(tfId);

        add(new JLabel("Title:"));
        tfTitle = new JTextField();
        add(tfTitle);

        add(new JLabel("Author:"));
        tfAuthor = new JTextField();
        add(tfAuthor);

        add(new JLabel("Price:"));
        tfPrice = new JTextField();
        add(tfPrice);

        btnSubmit = new JButton("Submit");
        btnSubmit.addActionListener(this);
        add(new JLabel("")); // Empty placeholder
        add(btnSubmit);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String url = "jdbc:mysql://localhost:3306/library_db";
        String user = "root";
        String password = "password"; // Replace with your MySQL password

        String query = "INSERT INTO books (book_id, title, author, price) VALUES (?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, Integer.parseInt(tfId.getText().trim()));
            pst.setString(2, tfTitle.getText().trim());
            pst.setString(3, tfAuthor.getText().trim());
            pst.setDouble(4, Double.parseDouble(tfPrice.getText().trim()));

            int rows = pst.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Book inserted successfully");
                tfId.setText("");
                tfTitle.setText("");
                tfAuthor.setText("");
                tfPrice.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Error in insertion", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error in insertion: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new InsertBookGUI();
    }
                          }
