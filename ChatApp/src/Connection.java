import java.net.Socket;
import java.util.Scanner;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;


public class Connection {

	private Socket socket;
	private String nick;
	private Scanner input;
	private PrintWriter output;
	
	public Connection (Socket s, String nick) throws IOException{
		this.socket = s;
		input = new Scanner(s.getInputStream(), "UTF-8");
		OutputStreamWriter ou = new OutputStreamWriter(s.getOutputStream(), "UTF-8");
		output = new PrintWriter(ou);
		this.nick = nick;
	}
	
	public boolean isOpen(){
		return !socket.isClosed();
	}
	
	public void close() throws IOException{
		socket.close();
	}
	
	public Command receive(){
		String str = input.nextLine();
		if (str.toLowerCase().startsWith("chatapp 2015 user")){
			Scanner inStr = new Scanner(str);
			inStr.next();
			NickCommand j = new NickCommand(inStr.next(), inStr.skip(" [a-z,A-Z]{4} ").next(), str.toLowerCase().endsWith("busy"));//спросить
			return j;
		}else if (str.toLowerCase().equals("message")){
			MessageCommand ms = new MessageCommand(input.nextLine());
			return ms;
		}
		return null;
		
	}
	
	public void sendNickHello(String ver, String nick){
		output.print("ChatApp 2015 user"+nick+"\n");
		output.flush();
	}
	
	public void sendNickBusy(String ver, String nick){
		output.print("ChatApp 2015 user"+nick+"busy\n");
		output.flush();
	}
	
	public void accept(){
		output.print("Accepted\n");
		output.flush();
	}
	
	public void reject(){
		output.print("Rejected\n");
		output.flush();
	}
	
	public void disconnect() throws IOException{
		output.print("Disconnect\n");
		output.flush();
		close();
	}
	
	public void sendMessage(String msg){
		output.print("Message\n");
		output.println(msg+"\n");
		output.flush();
	}
	
	public static void main(String[] args){
		
	}
}
