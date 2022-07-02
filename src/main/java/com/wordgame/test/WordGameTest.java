package com.wordgame.test;

import com.wordgame.java.WordGame;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class WordGameTest {

    @Test
    public void testInit() {
        WordGame wordGame = new WordGame("random");

        assertEquals(wordGame.getChosenWord(),"random");
        assertEquals(wordGame.getRedactedWord(), "******");
        assertEquals(wordGame.getLettersGuessed().toString(), "");
        assertEquals(wordGame.getCurrentLives(), 10);
    }

    @Test
    public void simpleWinTest() {
        WordGame wordGame = Mockito.spy(new WordGame("word"));

        Mockito.doReturn('w').when(wordGame).getLetterInput();
        wordGame.newTurn();

        Mockito.doReturn('o').when(wordGame).getLetterInput();
        wordGame.newTurn();

        Mockito.doReturn('r').when(wordGame).getLetterInput();
        wordGame.newTurn();

        Mockito.doReturn('d').when(wordGame).getLetterInput();
        wordGame.newTurn();

        assertTrue(WordGame.getWon());
        assertFalse(WordGame.getLost());
    }

    @Test
    public void simpleLossTest() {
        WordGame wordGame = Mockito.spy(new WordGame("z"));

        Mockito.doReturn('a').when(wordGame).getLetterInput();
        wordGame.newTurn();

        Mockito.doReturn('b').when(wordGame).getLetterInput();
        wordGame.newTurn();

        Mockito.doReturn('c').when(wordGame).getLetterInput();
        wordGame.newTurn();

        Mockito.doReturn('d').when(wordGame).getLetterInput();
        wordGame.newTurn();

        Mockito.doReturn('e').when(wordGame).getLetterInput();
        wordGame.newTurn();

        Mockito.doReturn('f').when(wordGame).getLetterInput();
        wordGame.newTurn();

        Mockito.doReturn('g').when(wordGame).getLetterInput();
        wordGame.newTurn();

        Mockito.doReturn('h').when(wordGame).getLetterInput();
        wordGame.newTurn();

        Mockito.doReturn('i').when(wordGame).getLetterInput();
        wordGame.newTurn();

        Mockito.doReturn('j').when(wordGame).getLetterInput();
        wordGame.newTurn();

        Mockito.doReturn('k').when(wordGame).getLetterInput();
        wordGame.newTurn();

        Mockito.doReturn('i').when(wordGame).getLetterInput();
        wordGame.newTurn();

        assertFalse(WordGame.getWon());
        assertTrue(WordGame.getLost());
        assertEquals(wordGame.getRedactedWord(), "*");
    }

    @Test
    public void specificationTest() {
        WordGame wordGame = Mockito.spy(new WordGame("book"));

        Mockito.doReturn('z').when(wordGame).getLetterInput();
        wordGame.newTurn();
        assertEquals(wordGame.getCurrentLives(), 9);
        assertEquals(wordGame.getRedactedWord(), "****");
        assertEquals(wordGame.getLettersGuessed().toString(), "z");

        Mockito.doReturn('o').when(wordGame).getLetterInput();
        wordGame.newTurn();
        assertEquals(wordGame.getCurrentLives(), 9);
        assertEquals(wordGame.getRedactedWord(), "*oo*");
        assertEquals(wordGame.getLettersGuessed().toString(), "zo");


        Mockito.doReturn('k').when(wordGame).getLetterInput();
        wordGame.newTurn();
        assertEquals(wordGame.getCurrentLives(), 9);
        assertEquals(wordGame.getRedactedWord(), "*ook");
        assertEquals(wordGame.getLettersGuessed().toString(), "zok");


        Mockito.doReturn('b').when(wordGame).getLetterInput();
        wordGame.newTurn();
        assertEquals(wordGame.getCurrentLives(), 9);
        assertEquals(wordGame.getRedactedWord(), "book");
        assertEquals(wordGame.getLettersGuessed().toString(), "zokb");

        assertTrue(WordGame.getWon());
        assertFalse(WordGame.getLost());
    }

    @Test
    public void badInputTest() {
        WordGame wordGame = Mockito.spy(new WordGame("word"));

        Mockito.doReturn('.').when(wordGame).getLetterInput();
        wordGame.newTurn();

        assertEquals(wordGame.getRedactedWord(), "****");
        assertEquals(wordGame.getCurrentLives(), 10);
        assertEquals(wordGame.getLettersGuessed().toString(), "");
        assertFalse(WordGame.getWon());
        assertFalse(WordGame.getLost());
    }
}
