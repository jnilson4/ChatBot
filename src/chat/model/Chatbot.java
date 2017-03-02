package chat.model;

import java.util.ArrayList;

/**
 * Base version of the 2016 Chatbot class. Only stub methods are provided.
 * Students will complete methods as part * of the project. * 
 * @author Jake Nilson * @version 1.0 10/14/16
 */

public class Chatbot
{
	private ArrayList<String> memesList;
	private ArrayList<String> politicalTopicList;
	private String userName;
	private String content;

	/**
	 * * Creates an instance of the Chatbot with the supplied username. * @param
	 * userName The username for the chatbot.
	 */
	public Chatbot(String userName)
	{
		this.memesList = new ArrayList<String>();
		this.politicalTopicList = new ArrayList<String>();
		this.userName = userName;
		this.content = new String("Chinese food");
		this.buildMemesList();
		this.buildPoliticalTopicsList();
	}

	/**
	 * Meme's list for the checker.
	 */
	private void buildMemesList()
	{
		memesList.add("john cena");
		memesList.add("doge");
		memesList.add("cute animals");
		memesList.add("grumpy cat");
		memesList.add("dat boi");
		memesList.add("willy wonka");
		memesList.add("harambe");
		memesList.add("fails");
		memesList.add("Donald Trump Hair");
		memesList.add("funny fails");
		memesList.add("pajama pictures");
		memesList.add("thug life");
		memesList.add("one doesn't simply");
		memesList.add("you had one job");
		memesList.add("aint nobody got time for dat");
		memesList.add("tell me more");
		memesList.add("does it wrong");
		memesList.add("steve harvey");
		memesList.add("cute animals");
	}

	/**
	 * Political topic list for the checker.
	 */
	private void buildPoliticalTopicsList()
	{
		politicalTopicList.add("election");
		politicalTopicList.add("2017");
		politicalTopicList.add("Hillary");
		politicalTopicList.add("liberal");
		politicalTopicList.add("conservative");
		politicalTopicList.add("Democrat");
		politicalTopicList.add("Republican");
		politicalTopicList.add("Clinton");
		politicalTopicList.add("Trump");
		politicalTopicList.add("Kaine");
		politicalTopicList.add("Pence");
		politicalTopicList.add("11/8/16");
		politicalTopicList.add("Stein");
		politicalTopicList.add("Johnson");
		politicalTopicList.add("November");
		politicalTopicList.add("states");
		politicalTopicList.add("White House");
		politicalTopicList.add("United States of America");
		politicalTopicList.add("President");
		politicalTopicList.add("Washington D.C.");
		politicalTopicList.add("presidential race");
		
	}
	
	/**
	 * * Checks the length of the supplied string. Returns false if the supplied
	 * String is empty or null, otherwise returns true. * @param currentInput * @return
	 * A true or false based on the length of the supplied String.
	 */
	public boolean lengthChecker(String currentInput)
	{
		boolean hasLength = false;
		
		if (currentInput != null && currentInput.length() > 0)
		{
			hasLength = true;
		}
		
		return hasLength;
	}

	/**
	 * * Checks if the supplied String matches the content area for this Chatbot
	 * instance.
	 * 
	 * @param currentInput
	 *            The supplied String to be checked. * @return Whether it
	 *            matches the content area.
	 */
	public boolean contentChecker(String currentInput)
	{
		boolean hasContent = false;
		
		if(currentInput.contains(content))
		{
			hasContent = true;
		}
		
		return hasContent;
	}

	public boolean helloChecker(String currentInput)
	{
		boolean hasHello = false;
		
		if(currentInput.equalsIgnoreCase("Hello") || currentInput.equalsIgnoreCase("Hi"))
		{
			hasHello = true;
		}
		
		return hasHello;
	}
	
	/**
	 * * Checks if supplied String matches ANY of the topics in the
	 * politicalTopicsList. Returns true if it does find a match and false if it
	 * does not match.
	 * 
	 * @param currentInput
	 *            The supplied String to be checked. * @return Whether the
	 *            String is contained in the ArrayList.
	 */
	public boolean politicalTopicChecker(String currentInput)
	{
		boolean topicChecker = false;
		
		for(int index = 0; index < politicalTopicList.size(); index++)
		{
			if(currentInput.equals(politicalTopicList.get(index)))
			{
				topicChecker = true;
			}
		}
		return topicChecker;
	}

	/**
	 * * Checks to see that the supplied String value is in the current
	 * memesList variable.
	 * 
	 * @param currentInput
	 *            The supplied String to be checked. * @return Whether the
	 *            supplied String is a recognized meme.
	 */
	public boolean memeChecker(String currentInput)
	{
		boolean hasMemes = false;
		
		for(int index = 0; index < memesList.size(); index++)
		{
			if(currentInput.equalsIgnoreCase(memesList.get(index)))
			{
				hasMemes = true;
			}
		}
		
		return hasMemes;
	}

	/**
	 * Checks to see if the currentInput has html information or tags.
	 * @param currentInput
	 * @return
	 */
	public boolean inputHTMLChecker(String currentInput)
	{
		boolean htmlPresent = false; 
		
		if(currentInput.contains("<P>"))
		{
			htmlPresent = true;
		}
		else if(currentInput.contains("<A HREF=\""))
		{
			int index = currentInput.indexOf("<A HREF=\"") + 9;
			String sub = currentInput.substring(index);
			
			if(sub.contains("\">"))
			{
				int index2 = sub.indexOf("\">");
				String sub2 = sub.substring(index2);
				
				if(sub2.contains(" </a>"))
				{
					htmlPresent = true;
				}
			}
		}
		else if(currentInput.contains("<"))
		{
			String lower = currentInput.toLowerCase();
			int openIndex1 = lower.indexOf("<") + 1;
			String tag = "";
			if(lower.contains(">"))
			{
				int openIndex2 = lower.indexOf(">");
				tag = lower.substring(openIndex1, openIndex2);
				
				String sub = lower.substring(openIndex2 + 1);
				
				if(sub.contains("</" + tag + ">"))
				{
					htmlPresent = true;
				}
			}
		}
		return htmlPresent;
	}
	
	/**
	 * Checks to see if twitter tags or usernames are in the users input.
	 * @param currentInput
	 * @return
	 */
	public boolean twitterChecker(String currentInput)
	{
		boolean twitterCheckerOn = false;
		String sample = currentInput.replaceAll(" ", " ");
		if(sample.length() > 1&& !currentInput.startsWith(" "))
		{
			twitterCheckerOn = true;
		}
		return twitterCheckerOn;
	}
	
	/**
	 * Checks to see if the program is being quit or exited.
	 * @param currentInput
	 * @return
	 */
	public boolean quitChecker(String currentInput)
	{
		boolean assertQuit = false;
		
		if(currentInput.equalsIgnoreCase("quit"))
		{
			assertQuit = true;
		} else if (currentInput.equalsIgnoreCase("exit"))
		{
			assertQuit = false;
		}
		
		return assertQuit;
	}
	
	/**
	 * Checks to see if the keyboard was mashed.
	 * @param currentInput
	 * @return
	 */
	public boolean keyboardMashChecker(String currentInput)
	{
		boolean keyboardMash = false;
		
		if (currentInput.equalsIgnoreCase("sdf") || currentInput.equalsIgnoreCase("dfg") || currentInput.equalsIgnoreCase("cvb") || currentInput.equalsIgnoreCase(",./"))
		{
			keyboardMash = true;
		}
		
		return keyboardMash;
	}
	
	/**
	 * * Returns the username of this Chatbot instance. * @return The username
	 * of the Chatbot.
	 */
	public String getUserName()
	{
		return userName;
	}

	/**
	 * * Returns the content area for this Chatbot instance. * @return The
	 * content area for this Chatbot instance.
	 */
	public String getContent()
	{
		return content;
	}

	/**
	 * * Getter method for the memesList object. * @return The reference to the
	 * meme list.
	 */
	public ArrayList<String> getMemesList()
	{
		return memesList;
	}

	/**
	 * * Getter method for the politicalTopicList object. * @return The
	 * reference to the political topic list.
	 */
	public ArrayList<String> getPoliticalTopicList()
	{
		return politicalTopicList;
	}

	/**
	 * * Updates the content area for this Chatbot instance. * @param content
	 * The updated value for the content area.
	 */
	public void setContent(String content)
	{
		this.content = content;
	}
}
