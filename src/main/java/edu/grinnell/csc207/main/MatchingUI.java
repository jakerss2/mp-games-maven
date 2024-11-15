package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * A matching game's user interface.
 * @author Alyssa Ryan
 * @author Jacob Bell
 */
public class MatchingUI {
  /**
   * MatchingGame object for the user to play on.
   */
  static MatchingGame game;
  /**
   * Scanner to use.
   */
  static Scanner eyes;

  /**
   * Allows the user to play the game.
   * @param args Command line arguments
   */
  public static void main(String[] args) {
    eyes = new Scanner(System.in);
    PrintWriter pen = new PrintWriter(System.out, true);
    pen.println("Please enter a single digit even number for the size of board(NxN board)\n");
    int size = eyes.nextInt();


    while ((size % 2) != 0 && size < 10) {
      pen.println("Invalid Integer, single digit even number");
      size = eyes.nextInt();
    } //while size is invalid

    game = new MatchingGame(size);

    pen.println("If you would ever like to quit the game, enter 'q'. \n");

    while (!game.finished) {
      game.printBoard(0);
      String value1 = "";
      String value2 = "";

      pen.println("Please enter your first guess: ");
      String guess1 = getGuess();
      int input1 = game.checkInput(guess1);
      while (input1 == -1) {
        pen.println("Invalid input. \n Enter q to quit game or a valid square\n");
        pen.flush();
        guess1 = getGuess();
        input1 = game.checkInput(guess1);
      } //while
      if (input1 == -3) {
        pen.println("Thanks for playing!");
        game.finished = true;
        break;
      } else {
        value1 = game.makeGuess(input1 - 1);
      } //if/else
      game.printBoard(1);

      pen.println("Please enter your second guess: ");
      String guess2 = getGuess();
      int input2 = game.checkInput(guess2);
      while (input2 == -1) {
        pen.println("Invalid input. \n Enter q to quit game, or a valid square\n");
        pen.flush();
        guess2 = getGuess();
        input2 = game.checkInput(guess2);
      } //while
      if (input2 == -1) {
        pen.println("Thanks for playing!");
        game.finished = true;
        break;
      } else {
        value2 = game.makeGuess(input2 - 1);
      } //if/else
      game.printBoard(1);

      Boolean guessedCorrect = (value1.equals(value2));
      game.finishRound(guessedCorrect);
    } //while

    pen.println("Correct values of the game:");
    game.printBoard(2);

    eyes.close();
  } //main

  /**
   * Gets input from the user.
   * @return String that represents user's input.
   */
  public static String getGuess() {
    String guess = "";
    System.out.flush();
    Scanner read = new Scanner(System.in);
    guess = read.nextLine();
    return guess;
  } //getGuess
} //MatchingUI
