package chat.view;

import chat.controller.ChatController;
import chat.controller.FileController;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatPanel extends JPanel
{
	private ChatController baseController;
	private SpringLayout baseLayout;
	private JTextArea chatDisplay;
	private JTextField chatField;
	private JScrollPane chatPane;
	private JButton chatButton, twitterButton, sendTweetButton, saveButton, loadButton;
	private JLabel chatLabel;
	private Color lightBlue;
	
	public ChatPanel(ChatController baseController)
	{
		super();
		this.baseController = baseController;
		chatDisplay = new JTextArea(5, 25);
		chatField = new JTextField(25);
		chatPane = new JScrollPane();
		chatButton = new JButton("Chat with the bot");
		chatLabel = new JLabel("CHATBOT!");
		chatLabel.setForeground(Color.WHITE);
		twitterButton = new JButton("Search Twitter");
		sendTweetButton = new JButton("Send Tweet");
		saveButton = new JButton("Save");
		loadButton = new JButton("Load");
		
		lightBlue = Color.decode("#1EB4E6");
		
		setupChatDisplay();
		setupPanel();
		setupLayout();
		setupListeners();
		
	}
	
	private void setupChatDisplay()
	{
		chatDisplay.setEditable(false);
		chatDisplay.setEnabled(false);
		chatDisplay.setWrapStyleWord(true);
		chatDisplay.setLineWrap(true);
		chatPane.setViewportView(chatDisplay);
		chatPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		chatPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	}
	
	private void setupPanel()
	{
		baseLayout = new SpringLayout();
		baseLayout.putConstraint(SpringLayout.NORTH, chatField, 10, SpringLayout.SOUTH, chatPane);
		baseLayout.putConstraint(SpringLayout.WEST, chatField, 20, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.EAST, chatField, -20, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, chatPane, 40, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, chatPane, 20, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.EAST, chatPane, -20, SpringLayout.EAST, this);
		this.setLayout(baseLayout);
		this.setBackground(lightBlue);
		this.setPreferredSize(new Dimension(500,300));
		this.add(chatButton);
		this.add(chatField);
		this.add(chatLabel);
		this.add(twitterButton);
		this.add(sendTweetButton);
		this.add(saveButton);
		this.add(loadButton);
		this.add(chatPane);
	}
	
	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.WEST, chatDisplay, 23, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.EAST, chatDisplay, -23, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, chatButton, -10, SpringLayout.SOUTH, this);
		baseLayout.putConstraint(SpringLayout.EAST, chatButton, -10, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, chatDisplay, 16, SpringLayout.SOUTH, chatLabel);
		baseLayout.putConstraint(SpringLayout.NORTH, chatLabel, 10, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, chatLabel, 217, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.WEST, twitterButton, 0, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, twitterButton, 0, SpringLayout.SOUTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, sendTweetButton, 0, SpringLayout.WEST, twitterButton);
		baseLayout.putConstraint(SpringLayout.SOUTH, sendTweetButton, -6, SpringLayout.NORTH, twitterButton);
		baseLayout.putConstraint(SpringLayout.NORTH, saveButton, 0, SpringLayout.NORTH, sendTweetButton);
		baseLayout.putConstraint(SpringLayout.WEST, saveButton, 68, SpringLayout.EAST, sendTweetButton);
		baseLayout.putConstraint(SpringLayout.NORTH, loadButton, 0, SpringLayout.NORTH, twitterButton);
		baseLayout.putConstraint(SpringLayout.WEST, loadButton, 0, SpringLayout.WEST, saveButton);
	}
	
	private void setupListeners()
	{
		chatButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click){
				String userWords = chatField.getText();
				String botResponse = baseController.useChatbotCheckers(userWords);
				String currentText = chatDisplay.getText();
				
				chatDisplay.setText("You said: " + userWords + "\n" + "Chatbot said: " + botResponse + "\n" + currentText);
				chatDisplay.setCaretPosition(chatDisplay.getCaretPosition());
				chatField.setText("");
			}
		});
		
		saveButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				String fileName = chatField.getText();
				
				FileController.saveFile(baseController, fileName.trim(), chatDisplay.getText());
			}
		});
		
		loadButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent clicked)
			{
				String fileName = chatField.getText() + ".txt";
				String saved = FileController.readFile(baseController, fileName);
				chatDisplay.setText(saved);
			}
		});
		
		twitterButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent clicked)
			{
				
			}
		});
		
		sendTweetButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent clicked)
			{
				
			}
		});
	}
}
