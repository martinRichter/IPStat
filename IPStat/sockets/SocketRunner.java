import java.net.UnknownHostException;


public class SocketRunner {

	public static void main(String[] args) {
		try {
			Client client = new Client();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
