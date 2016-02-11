import java.io.File;
import javax.crypto.SecretKey;

public class DecryptHandler {
	private SecretKey secKey;

	/**
	 * Creates DecryptHandler, takes in encrypted data, secret key and a string
	 * to name decrypted data file.
	 */
	public DecryptHandler(File encData, SecretKey secKey, String decData) {
		this.secKey = secKey;
	}
}
