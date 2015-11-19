
public class MessageCommand extends Command{

	public String message;
	
	public MessageCommand(String message){
		super(Command.CommandType.MESSAGE);
		this.message = message;
	}
	
	public String toString(){
		return message;
	}

}
