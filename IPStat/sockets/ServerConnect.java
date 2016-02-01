import java.io.BufferedReader;
import java.io.IOException;

public class ServerConnect implements Runnable {
	Thread t = new Thread(this);
	private boolean alive = true;
	private BufferedReader in;

	public ServerConnect(BufferedReader in) {
		this.in = in;

		t.start();
	}
	
	public String getText() throws IOException{
			return in.readLine();
	}

	@Override
	public void run() {
		while (alive) {
		}
	}

	public void kill() {
		alive = false;
	}
	
	public boolean isAlive() {
		return alive;
	}

}
