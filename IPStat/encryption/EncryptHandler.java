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

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.util.Base64;

public class EncryptHandler {
	public static void main(String[] args) {
		if (args.length != 3) {
			System.out
					.println("Please provide data file, key name and name for encrypted data file");
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

	/**
	 * Takes in a string and a key, encrypts string and returns encrypted string
	 * in base64 format.
	 */
	private String encrypt(String data, SecretKey key) {
		Cipher cipher;
		String str = "";
		try {
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			byte[] iv = new byte[cipher.getBlockSize()];
			IvParameterSpec ivParams = new IvParameterSpec(iv);
			cipher.init(Cipher.ENCRYPT_MODE, key, ivParams);
			byte[] stringBytes = data.getBytes("UTF-8");
			byte[] bytes = cipher.doFinal(stringBytes);
			byte[] encBytes = Base64.getEncoder().withoutPadding()
					.encode(bytes);
			str = new String(encBytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
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

	/**
	 * Loads a file from fileName, reads the file and returns content as a
	 * string.
	 */
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
