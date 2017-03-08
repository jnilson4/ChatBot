package chat.model;

import chat.controller.ChatController;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterException;
import twitter4j.Status;
import chat.view.ChatViewer;
import java.util.List;
import java.util.ArrayList;

public class CTECTwitter
{
	private ChatController baseController;
	private Twitter twitterBot;
	private ChatViewer chatView;
	private List<Status> searchedTweets;
	private List<String> ignoredWords;
	
	public CTECTwitter(ChatController baseController)
	{
		this.baseController = baseController;
		chatView = new ChatViewer();
		searchedTweets = new ArrayList<Status>();
		ignoredWords = new ArrayList<String>();
		this.twitterBot = TwitterFactory.getSingleton();
	}

	private void createIgnoredWordList()
	{
		
	}
	
	public String getMostCommonWord(String username)
	{
		return "";
	}
	
	private void removeBoringWords()
	{
		
	}
	
	private void removeBlankWords()
	{
		
	}
	
	public void sendTweet(String textToTweet)
	{
		try
		{
			twitterBot.updateStatus(chatView.collectResponse("Enter in your tweet."));
		}
		catch(TwitterException tweetError)
		{
			baseController.handleErrors(tweetError);
		}
		catch(Exception otherError)
		{
			baseController.handleErrors(otherError);
		}
	}
}
