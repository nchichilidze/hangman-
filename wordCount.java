import java.util.StringTokenizer;

import acm.program.ConsoleProgram;

public class wordCount extends ConsoleProgram  {
	public void run() { 
		String sent = readLine("sentence: "); 
		println(countIt(sent));
	}
	
	public int countIt(String sent) { 
		StringTokenizer tok = new StringTokenizer (sent) ; 
		int counter=0; 
		while(tok.hasMoreTokens()) { 
			tok.nextToken();
			++counter;
		}
		return counter;
	}
}
