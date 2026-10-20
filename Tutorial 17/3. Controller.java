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
