package chat.controller;

import javax.swing.JOptionPane;

import chat.model.Chatbot;
import chat.view.ChatViewer;
import chat.view.ChatFrame;						

public class ChatController
{
	private Chatbot stupidBot;
	private ChatViewer chatView;
	private ChatFrame baseFrame;
	
	public ChatController()
	{
		stupidBot = new Chatbot("Boaty McBoatFace");
		baseFrame = new ChatFrame(this);
		chatView = new ChatViewer();
	}
	
	public void start()
	{
		chatView.displayMessage("Welcome to Chatbot!");
	}
	
	public String useChatbotCheckers(String input)
	{
		String answer = "";
		
		if (!stupidBot.quitChecker(input))
		{
			if(stupidBot.contentChecker(input))
			{
				answer += "You know my special secret";
			}
			
			if(stupidBot.memeChecker(input))
			{
				answer += "I can has memes?";
			}
			
//			if(stupidBot.lengthChecker(input))
//			{
//				answer += "Sorry, I don't know about " + input;
//			}
			
			if(stupidBot.politicalTopicChecker(input))
			{
				answer += "You like politics huh?";
			}
			
			if(stupidBot.inputHTMLChecker(input))
			{
				answer += "I don't know how to talk about HTML.";
			}
			
//			if(stupidBot.twitterChecker(input))
//			{
//				answer += "\nTWITTER, CHEEP CHEEP\n";
//			}
			
			if(stupidBot.keyboardMashChecker(input))
			{
				answer += "MASH MASH MASH";
			}
			
			if(stupidBot.helloChecker(input))
			{
				answer+= "Hi!!";
			}
			
			int canBeRandom = (int) (Math.random() * 7);
			if (canBeRandom % 7 == 0)
			{
				answer += randomTopicGenerator();
			}
			
//			answer += "Wow";
		}
		else 
		{
			System.exit(0);
		}
		return answer;
	}
	
	public Chatbot getChatbot()
	{
		return stupidBot;
	}
	
	public ChatFrame getBaseFrame()
	{
		return baseFrame;
	}
	
	public String randomTopicGenerator()
	{
		String randomTopic = "";
		int random = (int) (Math.random() * 7);
		
		switch (random)
		{
		case 0:
			randomTopic = "Did you hear about the daft punk beastie boys mix?";
			break;
		case 1:
			randomTopic = "Can you bring me some Sriracha?";
			break;
		case 2:
			randomTopic = "Time for some industrial!";
			break;
		case 3:
			randomTopic = "Reading novels is fantastic!";
			break;
		case 4:
			randomTopic = "Computational and algorithmic thinking for the win!";
			break;
		case 5:
			randomTopic = "I love java";
			break;
		case 6:
			randomTopic = "Time to code!";
			break;
		default: 
			randomTopic = "This can't be happening!";
			break;
		}
		
		return randomTopic;
	}
	
	public void handleErrors(Exception currentException)
	{
		chatView.displayMessage("An error has occured. Details provided next.");
		chatView.displayMessage(currentException.getMessage());
	}
	
	public ChatViewer getPopup()
	{
		return chatView;
	}
}
