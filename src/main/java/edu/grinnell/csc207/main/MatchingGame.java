package edu.grinnell.csc207.main;
import java.io.PrintWriter;

/**
 * Game logic for the matching game.
 * @author Alyssa Ryan
 * @author Jacob Bell
 */
public class MatchingGame {
  // ---------- Fields --------------
  /**
   * Game board of this game.
   */
  GameBoard board;
  /**
   * Whether or not the user has found all pairs in the game.
   */
  Boolean finished;
  /**
   * How many pairs the user has found in the game.
   */
  int pairsFound;

  /**
   * Prints the board.
   * @param whichBoard The board to be printed
   *                    0 = beginningValues, 1 = currentValues, 2 = correctValues
   */
  public void printBoard(int whichBoard) {
    PrintWriter pen = new PrintWriter(System.out, true);
    String edge = ("+" + "-".repeat((board.sideLength * 2) - 1) + "+\n");
    String val = "huh";
    pen.printf(edge);
    for (int i = 0; i < board.sideLength; i++) {
      pen.printf("|");
      for (int j = 0; j < board.sideLength; j++) {
        if (whichBoard == 0) {
          val = board.beginningValues.get(i, j);
          pen.printf(val);
        } else if (whichBoard == 1) {
          val = board.currentValues.get(i, j);
          pen.printf(val);
        } else if (whichBoard == 2) {
          val = board.correctValues.get(i, j);
          pen.printf(val);
        } //if/else which board are we printing
        pen.printf("|");
      } // for j
      pen.printf("\n");
      pen.printf(edge);
      pen.printf("\n");
    } // for i
  } //printBoard

  /**
   * Checks whether the string is an integer.
   * @param input String to check
   * @return Returns -1 if not an integer, or the integer version of the string if it is
   */
  int isInteger(String input) {
    char[] chArray = input.toCharArray();
    for (int i = 0; i < input.length(); i++) {
      if (chArray[i] < 48 || chArray[i] > 56) {
        return -1;
      } //if
    } //for
    return Integer.parseInt(input);
  } //isInteger

  /**
   * Checks whether input is valid. If input is q, quits game. If input is n, restarts game
   * @param input Input entered from user
   * @return returns integer version of input if input was valid square,
   *         -3 if user asked to quit, -5 if user asked make new game, or -1 if invalid input
   */
  int checkInput(String input) {
    PrintWriter pen = new PrintWriter(System.out, true);
    int check = isInteger(input);
    if (check != -1 && check != -3 && check != -5) {
      return check;
    } else if (input == "q") {
      return -3;
    } else if (input == "n") {
      return -5;
    } else {
      pen.println("Invalid input. Please enter a square number, q to quit, or n for new game.");
      return -1;
    } //if checks input from user
  } //checkInput

  /**
   * Allows the user to make a guess.
   * @param square The square the user chose to see the value of
   * @return The value of the square the user chose
   */
  String makeGuess(int square) {
    int row = square / board.sideLength;
    int col = square % board.sideLength;
    String value = board.correctValues.get(row, col);
    board.currentValues.set(row, col, value);
    return value;
  } //makeGuess

  /**
   * Updates board if user guesses correctly.
   * @param guessedCorrect if the user correctly guessed
   */
  void finishRound(Boolean guessedCorrect) {
    PrintWriter pen = new PrintWriter(System.out, true);
    if (guessedCorrect) {
      pen.println("Correct guess! Congrats!");
      board.beginningValues = board.currentValues.clone();
      pairsFound++;
      if (pairsFound == board.numPairs) {
        finished = true;
        pen.println("Congratulations! You finished the game!");
      } //if game is finihsed
    } else {
      pen.println("Incorrect guess. Try again!");
      board.currentValues = board.beginningValues.clone();
    } //if user guessed correctly
  } //finishRound

  /**
   * Starts a new game, resetting the boards back to regular.
   */
  void newGame() {
    board.resetBoards();
    pairsFound = 0;
  } //newGame

  /**
   * Begins the game and initializes everything.
   * @param size The side length of the game board
   */
  public MatchingGame(int size) {
    board = new GameBoard(size);
    finished = false;
    pairsFound = 0;
  } //MatchingGame
} //MatchingGame