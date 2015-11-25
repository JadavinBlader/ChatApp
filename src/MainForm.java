import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.io.*;

public class MainForm{
	private JFrame frame;
	private JButton connectButton;
	private JButton disconnectButton;
	private static final int DEFAULT_WIDTH = 700;
	private static final int DEFAULT_HEIGHT = 500;
	
  


	public static void main(String[] args) throws java.io.IOException{
		EventQueue.invokeLater(new Runnable() {
			public void run (){
				MainFrame frame = new MainFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				//frame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		        //frame.setIconImage(icon);
				//frame.setBounds(150, 150, DEFAULT_WIDTH, DEFAULT_HEIGHT);
				//frame.setLocationByPlatform(true);
				frame.setTitle("ChatApp");
				frame.setVisible(true);
				frame.setResizable(false);
			}
		});	
	}

}
 class MainFrame extends JFrame{


	public MainFrame(){
	 Toolkit kit = Toolkit.getDefaultToolkit();
	 Dimension screenSize = kit.getScreenSize();
	 int screenHeight = screenSize.height;
	 int screenWidth = screenSize.width;
	 setSize(screenWidth/2, screenHeight/2);
	 setLocationByPlatform(true);
	 Image img = new ImageIcon("ChatApp.png").getImage();
	 setIconImage(img);
	 }
	 
 }
 
class MyComponent extends JComponent{
	
}

