import java.net.Socket;


public class Connection {

	private Socket socket;
	
	public Connection (Socket s){
		this.socket = s;
	}
	
	public boolean isOpen(){
		return false;
	}
	
	public void close(){
		
	}
	
	public Command receive(){
		return null;
		
	}
	
	public void sendNickHello(String ver, String nick){
		
	}
	
	public void sendNickBusy(String ver, String nick){
		
	}
	
	public void accept(){
		
	}
	
	public void reject(){
		
	}
	
	public void disconnect(){
		
	}
	
	public void sendMessage(String msg){
		
	}
	
	public static void main(String[] args){
		
	}
}
