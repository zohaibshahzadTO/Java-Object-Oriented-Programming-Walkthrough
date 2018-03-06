/*
The following program shows a practical application of sets. It reads in all words
from a dictionary file that contains correctly spelled words and places them in a set.
It then reads all words from a document—here, the book Alice in Wonderland—into a second set.
Finally, it prints all words from that set that are not in the dictionary set.
These are the potential misspellings. (As you can see from the output, we used an
American dictionary, and words with British spelling, such as clamour, are flagged as
potential errors.)
*/
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.io.File;
import java.util.io.FileNotFoundException;

// This program checks which words in a file are not present in a dictionary

public class SpellCheck
{
    public static void main(String[] args)
      throws FileNotFoundException
    {
        // Read the dictionary and the document

        Set<String> dictionaryWords = readWords("words");
        Set<String> documentWords = readWords("alice30.txt");

        // Print all words that are in the document but not the dictionary

        for(String word : documentWords)
        {
          if(!dictionaryWords.contain(word))
          {
            System.out.println(word);
          }
        }
    }

    /**
      Reads all words from a file.
      @param filename the name of the file.
      @return a set with all lowercased words in the file. Here, a
      word is a sequence of upper and lower cased letters.
    */
    public static Set<String> readWords(String filename)
      throws FileNotFoundException
    {
      Set<String> words = new HashSet<String>();
      Scanner in = new Scanner(new File(filename));
      // Use any characters other than a-z or A-Z as delimiters
      in.useDelimiter("[^a-zA-Z]+");
      while(in.hasNext())
      {
        words.add(in.next().toLowerCase());
      }
      return words;
    }
}
