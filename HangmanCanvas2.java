
/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import java.awt.Color;
import java.awt.Font;

import acm.graphics.*;
import javafx.animation.PauseTransition;

public class HangmanCanvas2 extends GCanvas {

	private GLabel hiddenWord;
	private GLabel incorrectLetter;
	private String incorrectGuesses = "";
	private Font firstFont = new Font(Font.MONOSPACED, Font.BOLD, 16);
	private Font secondFont = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
	private GImage zero;
	private GImage one;
	private GImage two;
	private GImage three;
	private GImage four;
	private GImage five;
	private GImage six;
	private GImage seven;
	private GImage eight;

	/** Resets the display so nothing appears */
	public void reset() {
		if (zero != null)
			removeAll();
		incorrectGuesses = "";
		add(zero);
	}

	/**
	 * Updates the word on the screen to correspond to the current state of the
	 * game. The argument string shows what letters have been guessed so far;
	 * unguessed letters are indicated by hyphens.
	 */
	public void displayWord(String word) {
		if (hiddenWord != null)
			remove(hiddenWord);
		double y = getHeight() / 2 - 180 + 320 + OFFSET;
		hiddenWord = new GLabel(word);
		double x = getWidth() / 2 - 187 / 2;
		hiddenWord.setFont(firstFont);
		add(hiddenWord, x, y);
	}

	/**
	 * draws the first guy on the screen the game renews by redrawing this
	 * screen
	 */

	public void drawInitialScreen() {
		double x = getWidth() / 2 - 187 / 2;
		double y = getHeight() / 2 - 180;
		zero = new GImage("level0.png");
		add(zero, x, y);
	}

	/**
	 * Updates the display to correspond to an incorrect guess by the user.
	 * Calling this method causes the next body part to appear on the scaffold
	 * and adds the letter to the list of incorrect guesses that appears at the
	 * bottom of the window.
	 */
	public void noteIncorrectGuess(char letter) {
		double y = getHeight() / 2 - 180 + 320 + 2 * OFFSET;
		if (incorrectLetter != null)
			remove(incorrectLetter);
		incorrectGuesses = incorrectGuesses + letter;
		incorrectLetter = new GLabel(incorrectGuesses);
		double x = getWidth() / 2 - 187 / 2;
		incorrectLetter.setFont(secondFont);
		add(incorrectLetter, x, y);
	}

	/**
	 * draws hearts above the image and changes it accordingly while counting
	 * the lives.
	 */

	public void drawHearts(int lives) {
		double x = getWidth() / 2 - 80 / 2;
		double y = getHeight() / 2 - 210;
		if (lives == 3) {
			GImage threeLives = new GImage("3lives.png", x, y);
			add(threeLives);
		}
		if (lives == 2) {
			GImage twoLives = new GImage("1life.png", x, y);
			add(twoLives);
		}
		if (lives == 1) {
			GImage oneLife = new GImage("2lives.png", x, y);
			add(oneLife);
		}
	}

	// displays zero hearts in the end
	public void zeroHearts() {
		double x = getWidth() / 2 - 80 / 2;
		double y = getHeight() / 2 - 210;
		GImage zeroLives = new GImage("0lives.png", x, y);
		add(zeroLives);
	}

	public void displayEndScreen(int lost, int won) {
		if (won >= 1) { // this gif appears if the player has won at least once
			double x = getWidth() / 2 - 187 / 2;
			double y = getHeight() / 2 - 180;
			GImage win = new GImage("won.gif", x, y);
			add(win);
		} else { // this gif appears if the player has lost 3 times
			double x = getWidth() / 2 - 187 / 2;
			double y = getHeight() / 2 - 180;
			GImage lose = new GImage("lost.gif", x, y);
			add(lose);
		}
		gameCounterLabels(won, lost);
	}

	/**
	 * adds counters at the bottom of the screen showing games won and games
	 * lost
	 */
	public void gameCounterLabels(int won, int lost) {
		double x = getWidth() / 2 - IWIDTH / 2;
		double y = getHeight() / 2 + IHEIGHT / 2 + OFFSET;
		GLabel gamesWon = new GLabel("games won: " + won);
		GLabel gamesLost = new GLabel("games lost: " + lost);
		gamesWon.setFont(firstFont);
		gamesLost.setFont(firstFont);
		gamesWon.setColor(Color.CYAN);
		gamesLost.setColor(Color.RED);
		add(gamesWon, x, y);
		add(gamesLost, x, y + OFFSET);
	}

	/**
	 * draws the guy changing expressions as the water rises
	 */
	public void drawGuys(int counter) {
		double x = getWidth() / 2 - 187 / 2;
		double y = getHeight() / 2 - 180;
		if (counter == 7) {
			one = new GImage("one.png");
			add(one, x, y);
		}
		if (counter == 6) {
			two = new GImage("level2.png", x, y);
			add(two);
		}
		if (counter == 5) {
			three = new GImage("level3.png", x, y);
			add(three);
		}
		if (counter == 4) {
			four = new GImage("level4.png", x, y);
			add(four);
		}
		if (counter == 3) {
			five = new GImage("level5.png", x, y);
			add(five);
		}
		if (counter == 2) {
			six = new GImage("level6.png", x, y);
			add(six);
		}
		if (counter == 1) {
			seven = new GImage("level7.png", x, y);
			add(seven);
		}
		if (counter <= 0) {
			eight = new GImage("level8.png", x, y);
			add(eight);
		}
	}

	/* Constants for the simple version of the picture (in pixels) */
	private static final int OFFSET = 30;
	private static final int IWIDTH = 187; // image width
	private static final int IHEIGHT = 320; // image height

}
