/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.anagrams;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class AnagramDictionary {

    private static final String TAG = "Anagrams";
    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private Random random = new Random();
    private ArrayList<String> wordList = new ArrayList<String>();
    private HashSet<String> wordSet = new HashSet<String>();
    private HashMap<String, ArrayList<String>> lettersToWord = new HashMap<>();

    public AnagramDictionary(Reader reader) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        String line;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            wordList.add(word);

            String sorted = sortLetters(word);

            if (!wordSet.contains(sorted))
            {
                wordSet.add(sorted);
            }
            if (lettersToWord.containsKey(sorted))
            {
                lettersToWord.get(sorted).add(word);
            }
            else
            {
                ArrayList<String> newList = new ArrayList<>();
                newList.add(word);
                lettersToWord.put(sorted, newList);
            }
        }
    }

    public boolean isGoodWord(String word, String base) {
        if (wordSet.contains(sortLetters(word)))
        {
            return true;
        }
        else
            return false;
    }

    public List<String> getAnagrams(String targetWord) {
        ArrayList<String> result = new ArrayList<String>();

        for (int i = 0; i < wordList.size(); i++){
            String word = wordList.get(i);
            if (targetWord.length() == word.length())
            {
//                Log.i(TAG, sortLetters(targetWord));
//                Log.i(TAG, sortLetters(word));
                if (sortLetters(targetWord).equals(sortLetters(word)))
                {
                    Log.i(TAG, word);
                    result.add(word);
                }
            }
        }
        return result;
    }

    public List<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < ALPHABET.length(); i++)
        {
            String sorted = sortLetters(word + ALPHABET.substring(i, i+1));
            if (wordSet.contains(sorted))
            {
                for (int j = 0; j < lettersToWord.get(sorted).size(); j++)
                {
                    result.add(lettersToWord.get(sorted).get(j));
                }
            }
        }

        return result;
    }

    public String pickGoodStarterWord() {
//        int rand = random.nextInt(wordList.size());
//        while (wordList.get(rand).length() < MIN_NUM_ANAGRAMS)
//        {
//            if (rand < wordList.size())
//            {
//                rand++;
//            }
//            else
//            {
//                rand = 0;
//            }
//        }
//        return wordList.get(rand);

        return "Post";
    }

    public String sortLetters(String input) {
        char[] inputChar = input.toCharArray();
        Arrays.sort(inputChar);
        return Arrays.toString(inputChar);
    }
}
