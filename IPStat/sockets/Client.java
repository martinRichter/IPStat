import java.net.InetAddress;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) {
		try {
			// Client client = new Client(InetAddress.getByName("atlas.dsv.su.se"), 9494);
			ClientRunner client = new ClientRunner();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
