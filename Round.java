import java.util.Scanner;
import java.net.*;
import java.io.*;

public class Round{
	Card c1 = new Card();
	Card c2 = new Card();
	int card1 = 0;
	int card2 = 0;
	String answerString = null;
	String outputLine = null;
	
	public void FirstStart(){
		c1.drawCard();
	}
	
	public String StartRoundRequest(){
		c2.drawCard();
		return "The Card is: "+c1.toString()+"\nDo you think the next card will be higher, lower or equal?\n";
	}

	public String FinishRoundRequest(){
		c1.changeValue(c2.getFace(), c2.getSuit());
		outputLine = "You answered correctly";
		return outputLine;
	}

	public String midGame(String choiceString){
		choiceString = choiceString.toLowerCase();
		//System.out.println("Card: "+c1.toString());
		card1 = c1.getFace();
		card2 = c2.getFace();
		System.out.println("Card1 "+card1+" Card2 "+card2);
		if(card1 < card2){
				//Bigger
				answerString="higher";
				//System.out.println("Higher "+card2);
			}
			else if(card1 > card2){
				//Smaller
				answerString="lower";
				//System.out.println("Lower "+card2);
			}
			else if(card1==card2){
				//Equal
				answerString="equal";
				//System.out.println("Equal "+c1.toString());
			}
		
		
		if(!choiceString.equals("higher") && !choiceString.equals("lower") && !choiceString.equals("equal")){
			outputLine = "Try again";
			return outputLine;
		}else{
			if(answerString.equals(choiceString)){
				outputLine = "Correct answer";
			}
			else{
				outputLine = "Wrong answer";

			}
			return outputLine;
		}
	}
}
