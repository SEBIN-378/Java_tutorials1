// ==========================================
// 1. CounterModel.java
// ==========================================
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CounterModel {
    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "root";
    private static final String PASSWORD = "password"; // Update with your MySQL password

    // Connects to the database and increments the visitor count
    public int incrementVisitorCount() {
        int updatedCount = 0;

        String selectQuery = "SELECT visitor_count FROM counter LIMIT 1";
        String updateQuery = "UPDATE counter SET visitor_count = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // 1. Read current count
            try (PreparedStatement selectStmt = conn.prepareStatement(selectQuery);
                 ResultSet rs = selectStmt.executeQuery()) {
                
                if (rs.next()) {
                    updatedCount = rs.getInt("visitor_count") + 1;
                } else {
                    // Handle case where table is empty
                    updatedCount = 1;
                    String insertQuery = "INSERT INTO counter (visitor_count) VALUES (1)";
                    try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                        insertStmt.executeUpdate();
                    }
                    return updatedCount;
                }
            }

            // 2. Update database with incremented count
            try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                updateStmt.setInt(1, updatedCount);
                updateStmt.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updatedCount;
    }
}


// ==========================================
// 2. CounterView.java
// ==========================================
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

public class CounterView extends JFrame {
    private JButton countButton;
    private JLabel countLabel;

    public CounterView() {
        setTitle("Visitor Counter");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout setup
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 25));

        // Required UI Components
        countButton = new JButton("Click Here to See Visitor Count");
        countLabel = new JLabel(""); // Initially blank
        countLabel.setFont(new Font("Arial", Font.BOLD, 16));

        panel.add(countButton);
        panel.add(countLabel);

        add(panel);
    }

    // Method to update the label
    public void displayVisitorCount(int count) {
        countLabel.setText("You are visitor No: " + count);
    }

    // Listener hook for the Controller
    public void addCounterListener(ActionListener listener) {
        countButton.addActionListener(listener);
    }
}


// ==========================================
// 3. CounterController.java
// ==========================================
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CounterController {
    private CounterModel model;
    private CounterView view;

    public CounterController(CounterModel model, CounterView view) {
        this.model = model;
        this.view = view;

        // Register button click listener
        this.view.addCounterListener(new CounterButtonListener());
    }

    private class CounterButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // 1. Call model to increment count
            int newCount = model.incrementVisitorCount();

            // 2. Update view with the new visitor number
            view.displayVisitorCount(newCount);
        }
    }
}


// ==========================================
// 4. Main.java
// ==========================================
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CounterModel model = new CounterModel();
            CounterView view = new CounterView();
            new CounterController(model, view);

            view.setVisible(true);
        });
    }
}
