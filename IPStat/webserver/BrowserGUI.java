import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class BrowserGUI extends JFrame {
	private JPanel panel;
	private JTextField textField;
	private JTextArea textArea;
	private JScrollPane scroll;
	private BrowserConnection conn;

	/**
	 * Creates GUI.
	 */
	public BrowserGUI(BrowserConnection conn) {
		this.conn = conn;
		createGUI();
	}

	/**
	 * Retrieves input from TextField, requests webpage from connection and
	 * displays the result.
	 */
	private void buttonClicked() {
		displayInput(conn.connect(textField.getText()));
	}

	/** Method for displaying input from server in textArea */
	public void displayInput(String s) {
		textArea.setText(s);
	}

	/**
	 * Creates the GUI, adds action listener, textArea & textField adapts to
	 * window size.
	 */
	private void createGUI() {
		setSize(750, 450);

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
		textField.setText("http://people.dsv.su.se/pierre/home/index.cgi");
		textArea.setLineWrap(true);

		panel.add(textField, BorderLayout.PAGE_START);
		panel.add(scroll, BorderLayout.CENTER);

		this.add(panel);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
