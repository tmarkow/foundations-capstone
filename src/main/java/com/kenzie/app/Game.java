package com.kenzie.app;
//

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {

    public static void gameLoop() throws JsonProcessingException {

        String testCluesDesiredURL = CustomHttpClient.getDesiredURL(ClueOptions.CLUE_LIST);

        CustomHttpClient clueHttpClient = new CustomHttpClient();

        String allClueResponseBody = clueHttpClient.sendGET(testCluesDesiredURL); //response
        List<CluesDTO.Clues> allClues = clueHttpClient.getClueList(allClueResponseBody);

        int score = 0;
        int counter = 0;

        System.out.println("\nWelcome to TNT!!\nTravis' Nightly Trivia!!\n");
        System.out.println("You will be asked 10 questions, each worth 1 point.");
        System.out.println("Type your answer after each question and then hit ENTER.");
        System.out.println("If you don't know the answer, take your best guess or leave it blank and hit ENTER.");
        System.out.println("One random question will be worth TRIPLE points!\nLETS BEGIN!!\n");

        Random rand = new Random();
        int triplePointsRound = rand.nextInt(10) + 1;
        while (counter < 10) {
            int questionNum = counter + 1;

            Scanner scanner = new Scanner(System.in);

            int numOfClues = allClues.toArray().length;
            int randClue = rand.nextInt(numOfClues + 1);

            if (questionNum == triplePointsRound) {
                System.out.println("********** TRIPLE POINT TRIVIA TIME **********");
                System.out.println("********** TRIPLE POINT TRIVIA TIME **********");
                System.out.println("********** TRIPLE POINT TRIVIA TIME **********\n");
                System.out.println("The next question is worth 3 points if you answer it correctly!");
            }
            System.out.println("Question " + questionNum + ":");
            System.out.println("Category: " + allClues.get(randClue).getCategory().getTitle());
            System.out.println(allClues.get(randClue).getQuestion());
//            System.out.println("Answer for testing:");
//            System.out.println(allClues.get(randClue).getAnswer());

            String userAnswer = scanner.nextLine();
            if (questionNum == triplePointsRound && checkAnswer(userAnswer, allClues.get(randClue).getAnswer()) == true) {
                score = score + 2;  //couldnt figure out how to skip the next if statement after a bonus question
            }                       // so just made this line 2 instead of 3, since the next if statement adds 1
            if (checkAnswer(userAnswer, allClues.get(randClue).getAnswer()) == true) {
                System.out.println("CORRECT!!!\n");
                score++;
            } else {
                System.out.println("I'm afraid that is incorrect.");
            }

            System.out.println("The correct answer is: ");
            System.out.println(allClues.get(randClue).getAnswer());
            System.out.println(" ");

            counter++;
            allClues.remove(randClue);
            System.out.println("Current Score: " + score + "\n");
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Congratulation! Your final score: " + score + " out of 10!");
        if (score > 10) {
            System.out.println("WOW!!! You are a GENIUS!!!");
        } else if (score == 10) {
            System.out.println("PERFECT score, amazing!!!");
        } else if (score > 5 && score < 10) {
            System.out.println("More than half right, great job!");
        } else if (score <= 5 && score > 1) {
            System.out.println("You got a few right, time to hit those books!");
        } else if (score == 1) {
            System.out.println("Hey, you got 1 right, that's better than none!");
        } else if (score == 0) {
            System.out.println("Trivia is hard, huh?");
        }

        boolean exitLoop = false;

        while (!exitLoop) {
            System.out.println("Would you like to play again? Type Y/N and hit ENTER.");
            String playAgain = scanner.nextLine();

            if (playAgain.equalsIgnoreCase("y")) {
                Game.gameLoop();
                break;  //if not here, and you say 'Y' to 4 games, it seems you'd have to hit 'N' 4 times before exitLoop
            } else if (playAgain.equalsIgnoreCase("n")) {
                System.out.println("Thanks for playing, see you next time!");
                exitLoop = true;

            } else {
                System.out.println("All that trivia knowledge but the 'Y' or 'N' key allude you??");
                System.out.println("Try again please...");
            }
        }
    }

    public static boolean checkAnswer(String userAnswer, String actualAnswer) {
        if (userAnswer.equalsIgnoreCase(actualAnswer)) {
            return true;
        } else {
            return false;
        }
    }

//    public static void gameInto(){
//
//        String singleQuestionURL = CustomHttpClient.getDesiredURL(ClueOptions.SINGLE_QUESTION);
//        String categoryURL = CustomHttpClient.getDesiredURL(ClueOptions.CATEGORY_LIST);
//
//        System.out.println("Welcome to Trivia!\n Please enter option 1, 2, or 3 and press ENTER:");
//        System.out.println("1. Single trivia question.\n2. Play a round of 10 questions.  Go for the High Score!");
//        System.out.println("3. Select a category of questions to play.");
//
//    }
}
