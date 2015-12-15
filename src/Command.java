
public class Command {

	protected CommandType type;

	public static enum CommandType {
		ACCEPT{
			public String toString(){
				return "Accepted";
			}
		}, DISCONNECT, MESSAGE, NICK, REJECT{
			public String toString(){
				return "Rejected";
			}
		};
	}

	public Command(CommandType t) {
		type = t;
	}

	public String toString(){
		return type.toString();
	}

}