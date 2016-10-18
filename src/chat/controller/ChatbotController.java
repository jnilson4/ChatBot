package chat.controller;

import chat.model.Chatbot;
import chat.view.ChatViewer;

public class ChatbotController
{
	private Chatbot stupidBot;
	private ChatViewer chatView;
	
	public ChatbotController()
	{
		stupidBot = new Chatbot("Boaty McBoatFace");
	}
	
	public void start()
	{
		
	}
}
