import java.util.Random;

public class Card{
    private int suit;
    private int faceValue;

    public void firstCard(){
        drawCard();
    }

    public void changeValue(int changeFace, int changeSuit){
        suit=changeSuit;
        faceValue=changeFace;
    }

    public void drawCard(){
        faceValue = (int) (Math.random() * 13) + 2;
        suit = (int) (Math.random() * 4);
    }

    public int getFace(){
		if (faceValue>=10){
			return 10;
		}else{
			return faceValue;
		}
    }

    public int getSuit(){
        return suit;
    }

    public String toString()
   {
      String cardName = null;

      switch (faceValue)
      {
         case 2:   cardName = "Two";
                   break;
         case 3:   cardName = "Three";
                   break;
         case 4:   cardName = "Four";
                   break;
         case 5:   cardName = "Five";
                   break;
         case 6:   cardName = "Six";
                   break;
         case 7:   cardName = "Seven";
                   break;
         case 8:   cardName = "Eight";
                   break;
         case 9:   cardName = "Nine";
                   break;
         case 10:  cardName = "Ten";
                   break;
         case 11:  cardName = "Jack";
                   break;
         case 12:  cardName = "Queen";
                   break;
         case 13:  cardName = "King";
                   break;
         case 14:  cardName = "Ace";
      }

      switch (suit)
      {
         case 0:   cardName += " of Clubs";
                   break;
         case 1:   cardName += " of Spades";
                   break;
         case 2:   cardName += " of Hearts";
                   break;
         case 3:   cardName += " of Diamonds";
                   break;
      }

      return cardName;
   }
}
