#!/bin/bash

#Run java code that generates test cases to be piped in
java -Dfile.encoding=US-ASCII -classpath bin Spellcheck.GenerateTests

#Pipe it in to our spell checker and print out the results
java -Dfile.encoding=US-ASCII -classpath bin Spellcheck.Main < /Users/b0ard/dev/workspace/TwitchSpellcheck/tests.txt > results.txt

echo "grepping results.txt for \"NO SUGGESTION\"... If there's nothing below, we're all happy Kappas!"
grep 'NO SUGGESTION' results.txt 