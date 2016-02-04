import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.PrintWriter;

import javax.swing.*;
import javax.swing.text.DefaultCaret;

public class ServerGUI extends JFrame {
	private JPanel panel;
	private JTextArea textArea;
	private JScrollPane scroll;

	/**
	 * Creates GUI
	 */
	public ServerGUI() {
		createGUI();
	}


	/** Method for displaying text in textArea */
	public void display(String s) {
		textArea.append(s + "\n");
		textArea.validate();
	}

	/** Method for setting window title */
	public void updateWindowTitle(String s) {
		setTitle(s);
	}

	private void createGUI() {
		setSize(300, 300);

		//BorderLayout so the fields adapt to window size
		panel = new JPanel();
		panel.setLayout(new BorderLayout());

		textArea = new JTextArea();
		textArea.setLineWrap(true);
		JScrollPane scroll = new JScrollPane(textArea);
		
		//To make the textArea go to the bottom
		DefaultCaret caret = (DefaultCaret)textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		panel.add(scroll, BorderLayout.CENTER);

		this.add(panel);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
