
public class Command {

	protected CommandType type;

	public static enum CommandType {
		ACCEPT, DISCONNECT, MESSAGE, NICK, REJECT;
	}

	public Command(CommandType t) {
		type = t;
	}

	public String toString(){
		return type.toString();
	}

}