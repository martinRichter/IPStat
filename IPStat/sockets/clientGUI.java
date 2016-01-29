import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class clientGUI extends JFrame {
	private JTextField textField;
	private JTextArea textArea;
	private JScrollPane scroll;
	private Handler handler;

	public clientGUI(Handler handler) {
		
		this.handler = handler;
		
		setTitle("Client");
		setSize(300, 300);
		textField = new JTextField(25);
		textArea = new JTextArea(14, 23);

		this.getContentPane().setLayout(new FlowLayout());
		this.getContentPane().add(textField);

		/**
		 * Action listener that listens for click and then calls for method to
		 * retrieve keyboard input and clears textField.
		 */
		Action action = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// catchInput();
				displayInput(fetchKeyInput()); // for testing GUI
				textField.setText("");
			}
		};
		textField.addActionListener(action);

		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);

		scroll = new JScrollPane(textArea);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		this.add(scroll);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
	
	/** Method for setting windows title*/
	public void setWindowTitle(String s){
		setTitle(s);
	}

}
