import java.io.ObjectOutputStream;
import java.security.NoSuchAlgorithmException;

import javax.crypto.*;

public class KeyHandler {
	private KeyGenerator keyGen;
	private String keyName;

	/** Creates KeyHandler, takes in a string to name secret key. */
	public KeyHandler(String keyName) {
		this.keyName = keyName;
	}

	/** Generates the key and returns it */
	private SecretKey generateKey() {
		try {
			keyGen = KeyGenerator.getInstance("AES");
		} catch (NoSuchAlgorithmException e) {
			System.out.println("No such algorithm exists, please try again.");
			System.exit(0);
		}
		keyGen.init(256);
		return keyGen.generateKey();
	}
}
