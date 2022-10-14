package com.kenzie.app;

import com.fasterxml.jackson.core.JsonProcessingException;

enum ClueOptions {
    CLUE_LIST,
    SINGLE_QUESTION,
    CATEGORY_LIST
}

public class Main {

    public static void main(String[] args) throws JsonProcessingException {

        Game.gameLoop();
    }
}

/**
 * So once it was all said and done, I felt uneasy about how bare Main is... felt kind of unnecessary to even have
 * a Game folder.  Or maybe it's a good habit to get into, since if there were other files to run in a larger
 * program, it would keep Main tidy.
 *
 * I left some commented out sections, ones I want to go back and add too, but for now, I am submitting, so I can
 * prepare for my grandma's funeral.  I worked on the project solo, with the help of Claire in the Topic Sessions
 * and Q&As.
 *
 * The current game asks the user 10 questions.  Each question is worth 1 point if answered correctly, and one
 * random question becomes a Triple Point Question.  I commented out lines 48 and 49 in Game.java, but for testing
 * purposes, if you uncomment them, it allows you to input the correct answers in order to verify ignore case,
 * correct/incorrect answers, point totals increasing, and ending message.  Blank entries or blank spaces added
 * as user input do not give errors, but register as an incorrect answer.  The score of the game is displayed,
 * as well as a messaged based on how many questions were answered correctly.  The user is then prompted to
 * play again or not.
 * The game loop makes sure that in each set of 10 questions, there will be no repeat questions.  I struggled to
 * find a way to ensure that if the user wanted to play again, that no questions would be reused.  My workaround,
 * which probably isn't ideal or optimal, was to make the clue list generated use the API to offset the listing
 * of 100 questions by a random number equaling almost the total number of clues.  The chances of a user encountering
 * a repeat questions is extremely minuscule.
 **/


