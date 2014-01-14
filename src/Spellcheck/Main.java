package Spellcheck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// http://www.twitch.tv/problems/spellcheck

public class Main {
	public static void main(String[] args) {
		String dictionaryDirectory;
		
		// Optional parameter for using a different dictionary
		if (args.length > 0) {
			dictionaryDirectory = args[0];
		}
		else {
			dictionaryDirectory = "/usr/share/dict/words";
		}
		
		Trie trie = new Trie(dictionaryDirectory);

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			// Run indefinitely until killed
			while (true) {
				System.out.print("> ");
				System.out.println(trie.suggestCorrection(br.readLine()));
			}
		}
		catch (IOException iox) {
			System.out.println(iox);
		}
	}
}