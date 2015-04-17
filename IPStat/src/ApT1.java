import javax.swing.JTextArea;

/**
 * @author Martin A class representing a thread doing this by extending
 *         java.Lang.Thread
 */
public class ApT1 extends Thread {
	private JTextArea textArea;
	private boolean loopIt = false;

	/**
	 * Creates a new thread of ApT1.
	 */
	public ApT1(JTextArea textArea) {
		this.textArea = textArea;
	}

	/**
	 * Starts the thread.
	 */
	public void startT() {
		loopIt = true;
		start();
	}

	/**
	 * Stops the thread.
	 */
	public void stopT() {
		loopIt = false;
	}

	public void run() {
		while (loopIt) {
			textArea.append("Thread 1 running\n");
			textArea.setCaretPosition(textArea.getText().length());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
	}

}
