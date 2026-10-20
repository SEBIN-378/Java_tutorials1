import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AWTCounter extends Frame implements ActionListener {
    private Label lblCounter;
    private TextField tfCounter;
    private Button btnCountUp;
    private Button btnCountDown;
    private Button btnReset;
    private int count = 0;

    public AWTCounter() {
        // Frame setup using FlowLayout
        setTitle("AWT Counter");
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        setSize(420, 130);
        setLocationRelativeTo(null);

        // Components
        lblCounter = new Label("Counter");
        tfCounter = new TextField(count + "", 8);
        tfCounter.setEditable(false); // Make display read-only

        btnCountUp = new Button("Count Up");
        btnCountDown = new Button("Count Down");
        btnReset = new Button("Reset");

        // Register action listeners
        btnCountUp.addActionListener(this);
        btnCountDown.addActionListener(this);
        btnReset.addActionListener(this);

        // Add components to the frame
        add(lblCounter);
        add(tfCounter);
        add(btnCountUp);
        add(btnCountDown);
        add(btnReset);

        // Window closing handler
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == btnCountUp) {
            count++;
        } else if (source == btnCountDown) {
            count--;
        } else if (source == btnReset) {
            count = 0;
        }

        tfCounter.setText(String.valueOf(count));
    }

    public static void main(String[] args) {
        new AWTCounter();
    }
}
