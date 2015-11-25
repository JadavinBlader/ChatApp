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
	
  


	public static void main(String[] args) throws IOException{
		EventQueue.invokeLater(new Runnable() {
			public void run (){
				MainForm frame;
				try {
					frame = new MainForm();
					frame.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.frame.setTitle("ChatApp");
					frame.frame.setVisible(true);
					frame.frame.setResizable(true);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		});	
	}

public MainForm() throws IOException {
	frame = new JFrame();
	frame.setBounds(150, 150, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	Toolkit kit = Toolkit.getDefaultToolkit();
	 Dimension screenSize = kit.getScreenSize();
	 int screenHeight = screenSize.height;
	 int screenWidth = screenSize.width;
	 //frame.setSize(screenWidth/2, screenHeight/2);
	 //frame.setLocationByPlatform(true);
	 Image img = new ImageIcon("ChatApp.png").getImage();
	 frame.setIconImage(img);
 }
}
