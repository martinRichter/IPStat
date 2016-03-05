import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class SecurityHandler {
	private SecretKey key;
	private Cipher encCipher;
	private Cipher decCipher;

	public SecurityHandler(SecretKey key) {
		this.key = key;
		initCiphers();
	}


	/**
	 * Takes in a string and returns a sealed object, encrypted with the
	 * SecretKey key.
	 */
	public SealedObject seal(String s) {
		try {
			return new SealedObject(s, encCipher);
		} catch (IllegalBlockSizeException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/** Takes in a sealed object, unseals and decrypts it and returns a string. */
	public String unSeal(SealedObject so) {
		try {
			return (String) so.getObject(key);
		} catch (InvalidKeyException | ClassNotFoundException
				| NoSuchAlgorithmException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/** Method for initiating ciphers for encryption and decryption */
	private void initCiphers() {
		try {
			encCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			byte[] iv = new byte[encCipher.getBlockSize()];
			IvParameterSpec ivParams = new IvParameterSpec(iv);
			encCipher.init(Cipher.ENCRYPT_MODE, key, ivParams);
		} catch (InvalidKeyException | NoSuchAlgorithmException
				| NoSuchPaddingException | InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}

		try {
			decCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			byte[] iv = new byte[encCipher.getBlockSize()];
			IvParameterSpec ivParams = new IvParameterSpec(iv);
			decCipher.init(Cipher.DECRYPT_MODE, key, ivParams);
		} catch (InvalidKeyException | NoSuchAlgorithmException
				| NoSuchPaddingException | InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}

	}

	public String decryptString(String data) {
		String str = "";

		try {
			byte[] stringBytes = data.getBytes();
			byte[] decBytes = Base64.getDecoder().decode(stringBytes);
			byte[] bytes = decCipher.doFinal(decBytes);
			str = new String(bytes, "UTF-8");
		} catch (IllegalBlockSizeException | BadPaddingException
				| UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return str;
	}

	public String encryptString(String data) {
		String str = "";
		try {
			byte[] stringBytes = data.getBytes("UTF-8");
			byte[] bytes = encCipher.doFinal(stringBytes);
			byte[] encBytes = Base64.getEncoder().withoutPadding()
					.encode(bytes);
			str = new String(encBytes);
		} catch (IllegalBlockSizeException | BadPaddingException
				| UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}

}
