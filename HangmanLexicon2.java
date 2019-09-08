/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import acm.util.*;


public class HangmanLexicon2 {
	public ArrayList <String> list = new ArrayList<String>();
	public HangmanLexicon2() {
	try { 
		BufferedReader br = new BufferedReader(new FileReader ("hangmanLexicon.txt"));
		while (true) { 
			String word = br.readLine();
			if (word == null) break;
			list.add(word);
		}
		} catch (IOException e) { 
	}
	} 

	
	/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return list.size();
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
		return list.get(index);
	}
}
