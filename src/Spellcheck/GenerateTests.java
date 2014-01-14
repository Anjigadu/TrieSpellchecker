package Spellcheck;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Stack;

public class GenerateTests {
	public static int MAX_NUMBER_OF_DUPLICATE_CHAR = 3;

	public static void main(String[] args) {
		PrintWriter writer = null;
		BufferedReader reader = null;
		try  
		{
			writer = new PrintWriter(new FileWriter("tests.txt"));

			// TODO: If spell checker starts using different dictionary, and this isn't updated
			// it will cause problems since the knowledge of what is considered a valid word
			// is in different domains. Possibly fix with using a config file that both code
			// can access
			reader = new BufferedReader(new FileReader("/usr/share/dict/words"));

			String line;

			while ((line = reader.readLine()) != null) {
				writer.println(mutateWord(line));
			}
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
				if (reader != null) {
					reader.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Inserts a bunch of duplicate characters, then randomly substitutes
	 * them with different vowel for vowels, and upper/lower case for all characters
	 * @param word
	 * @return mutated String
	 */
	public static String mutateWord(String word) {
		String mutated = "";
		
		char[] tmp = word.toCharArray();

		for (int i = 0; i < word.length(); ++i) {
			Stack<Character> randomValidTypo = Trie.createPermutations(word.charAt(i));
			int randomIndex = (int) (Math.random() * randomValidTypo.size());
			tmp[i] = randomValidTypo.elementAt(randomIndex).charValue();
		}


		for (int i = 0; i < tmp.length; ++i) {
			// Insert up to 4 duplicate characters and at least 1
			int randomNumberOfDuplicates = (int) (Math.random() * MAX_NUMBER_OF_DUPLICATE_CHAR)+1; 
			for (int j = 0; j < randomNumberOfDuplicates; ++j) {
				mutated += tmp[i];
			}
		}
		
		return mutated;
	}

}
