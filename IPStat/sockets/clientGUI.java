import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class clientGUI extends JFrame {
	private JTextField textField = new JTextField(25);
	private JTextArea textArea = new JTextArea(14, 25);

	public clientGUI() {
		initGUI();
		//waitForText();
	}

	private void initGUI() {
		setTitle("Client");
		setSize(300, 300);

		this.getContentPane().setLayout(new FlowLayout());
		this.getContentPane().add(textField);

		/**
		 * Action listener that listens for click and then calls for method to
		 * retrieve keyboard input and clears textField.
		 */
		Action action = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//catchInput();
				displayInput(fetchKeyInput()); //for testing GUI
				textField.setText("");
			}
		};
		textField.addActionListener(action);

		textArea.setLineWrap(true);
		this.getContentPane().add(textArea);
	}

	/**
	 * Method for retrieving input to TextField, return string
	 */
	private String fetchKeyInput() {
		return textField.getText();

	}

	/** Method for displaying input from server in textArea */
	private void displayInput(String s) {
		textArea.append(s + "\n");
		textArea.validate();
	}

}
