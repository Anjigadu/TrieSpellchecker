Directions

To run, assuming java environment variable is set, enter
java -Dfile.encoding=US-ASCII -classpath bin Spellcheck.
Main [OPTIONAL DICTIONARY FILE DIRECTORY]

at the project's root folder. Without dictionary file directory, it defaults to /usr/share/dict/words

To test, execute the test.sh file. 

Discussion
This is slightly different from real spell checkers out there in the sense that it doesn't rely on probablity, 
and we don't care about distances from the original input as long as it follows the given set of 3 rules.

Given user input, the number of candidate, n, Strings to search is as follows:

n *= [length of duplicate segments] for every duplicate segments
n *= 5 for every vowel
n *= 2 for every character in the String

This grows exponentially by length of the original input, and it would take ridiculously long just to
generate all of the candidates. 

So when we say look up has to be less than O(n), where n is number of words in the dictionary, the 
length of the dictionary was never really the problem. Once it's indexed in a hash table, look up is constant. 

So we use a Trie instead. That way, insteat of wasting time generating extremely large number of strings, 
we generate the candidate prefixes, going through the input character by character. This way, ridiculous inputs
like "aPPPPPPLLLEEEEEEEE" with all the its mutatable vowels at the end will never be checked -- the prefix
"aP" does not exist in the first place in the tree.

When deciding how to implement the Node of the trie, I debated between HashMap and Array of characters. After 
some thinking, I went with the former, because English follows a certain pattern, and it seemed likey that
many nodes will waste up te 51 spaces in the array. In addition, if we decided to use a different dictionary, 
the HashMap implementation supports all characters. The disadvantage is same O(log n) look up time that's slower
by a constant factor because of the hash function.

Because we do not care about which candidate we return as long as it is valid word, we use DFS for better speed
and memory. There are subtle optimization like always looking up capital letters last (because we would rarely
see a capital letter anywhere other than the first character) and assuming for any duplicate characters, the user
meant just one character first (because ridiulous inputs like aaaaaaaaa are harder to deal with and search for
than a).

For testing, I wrote a simple java application that makes a mutated version of every word in the dictionary,
using the same algorithm in the Trie to select characters to search, except I pick a random one of the characters. 
This java generates a test.txt file so I can keep track of what is exactly being tested, and a bash script inputs
it into the Trie, whose result is printed to result.txt. If results.txt contained any "NO SUGGESTION", it will
grep and let you know. If nothing is seen, all is well. 