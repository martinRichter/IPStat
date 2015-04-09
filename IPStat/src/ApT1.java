import javax.swing.JTextArea;

/**
 * @author Martin A class representing a thread doing this by extending
 *         java.Lang.Thread
 */
public class ApT1 extends Thread {
	private JTextArea textArea;
	private boolean alive = true;
	private boolean active = true;

	/**
	 * Creates a new thread of T1 and runs it.
	 */
	public ApT1(JTextArea textArea) {
		this.textArea = textArea;
		textArea.append("Creating and starting thread 1\n");
		run();
	}

	public void run() {
		textArea.append("Thread 1\n");
//		while (alive) {
//			while (active) {
//				textArea.append("Thread 1\n");
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					System.out.println("Sleep didn't work in ApT1");
//				}
//			}
//			try {
//				Thread.sleep(25);
//			} catch (InterruptedException ie) {
//			}
//
//		}
	}

	/**
	 * Kills the thread, calls pause() so inner while loop is stopped.
	 */
	public void kill() {
		alive = false;
		pause();
	}

	/**
	 * Resuscitates the thread and unPauses it so the inner while loop is run as
	 * well.
	 */
	public void wake() {
		alive = true;
		unPause();
	}

	/**
	 * Pauses the thread by stopping the inner while loop.
	 */
	public void pause() {
		active = false;
	}

	/**
	 * Resumes the thread by starting the inner while loop.
	 */
	public void unPause() {
		active = true;
	}

}
