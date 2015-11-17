package ChatApp.src;

import java.lang.invoke.CallSite;
import java.net.SocketAddress;


public class Caller {
	private String Lnick;
	private String Rnick;
	private String ip;
	private SocketAddress remoteAddress;
	public Caller(){
		
	}
	public Caller(String Lnick){
		
	}
	public Caller(String Lnick,
		      SocketAddress remoteAddress){
		
	}
	public Caller(String Lnick,
		      String ip){
		
	}
	public Connection call()
            throws java.io.IOException{
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
	public CallSite getStatus(){
		return null;		
	}
	public void setLocalNick(String Lnick){
		
	}
	public void setRemoteAddress(SocketAddress remoteAddress){		
		
	}
	public String toString(){
		return Lnick;
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
	
	
	
