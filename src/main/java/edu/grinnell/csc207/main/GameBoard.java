package edu.grinnell.csc207.main;

import edu.grinnell.csc207.util.MatrixV0;
import java.util.Random;
import java.io.PrintWriter;

/**
 * Game board object for the matching game.
 * @author Alyssa Ryan
 * @author Jacob Bell
 */
public class GameBoard {

  // -------- Fields --------
  /**
   * The values the board holds at the beginning of the round.
   * Holds square numbers until the user gets a pair correct.
   * Then updates to reflect the correct pair.
   */
  MatrixV0<String> beginningValues;
  /**
   * The values currently shown.
   * The same as beginning_values, with the user's current guesses as well.
   */
  MatrixV0<String> currentValues;
  /**
   * Holds the pairs of values and their locations.
   */
  MatrixV0<String> correctValues;
  /**
   * The values that will be used as the pairs in the game.
   * Max of thirty two values, as that is the max allowed in the user interface.
   */
  String[] values = {"A", "B", "C", "D", "E", "F", "G",
    "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
    "U", "V", "W", "X", "Y", "Z", "!", "@", "#", "$", "%", "&"};
  /**
   * Side length of the the board.
   */
  int sideLength;
  /**
   * The number of pairs in the board.
   */
  int numPairs;
  /**
   * Printer for the board.
   */
  PrintWriter pen;


  // ------------ Methods -------------
  /**
   * Adds the pairs randomly generated throughout the correct_values board.
   */
  public void addPairs() {
    Random rand = new Random();
    int row = 0;
    int col = 0;

    //Adds all pairs values to the game
    for (int i = 0; i < numPairs; i++) {
      //Adds the value twice
      for (int j = 0; j < 2; j++) {
        row = rand.nextInt(sideLength);
        col = rand.nextInt(sideLength);

        //If there isn't already a value there, put the value in
        if (correctValues.get(row, col).equals("n")) {
          correctValues.set(row, col, values[i]);
        } else {
          //If there is a value there already, go through the board until it finds an empty value
          while (!(correctValues.get(row, col).equals("n"))) {
            row++;
            //Ensures row stays within bounds
            if (row >= sideLength) {
              row = 0;
              col++;
            } //If
            //Ensures col stays within bounds
            if (col >= sideLength) {
              col = 0;
            } //If
          } //while
          correctValues.set(row, col, values[i]);
        } //while
      } //for
    } //for
  } //addPairs

  /**
   * Resets the boards back to original.
   */
  public void resetBoards() {
    int count = 1;
    for (int i = 0; i < sideLength; i++) {
      for (int j = 0; j < sideLength; j++) {
        beginningValues.set(i, j, Integer.toString(count));
        currentValues.set(i, j, Integer.toString(count));
        count++;
      } //for
    } //for
    addPairs();
  } //resetBoard

  /**
   * Creates a game board object.
   * Populates beginning_values, current_values, and correct_values.
   * @param length The side length of the board
   */
  public GameBoard(int length) {
    this.sideLength = length;
    this.numPairs = (sideLength * sideLength) / 2;
    pen = new PrintWriter(System.out, true);

    beginningValues = new MatrixV0<String>(sideLength, sideLength, "n");
    currentValues = new MatrixV0<String>(sideLength, sideLength, "n");
    correctValues = new MatrixV0<String>(sideLength, sideLength, "n");

    resetBoards();
  } //GameBoard
} //GameBoard
