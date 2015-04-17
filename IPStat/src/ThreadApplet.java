import javax.swing.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A class that creates the scrollPane and acts similar to ThreadRunner in task
 * 1.1 but instead we can start/stop the threads with buttons.
 */
public class ThreadApplet extends JApplet {
	private JTextArea textArea = new JTextArea();
	private JButton b1 = new JButton("Start T1");
	private JButton b2 = new JButton("Start T2");
	private ApT1 t1;
	private ApT2 t2 = new ApT2(textArea);

	/**
	 * Called when the browser sees the apple for the first time. Creates hte
	 * GUI and adds listeners to the buttons.
	 */
	public void init() {
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		b1.addActionListener(new Listener());
		b2.addActionListener(new Listener());

		JPanel p = new JPanel();
		p.setLayout(new FlowLayout());
		p.add(b1);
		p.add(b2);
		getContentPane().add("Center", scrollPane);
		getContentPane().add("South", p);

		setSize(300, 200);
	}
	
	/**Listens for clicks on the buttons and starts/stops the threads*/
	private class Listener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			if (ae.getSource() == b1) {
				if (b1.getText().equals("Start T1")) {
					t1 = new ApT1(textArea);
					t1.startT();
					b1.setText("Stop T1");
				} else {
					t1.stopT();
					t1 = null;
					b1.setText("Start T1");
				}
			} else {
				if (b2.getText().equals("Start T2")) {
					t2.startT();
					b2.setText("Stop T2");
				} else {
					t2.stopT();
					b2.setText("Start T2");
				}
			}
		}
	}
}