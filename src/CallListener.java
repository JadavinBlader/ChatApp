
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;

public class CallListener {
	private String Lnick;
	private String Rnick;
	private String localIp;
	private ServerSocket sSocket;
	private SocketAddress Address, rAddress;

	public CallListener() throws IOException {
		this("noNick", null);
	}

	public CallListener(String Lnick) throws IOException {
		this(Lnick, null);
	}

	public CallListener(String Lnick, String Lip) throws IOException {
		sSocket = new ServerSocket(28411);
		if (localIp != null)
			sSocket.bind(new InetSocketAddress(localIp, 28411));
		this.Lnick = Lnick;
		this.Address = sSocket.getLocalSocketAddress();
	}

	public Connection getConnection() throws java.io.IOException {
		return new Connection(sSocket.accept(), Lnick);

	}

	public String getLocalNick() {
		return Lnick;

	}

	public boolean isBusy() {
		return false;

	}

	public SocketAddress getListenAddress() {
		return Address;

	}

	public String getRemoteNick() {
		return Rnick;

	}

	public SocketAddress getRemoteAddress() {
		return rAddress;

	}

	public void setLocalNick(String Lnick) {
		this.Lnick = Lnick;
	}

	public void setBusy(boolean busy) {

	}

	public void setListenAddress(SocketAddress Address) {
		this.Address = Address;
	}

	/*
	 * public String toString(){ return Lnick ;
	 * 
	 * }
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
