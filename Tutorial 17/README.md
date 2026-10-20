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
import javax.swing.SwingConstants;
import javax.swing.Timer;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class CounterView extends JFrame {
    private CustomButton countButton;
    private JLabel countLabel;
    private JLabel subtitleLabel;

    // Palette: Modern Violet/Indigo Dark Gradient Theme
    private static final Color GRADIENT_BG_START = new Color(15, 23, 42);   // Deep slate
    private static final Color GRADIENT_BG_END = new Color(30, 27, 75);     // Deep indigo background
    private static final Color CARD_BG = new Color(255, 255, 255, 22);      // Glassmorphism card fill
    private static final Color CARD_BORDER = new Color(255, 255, 255, 38);  // Card border highlight

    private static final Font TITLE_FONT = new Font("SansSerif", Font.BOLD, 15);
    private static final Font NUMBER_FONT = new Font("SansSerif", Font.BOLD, 22);
    private static final Font SUBTITLE_FONT = new Font("SansSerif", Font.PLAIN, 12);

    public CounterView() {
        setTitle("Visitor Counter Dashboard");
        setSize(480, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main Gradient Panel
        JPanel mainPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                // Background Gradient
                GradientPaint bgGradient = new GradientPaint(
                        0, 0, GRADIENT_BG_START, 
                        getWidth(), getHeight(), GRADIENT_BG_END
                );
                g2d.setPaint(bgGradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        // Glassmorphic Card Container
        JPanel cardPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int arc = 24;
                // Card Background
                g2d.setColor(CARD_BG);
                g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), arc, arc));

                // Subtle Card Border
                g2d.setColor(CARD_BORDER);
                g2d.draw(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, arc, arc));
            }
        };
        cardPanel.setOpaque(false);
        cardPanel.setPreferredSize(new Dimension(380, 210));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15);
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Custom Gradient Hover Button
        countButton = new CustomButton("Click Here to See Visitor Count");

        // Visitor Output Count Label
        countLabel = new JLabel("Welcome!", SwingConstants.CENTER);
        countLabel.setFont(NUMBER_FONT);
        countLabel.setForeground(new Color(248, 250, 252));

        // Subtitle Status Indicator
        subtitleLabel = new JLabel("Press the button to record your visit", SwingConstants.CENTER);
        subtitleLabel.setFont(SUBTITLE_FONT);
        subtitleLabel.setForeground(new Color(148, 163, 184));

        // Assemble Components inside Glass Card
        gbc.gridy = 0;
        cardPanel.add(countButton, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(12, 15, 2, 15);
        cardPanel.add(countLabel, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(0, 15, 10, 15);
        cardPanel.add(subtitleLabel, gbc);

        mainPanel.add(cardPanel);
        add(mainPanel);
    }

    public void displayVisitorCount(int count) {
        countLabel.setText("You are visitor No: " + count);
        subtitleLabel.setText("Database updated successfully");
        subtitleLabel.setForeground(new Color(129, 140, 248));
    }

    public void addCounterListener(ActionListener listener) {
        countButton.addActionListener(listener);
    }

    // Custom Button with Round Edges, Gradient, and Smooth Hover Animation
    private static class CustomButton extends JButton {
        private float hoverAlpha = 0.0f;
        private Timer alphaTimer;

        private static final Color BTN_START = new Color(99, 102, 241);   // Bright indigo
        private static final Color BTN_END = new Color(168, 85, 247);     // Electric purple
        private static final Color HOVER_COLOR = new Color(255, 255, 255, 45);

        public CustomButton(String text) {
            super(text);
            setFont(TITLE_FONT);
            setForeground(Color.WHITE);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setPreferredSize(new Dimension(320, 48));

            // Hover fade-in transition timer
            alphaTimer = new Timer(15, e -> {
                repaint();
            });

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    hoverAlpha = 1.0f;
                    alphaTimer.start();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    hoverAlpha = 0.0f;
                    alphaTimer.start();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int arc = 18;

            // Soft Drop Shadow
            g2d.setColor(new Color(0, 0, 0, 60));
            g2d.fillRoundRect(2, 4, getWidth() - 4, getHeight() - 4, arc, arc);

            // Gradient Fill
            GradientPaint btnGradient = new GradientPaint(
                    0, 0, BTN_START, 
                    getWidth(), 0, BTN_END
            );
            g2d.setPaint(btnGradient);
            g2d.fillRoundRect(0, 0, getWidth(), getHeight() - 2, arc, arc);

            // Hover Overlay Highlight
            if (hoverAlpha > 0) {
                g2d.setColor(HOVER_COLOR);
                g2d.setComposite(AlphaComposite.SrcOver.derive(hoverAlpha));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight() - 2, arc, arc);
                g2d.setComposite(AlphaComposite.SrcOver);
            }

            super.paintComponent(g);
        }
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

        this.view.addCounterListener(new CounterButtonListener());
    }

    private class CounterButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int newCount = model.incrementVisitorCount();
            view.displayVisitorCount(newCount);
        }
    }
}


// ==========================================
// 4. Main.java
// ==========================================
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> {
            CounterModel model = new CounterModel();
            CounterView view = new CounterView();
            new CounterController(model, view);

            view.setVisible(true);
        });
    }
}
