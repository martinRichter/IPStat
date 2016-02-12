import java.io.File;

import javax.crypto.SecretKey;

public class EncryptHandler {
	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("Please provide data file, key name and name for encrypted data file");
			System.exit(0);
		}
		//TODO read args[0], for data file
		//TODO read args[1], for key file
		//TODO read args[2], for encData name
	}

	private SecretKey secKey;

	/**
	 * Creates EncryptHandler, takes in data to encrypt, secret key and name for
	 * encrypted file.
	 */
	public EncryptHandler(File data, SecretKey secKey, String encData) {
		this.secKey = secKey;
	}

}
