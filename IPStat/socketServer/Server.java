public class Server {
	public static void main(String[] args) {
		int port;

		if (args.length == 0) {
			port = 2000;
		} else {
			port = Integer.parseInt(args[0]);
		}
	}
}
