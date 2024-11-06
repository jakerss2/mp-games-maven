package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import java.util.Scanner;

public class MatchingUI {
  public static void main(String[] args) {
    Scanner eyes = new Scanner(System.in);
    PrintWriter pen = new PrintWriter(System.out, true);
    pen.println("Please enter a single digit EVEN number for the size of board(NxN board)\n");
    int size = eyes.nextInt();

    while ((size % 2) != 0 && size < 10) {
      pen.println("Invalid Integer, single digit EVEN NUMBER");
      size = eyes.nextInt();
    }

    MatchingGame game = new MatchingGame(size);

    while(!game.finishedBoard()) {
      printBoard(game.getBoard());

      pen.println("Please enter your first guess: ");
      char guess1 = getGuess(size);
      game.makeGuess(guess1);
      printBoard(game.getBoard());

      pen.println("Please enter your second guess: ");
      char guess2 = (char) eyes.nextInt();
      game.makeGuess(guess2);
      printBoard(game.getBoard());

      if (game.hasMatch()) {
        pen.println("Correct guess! Congrats!");
      } else {
        pen.println("Incorrect. Try Again");
      }
      
      game.updateBoard();
    }
    pen.println("Congratulations! You won our game!!");
  }

  public static void printBoard(char[][] board) {
    PrintWriter pen = new PrintWriter(System.out, true);
    String Edge = ("+" + "-".repeat((board.length * 2) - 1) + "+\n");
    pen.print(Edge);
    for (int i = 0; i < board.length; i++) {
      pen.print("|");
      for (int j = 0; i < board.length; i++) {
        pen.print(board[i][j] + "|");
      }
      pen.print("\n");
    }
    pen.print(Edge);

    pen.flush();
  }

  public static char getGuess(int size) {
    int guess = 0;
    Scanner eyes = new Scanner(System.in);
    PrintWriter pen = new PrintWriter(System.out, true);
    guess = eyes.nextInt();
    if (guess > (size * size)) {
      pen.println("Invalid guess, please provide a number between 0 - " + (size * size));
      guess = eyes.nextInt();
    }
    return (char) guess;
  }
}
