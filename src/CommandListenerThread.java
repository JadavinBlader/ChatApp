import java.util.Observable;

public class CommandListenerThread extends Observable implements Runnable {

	private boolean disconnect;
	private Connection connection;
	Command LastCommand = new Command(null);

	public CommandListenerThread() {

	}

	public CommandListenerThread(Connection connect) {
		this.disconnect = false;
		this.connection = connect;

	}

	public void start() {
		Thread thr = new Thread(this);
		thr.start();
	}

	public void stop() {
		disconnect = true;
	}

	public void run() {
		while (!disconnect) {

			Command tmp = connection.receive();
			if (LastCommand != null) {
				if ((LastCommand.type == (Command.CommandType.DISCONNECT)) || (LastCommand.type.toString().equals("Rejected"))) {
					disconnect = true;
				}
			}

			this.setChanged();
			this.notifyObservers();

		}
	}

	public Command getLastCommand() {
		return LastCommand;
	}

	public boolean isDisconnected() {
		return disconnect;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
