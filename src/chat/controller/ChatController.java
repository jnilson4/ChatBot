package chat.controller;

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
		chatView = new ChatViewer();
		baseFrame = new ChatFrame(this);
	}
	
	public void start()
	{
		String response = chatView.collectResponse("What do you want to talk about today???");
	}
	
	public String useChatbotCheckers(String input)
	{
		String answer = "";
		
		if (!stupidBot.quitChecker(input))
		{
			if(stupidBot.contentChecker(input))
			{
				answer += "\nYou know my special secret\n";
			}
			
			if(stupidBot.memeChecker(input))
			{
				answer += "\nI can has memes?\n";
			}
			
			if(stupidBot.lengthChecker(answer))
			{
				answer += "Sorry, I don't know about " + input;
			}
			
			int canBeRandom = (int) (Math.random() * 7);
			if (canBeRandom % 7 == 0)
			{
				answer += randomTopicGenerator();
			}
			
			answer += "Wow";
		}
		else 
		{
			chatView.displayMessage("Thank you for chatting with me :D");
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
		case 7: 
			randomTopic = "Tennis is awesome!";
			break;
		}
		
		return randomTopic;
	}
}
