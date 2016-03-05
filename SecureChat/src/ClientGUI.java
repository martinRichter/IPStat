import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.text.DefaultCaret;

public class ClientGUI extends JFrame {
	private JPanel panel;
	private JTextField textField;
	private JTextArea textArea;
	private JScrollPane scroll;
	private Handler handler;

	/**
	 * Creates GUI, takes in a handler.
	 */
	public ClientGUI(Handler handler) {
		createGUI();
		this.handler = handler;
	}

	/**
	 * Retrieves input to TextField & sends to handler.
	 */
	private void buttonClicked() {
		if (textField.getText().length() != 0) {
			handler.send(textField.getText());
			textField.setText("");
		}
	}

	/** Method for displaying input from server in textArea */
	public void displayInput(String s) {
		textArea.append(s + "\n");
		textArea.validate();
	}

	/**
	 * Creates GUI, uses BorderLayout so the fields adapts to window size. Adds
	 * actionListener to textField and uses caret to make the textArea go to the
	 * bottom when new text is displayed.
	 */
	private void createGUI() {
		setSize(300, 300);

		panel = new JPanel();
		panel.setLayout(new BorderLayout());

		textField = new JTextField();
		textArea = new JTextArea();
		scroll = new JScrollPane(textArea);

		Action action = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				buttonClicked();
			}
		};
		textField.addActionListener(action);

		textArea.setLineWrap(true);

		DefaultCaret caret = (DefaultCaret) textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		panel.add(textField, BorderLayout.PAGE_START);
		panel.add(scroll, BorderLayout.CENTER);

		this.add(panel);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
