package chat.model;

import chat.controller.ChatController;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterException;
import twitter4j.Status;
import chat.view.ChatViewer;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import twitter4j.Paging;

public class CTECTwitter
{
	private ChatController baseController;
	private Twitter twitterBot;
	private ChatViewer chatView;
	private List<Status> allTheTweets;
	private List<String> tweetedWords;
	public CTECTwitter(ChatController baseController)
	{
		this.baseController = baseController;
		chatView = new ChatViewer();
		allTheTweets = new ArrayList<Status>();
		tweetedWords = new ArrayList<String>();
		this.twitterBot = TwitterFactory.getSingleton();
	}

	private String [] createIgnoredWordList()
	{
		String [] boringWords;
		int wordCount = 0;
		
		Scanner boringWordScanner = new Scanner(this.getClass().getResourceAsStream("commondWords.txt"));
		while(boringWordScanner.hasNextLine())
		{
			wordCount++;
		}
		boringWordScanner.close();
		
		boringWords = new String [wordCount];
		
		boringWordScanner = new Scanner(this.getClass().getResourceAsStream("commonWords.txt"));
		
		for(int index = 0; index < boringWords.length; index++)
		{
			boringWords[index] = boringWordScanner.next();
		}
		boringWordScanner.close();
		
		return boringWords;
	}
	
	public String getMostCommonWord(String username)
	{
		removeBoringWords();
		removeBlankWords();
		
		return "";
	}
	
	private void removeBoringWords()
	{
		String [] boringWords = createIgnoredWordList();
		
		for(int index = 0; index < tweetedWords.size(); index++)
		{
			for(int boringIndex = 0; boringIndex < boringWords.length; boringIndex++)
			{
				if(tweetedWords.get(index).equalsIgnoreCase(boringWords[boringIndex]))
				{
					tweetedWords.remove(index);
					index--;
					boringIndex = boringWords.length;
				}
			}
		}
	}
	
	private void removeBlankWords()
	{
		for(int index = 0; index < tweetedWords.size(); index++)
		{
			if(tweetedWords.get(index).trim().equals(""))
			{
				tweetedWords.remove(index);
				index--;
			}
		}
	}
	
	private void gatherTheTweets(String user)
	{
		tweetedWords.clear();
		allTheTweets.clear();
		int pageCount = 1;
		
		Paging statusPage = new Paging(1,200);
		
		while(pageCount <= 10)
		{
			try
			{
				allTheTweets.addAll(twitterBot.getUserTimeline(user, statusPage));
			}
			catch (TwitterException twitterError)
			{
				baseController.handleErrors(twitterError);
			}
			pageCount++;
		}
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
