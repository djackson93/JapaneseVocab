//Dakota Jackson
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class JapaneseVocabGame {

	public static void main(String[] args) {
		
		File jpnFile = new File("C:\\Users\\Dakota\\Desktop\\JapaneseWordList.txt");
		File engFile = new File("C:\\Users\\Dakota\\Desktop\\EnglishTranslationWordList.txt");
		ArrayList<String> jpnWords = new ArrayList<String>();
		ArrayList<String> engWords = new ArrayList<String>();
		
	
		try {
			Scanner scanner = new Scanner(jpnFile);
			
			while(scanner.hasNextLine()) {
				String currentWord = scanner.nextLine();
				jpnWords.add(currentWord);		
				}
			
			scanner.close();
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			Scanner scanner2 = new Scanner(engFile);
			
			while(scanner2.hasNextLine()) {
				String currentWord = scanner2.nextLine();
				engWords.add(currentWord);		
				}
			
			scanner2.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
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
	 *		- EDIT: player lives counter has taken over as game ending counter
	 * -if wrong, tell, then move to next question
	 * -profit(?)
	***********************************************************************************************/
		
		String playerName = gameIntroduction();
		
		gameStart(jpnWords, engWords, playerName);
		
	}
	
	public static String gameIntroduction() {
		
		//default value to passed in case something goes wrong
		String playerName = "Null";
		
		System.out.println("Hi! Welcome to my Japanese-English vocabulary game!\n"
				+ "I'm Cabby! I'll be explaining definitions "
				+ "and telling you whether your choices are right or wrong!");
		
		System.out.println("\nCABBY: First off, can I get you to enter a name so we can keep track of your scores?");
		
		Scanner inputPlayerName = new Scanner(System.in);
		playerName = inputPlayerName.next();
	
		
		System.out.println("\nThanks " + playerName + "! Next we can get you started with the rules if needed!\n" 
				+ "Do you need to hear the rules?");
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("1: Yes\n" + "2: No");
		
		Scanner inputRulesChoice = new Scanner(System.in);
		int rulesChoice = inputRulesChoice.nextInt();
		
		if(rulesChoice == 1) {
			System.out.println("\nCABBY:\nOkay! Let's get you started with the rules!\n"
				+ "it's actually pretty simple. You will be prompted with a word/phrase in either " 
				+ "English or Japanese.\nIt will be accompanied by 4 choices of the opposite language and you must choose "
				+ "the correct choice!");
			
			System.out.println("If you get the answer correct you will move onto the next question worry free! However, "
					+ "if you get it wrong,\nyou will be told the correct choice and will lose 1 of your 3 remaining lives.");
			
			System.out.println("If your lives deplete to 0, the game will be over and the total you got correct will be tallied up and "
					+ "kept record of!\nThat is basically all there is to it! But remember, don't worry too much about failing and losing "
					+ "in the game.\nIt's all about learning and failing is a part of the learning process!");
		}
		
		System.out.println("\nCABBY: Okay! Have Fun!");
		
		return playerName;
		}
		

	private static void gameStart(ArrayList<String> jpnWords, ArrayList<String> engWords, String playerName) {
		
			System.out.println("\n***GAME IS STARTING***\n");
			String name = playerName;
		
			//NEW IDEA: SET LIVES (Prob 3), then keep a score of how many they got right
			int numOfLives = 3;
			int correctCounter = 0;
			//Write to text file (for now) a list of scores
		
			boolean gameFlag = false;
			
			int questionNumber = 1;
			
			//TO-DO: Make it so the same VocabWord cannot be chosen twice (or at least not back-to-back)
			ArrayList<String> usedJpnVocabWords = new ArrayList<String>();
			ArrayList<String> usedEngVocabWords = new ArrayList<String>();
			
			while(!gameFlag) {
				
				String currentVocabWord = "";
				
				
				
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
					
					System.out.println("\n" + questionNumber + ") VOCAB WORD: " + currentVocabWord);
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
						System.out.println("\nCABBY: That's right!");
						correctCounter++;
					} else {
						System.out.println("\nCABBY:\nThat is incorrect. -----> '" + currentVocabWord + "' means '" + rightChoice + "' in English!");
						numOfLives--;
						//If the user gets the answer wrong, put the vocab word back in usable list
						usedJpnVocabWords.remove(currentVocabWord);
						
						if(!(numOfLives == 0)) {
							System.out.println("You have " + numOfLives + " lives left.\nHere is another question!");
						}
					}
					
					System.out.println("******************************************************************");
					
					//If the player has lost all lives, the game is over
					if(numOfLives == 0) {
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
					
					System.out.println("\n" + questionNumber + ") VOCAB WORD/PHRASE: " + currentVocabWord);
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
						System.out.println("\nCABBY: That's right!");
						correctCounter++;
					} else {
						System.out.println("\nCABBY:\nThat is incorrect. -----> '" + currentVocabWord + "' means '" + rightChoice + "' in Japanese!");
						numOfLives--;
						
						//If the user gets the answer wrong, put the vocab word back in usable list
						usedJpnVocabWords.remove(currentVocabWord);
						
						if(!(numOfLives == 0)) {
						System.out.println("You have " + numOfLives + " lives left.\nHere is another question!");
						}
					}
					
					System.out.println("******************************************************************");
					
					
					//If the player has lost all lives, the game is over
					if(numOfLives == 0) {
						gameFlag = true;
					}
				}
				
			}
			System.out.println("\nCABBY:\nYou have lost all your lives!");
			System.out.println("You got " + correctCounter + " correct " + name +"!");
			//System.out.println(currentVocabWord);
			
			//Write to file highscores		
			placeScore(name, correctCounter);
	}
	
	private static void placeScore(String name, int correctCounter) {
		
		
		/************************************************************************************************
		 * IDEA for this:
		 *  - Get the player's name and the current Score they just received
		 *  - Check to see if the current player name has been used before
		 *  	-If yes, Check to see what their highscore listed is
		 *  		-If current Score is greater, overwrite and congratulate!
		 *  		-if current Score is less than, do nothing
		 *  	-If no, create a new entry to be added to the highscore file
		 * 
		 * - Would like to have the entries in ascending order in format: "'name' - 'score'"
		 * 		- maybe use an array list or something every time writing to file by retrieving 
		 * 		  all scores currently in list and placing in using binary search and then rewriting the
		 * 		  file(?)
		 * 
		 * - Also leads to the idea of creating a getScore method that retrieves the name and score of:
		 * 		-A: an inputted player name (if it exists)
		 * 		-B: the highest score recorded 
		 *************************************************************************************************/
		
		//create a new score object with player name and player score
		Score newScore = new Score(name, correctCounter);
		
		String fileName = "HighScores.txt";
		String filePath = "C:\\Users\\Dakota\\Desktop\\" + fileName;
		File file = new File(filePath);
		
		try {
			
			//If file does not yet exist, create it
			if (!(file.exists())) {
				file.createNewFile();
			
			}
			
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter writer = new BufferedWriter(fw);
			
			writer.write(newScore.toString());
			writer.close();
			
			System.out.println("Your score has been succesfully recorded! Thanks for playing!");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
		
		
	private static Score getScore(String userName) {
		
		String fileName = "HighScores.txt";
		String filePath = "C:\\Users\\Dakota\\Desktop\\" + fileName;
		File file = new File(filePath);
		
		try {
			
			//If file does not yet exist, create it
			if (!(file.exists())) {
				System.out.println("There are no scores recorded for the game yet!");
			
			} else {		
				
				FileReader fr = new FileReader(file);
				BufferedReader reader = new BufferedReader(fr);
				
				String s;
				while((s = reader.readLine()) != null) {
					String[] parts = s.split("-");
					//This gives the player name by getting everything from start up to the "-" without the space before it
					String playerName = parts[0].substring(0, parts[0].length()-1);
					
					//This should get the score of the player by getting everything after dash minus the spaces and date
					int playerScore = Integer.valueOf(parts[1].substring(1, parts[1].length()-20));
					
					ArrayList<Score> listedPlayers = new ArrayList<Score>();
					
					//This should create a list of Score objects based off what's in the highScores file
					listedPlayers.add(new Score(playerName, playerScore));
					listedPlayers.
				}
			
				System.out.println("Your score has been succesfully recorded! Thanks for playing!");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private static Score getHighScore() {
		
		String fileName = "HighScores.txt";
		String filePath = "C:\\Users\\Dakota\\Desktop\\" + fileName;
		File file = new File(filePath);
		
	}
}
