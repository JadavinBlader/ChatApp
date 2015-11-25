

import java.lang.invoke.CallSite;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;


public class Caller {
	private String Lnick;
	private String Rnick;
	private String ip;
	private CallStatus s;
	private SocketAddress remoteAddress;
	
	public static enum CallStatus {
		 OK, REJECTED, BUSY, NO_SERVICE, NOT_ACCESSIBLE
	}
	public Caller(){
		this("noNick", new InetSocketAddress("127.0.0.1", 28411));
	}
	public Caller(String Lnick){
		this(Lnick, new InetSocketAddress("127.0.0.1", 28411));
	}
	public Caller(String Lnick,
		      SocketAddress remoteAddress){
		this.remoteAddress=remoteAddress;
		this.Lnick=Lnick;
	}
	public Caller(String Lnick,
		      String ip){
		this(Lnick, new InetSocketAddress(ip, 28411));
	}
	public Connection call()
            throws java.io.IOException{
		Socket socket = new Socket();
		socket.connect(remoteAddress);
		Connection outputCon = new Connection(socket, Lnick);
		outputCon.sendNickHello("2015", Lnick);
		Command Com;
		String str;
		if (outputCon.receive().toString() == Command.CommandType.NICK.toString()){
	        Rnick = outputCon.receive();
		  str = Rnick.toUpperCase();
		  Rnick = Rnick.substring(str.indexOf(" USER "));
		     if (Rnick.isEmpty())
			   outputCon.close();
		}
				return null;
		
	}
	public String getLocalNick(){
		return Lnick;
		
	}
	public SocketAddress getRemoteAddress(){
		return remoteAddress;		
	}
	public String getRemoteNick(){
		return Rnick;
		
	}
	public CallStatus getStatus(){
		return s;		
	}
	public void setLocalNick(String Lnick){
		this.Lnick = Lnick;
	}
	public void setRemoteAddress(SocketAddress remoteAddress){		
		this.remoteAddress = remoteAddress;
	}
/*	public String toString(){
		return s.toString();
		
	}
 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
	
	
	
