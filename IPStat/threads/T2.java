/**
 * @author Martin
 * Another kind of thread for pedagogical reasons. This one implementing Runnable.
 */
public class T2 implements Runnable {
	Thread t = new Thread(this);
	private boolean alive = true;
	private boolean active = true;

	/**
	 * Creates a new thread of T2 and runs it.
	 */
	public T2() {
		System.out.println("Creating and starting thread 2");
		t.start();
	}

	public void run() {
		while (alive) {
			while (active) {
				System.out.println("Thread 2");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(25);
			} catch (InterruptedException ie) {
			}

		}
	}

	/**
	 * Kills the thread, calls pause() so inner while loop is stopped.
	 */
	public void kill() {
		alive = false;
		pause();
	}

	/**
	 * Resuscitates the thread and unPauses it so the inner while loop is run as well.
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
