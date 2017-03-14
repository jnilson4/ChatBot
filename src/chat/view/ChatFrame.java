package chat.view;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import chat.controller.ChatController;
import java.awt.Dimension;

public class ChatFrame extends JFrame
{
	private ChatController baseController;
	private ChatPanel basePanel;
	
	public ChatFrame(ChatController baseController)
	{
		super();
		this.baseController = baseController;
		basePanel = new ChatPanel(baseController);
		
		setupFrame();
	}
	
	private void setupFrame()
	{
		this.setContentPane(basePanel);
		this.setTitle("Chatbot 2.0");
		this.setSize(new Dimension(600,385));
		this.setResizable(true);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
}
