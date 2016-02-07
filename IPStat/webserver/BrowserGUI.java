import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class BrowserGUI extends JFrame {
	private JPanel panel;
	private JTextField textField;
	private JTextArea textArea;
	private JScrollPane scroll;

	/**
	 * Creates GUI.
	 */
	public BrowserGUI() {
		// TODO
		createGUI();
	}

	/**
	 * Retrieves input from TextField & requests web page.
	 */
	private void buttonClicked() {
		// TODO textField.getText();
	}

	/** Method for displaying input from server in textArea */
	public void displayInput(String s) {
		// TODO
	}

	/** Method for setting window title */
	public void setWindowTitle(String host, String port) {
		// TODO
		// setTitle("");
	}

	private void createGUI() {
		setSize(300, 300);

		// BorderLayout so the fields adapt to window size
		panel = new JPanel();
		panel.setLayout(new BorderLayout());

		textField = new JTextField();
		textArea = new JTextArea();
		JScrollPane scroll = new JScrollPane(textArea);

		/**
		 * Action listener that listens for click and calls buttonClicked().
		 */
		Action action = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buttonClicked();
			}
		};
		textField.addActionListener(action);

		textArea.setLineWrap(true);

		panel.add(textField, BorderLayout.PAGE_START);
		panel.add(scroll, BorderLayout.CENTER);

		this.add(panel);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
