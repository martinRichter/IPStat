import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.PrintWriter;

import javax.swing.*;
import javax.swing.text.DefaultCaret;

public class ClientGUI extends JFrame {
	private JPanel panel;
	private JTextField textField;
	private JTextArea textArea;
	private JScrollPane scroll;
	private PrintWriter out;

	/**
	 * Creates GUI, takes in a PrintWriter.
	 */
	public ClientGUI(PrintWriter out) {
		createGUI();
		this.out = out;
	}

	/**
	 * Retrieves input to TextField & sends to PrintWriter.
	 */
	private void buttonClicked() {
		out.println(textField.getText());
		textField.setText("");
	}

	/** Method for displaying input from server in textArea */
	public void displayInput(String s) {
		textArea.append(s + "\n");
		textArea.validate();
	}

	/** Method for setting windows title */
	public void setWindowTitle(String s) {
		setTitle(s);
	}

	private void createGUI() {
		setSize(300, 300);

		//BorderLayout so the fields adapt to window size
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
		
		//To make the textArea go to the bottom
		DefaultCaret caret = (DefaultCaret)textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		panel.add(textField, BorderLayout.PAGE_START);
		panel.add(scroll, BorderLayout.CENTER);

		this.add(panel);

		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
