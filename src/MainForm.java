import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.io.*;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

public class MainForm{
	private static JFrame frame;
	private static JButton connectButton;
	private static JTextField remoteNickField;
	private static JTextField remoteAdressField;
	private static JButton disconnectButton;
	private static JTextField localNickField;
	private static JTextField messageField;
	private static JButton applyButton;
	private static JButton sendButton;
	private static JTextArea dialogArea;
	private static JSeparator topSeparator;
	private static JSeparator bottomSeparator;
	
  


	public static void main(String[] args) throws IOException{
		EventQueue.invokeLater(new Runnable() {
			public void run (){
				frame = new JFrame();
				frame.setLocationByPlatform(true);
				frame.setMinimumSize(new Dimension(800, 500));
				frame.setMaximumSize(new Dimension(1200, 800));
				frame.setVisible(true);
				frame.setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\OOP\\Brazauskaite_Veronika\\workspace\\ChatApp\\ChatApp.png"));
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setTitle("ChatApp v1.0.0");
				frame.setResizable(false);
				frame.getContentPane().setLayout(null);
				
				JPanel topPanel = new JPanel();
				topPanel.setBounds(2, 0, 790, 100);
				frame.getContentPane().add(topPanel);
				topPanel.setLayout(null);
				
				connectButton = new JButton("Connect");
				connectButton.setRequestFocusEnabled(false);
				connectButton.setBackground(new Color(255, 215, 0));
				connectButton.setForeground(new Color(51, 204, 51));
				connectButton.setFont(new Font("Broadway", Font.PLAIN, 14));
				connectButton.setToolTipText("Click to start chat");
				connectButton.setBounds(596, 17, 145, 22);
				topPanel.add(connectButton);
				
				disconnectButton = new JButton("Disconnect");
				disconnectButton.setRequestFocusEnabled(false);
				disconnectButton.setBackground(new Color(255, 215, 0));
				disconnectButton.setForeground(new Color(255, 0, 0));
				disconnectButton.setFont(new Font("Broadway", Font.PLAIN, 14));
				disconnectButton.setToolTipText("Click to finish chat");
				disconnectButton.setBounds(596, 51, 145, 22);
				topPanel.add(disconnectButton);
				
				remoteNickField = new JTextField();
				remoteNickField.setCaretColor(new Color(0, 0, 0));
				remoteNickField.setToolTipText("Nick of interlocutor");
				remoteNickField.setFont(new Font("Dialog", Font.PLAIN, 12));
				remoteNickField.setForeground(new Color(0, 0, 0));
				remoteNickField.setBounds(420, 17, 150, 22);
				topPanel.add(remoteNickField);
				remoteNickField.setColumns(15);
				
				remoteAdressField = new JTextField();
				remoteAdressField.setCaretColor(new Color(0, 0, 0));
				remoteAdressField.setToolTipText("Enter IP adress of interlocutor");
				remoteAdressField.setForeground(new Color(0, 0, 0));
				remoteAdressField.setBounds(420, 51, 150, 22);
				topPanel.add(remoteAdressField);
				remoteAdressField.setColumns(12);
				
				JLabel remoteNickLabel = new JLabel("Remote Login");
				remoteNickLabel.setLabelFor(remoteNickField);
				remoteNickLabel.setHorizontalAlignment(SwingConstants.CENTER);
				remoteNickLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
				remoteNickLabel.setForeground(new Color(255, 153, 0));
				remoteNickLabel.setBounds(306, 16, 110, 22);
				topPanel.add(remoteNickLabel);
				
				JLabel remoteAdressLabel = new JLabel("Remote Adress");
				remoteAdressLabel.setLabelFor(remoteAdressField);
				remoteAdressLabel.setHorizontalAlignment(SwingConstants.CENTER);
				remoteAdressLabel.setForeground(new Color(255, 153, 0));
				remoteAdressLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
				remoteAdressLabel.setBounds(306, 50, 110, 22);
				topPanel.add(remoteAdressLabel);
				
				localNickField = new JTextField();
				localNickField.setToolTipText("Enter your nick");
				localNickField.setCaretColor(new Color(0, 0, 0));
				localNickField.setForeground(new Color(0, 0, 0));
				localNickField.setBounds(125, 17, 150, 22);
				topPanel.add(localNickField);
				localNickField.setColumns(15);
				
				JLabel localNickLabel = new JLabel("Local Login");
				localNickLabel.setLabelFor(localNickField);
				localNickLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
				localNickLabel.setForeground(new Color(255, 153, 0));
				localNickLabel.setHorizontalAlignment(SwingConstants.CENTER);
				localNickLabel.setBounds(32, 16, 85, 22);
				topPanel.add(localNickLabel);
				
				applyButton = new JButton("Apply");
				applyButton.setRequestFocusEnabled(false);
				applyButton.setToolTipText("Click to apply your nick");
				applyButton.setBackground(new Color(255, 215, 0));
				applyButton.setFont(new Font("Broadway", Font.PLAIN, 14));
				applyButton.setForeground(new Color(0, 51, 255));
				applyButton.setActionCommand("Apply");
				applyButton.setBounds(77, 51, 85, 22);
				topPanel.add(applyButton);
				
				topSeparator = new JSeparator();
				topSeparator.setForeground(new Color(105, 105, 105));
				topSeparator.setBounds(0, 99, 790, 4);
				topPanel.add(topSeparator);
				
				JPanel mainPanel = new JPanel();
				mainPanel.setBounds(2, 102, 790, 370);
				frame.getContentPane().add(mainPanel);
				mainPanel.setLayout(null);
				
				sendButton = new JButton("Send");
				sendButton.setRequestFocusEnabled(false);
				sendButton.setFont(new Font("Broadway", Font.PLAIN, 14));
				sendButton.setForeground(new Color(0, 51, 255));
				sendButton.setToolTipText("Click to send message");
				sendButton.setBackground(new Color(255, 215, 0));
				sendButton.setBounds(644, 332, 98, 26);
				mainPanel.add(sendButton);
				
				messageField = new JTextField();
				messageField.setCaretColor(new Color(0, 0, 0));
				messageField.setBounds(43, 332, 575, 26);
				mainPanel.add(messageField);
				messageField.setColumns(10);
				
				dialogArea = new JTextArea();
				dialogArea.setEnabled(false);
				dialogArea.setForeground(new Color(0, 0, 0));
				dialogArea.setBounds(43, 12, 699, 288);
				mainPanel.add(dialogArea);
				
				bottomSeparator = new JSeparator();
				bottomSeparator.setForeground(new Color(105, 105, 105));
				bottomSeparator.setBounds(0, 318, 790, 2);
				mainPanel.add(bottomSeparator);
				
			}
		});	
	}
	public Color getConnectButtonBackground() {
		return connectButton.getBackground();
	}
	public void setConnectButtonBackground(Color background) {
		connectButton.setBackground(background);
	}
}
