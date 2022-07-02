package com.wordgame.java;

import java.util.Scanner;

public class WordGame {

    private final Scanner keyboardInput = new Scanner(System.in);
    private static final Integer maxLives = 10;

    private final String chosenWord;
    private Integer currentLives;
    private String redactedWord;
    private StringBuilder lettersGuessed;
    private static Boolean won;
    private static Boolean lost;

    public WordGame(String randomWord) {
        chosenWord = randomWord;
        redactedWord = chosenWord.replaceAll("[a-z]", "*"); // Generate a sequence of asterisks
        lettersGuessed = new StringBuilder();
        currentLives = maxLives;
        won = false;
        lost = false;
    }

    public static void main(String[] args) {
        WordList wordList = new WordList();
        WordGame wordGame = new WordGame(wordList.getRandomWord());

        while (!won && !lost) {
            wordGame.newTurn();
        }
    }


    public void newTurn(){
        // Loop whilst alive and there's still letters to guess
        if (currentLives > -1 && redactedWord.contains("*")) {
            char guess = getLetterInput();

            if (!Character.isAlphabetic(guess)) {
                System.out.printf("%s is not alphabetic, try again \n", guess);
                return;
            }

                if (alreadyGuessed(guess)) {
                    System.out.printf("You've already guessed the letter %s\n", guess);
                    return;
                }

                // Haven't guessed this letter yet
                else {
                    lettersGuessed.append(guess);

                    // Guess is correct
                    if (letterInWord(guess, getChosenWord())) {

                        setRedactedWord(replaceAsteriskWithGuessedLetter(guess, getRedactedWord(), getChosenWord()));

                        // Does this guess mean the user wins?
                        if (checkWin()) {
                            won = true;
                            return;
                        }

                        System.out.printf("You guessed correctly. Current word: %s\n", redactedWord);

                    } else { // Incorrect guess
                        currentLives -= 1;
                        System.out.printf("Incorrect: 1 life lost. Lives remaining: %d. Current word is: %s\n", currentLives, redactedWord);
                    }
                    // These returns are redundant but I prefer the readability
                    return;
                }
        }

        else {
            System.out.printf("You ran out of lives! The word was: %s", chosenWord);
            lost = true;
            return;
        }
    }

    private boolean checkWin() {
        if (!getRedactedWord().contains("*")) {
            System.out.printf("You guessed the word! %s", getRedactedWord());
            won = true;
            return true;
        } else {
            return false;
        }
    }

    private boolean alreadyGuessed(char c) {
        if (getLettersGuessed().indexOf(String.valueOf(c)) > -1) {
            return true;
        } else {
            return false;
        }
    }

    private boolean letterInWord(char c, String word) {
        // Can be simplified to a 1-liner, but I think this is more readable
        if (word.indexOf(c) > -1) {
            return true;
        } else {
            return false;
        }
    }

    public char getLetterInput() {
        // Could be abstracted to a separate class, use UI input etc
        System.out.println("Please enter a letter: ");
        return keyboardInput.next().charAt(0);
    }

    private String replaceAsteriskWithGuessedLetter(char guess, String guessString, String wordString) {
        // Not too happy with this solution, would retry this in future
        char[] guessStringChars = guessString.toCharArray();
        char[] wordStringChars = wordString.toCharArray();

        for (int i = 0; i < wordString.length(); i++) {
            if (wordStringChars[i] == guess) {
                guessStringChars[i] = guess;
            }
        }
        return String.valueOf(guessStringChars);
    }

    // Lombok would tidy these getters/setters up
    public String getChosenWord() {
        return chosenWord;
    }

    public StringBuilder getLettersGuessed() {
        return lettersGuessed;
    }

    public Integer getCurrentLives() {
        return currentLives;
    }

    public String getRedactedWord() {
        return redactedWord;
    }

    public void setRedactedWord(String redactedWord) {
        this.redactedWord = redactedWord;
    }

    public static Boolean getWon() {
        return won;
    }

    public static Boolean getLost() {
        return lost;
    }
}
