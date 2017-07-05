package chess;

import java.util.Scanner;

public class Main {
	
  public static void main(String[] args) {
  
//	  Board board = new Board();
//	  board.initializePieces();
//	  board.printBoard();
	  
	  Scanner scan = new Scanner(System.in);
	  String word = "a";
	  boolean turn = true;
	  
	  while(word.charAt(0) != 'q') {
		  if (turn) {
			  System.out.print("Enter a move for WHITE: ");
			  word = scan.nextLine();
			  System.out.println(word);
		  } else {
			  System.out.print("Enter a move for BLACK: ");
			  word = scan.nextLine();
			  System.out.println(word);
		  }
		  turn = !turn;
	  }
	  
	  scan.close();
  }
}