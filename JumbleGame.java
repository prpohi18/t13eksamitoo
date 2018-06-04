import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.io.*;
import java.util.*;

public class JumbleGame{
	int numberOfGuesses = 0;

    private static final String[] WORDS_DATABASE = new String[] {
        "kass","maja","koer","nina","auto","kood"
    };
    
    public static void main(String[] args) {
        JumbleGame jg = new JumbleGame();
        jg.startGame();
    }

    private void startGame() {
        String original = selectRandomWord();
        String shuffled = getShuffledWord(original);
        boolean gameOn = true;
        while(gameOn) {
            System.out.println("Segamini aetud sõna on: "+shuffled);
            numberOfGuesses++;
            String userGuess = getUserGuess();	

try {

			//PrintWriter pw = new PrintWriter(new FileWriter("./dataOut.txt"));
            FileWriter fileWriter = new FileWriter("./dataOut.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
			
			printWriter.println("Sõna oli: " + userGuess);
            printWriter.println("Arvasid sõna ära: " + numberOfGuesses + ". korraga.\n");
			
        
		printWriter.flush();
        printWriter.close();
       
        } catch (IOException e) {
            // do something, when the file is not readable
            System.out.println("The file could not be read.");
        }
		
            if(original.equalsIgnoreCase(userGuess)) {
                System.out.println("Õige! Arvasid sõna ära "+numberOfGuesses+" arvamisega");
                gameOn = false;
            }else {
                System.out.println("Vale vastus");
            }
			
			
        }        
    }
    

    public String getUserGuess() {
        Scanner sn = new Scanner(System.in);
        System.out.println("Kirjuta originaalne sõna: ");
        return sn.nextLine();
    }
    

    public String selectRandomWord() {
        int rPos = ThreadLocalRandom.current().nextInt(0, WORDS_DATABASE.length);
        return WORDS_DATABASE[rPos];
    }
    

    public String getShuffledWord(String original) {
        String shuffledWord = original;
        int wordSize = original.length();
        int shuffleCount = 10; 
        for(int i=0;i<shuffleCount;i++) {
            int position1 = ThreadLocalRandom.current().nextInt(0, wordSize);
            int position2 = ThreadLocalRandom.current().nextInt(0, wordSize);
            shuffledWord = swapCharacters(shuffledWord,position1,position2);
        }
        return shuffledWord;
    }

	
    private String swapCharacters(String shuffledWord, int position1, int position2) {
        char[] charArray = shuffledWord.toCharArray();

        char temp = charArray[position1];
        charArray[position1] = charArray[position2];
        charArray[position2] = temp;
        return new String(charArray);
    }
	
	 public void writing() {
        try {

			//PrintWriter pw = new PrintWriter(new FileWriter("./dataOut.txt"));
            FileWriter fileWriter = new FileWriter("dataOut.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.println("Arvasid sõna ära: " + numberOfGuesses + ". korraga.\n");
        
		printWriter.flush();
        printWriter.close();
       
        } catch (IOException e) {
            // do something, when the file is not readable
            System.out.println("The file could not be read.");
        }
		
    }

    
}