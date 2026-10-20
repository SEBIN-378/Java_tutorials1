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
