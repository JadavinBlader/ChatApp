package ChatApp.src;
import java.util.Observable;


public class CommandListenerThread extends Observable implements Runnable{

	Connection connect;
	Command lastCommand;
	boolean discon;
	
	public CommandListenerThread(){
		
	}
	
	public CommandListenerThread(Connection con){
		connect = con;
	}
	
	public void start(){
		
	}
	
	public void stop(){
		discon = true;
	}
	
	public void run(){
		
	}
	
	public Command getLastCommand(){
		return lastCommand;
	}
	
	public boolean isDisconnected(){
		return discon;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
