import javax.swing.JTextArea;

/**
 * @author Martin Another kind of thread for pedagogical reasons. This one
 *         implementing Runnable.
 */
public class ApT2 implements Runnable {
	private Thread t = new Thread(this);
	private JTextArea textArea;

	/**
	 * Creates a new thread of ApT2.
	 */
	public ApT2(JTextArea textArea) {
		this.textArea = textArea;
	}
	
	/**
	 * Starts the thread.
	 */
	public void startT() {
		t = new Thread(this);
		t.start();
	}

	/**
	 * Stops the thread.
	 */
	public void stopT() {
		t = null;
	}

	public void run() {
		while (t == Thread.currentThread()) {
			textArea.append("Thread 2 running\n");
			textArea.setCaretPosition(textArea.getText().length());
			try {
				t.sleep(1000);
			} catch (InterruptedException ie) {
			}
		}
	}
}
