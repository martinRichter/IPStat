import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

/**
 * Class for handling the drawing, taken from course webpage. Small
 * modifications might occur.
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
	
	public void addPoint(Point p){
		paper.addPoint(p);
	}
}

class Paper extends JPanel {
	private HashSet hs = new HashSet();
	private ConnectionHandler connection;

	public Paper(ConnectionHandler connection) {
		this.connection = connection;
		setBackground(Color.white);
		addMouseListener(new L1());
		addMouseMotionListener(new L2());
	}

	public void paintComponent(Graphics g) {
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
	 * locally as well.
	 */
	private void sendPoint(Point p) {
		addPoint(p);
		connection.sendPoint(p);
	}

	public void addPoint(Point p) {
		hs.add(p);
		repaint();
	}

	class L1 extends MouseAdapter {
		public void mousePressed(MouseEvent me) {
			sendPoint(me.getPoint());
		}
	}

	class L2 extends MouseMotionAdapter {
		public void mouseDragged(MouseEvent me) {
			sendPoint(me.getPoint());
		}
	}
}