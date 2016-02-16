import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


public class KeyGUI {
	
	public KeyGUI (){
		
	}
	
	public SecretKey getKey(){
		return loadKey("key");
	}
	
	private SecretKey loadKey(String keyFile){
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
