
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.*;

public class KeyHandler {
	public static void main(String[] args) {
		if (args[0] == null) {
			System.out.println("Please provide key name");
			System.exit(0);
		}
		KeyHandler keyHandler = new KeyHandler();
		keyHandler.saveKey(keyHandler.generateKey(), args[0]);
	}

	private KeyGenerator keyGen;

	/** Creates KeyHandler */
	public KeyHandler() {
	}

	/** Generates the key and returns it */
	private SecretKey generateKey() {
		try {
			keyGen = KeyGenerator.getInstance("AES");
		} catch (NoSuchAlgorithmException e) {
			System.out.println("No such algorithm exists, please try again.");
			System.exit(0);
		}
		keyGen.init(128);
		SecretKey secKey = keyGen.generateKey();
		System.out.println(secKey.toString());
		return secKey;
	}

	/**
	 * Takes in a key and save it to file with name. Generates encoding for key
	 * and writes to file.
	 */
	private void saveKey(SecretKey key, String name) {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(name);
			fos.write(key.getEncoded());
		} catch (FileNotFoundException e) {
			System.out.println("Can't create outputstream");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Can't write key to file");
		}
	}
}