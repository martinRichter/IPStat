import javax.crypto.SecretKey;


public class ChatRunner {

	public static void main(String[] args) {
		KeyGUI keyGUI = new KeyGUI();
		SecurityHandler secH = new SecurityHandler (keyGUI.getKey());
	}

}
