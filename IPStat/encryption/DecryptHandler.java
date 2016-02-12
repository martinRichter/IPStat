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

public class DecryptHandler {
	public static void main(String[] args) {
		if (args[0] == null) {
			System.out.println("Please provide key name");
			System.exit(0);
		}
		// TODO read args[0], for encData file
		// TODO read args[1], for key file
		// TODO read args[2], for decData name
	}

	/**
	 * Creates DecryptHandler.
	 */
	public DecryptHandler() {
	}

	
	/** Takes in a string and a key, encrypts string and returns encrypted string */
	private String decrypt(String data, SecretKey key) {
		Cipher cipher;
		String str ="";
		try {
			cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] stringBytes = data.getBytes("UTF8");
			byte[] bytes = cipher.doFinal(stringBytes);
			str = new String(bytes, "UTF-8");
		} catch (NoSuchAlgorithmException e) {
			System.out.println("No such algorithm for decryption");
			System.exit(0);
		} catch (NoSuchPaddingException e) {
			System.out.println("No such padding for decryption");
			System.exit(0);
		} catch (InvalidKeyException e) {
			System.out.println("Invalid key for decryption");
			System.exit(0);
		} catch (UnsupportedEncodingException e) {
			System.out.println("Unsuported padding for decryption");
			System.exit(0);
		} catch (IllegalBlockSizeException e) {
			System.out.println("Illegal block size for decryption");
			System.exit(0);
		} catch (BadPaddingException e) {
			System.out.println("Bad padding for decryption");
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
	
	/**Takes in name for key file and decodes it, returns a SecretKey*/
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
