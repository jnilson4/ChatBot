package chat.view;

import chat.controller.ChatController;
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
	private JButton chatButton, twitterButton, sendTweetButton, saveButton, loadButton;
	private JLabel chatLabel;
	
	public ChatPanel(ChatController baseController)
	{
		super();
		this.baseController = baseController;
		baseLayout = new SpringLayout();
		chatDisplay = new JTextArea(5, 25);
		chatField = new JTextField(25);
		chatButton = new JButton("Chat with the bot");
		chatLabel = new JLabel("CHATBOT!");
		twitterButton = new JButton("Search Twitter");
		sendTweetButton = new JButton("Send Tweet");
		saveButton = new JButton("Save");
		loadButton = new JButton("Load");
		setupPanel();
		setupLayout();
		setupListeners();
		
	}
	
	private void setupPanel()
	{
		this.setLayout(baseLayout);
		this.setBackground(Color.GRAY);
		this.setPreferredSize(new Dimension(600,400));
		this.add(chatButton);
		this.add(chatField);
		this.add(chatDisplay);
		this.add(chatLabel);
		this.add(twitterButton);
		this.add(sendTweetButton);
		this.add(saveButton);
		this.add(loadButton);
		chatDisplay.setEditable(false);
		chatDisplay.setEnabled(false);
	}
	
	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.WEST, chatDisplay, 69, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, chatDisplay, -6, SpringLayout.NORTH, chatField);
		baseLayout.putConstraint(SpringLayout.EAST, chatDisplay, 0, SpringLayout.EAST, chatField);
		baseLayout.putConstraint(SpringLayout.WEST, chatLabel, 267, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, chatLabel, -6, SpringLayout.NORTH, chatDisplay);
		baseLayout.putConstraint(SpringLayout.NORTH, twitterButton, 5, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, twitterButton, 40, SpringLayout.EAST, sendTweetButton);
		baseLayout.putConstraint(SpringLayout.NORTH, sendTweetButton, 5, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.NORTH, saveButton, 5, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.EAST, loadButton, -370, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.WEST, sendTweetButton, 40, SpringLayout.EAST, loadButton);
		baseLayout.putConstraint(SpringLayout.EAST, saveButton, -40, SpringLayout.WEST, loadButton);
		baseLayout.putConstraint(SpringLayout.NORTH, loadButton, 5, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.NORTH, chatDisplay, 65, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, chatField, 68, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, chatField, -56, SpringLayout.SOUTH, this);
		baseLayout.putConstraint(SpringLayout.EAST, chatField, -68, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, chatButton, 6, SpringLayout.SOUTH, chatField);
		baseLayout.putConstraint(SpringLayout.WEST, chatButton, 148, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, chatButton, -21, SpringLayout.SOUTH, this);
		baseLayout.putConstraint(SpringLayout.EAST, chatButton, -149, SpringLayout.EAST, this);
	}
	
	private void setupListeners()
	{
		chatButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click){
				String userWords = chatField.getText();
				String botResponse = baseController.useChatbotCheckers(userWords);
				String currentText = chatDisplay.getText();
				
				chatField.setText("");
				chatDisplay.setText("You said: " + userWords + "\n" + "Chatbot said: " + botResponse + "\n" + currentText);
			}
		});
	}
}
