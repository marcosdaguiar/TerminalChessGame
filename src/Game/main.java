package Game;

import java.util.Scanner;

public class main {
	public static Scanner scnr = new Scanner(System.in);
	
	/**
	 * The purpose of the Terminal Chess Game:
	 * A simple 2 players chess game made all from scratch.
	 * It does not have a GUI.
	 * It consists of allowing pieces only to move into the right direction and kill the opponent pieces.
	 * The player in control cannot move a piece that is exposing the king to a check
	 * or, cannot move a piece that does not protect a king while being checked.
	 * a check verification is done to the opposite player after the move occurred.
	 * After each move is performed, all position of the pieces are saved in a text file.
	 * -------------------------------------------------------------------------------------
	 * In the beginning, the game lets you:
	 * 1- start a new game
	 * 2- Load last game
	 * 3- Quit the game
	 * 
	 * @param args
	 */
	public static void main(String[] args) {	
		System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::");
		System.out.println(":::::   WELCOME TO THE TERMINAL CHESS GAME!   :::::");
		System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::\n");
		System.out.println("MENU: Please enter integer to select: \n"
				+ "   1 = Start New Game\n"
				+ "   2 = Load Last Game\n"
				+ "   3 = Quit");
		
		int menuOption= scnr.nextInt();
		scnr.nextLine();
		
		switch (menuOption) {
			case 1:
				newGame();
				break;
			case 2:
				loadGame();
				break;
			case 3:
				System.out.println("Goodbye!!!");
				System.exit(0);
		}
	}
	
	/**
	 * Initiates a new Game
	 * Initiates Board in default positions.
	 * @param player1, player2: Ask player's names and initiates Player Class
	 */
	public static void newGame() {
		String player1 = "Player 1";
		String player2 = "Player 2";
		System.out.println("Enter Name for Player 1...");
		player1 = scnr.nextLine();
		
		System.out.println("\nEnter Name for Player 2...");
		player2 = scnr.nextLine();
		
		Player [] player = new Player[2];
		player[0]=new Player (player1, true);
		player[1]=new Player (player2, false);
		
		Board board = new Board(player);
		board.newBoard();
	}
	
	/**
	 * Loads last game
	 */
	public static void loadGame() {
		String player1 = "Player 1";
		String player2 = "Player 2";
		
		Player [] player = new Player[2];
		player[0]=new Player (player1, true);
		player[1]=new Player (player2, false);
		
		Board board = new Board(player);
		board.loadBoard();		
	}
}
