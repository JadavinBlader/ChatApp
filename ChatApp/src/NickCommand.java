public class NickCommand extends Command {

	private boolean busy;
	private String nick;
	private String version;

	public NickCommand(String version, String nick, boolean busy) {
		super(Command.CommandType.NICK);
		this.busy = busy;
		this.nick = nick;
		this.version = version;
	}

	public String toString() {
		return nick;
	}

}