import javax.crypto.SecretKey;


public class Handler implements Runnable {
	Thread t = new Thread(this);
	private SecretKey secKey;
	private Connection conn;
	private ClientGUI GUI;
	private KeyGUI keyGUI;
	
	public Handler(){
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
	}

	private String encrypt(String s){
		// TODO
		return "";
	}
	
	private String decrypt(String s){
		// TODO
		return "";
	}
	
	public void display(String s){
		GUI.displayInput(decrypt(s));
		// TODO
	}
	
	public void send(String s){
		// TODO
	}
	
	private void selectKey(){
		// TODO
	}
}
