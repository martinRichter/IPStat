import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class KeyGUI {
	private JFileChooser fileChooser;

	public KeyGUI() {
	}
	
	/**Method for getting key, calls chooseFile and retrieveKey*/
	public SecretKey getKey() {
		return retrieveKey(chooseFile());
	}

	/** Opens a JFileChoose to select key file. Returns file. */
	private File chooseFile() {
		fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("."));
		int result = fileChooser.showOpenDialog(fileChooser);
		if (result == JFileChooser.APPROVE_OPTION) {
			return fileChooser.getSelectedFile();
		} else {
			JOptionPane
					.showMessageDialog(null, "File not approved, try again.");
			return chooseFile();
		}
	}

	/**Takes in a file and reads the bytes to a key which it returns.*/
	private SecretKey retrieveKey(File keyFile) {
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
			JOptionPane.showMessageDialog(null, "File not found, try again.");
			return getKey();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"IOException reading key, try again.");
			return getKey();
		}
		byte[] keyBytes = baos.toByteArray();
		SecretKey secKey = new SecretKeySpec(keyBytes, "AES");
		return secKey;
	}
}
