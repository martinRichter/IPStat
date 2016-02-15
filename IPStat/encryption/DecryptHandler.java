import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class DecryptHandler {
	public static void main(String[] args) {
		if (args[0] == null) {
			System.out.println("Please provide key name");
			System.exit(0);
		}
		DecryptHandler decH = new DecryptHandler();
		String data = decH.loadFile(args[0]); // loads data to be decrypted to a
												// string
		SecretKey secKey = decH.loadKey(args[1]); // loads key
		String decData = decH.decrypt(data, secKey); // decrypts data using key.
		decH.saveFile(decData, args[2]); // saves decrypted data to file.
	}

	/**
	 * Creates DecryptHandler.
	 */
	public DecryptHandler() {
	}

	/**
	 * Takes in a string and a key, decrypts string and base64 decodes it and
	 * returns it.
	 */
	private String decrypt(String data, SecretKey key) {
		Cipher cipher;
		String str = "";
		try {
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			byte[] iv = new byte[cipher.getBlockSize()];
			IvParameterSpec ivParams = new IvParameterSpec(iv);
			cipher.init(Cipher.DECRYPT_MODE, key, ivParams);
			byte[] stringBytes = data.getBytes();
			byte[] decBytes =  Base64.getDecoder().decode(stringBytes);
			byte[] bytes = cipher.doFinal(decBytes);
			str = new String(bytes, "UTF-8");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (BadPaddingException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			System.exit(0);
		}
		return str;
	}

	/** Takes in a string and fileName and prints to to file with fileName */
	private void saveFile(String file, String fileName) {
		try (PrintWriter out = new PrintWriter(fileName)) {
			out.print(file);
			out.close();
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

	/** Takes in name for key file and returns a SecretKey */
	private SecretKey loadKey(String keyFile) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		FileInputStream fin;
		int i = 0;
		try {
			fin = new FileInputStream(keyFile);
			while ((i = fin.read()) != -1) {
				baos.write(i);
			}
			fin.close();
			baos.close();
		} catch (FileNotFoundException e) {
			System.out.println("Keyfile not found.");
			System.exit(0);
		} catch (IOException e) {
			System.out.println("IOException reading keybyte");
			System.exit(0);
		}
		byte[] keyBytes = baos.toByteArray();
		SecretKey secKey = new SecretKeySpec(keyBytes, "AES");
		return secKey;
	}
}
