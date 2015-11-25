package ChatApp.src;

import java.io.IOException;
import java.net.SocketAddress;
import java.util.Observable;

public class CallListenerThread extends Observable implements Runnable {
	private CallListener callListener;
	private Connection last;
	private boolean sleep;
	private boolean pause;

	public CallListenerThread() throws IOException {
		callListener = new CallListener();
	}

	public CallListenerThread(String Lnick) throws IOException {
		callListener = new CallListener(Lnick);
	}

	public CallListenerThread(String Lnick, String localIp) throws IOException {
		callListener = new CallListener(Lnick, localIp);
	}

	public void start() {
		run();
	}

	public void stop() {
		pause = true;
	}

	public void run() {
		while (!pause) {

			try {
				Connection check;
				check = callListener.getConnection();
				if (check != null) {
					last = check;
					notifyObservers();

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean isBusy() {
		return callListener.isBusy();

	}

	public SocketAddress getListenAddress() {
		return callListener.getListenAddress();

	}

	public String getRemoteNick() {
		return callListener.getRemoteNick();

	}

	public SocketAddress getRemoteAddress() {
		return callListener.getRemoteAddress();
	}

	public void setLocalNick(String Lnick) {
		callListener.setLocalNick(Lnick);
	}

	public void setBusy(boolean busy) {

	}

	public void setListenAddress(java.net.SocketAddress listenAddress) {
		callListener.setListenAddress(listenAddress);
	}

	public String getLocalNick() {
		return callListener.getLocalNick();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
