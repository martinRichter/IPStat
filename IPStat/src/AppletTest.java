import javax.swing.*;

import java.applet.*;
import java.awt.*;

/**
 * A class that creates the scrollPane and acts similair to ThreadRunner in task
 * 1.1
 */
public class AppletTest extends JApplet implements Runnable {
	private Thread thread = new Thread(this);
	private JTextArea textArea = new JTextArea();
	private ApT1 t1;
	private ApT2 t2;

	// metoden anropas av webbl�saren f�rsta g�ngen den ser �pplet
	public void init() {
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		thread.run();
	}

	public void run() {
		t1 = new ApT1(textArea);
		pause5();

		t2 = new ApT2(textArea);
		pause5();

		t2.pause();
		textArea.append("Thread 2 paused\n");
		textArea.setCaretPosition(textArea.getText().length());
		textArea.revalidate();
		pause5();

		t2.unPause();
		textArea.append("Thread 2 resumed\n");
		textArea.setCaretPosition(textArea.getText().length());
		textArea.revalidate();
		pause5();

		t1.kill();
		textArea.append("Killed thread 1\n");
		textArea.setCaretPosition(textArea.getText().length());
		textArea.revalidate();
		pause5();

		t2.kill();
		textArea.append("Killed thread 2\n");
		textArea.setCaretPosition(textArea.getText().length());
		textArea.revalidate();
	}

	/**
	 * Helper method that pauses the thread for 5 seconds
	 */
	public static void pause5() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException ie) {
			System.out.println("Sleep error");
		}
	}

	// metoden anropas av webbl�saren varje g�ng webbl�saren kommer tillbaka
	// till �pplet
	public void start() {

	}

	// metoden anropas av webbl�saren varje g�ng webbl�saren l�mnar �pplet och
	// g�r till en annan sida
	public void stop() {

	}

	// metoden anropas av webbl�saren n�r webbl�saren avslutas
	public void destroy() {

	}
}