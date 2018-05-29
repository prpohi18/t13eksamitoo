
package eksam;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Hangman
{
	public static void main( String[] args )
	{
            
                //AJA KÄIMA PANEMINE
                long startTime = System.currentTimeMillis();

                long total = 0;
                for (int i = 0; i < 10000000; i++) {
                    total += i;
                }

                
                //HANGMAN START
                String[] word_list = { "java", "kass", "koer", "elevant", "kotttool", "jäääär", "hügieen", "dušš", "ripsmed", "juuksed", "käärid" };
		String rand_word;
		char[] hidden_word;
		Scanner keyboard = new Scanner(System.in);
		String user_guess;
		int miss_chance = 0;
		char[] missed = new char[8];
		boolean letter_found = false, solved = false;
				
		rand_word = word_list[ (int)(Math.random() * word_list.length) ];
		hidden_word = new char[rand_word.length()];
		for ( int i = 0; i < rand_word.length(); i++ )
		{
			hidden_word[i] = '_';
		}		
				
		while (miss_chance < 7 && ! solved)
		{
			System.out.println( "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\n" );
			System.out.println( "Sul on " + (7 - miss_chance) + " arvamist jäänud." );
			System.out.print( "Sõna:\t" );
			for ( int i = 0; i < rand_word.length(); i++ ) {
				System.out.print( hidden_word[i] + " " );
			}
			System.out.print("\nMööda arvamised: ");
			for ( int i = 0; i < missed.length; i++ ) {
				System.out.print( missed[i] );
			}
			
			System.out.print( "\nArvatud tähed: " );
			user_guess = keyboard.next();
			letter_found = false;
			
			for ( int i = 0; i < rand_word.length(); i++ ) {
				if ( user_guess.charAt(0) == rand_word.charAt(i) ) {
					hidden_word[i] = rand_word.charAt(i);
					letter_found = true;
				}
			}
			if (!letter_found) {
				miss_chance++;
				missed[miss_chance] = user_guess.charAt(0);	
			}
			
			int hidden_count = 0;
			for ( int i = 0; i < rand_word.length(); i++ ) {
				if ( '_' == hidden_word[i] )
					hidden_count++;
			}
			if (hidden_count > 0) 
				solved = false;
			else
				solved = true;
		}

		System.out.println( "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\n" );
		System.out.println( "Sul on " + (7 - miss_chance) + " katset veel." );
		System.out.print( "Sõna:\t" );
		for ( int i = 0; i < rand_word.length(); i++ ) {
			System.out.print( hidden_word[i] + " " );
		}
		System.out.print("\nMööda pandud tähed: ");
		for ( int i = 0; i < missed.length; i++ ) {
			System.out.print( missed[i] );
		}
                
		if (solved){
			System.out.println( "\nSa arvasid sõna ära!" );
                        long stopTime = System.currentTimeMillis();
                        long elapsedTime = stopTime - startTime;
                        System.out.println("Sõna ära arvamiseks kulus " + elapsedTime / 1000 + " sekundit");
                }
                else{
			System.out.println( "\n\nSa kaotasid ja sõna oli..." + rand_word );
                        long stopTime = System.currentTimeMillis();
                        long elapsedTime = stopTime - startTime;
                        System.out.println("Sul kulus valesti arvamiseks " + elapsedTime / 1000 + " sekundit");
                }
    }
}