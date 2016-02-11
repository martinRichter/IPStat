import java.io.File;

import javax.crypto.SecretKey;

public class EncryptHandler {
	private SecretKey secKey;
	/**
	 * Creates EncryptHandler, takes in data to encrypt, secret key and name for
	 * encrypted file.
	 */
	public EncryptHandler(File data, SecretKey secKey, String encData) {
		this.secKey = secKey;
	}

}
