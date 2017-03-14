package chat.model;

import chat.controller.ChatController;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterException;
import twitter4j.Status;
import chat.view.ChatViewer;
import java.util.List;
import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.ArrayList;

import twitter4j.GeoLocation;
import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.QueryResult;

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
		
		Scanner boringWordScanner = new Scanner(this.getClass().getResourceAsStream("commonWords.txt"));
		while(boringWordScanner.hasNextLine())
		{
			boringWordScanner.nextLine();
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
		gatherTheTweets(username);
		turnTweetsToWords();
		removeMentions();
		removeBoringWords();
		removeBlankWords();
		
		String information = "The tweetcount is " + allTheTweets.size() + " and " + username + "'s " + calculateTopWord();
		
		return information;
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
	
	private void turnTweetsToWords()
	{
		for(Status currentTweet : allTheTweets)
		{
			String tweetText = currentTweet.getText();
			String [] tweetWords = tweetText.split(" ");
			for(int index = 0; index < tweetWords.length; index++)
			{
				tweetedWords.add(removePunctuation(tweetWords[index]));
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
	
	private void removeMentions()
 	{
 		for(int index = 0; index < tweetedWords.size(); index++)
 		{
 			if(tweetedWords.get(index).substring(0,1).equals("@"))
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
		
		Paging statusPage = new Paging(1,20);
		
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

 	private String calculateTopWord()
 	{
 		String results = "";
 		String topWord = "";
 		int mostPopularIndex = 0;
 		int popularCount = 0;
 		
 		for(int index = 0; index < tweetedWords.size(); index++)
 		{
 			int currentPopularity = 0;
 			for(int searched = index + 1; searched < tweetedWords.size(); searched++)
 			{
 				if(tweetedWords.get(index).equalsIgnoreCase(tweetedWords.get(searched)) && !tweetedWords.get(index).equals(topWord));
 				{
 					currentPopularity++;
 				}
 			}
 			if(currentPopularity > popularCount)
 			{
 				popularCount = currentPopularity;
 				mostPopularIndex = index;
 				topWord = tweetedWords.get(mostPopularIndex);
 			}
 		}
 		results = " the most popular word is: " + topWord + ", and it occured " + popularCount + " times out of " + tweetedWords.size() + ", AKA " + (DecimalFormat.getPercentInstance().format(((double) popularCount)/tweetedWords.size()));
 	
 		return results;
 	}
 	
 	private String removePunctuation(String currentString)
 	{
 		String puncuation = ".,'?!:;\"(){}^[]<>-";
 		
 		String scrubbedString = "";
 		for(int i = 0; i < currentString.length(); i++)
 		{
 			if(puncuation.indexOf(currentString.charAt(i)) == -1)
 			{
 				scrubbedString += currentString.charAt(i);
 			}
 		}
 		return scrubbedString;
 	}

 	public String searchForTweet()
 	{
 		String results = "";
 		
 		Query query = new Query();
 		
 		query.setCount(2);
 		query.setGeoCode(new GeoLocation(40.587521, -111.869178), 5, Query.MILES);
 		query.setSince("2017-1-1");
 		
 		try
 		{
 			QueryResult result = twitterBot.search(query);
 			results += "The most recent two tweets within 5 miles of CTEC is: \n";
 			for(Status tweet : result.getTweets())
 			{
 				results += "@" + tweet.getUser().getName() + ": " + tweet.getText() + "\n";
 			}
 		}
 		catch(TwitterException error)
 		{
 			baseController.handleErrors(error);
 		}
 		
 		return results;
 	}
}
