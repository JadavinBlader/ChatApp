import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.io.*;
import java.net.*;
import java.util.Date;

public class MainForm{
	private JFrame frame;
	private JTextField textField;
	private JTextField nickField;
	private JTextField remoteLoginField;
	private JTextField remoteAddressField;
	private JTextArea messageArea;
	private JButton sendButton;
	private JButton disconnectButton;
	private CallListener callListener;
	private JButton connectButton;
	private Caller caller;
	private Connection connection;
	private CallListenerThread callLT;
	private CommandListenerThread commandLT;
	private Connection socket;
	private static final int DEFAULT_WIDTH = 700;
	private static final int DEFAULT_HEIGHT = 500;
	
  


	public static void main(String[] args) throws IOException{
		EventQueue.invokeLater(new Runnable() {
			public void run (){
				MainForm frame;
				try {
					frame = new MainForm();
					frame.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.frame.setTitle("ChatApp v1.0.0");
					frame.frame.setVisible(true);
					frame.frame.setResizable(true);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		});	
	}

public MainForm() throws IOException {
	Toolkit kit = Toolkit.getDefaultToolkit();
	Dimension screenSize = kit.getScreenSize();
	int screenHeight = screenSize.height;
	int screenWidth = screenSize.width;
	
	frame = new JFrame();
	frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.LINE_AXIS));
	frame.setBounds(screenWidth/6, screenHeight/6, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	Image img = new ImageIcon("ChatApp.png").getImage();
	frame.setIconImage(img);
	
	frame.getContentPane().setLayout(new BorderLayout());
    JPanel messagePanel = new JPanel();
    messagePanel.setLayout(new GridLayout(1, 2));
    frame.add(messagePanel, BorderLayout.SOUTH);
    
    sendButton = new JButton("Send");
    sendButton.setPreferredSize(new Dimension(10,25));
    sendButton.setMaximumSize(new Dimension(10,25));
    sendButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
	sendButton.setEnabled(false);
    
    disconnectButton = new JButton("Disconnect");
    disconnectButton.setPreferredSize(new Dimension(100,25));
    disconnectButton.setMaximumSize(new Dimension(100,25));
    disconnectButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
   	disconnectButton.setEnabled(false);
    
    connectButton = new JButton("Connect");
    connectButton.setPreferredSize(new Dimension(100,25));
    connectButton.setMaximumSize(new Dimension(100,25));
    
    messagePanel.add(new JTextField(100));
    messagePanel.add(sendButton);
    
    textField = new JTextField();
    textField.setMaximumSize(new Dimension(50, 25));
    textField.setPreferredSize(new Dimension(50, 25));
    frame.add(textField, BorderLayout.CENTER);
    
    JPanel topPanel = new JPanel();
    topPanel.setLayout(new GridLayout(2, 2));
    
    JPanel loginPanel = new JPanel();
    loginPanel.add(new JLabel("local nick"));
    loginPanel.add(new JTextField(10));
    topPanel.add(loginPanel);
    
    JPanel nickPanel = new JPanel();
    nickPanel.add(new JLabel("remote login"));
    nickPanel.add(new JTextField(10));
    nickPanel.add(disconnectButton);
    topPanel.add(nickPanel);
    
    final JButton applyButton = new JButton("Apply");
    applyButton.setMaximumSize(new Dimension(25,25));
    applyButton.setPreferredSize(new Dimension(25,25));
    applyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    topPanel.add(applyButton);
    
    JPanel connectionPanel = new JPanel();
    connectionPanel.add(new JLabel("remote address"));
    
    connectionPanel.add(new JTextField(10));
    connectionPanel.add(connectButton);
    topPanel.add(connectionPanel);
    frame.add(topPanel, BorderLayout.NORTH);
    
	disconnectButton.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {

			try {
				if (connection != null) {

					connection.disconnect();
					forDisconnect();
					connection = null;
					commandLT.stop();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}

	});

	connectButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (remoteAddressField.getText() != "") {
				String login;
				login = nickField.getText();
				caller = new Caller(login, remoteAddressField.getText());
				try {
					connection = caller.call();
					if (connection != null) {
						commandLT = new CommandListenerThread(connection);
						commandLT.start();
						connection.sendNickHello("1", nickField.getText());
						forConnect();
					} else{
						JOptionPane.showMessageDialog(null,
								"Couldn't connect this ip ");
					}
				} catch (UnsupportedEncodingException e1) {

					e1.printStackTrace();
				} catch (IOException e1) {

					e1.printStackTrace();
				}

			}
		}
	});
	messageArea.addKeyListener(new KeyAdapter() {
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				connection.sendMessage(messageArea.getText());
				messageArea.setText("");
				messageArea.setCursor(new Cursor(0));

			} else {
				JOptionPane.showMessageDialog(null, "You must write remote address");
			}
		}
	});
	sendButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (!messageArea.getText().equals("")) {
				connection.sendMessage(messageArea.getText());
				messageArea.setText("");

			}
		}

	});
	applyButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String login;
			if (nickField.getText().equals("")) {
				login = "unnamed";
			} else
				login = nickField.getText();
			boolean isCorrectLogin=false;
			for (int i=0; i<login.toCharArray().length;i++)
				if (login.toCharArray()[i]!=' '){
						isCorrectLogin=true;
						break;
				}
			if (!isCorrectLogin){
				login ="unnamed";
			}
			while (login.charAt(0)==' ')
				login=login.substring(1);
			nickField.setText(login);
			nickField.setEnabled(false);
			try {
				connection.isOpen();
				callLT = new CallListenerThread();
				callLT.start();
				commandLT = new CommandListenerThread();
				applyButton.setEnabled(false);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			callLT.setLocalNick(login);
		}
	});
}

void forDisconnect() {
	sendButton.setEnabled(false);
	remoteLoginField.setText("");
	messageArea.setEnabled(false);
	disconnectButton.setEnabled(false);
	connectButton.setEnabled(true);
	remoteAddressField.setText("");
	remoteAddressField.setEnabled(true);
}

void forConnect() throws IOException {
	sendButton.setEnabled(true);
	connectButton.setEnabled(false);
	messageArea.setEnabled(true);
	disconnectButton.setEnabled(true);
	remoteAddressField.setEnabled(false);
}
}