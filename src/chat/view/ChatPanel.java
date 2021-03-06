package chat.view;

import chat.controller.ChatController;
import chat.controller.FileController;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ChatPanel extends JPanel
{
	private ChatController baseController;
	private SpringLayout baseLayout;
	private JTextArea chatDisplay;
	private JTextField chatField;
	private JScrollPane chatPane;
	private JButton chatButton, searchTwitter, sendTweetButton, saveButton, loadButton, searchTwitterUser, clearText;
	private JLabel chatLabel;
	private Color lightBlue;
	private ImageIcon chatBot, save, twitter, upload, clear;
	
	public ChatPanel(ChatController baseController)
	{
		super();
		this.baseController = baseController;
		
		this.chatBot = new ImageIcon(getClass().getResource("/chat/view/images/chatbot.png"));
		this.save = new ImageIcon(getClass().getResource("/chat/view/images/save.png"));
		this.upload = new ImageIcon(getClass().getResource("/chat/view/images/upload.png"));
		this.twitter = new ImageIcon(getClass().getResource("/chat/view/images/twitterlogo.png"));
		this.clear = new ImageIcon(getClass().getResource("/chat/view/images/clear.png"));
		
		chatDisplay = new JTextArea(5, 25);
		chatField = new JTextField(25);
		chatPane = new JScrollPane();
		chatButton = new JButton("Chat with the Bot", chatBot);
		chatLabel = new JLabel("CHATBOT!");
		searchTwitter = new JButton("Search Twitter", twitter);
		sendTweetButton = new JButton("Send Tweet", twitter);
		saveButton = new JButton("Save", save);
		loadButton = new JButton("Load", upload);
		searchTwitterUser = new JButton("Search User Tweets", twitter);
		clearText = new JButton("Clear Text", clear);
		
		lightBlue = Color.decode("#FA4B4B");
		
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
		
		chatLabel.setForeground(Color.WHITE);
		
		chatButton.setVerticalTextPosition(AbstractButton.BOTTOM);
		chatButton.setHorizontalTextPosition(AbstractButton.CENTER);
	}
	
	private void setupPanel()
	{
		baseLayout = new SpringLayout();
		baseLayout.putConstraint(SpringLayout.NORTH, clearText, 15, SpringLayout.SOUTH, chatButton);
		baseLayout.putConstraint(SpringLayout.WEST, clearText, 0, SpringLayout.WEST, chatButton);
		baseLayout.putConstraint(SpringLayout.SOUTH, clearText, 0, SpringLayout.SOUTH, searchTwitterUser);
		baseLayout.putConstraint(SpringLayout.EAST, clearText, 0, SpringLayout.EAST, chatButton);
		baseLayout.putConstraint(SpringLayout.EAST, searchTwitterUser, 0, SpringLayout.EAST, loadButton);
		this.setLayout(baseLayout);
		this.setBackground(lightBlue);
		this.setPreferredSize(new Dimension(600, 385));
		this.add(chatButton);
		this.add(chatField);
		this.add(chatLabel);
		this.add(searchTwitter);
		this.add(sendTweetButton);
		this.add(saveButton);
		this.add(loadButton);
		this.add(chatPane);
		this.add(searchTwitterUser);
		this.add(clearText);
	}
	
	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.WEST, chatDisplay, 23, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.EAST, chatDisplay, -23, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, chatDisplay, 16, SpringLayout.SOUTH, chatLabel);
		baseLayout.putConstraint(SpringLayout.NORTH, chatField, 10, SpringLayout.SOUTH, chatPane);
		baseLayout.putConstraint(SpringLayout.NORTH, chatPane, 40, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, chatPane, 20, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.EAST, chatPane, -20, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.EAST, loadButton, -190, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.EAST, saveButton, -190, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, sendTweetButton, 35, SpringLayout.NORTH, chatField);
		baseLayout.putConstraint(SpringLayout.NORTH, chatButton, 0, SpringLayout.NORTH, saveButton);
		baseLayout.putConstraint(SpringLayout.WEST, chatButton, 15, SpringLayout.EAST, saveButton);
		baseLayout.putConstraint(SpringLayout.SOUTH, chatButton, 0, SpringLayout.SOUTH, loadButton);
		baseLayout.putConstraint(SpringLayout.EAST, chatButton, 0, SpringLayout.EAST, chatField);
		baseLayout.putConstraint(SpringLayout.SOUTH, searchTwitter, 65, SpringLayout.SOUTH, sendTweetButton);
		baseLayout.putConstraint(SpringLayout.SOUTH, sendTweetButton, 60, SpringLayout.SOUTH, chatField);
		baseLayout.putConstraint(SpringLayout.WEST, searchTwitter, 0, SpringLayout.WEST, sendTweetButton);
		baseLayout.putConstraint(SpringLayout.WEST, sendTweetButton, 0, SpringLayout.WEST, chatField);
		baseLayout.putConstraint(SpringLayout.WEST, chatField, 0, SpringLayout.WEST, chatPane);
		baseLayout.putConstraint(SpringLayout.EAST, chatField, 0, SpringLayout.EAST, chatPane);
		baseLayout.putConstraint(SpringLayout.SOUTH, loadButton, 0, SpringLayout.SOUTH, searchTwitter);
		baseLayout.putConstraint(SpringLayout.NORTH, saveButton, 0, SpringLayout.NORTH, sendTweetButton);
		baseLayout.putConstraint(SpringLayout.SOUTH, saveButton, 0, SpringLayout.SOUTH, sendTweetButton);
		baseLayout.putConstraint(SpringLayout.NORTH, loadButton, 0, SpringLayout.NORTH, searchTwitter);
		baseLayout.putConstraint(SpringLayout.NORTH, searchTwitter, 15, SpringLayout.SOUTH, sendTweetButton);
		baseLayout.putConstraint(SpringLayout.NORTH, searchTwitterUser, 15, SpringLayout.SOUTH, searchTwitter);
		baseLayout.putConstraint(SpringLayout.WEST, searchTwitterUser, 0, SpringLayout.WEST, sendTweetButton);
		baseLayout.putConstraint(SpringLayout.WEST, saveButton, 15, SpringLayout.EAST, sendTweetButton);
		baseLayout.putConstraint(SpringLayout.EAST, sendTweetButton, 185, SpringLayout.WEST, chatField);
		baseLayout.putConstraint(SpringLayout.EAST, searchTwitter, 185, SpringLayout.WEST, chatField);
		baseLayout.putConstraint(SpringLayout.WEST, loadButton, 15, SpringLayout.EAST, searchTwitter);
		baseLayout.putConstraint(SpringLayout.NORTH, chatLabel, 10, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, chatLabel, 267, SpringLayout.WEST, this);
		
	}
	
	private void setupListeners()
	{
		chatButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click){
				processText();
			}
		});
		
		chatField.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent enterPress)
			{
				processText();
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
		
		searchTwitter.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent clicked)
			{
				String username = chatField.getText();
				String results = baseController.searchTwitter(username);
				chatDisplay.setText(results + chatDisplay.getText());
				chatDisplay.setCaretPosition(0);
			}
		});
		
		sendTweetButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent clicked)
			{
				baseController.useTwitter(chatField.getText());
				chatDisplay.append("You just tweeted from Chatbot!!!\n");
			}
		});
	
		searchTwitterUser.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent clicked)
			{
				String username = chatField.getText();
				
				String results = baseController.searchTwitterUser(username);
				chatDisplay.setText(results + chatDisplay.getText());
				chatDisplay.setCaretPosition(0);
			}
		});
		
		clearText.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent clicked)
			{
				chatDisplay.setText("");
			}
		});
	}

	private void processText()
	{
		String userWords = chatField.getText();
		String botResponse = baseController.useChatbotCheckers(userWords);
		String currentText = chatDisplay.getText();
		
		chatDisplay.setText("You said: " + userWords + "\n" + "Chatbot said: " + botResponse + "\n" + currentText);
		chatDisplay.setCaretPosition(0);
		chatField.setText("");
	}
}
