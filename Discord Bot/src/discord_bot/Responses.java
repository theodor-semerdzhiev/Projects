package discord_bot;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Responses extends ListenerAdapter{

	boolean gamestarted=false;
	int guess;
	int border1,border2;
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		

		try {
			if(event.getMessage().getAuthor().isBot()) {
				return;
			} else if(!gamestarted) {
			event.getChannel().sendMessage("Hi, I will guess a WHOLE number between "+ numberGetter(event.getMessage().getContentRaw())[0]+" and "+numberGetter(event.getMessage().getContentRaw())[1]).queue();
			gamestarted=true;
			if(numberGetter(event.getMessage().getContentRaw())[0] > numberGetter(event.getMessage().getContentRaw())[1]) {
				border2=numberGetter(event.getMessage().getContentRaw())[0];
				border1=numberGetter(event.getMessage().getContentRaw())[1];
			} else {
				border2=numberGetter(event.getMessage().getContentRaw())[1];
					border1=numberGetter(event.getMessage().getContentRaw())[0];
				}
				return;
				} else {
				
				event.getChannel().sendMessage("Tell me if im right, if im not, tell me higher or lower \n " + Guess(event.getMessage().getContentRaw(), border1, border2, guess)).queue();; 
											
			}
			
		}catch(Exception e){
			event.getChannel().sendMessage("Error: "+e).queue();;
		}
	}
	
	
	public int[] numberGetter(String text) {
		
		String[] message=text.split(" ");
		int[]higherlower= new int[2];
		
		int j=0;

		for(int i=0; i<message.length; i++) {
			if(isNumeric(message[i])) {
				if(j==0) {
				higherlower[0]=Integer.parseInt(message[i]);
				j++;
				} else {
				higherlower[1]=Integer.parseInt(message[i]);
				break;
				}
			}
		}
		return higherlower;
	}
	public static boolean isNumeric(String word) {
	    if (word == null) {
	        return false;
	    }
	    try {
	        int d = Integer.parseInt(word);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
	public int Guess(String lowerORhigher, int limit1, int limit2, int guess){
		
		
		
		if(lowerORhigher.equalsIgnoreCase("Lower")) {
			guess=(guess-(Math.abs(limit1-guess)/2));
		} else if(lowerORhigher.equalsIgnoreCase("Higher")) {
			guess=((Math.abs(limit2-guess)/2)+guess);
		} else {
			guess=Math.abs((limit2-limit1)/2);
		}
		return guess;
	}
}
