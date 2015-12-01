import java.net.ServerSocket;
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

	public Connection(Socket s, String nick) throws IOException {
		this.socket = s;
		input = new Scanner(s.getInputStream(), "UTF-8");
		OutputStreamWriter ou = new OutputStreamWriter(s.getOutputStream(),
				"UTF-8");
		output = new PrintWriter(ou);
		this.nick = nick;
	}

	public boolean isOpen() {
		return !socket.isClosed();
	}

	public void close() throws IOException {
		socket.close();
	}

	public Command receive() {
		String str = input.nextLine();
		if (str.toLowerCase().startsWith("chatapp 2015 user")) {
			Scanner inStr = new Scanner(str);
			inStr.next();
			NickCommand j = new NickCommand(inStr.next(), inStr.skip(" [a-z,A-Z]{4} ").next(), str.toLowerCase().endsWith("busy"));
			return j;
		} else if (str.toLowerCase().equals("message")) {
			MessageCommand ms = new MessageCommand(input.nextLine());
			return ms;
		} else {
 			str = str.toUpperCase().replaceAll("[\r\n]","");
 			for (Command.CommandType ct : Command.CommandType.values())
 				if (ct.toString().equals(str))
 					return new Command(Command.CommandType.valueOf(str.replaceAll("ED", "")));
 		}
		return null;
	}

	public void sendNickHello(String ver, String nick) {
		output.print("ChatApp 2015 user" + nick + "\n");
		output.flush();
	}

	public void sendNickBusy(String ver, String nick) {
		output.print("ChatApp 2015 user" + nick + "busy\n");
		output.flush();
	}

	public void accept() {
		output.print("Accepted\n");
		output.flush();
	}

	public void reject() {
		output.print("Rejected\n");
		output.flush();
	}

	public void disconnect() throws IOException {
		output.print("Disconnect\n");
		output.flush();
		close();
	}

	public void sendMessage(String msg) {
		output.print("Message\n");
		output.println(msg + "\n");
		output.flush();
	}

	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(28411);
		Socket s = ss.accept();
		Connection c = new Connection(s, "max");

		Command cc = c.receive();
		System.out.printf("%s : %s\n", cc.getClass(), cc);
		cc = c.receive();
		System.out.printf("%s : %s\n", cc.getClass(), cc);
	}
}
