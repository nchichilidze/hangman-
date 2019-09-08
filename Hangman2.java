
/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman2 extends ConsoleProgram {

	private String mysteryWord; // the word the user has to guess.
	private String hiddenWord; // the hidden word the program returns each time.
	private String letterEntered; // the letter entered by the user.
	private char character; // letter turned into char.
	private int initCounter = 8;// number of guesses.
	private static final int LIVES = 3;
	private int lives = 3;
	private int lost = 0;
	private int won = 0;
	private String correctGuesses = "";

	/* private variables: */
	private HangmanLexicon2 hl = new HangmanLexicon2();
	private RandomGenerator rg = RandomGenerator.getInstance();
	private HangmanCanvas2 canvas;

	public void init() { // initializes the program adds a canvas where the guy
							// gets drawn.
		canvas = new HangmanCanvas2();
		add(canvas);
	}

	/* runs the program */
	public void run() {
		firstMessage();
		for (int i = 0; i < LIVES; i++) {
			preActions();
			play();
		}
		endScreen();
	}

	public void preActions() {
		initialGap();
		correctGuesses = "";
		randomizeWord(); // randomly chooses the word
		hiddenWord = hiddenWord(); // hides word
		greetingMessage(); // prints a greeting message
		actionsOnCanvas();
	}

	public void initialGap() {
		for (int i = 0; i < 2; i++) {
			println(""); // spaces between games so the console program is more
							// readable
		}
	}

	public void actionsOnCanvas() {
		canvas.reset();
		canvas.drawHearts(lives); // draws hearts accordingly
		canvas.displayWord(hiddenWord); // puts the hidden word on the canvas
	}

	private void endScreen() { // the display at the end of the game
		canvas.zeroHearts();
		canvas.reset();
		canvas.displayEndScreen(lost, won);
	}

	private void randomizeWord() { // randomly chooses word from the list.
		int max = hl.getWordCount();
		int index = rg.nextInt(0, max);
		mysteryWord = hl.getWord(index);
	}

	private String hiddenWord() { // turns the word completely into dashes
		String hiddenWord = "";
		for (int i = 0; i < mysteryWord.length(); i++) { // turns the word into
															// dashes in order
															// to hide it
			hiddenWord = hiddenWord + "-";
		}
		return hiddenWord;
	}

	private void greetingMessage() { // prints a greeting message
		println("the word now looks like this: " + hiddenWord);
		println("you have " + initCounter + " guesses left.");
		canvas.drawInitialScreen();
	}

	private void correctMessage(int counter) {
		println("that guess is correct.");
		println("the word now looks like this " + hiddenWord + ".");
		println("you have " + counter + " guess(es) left."); // tells the user
																// how many
																// guesses are
																// left
	}

	private void incorrectMessage(int counter) {
		println("there are no " + character + "'s in the word.");
		println("the word now looks like this " + hiddenWord + ".");
		println("you have " + counter + " guess(es) left.");
	}

	private void caseLost(String mysteryWord) {
		println("you could not guess the word.");
		println("the word was " + mysteryWord + ".");
		lives--;
		lost++;
	}

	private void caseWon(String mysteryWord) {
		println("you guessed the word: " + mysteryWord);
		println("you win. ");
		won++;
		lives--;
	}

	private void play() {
		int counter = 8;
		while (true) {
			letterEntered = readLine("your guess: "); // gets the letter from
														// the user
			if (isLegal(letterEntered) == false) { // checks if its a legal or
													// not
				println("that is not a legal guess:");
				continue;
			}
			letterEntered = letterEntered.toUpperCase(); // returns the same
															// letter but in
															// upper case
			character = letterEntered.charAt(0); // gets the char
			if (contains(character) == true) { // if the word contains this
												// character
				if (isEntered(character) == true) {
					println("you have already entered that guess, try again.");
					continue;
				}
				revealWord(character); // reveals the correct characters place
										// in the word
				correctGuesses = correctGuesses + character;
				canvas.displayWord(hiddenWord);
				correctMessage(counter);
			} else if (contains(character) == false) {
				counter--; // - 1 guess
				canvas.drawGuys(counter);// draws a different guy
				incorrectMessage(counter);
				canvas.noteIncorrectGuess(character); // puts the incorrect
														// letter on the canvas
			}
			if (counter == 0 && !hiddenWord.equals(mysteryWord)) { // means the
																	// player
																	// has lost
				caseLost(mysteryWord);
				break;
			} else if (hiddenWord.equals(mysteryWord)) { // the player has won
				caseWon(mysteryWord);
				break;
			}
		}
	}

	public void firstMessage() {
		println("welcome to hangman!");
		println("You have three tries. ");
		println("This guy won't die if you win once or more.");
	}

	private boolean contains(char character) { // this boolean returns true if
												// the word contains this letter
		for (int i = 0; i < mysteryWord.length(); i++) {
			if (mysteryWord.charAt(i) == character)
				return true;
		}
		return false;
	}

	/**
	 * makes sure the character entered is actually a character and its length
	 * is 1 also makes sure its a letter and not any other symbol by checking
	 * whether its from a to z
	 */
	private boolean isLegal(String letterEntered) {
		if (letterEntered.length() == 1 && letterEntered.charAt(0) >= 'a' && letterEntered.charAt(0) <= 'z') {
			return true;
		} else {
			return false;
		}
	}

	private boolean isEntered(char character) {
		if (correctGuesses.indexOf(character) != -1) {
			return true;
		}
		return false;
	}

	/**
	 * reveals the character in the word guessed by the user .
	 */
	private void revealWord(char character) {
		for (int i = 0; i < mysteryWord.length(); i++) {
			if (character == mysteryWord.charAt(i)) {
				hiddenWord = hiddenWord.substring(0, i) + character + hiddenWord.substring(i + 1);
			}
		}
	}
}
