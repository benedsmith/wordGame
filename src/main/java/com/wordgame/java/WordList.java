package com.wordgame.java;

import java.util.Random;

public class WordList {
    private final String[] wordList;

    public WordList() {
        // We could read this from an external file in the future, making it easier to modify
        wordList = new String[]{"time", "person", "year", "way", "day", "thing", "man", "world", "life", "hand", "part", "child", "eye", "woman", "place", "work", "week", "case", "point", "government", "company", "number", "group", "problem", "fact"};
    }

    public String getRandomWord() {
        Random random = new Random();
        Integer numberOfWords = this.wordList.length;
        return wordList[random.nextInt(numberOfWords)];
    }

}
