import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class EncryptHandler {
	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("Please provide data file, key name and name for encrypted data file");
			System.exit(0);
		}
		EncryptHandler encH = new EncryptHandler();
		String data = encH.loadFile(args[0]); // loads data to be encrypted to a
												// string
		SecretKey secKey = encH.loadKey(args[1]); // loads key
		String encData = encH.encrypt(data, secKey); // encrypts data using key.
		encH.saveFile(encData, args[2]); // saves encrypted data to file.
	}

	/**
	 * Creates EncryptHandler.
	 */
	public EncryptHandler() {
	}

	/** Takes in a string and a key, encrypts string and returns encrypted string */
	private String encrypt(String data, SecretKey key) {
		Cipher cipher;
		String str ="";
		try {
			cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] stringBytes = data.getBytes("UTF8");
			byte[] bytes = cipher.doFinal(stringBytes);
			str = new String(bytes, "UTF-8");
		} catch (NoSuchAlgorithmException e) {
			System.out.println("No such algorithm for encryption");
			System.exit(0);
		} catch (NoSuchPaddingException e) {
			System.out.println("No such padding for encryption");
			System.exit(0);
		} catch (InvalidKeyException e) {
			System.out.println("Invalid key for encryption");
			System.exit(0);
		} catch (UnsupportedEncodingException e) {
			System.out.println("Unsuported padding for encryption");
			System.exit(0);
		} catch (IllegalBlockSizeException e) {
			System.out.println("Illegal block size for encryption");
			System.exit(0);
		} catch (BadPaddingException e) {
			System.out.println("Bad padding for encryption");
			System.exit(0);
		}
		return str;
	}

	/** Takes in a string and fileName and prints to to file with fileName */
	private void saveFile(String file, String fileName) {
		try(PrintWriter out = new PrintWriter(fileName)) {
		    out.println( file );
		} catch (FileNotFoundException e) {
			System.out.println("File not found when saving file");
			System.exit(0);
		}
	}

	/** Loads a file from fileName and returns as a string. */
	private String loadFile(String fileName) {
		String stringFile = "";
		try {
			stringFile = new String(Files.readAllBytes(Paths.get(fileName)));
		} catch (IOException e) {
			System.out.println("IOException reading file");
			System.exit(0);
		}
		return stringFile;
	}

	/** Takes in name for key file and decodes it, returns a SecretKey */
	private SecretKey loadKey(String keyFile) {
		byte[] keybyte = new byte[16];
		FileInputStream fin;
		try {
			fin = new FileInputStream(keyFile);
			fin.read(keybyte);
		} catch (FileNotFoundException e) {
			System.out.println("Keyfile not found.");
			System.exit(0);
		} catch (IOException e) {
			System.out.println("IOException reading keybyte");
			System.exit(0);
		}
		SecretKey secKey = new SecretKeySpec(keybyte, 0, 16, "AES");
		return secKey;
	}
}
