import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.*;

public class Browser extends JFrame {

	public static void main(String[] args) {
		new Browser();
	}

	private JPanel panel;
	private JTextField textField;
	private JScrollPane scroll;
	private JEditorPane jPane;

	/**
	 * Creates GUI and calls buttonClicked in order to load the default page.
	 */
	public Browser() {
		createGUI();
		buttonClicked();
	}

	/**
	 * Retrieves input from TextField, requests webpage from connection and
	 * displays the result. If an IOException occurs, calls connect in order to
	 * get text representation or error messages displayed.
	 */
	private void buttonClicked() {
		try {
			jPane.setPage(textField.getText());
		} catch (IOException e) {
			jPane.setText(connect(textField.getText()));
		}
	}

	// /** Method for displaying input from server in textArea, also scrolls to
	// top */
	// public void displayInput(String s) {
	// textArea.setText(s);
	// textArea.setCaretPosition(0);
	// }

	/**
	 * Tries to connect to URL provided and returns string. Adds result to
	 * string and adds error messages if exceptions are thrown.
	 */
	private String connect(String urlString) {
		StringBuffer buffer = new StringBuffer();
		String line = "";
		String text = "";
		URL url;
		try {
			url = new URL(urlString);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					url.openStream()));
			while ((line = br.readLine()) != null) {
				buffer.append(line + "\n");
			}
			text = buffer.toString();
		} catch (MalformedURLException e) {
			text += ("ERROR: MalformedURL");
		} catch (IOException e) {
			text += ("ERROR: IOException");
		}
		return text;
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
		jPane = new JEditorPane();
		JScrollPane scroll = new JScrollPane(jPane);

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

		panel.add(textField, BorderLayout.PAGE_START);
		panel.add(scroll, BorderLayout.CENTER);

		this.add(panel);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
