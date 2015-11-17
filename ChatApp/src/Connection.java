import java.net.Socket;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;


public class Connection {

	private Socket socket;
	private String nick;
	private Scanner input;
	private PrintWriter output;
	
	public Connection (Socket s, String nick) throws IOException{
		this.socket = s;
		input = new Scanner(s.getInputStream(), "UTF-8");
		output = new PrintWriter(s.getOutputStream(), true);
		this.nick = nick;
	}
	
	public boolean isOpen(){
		return !socket.isClosed();
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
		output.print("Accepted\n");
	}
	
	public void reject(){
		output.print("Rejected\n");
	}
	
	public void disconnect(){
		
	}
	
	public void sendMessage(String msg){
		
	}
	
	public static void main(String[] args){
		
	}
}
