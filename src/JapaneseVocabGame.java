//Dakota Jackson
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class JapaneseVocabGame {

	public static void main(String[] args) {
		
		try {
		File jpnFile = new File("C:\\Users\\Dakota\\Desktop\\JapaneseWordList.txt");
		File engFile = new File("C:\\Users\\Dakota\\Desktop\\EnglishTranslationWordList.txt");
		ArrayList<String> jpnWords = new ArrayList<String>();
		ArrayList<String> engWords = new ArrayList<String>();
		Scanner scanner = new Scanner(jpnFile);
		
		while(scanner.hasNextLine()) {
			String currentWord = scanner.nextLine();
			jpnWords.add(currentWord);		
			}
		
		scanner.close();
		
		Scanner scanner2 = new Scanner(engFile);
		
		while(scanner2.hasNextLine()) {
			String currentWord = scanner2.nextLine();
			engWords.add(currentWord);		
			}
		
		scanner2.close();

	 /*********************************************************************************************
	 * TYPING OUT MY IDEAS BEFORE CODING:
	 * -----------------------------------
	 * -so skeleton code for actual game:
	 * -randomly pick 0 or 1: 0 = Japanese words list, 1 = English
	 * -Pick a random word from the list indicated by the number above
	 * 		-(that is now the displayed vocabulary word)
	 * -grab the same number index word from opposite list (right) and 3 other random words (wrong)
	 * -display them randomly as choices 1 through 4
	 * -If right choice, then repeat process with different word(s) 
	 *	--> (so probably a while loop and maybe a counter like "till player gets 10 right")
	 *		if counter hits [x] (probably 10), then exit as player has won
	 * -if wrong, tell, then move to next question
	 * -profit(?)
	***********************************************************************************************/
		
		
		/* TO-DO: ADD A prompt asking user how many they would like to get correct before ending:
		 *  -scan for that input and then use it as the number to compare against before setting the
		 *   gameFlag to done
		 */
		
			boolean gameFlag = false;
			//When this reaches 10, the game flag sets to true
			int correctCounter = 0;
			
			int questionNumber = 1;
			
			//TO-DO: Make it so the same VocabWord cannot be chosen twice (or at least not back-to-back)
			ArrayList<String> usedJpnVocabWords = new ArrayList<String>();
			ArrayList<String> usedEngVocabWords = new ArrayList<String>();
			
			while(!gameFlag) {
				
				String currentVocabWord = "";
				
				
				//***THIS CODE NEEDS TO BE FIXED OR REMOVED*********************************
				/*System.out.print("How many would you like to get correct before ending? ");
				Scanner userCorrectInput = new Scanner(System.in);
				int userCorrect = 1;
				try {
					userCorrect = userCorrectInput.nextInt();
				} catch (IllegalArgumentException e) {
					System.out.println("Please enter a valid number.");
					userCorrectInput.nextLine();
				}
				
				userCorrectInput.close();*/
				
				
				//grabbing from Japanese list for currentVocabWord
				if(Math.random() > 0.4) {
					
					int index = 0;
					int a = 0;
					while (a < 1) {
						//If all unique choices of vocabulary words from list are exhausted, reset the list
						if(usedJpnVocabWords.size() == jpnWords.size()) {
							usedJpnVocabWords.clear();
						}
						index = new Random().nextInt(jpnWords.size());
						currentVocabWord = jpnWords.get(index); 
						//If the vocabulary word has not been used yet, continue
						if(!usedJpnVocabWords.contains(currentVocabWord)) {
							usedJpnVocabWords.add(currentVocabWord);
							a++;
						}
					}
					//choices here
					ArrayList<String> choices = new ArrayList<String>();
					//This adds the right choice to the list
					String rightChoice = engWords.get(index);
					choices.add(engWords.get(index));
					
					int x = 0;
					while(x != 3) {
						String wrongChoice = engWords.get(new Random().nextInt(engWords.size()));
						//System.out.println(wrongChoice);
						boolean containsFlag = choices.contains(wrongChoice);
						if(!containsFlag) {
							choices.add(wrongChoice);
						    //System.out.println("Debug Flag 1");
							x++;
						}
					}
					
					//shuffles order of choices
					Collections.shuffle(choices);
					int correctChoiceNum = choices.indexOf(rightChoice) + 1;
					//System.out.println("The correct choice is: " + correctChoiceNum);
					
					
					System.out.println(questionNumber + ") VOCAB WORD: " + currentVocabWord);
					questionNumber++;
					System.out.println("-----------------------------------");
					System.out.println("1: " + choices.get(0));
					System.out.println("2: " + choices.get(1));
					System.out.println("3: " + choices.get(2));
					System.out.println("4: " + choices.get(3));
					
					System.out.println();
					System.out.print("Enter the number indicating the correct translation: ");
					
					Scanner userInput = new Scanner(System.in);
					int userInt = userInput.nextInt();
					
					
					//Checks for correctness of user choice
					if(userInt == correctChoiceNum) {
						System.out.println("That's right!\n\n");
						correctCounter++;
					} else {
						System.out.println("That is incorrect. Here's another question!\n");
					}
					
					//If the player has got 10 correct, the game is over
					if(correctCounter == 10) {
						gameFlag = true;
					}
			
					
					
				//grabbing from	English list for currentVocabWord
				} else {
					
					int index = 0;
					int a = 0;
					while (a < 1) {
						//If all unique choices of vocabulary words from list are exhausted, reset the list
						if(usedEngVocabWords.size() == engWords.size()) {
							usedEngVocabWords.clear();
						}
						index = new Random().nextInt(engWords.size());
						currentVocabWord = engWords.get(index); 
						//If the vocabulary word has not been used yet, continue
						if(!usedEngVocabWords.contains(currentVocabWord)) {
							usedEngVocabWords.add(currentVocabWord);
							a++;
						}
					}
					//choices here
					ArrayList<String> choices = new ArrayList();
					//This adds the right choice to the list
					String rightChoice = jpnWords.get(index);
					choices.add(jpnWords.get(index));
					
					int x = 0;
					while(x != 3) {
						String wrongChoice = jpnWords.get(new Random().nextInt(jpnWords.size()));	
						//System.out.println(wrongChoice);
						boolean containsFlag = choices.contains(wrongChoice);
						if(!containsFlag) {
							choices.add(wrongChoice);
							//System.out.println("Debug Flag 2");
							x++;
							
						}
					}
					
					//shuffles order of choices
					Collections.shuffle(choices);
					int correctChoiceNum = choices.indexOf(rightChoice) + 1;
					//System.out.println("The correct choice is: " + correctChoiceNum);
					
					System.out.println(questionNumber + ") VOCAB WORD/PHRASE: " + currentVocabWord);
					questionNumber++;
					System.out.println("-----------------------------------");
					System.out.println("1: " + choices.get(0));
					System.out.println("2: " + choices.get(1));
					System.out.println("3: " + choices.get(2));
					System.out.println("4: " + choices.get(3));
					
					System.out.println();
					System.out.print("Enter the number indicating the correct translation: ");
					
					Scanner userInput = new Scanner(System.in);
					int userInt = userInput.nextInt();
					
					//Checks for correctness of user choice
					if(userInt == correctChoiceNum) {
						System.out.println("That's right!\n\n");
						correctCounter++;
					} else {
						System.out.println("That is incorrect. Here's another question!\n");
					}
					
					
					//If the player has got 10 correct, the game is over
					if(correctCounter == 10) {
						gameFlag = true;
					}
				}
				
			}
			System.out.println("\nYou got " + correctCounter + " correct! Thanks for playing!");
			//System.out.println(currentVocabWord);

		
		
		} catch(FileNotFoundException E) {
			System.out.println("The inputted file was not found.");
		} catch (Exception E) {
			System.out.println("An error occured.");
		}	
		
	}
	


}
