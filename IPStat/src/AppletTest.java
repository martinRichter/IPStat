
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

	// metoden anropas av webbläsaren första gången den ser äpplet
	public void init() {
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		thread.run();
	}

	public void run() {
		textArea.append("test\n");
		//t1 = new ApT1(textArea);
		// //pause5();
		//
		// t2 = new ApT2(textArea);
		// //pause5();
		//
		// t2.pause();
		// textArea.append("Thread 2 paused\n");
		// //pause5();
		//
		// t2.unPause();
		// textArea.append("Thread 2 resumed\n");
		// //pause5();
		//
		// t1.kill();
		// textArea.append("Killed thread 1\n");
		// //pause5();
		//
		// t2.kill();
		// textArea.append("Killed thread 2\n");
	}

	// metoden anropas av webbläsaren varje gång webbläsaren kommer tillbaka
	// till äpplet
	public void start() {

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

	// metoden anropas av webbläsaren varje gång webbläsaren lämnar äpplet och
	// går till en annan sida
	public void stop() {

	}

	// metoden anropas av webbläsaren när webbläsaren avslutas
	public void destroy() {

	}
}