import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.PrintWriter;

import javax.swing.*;

public class clientGUIthread extends JFrame implements Runnable {
	private JTextField textField;
	private JTextArea textArea;
	private JScrollPane scroll;
	private boolean alive = true;
	private PrintWriter out;
	Thread t = new Thread(this);

	/**
	 * Creates GUI and starts running the thread.
	 */
	public clientGUIthread(PrintWriter out) {
		createGUI();
		this.out = out;
		t.start();
	}

	/**
	 * Method for retrieving input to TextField, sends to PrintWriter
	 */
	private void sendInput() {
		out.println(textField.getText());
	}

//	/**
//	 * Method for retrieving the input written, used when actionEvent. When
//	 * called, text field is reset as well
//	 */
//	public String retrieveInput() {
//		String str = textField.getText();
//		textField.setText("");
//		return str;
//	}

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
				sendInput();
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

	@Override
	public void run() {
		while (alive) {

		}
	}

	public void kill() {
		alive = false;
	}

}
