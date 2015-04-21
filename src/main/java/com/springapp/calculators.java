package com.springapp;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mkorshun on 4/13/2015.
 */
public class calculators {


    public void calculate_param (String string)
    {
        int wordCount = 0;
        boolean word = false;
        int endOfLine = string.length() - 1;
        for (int i = 0; i < string.length(); i++) {
            if (Character.isLetter(string.charAt(i)) && i != endOfLine) {
                word = true;
            } else if (!Character.isLetter(string.charAt(i)) && word) {
                wordCount++;
                word = false;
            } else if (Character.isLetter(string.charAt(i)) && i == endOfLine) {
                wordCount++;
            }
        }
        setWords(wordCount);
        int count = string.split("[!?.:]+").length;
        setSentences(count);
        setPrepos(countPrepositions(string));
    }
    public static int countPrepositions(String input) {
        int total = 0;
        Matcher matches = Pattern.compile("((?i)of|until|about|at|in)").matcher(input);
        while (matches.find()) {
            total++;
        }
        return total;}

    public double words;
    public double prepos;
    public double sentences;


    public double getWords() {
        return words;
    }

    public void setWords(double words) {
        this.words = words;
    }

    public double getPrepos() {
        return prepos;
    }

    public void setPrepos(double prepos) {
        this.prepos = prepos;
    }

    public double getSentences() {
        return sentences;
    }

    public void setSentences(double sentences) {
        this.sentences = sentences;
    }
}
