import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

/**
 * Class for handling the drawing, taken from course webpage. Changes are
 * commented.
 */
public class Draw extends JFrame {
	private Paper paper;

	public Draw(ConnectionHandler connection) {
		paper = new Paper(connection);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().add(paper, BorderLayout.CENTER);

		setSize(640, 480);
		setVisible(true);
	}

	/**Added method so it can pass on the point to the Paper class.*/
	public void addPoint(Point p) {
		paper.addPoint(p);
	}
}

class Paper extends JPanel {
	private HashSet hs = new HashSet();
	private ConnectionHandler connection;

	/**Added a ConnectionHandler to the class so it can send points to socket.*/
	public Paper(ConnectionHandler connection) {
		this.connection = connection;
		setBackground(Color.white);
		addMouseListener(new L1());
		addMouseMotionListener(new L2());
	}

	/**
	 * Made it synchronized so iterator doesn't iterator over it while addPoint
	 * is adding new elements.
	 */
	public synchronized void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);
		Iterator i = hs.iterator();
		while (i.hasNext()) {
			Point p = (Point) i.next();
			g.fillOval(p.x, p.y, 2, 2);
		}
	}

	/**
	 * Method for sending point to socket, calls addPoint so point is drawn
	 * locally as well. Added this method.
	 */
	private void sendPoint(Point p) {
		addPoint(p);
		connection.sendPoint(p);
	}

	/**
	 * Made it synchronized so it is not adding points to hashSet while iterator
	 * is iterating over it.
	 */
	public synchronized void addPoint(Point p) {
		hs.add(p);
		repaint();
	}

	/**
	 * Instead of calling addPoint it calls sendPoint that both sends in over
	 * socket and draws it locally.
	 */
	class L1 extends MouseAdapter {
		public void mousePressed(MouseEvent me) {
			sendPoint(me.getPoint());
		}
	}

	/**
	 * Instead of calling addPoint it calls sendPoint that both sends in over
	 * socket and draws it locally.
	 */
	class L2 extends MouseMotionAdapter {
		public void mouseDragged(MouseEvent me) {
			sendPoint(me.getPoint());
		}
	}
}