package Game;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
	String player1, player2, playerturn;
	Board board;
	Player[] player;
	Check[] check;
	Scanner scnr = new Scanner(System.in);
	Spot[][] spot = new Spot[8][8];

	public Game(Player[] player, Spot[][] spot, Check[] check) {
		this.spot = spot;
		this.player = player;
		this.player1 = player[0].getName();
		this.player2 = player[1].getName();
		this.check = check;
	}

	/**
	 * infinite loop
	 * asks user to select piece row and column and destination row and column
	 *
	 */
	public void pieceSelector(String player1, String player2, String playerTurn) {
		printBoard(spot, player, playerTurn);

		int t = 1;
		while (t == 1) {
			checkVerify();

			int startRow, startCol, destRow, destCol;
			String position;

			System.out.println(playerTurn + ", Select the piece you wish to move, column and row. For example \"e5\"\n"
					+ "Type Column letter + Row number:");
			position = positionAuthenticator();
			startCol = positionLetterToInt(position);
			startRow = Character.getNumericValue(position.charAt(1) - 1);

			if (validatePiece(startRow, startCol, playerTurn)) {
				System.out.println("Type Destination column + row:");
				position = positionAuthenticator();
				destCol = positionLetterToInt(position);
				destRow = Character.getNumericValue(position.charAt(1) - 1);
				playerTurn = performMovement(playerTurn, startRow, startCol, destRow, destCol);
			}
		}
	}

	public String positionAuthenticator() {
		char rowLetter;
		int colNumber;
		boolean answer = false;
		String position = null;

		while (answer == false) {
			position = scnr.nextLine();
			String[] pos = position.toLowerCase().split("");

			if (position.length() == 2) {
				String posChar = pos[0];
				
				rowLetter =  posChar.charAt(0);
				colNumber = Integer.parseInt(pos[1]) - 1;

				if (rowLetter >= 'a' && rowLetter <= 'h') {
					if (authenticaRange(colNumber)) {
						answer = true;
					}
				} else {
					System.out.println("Column must be from a to h... try again");
				}
			} else {
				System.out.println("The length should be 2 characters... try again");
			}

		}
		return position;
	}

	public int authenticateInteger() {

		int userInput = 0;

		boolean answer = false;
		while (answer == false) {
			try {
				answer = true;
				userInput = scnr.nextInt();
				answer = authenticaRange(userInput);
			} catch (InputMismatchException e) {
				System.out.println("Integer was not entered, try again");
				answer = false;
			}
			scnr.nextLine();
		}
		return userInput;
	}

	/**
	 * authenticates that the typed row or column is in range
	 * 
	 * @return true or false
	 */
	public boolean authenticaRange(int input) {
		boolean range = false;

		if (input < 0 || input > 7) {

			System.out.println("Index out of bound, try again, integers from 0 to 7");
			range = false;
		} else {
			range = true;
		}
		return range;
	}

	// pos[0]
	public int positionLetterToInt(String position) {
		int row = 0;
		char rowLetter = position.charAt(0);
		switch (rowLetter) {
			case 'a':
				row = 0;
				break;
			case 'b':
				row = 1;
				break;
			case 'c':
				row = 2;
				break;
			case 'd':
				row = 3;
				break;
			case 'e':
				row = 4;
				break;
			case 'f':
				row = 5;
				break;
			case 'g':
				row = 6;
				break;
			case 'h':
				row = 7;
				break;
		}
		return row;
	}

	/**
	 * perform movements and validates if there is a check to the other player
	 * or if the player left his own king in a check, it will reverse movement.
	 * if player finished his move, player changes turns.
	 */
	public String performMovement(String playerTurn, int startRow, int startCol, int destRow, int destCol) {
		boolean check = false;
		boolean kingExposed = false;

		moves mv = new moves(startRow, startCol, destRow, destCol, spot);
		if (mv.verifyMovement()) {
			mv.setMoves();

			kingTracker(destRow, destCol);

			kingExposed = kingExposure(playerTurn);

			if (!kingExposed) {
				playerTurn = changePlayer(playerTurn);
				printBoard(spot, player, playerTurn);
			}else {
				mv = new moves(destRow, destCol, startRow, startCol, spot);
				mv.setMoves();
			}
		}
		return playerTurn;
	}

	/**
	 * changes player 1 to player2
	 *
	 */
	public String changePlayer(String playerTurn) {

		if (playerTurn.equals(player1)) {
			playerTurn = player2;
		} else {
			playerTurn = player1;
		}

		return playerTurn;
	}

	/**
	 * if player is leaving his king in check returns true
	 * otherwise, returns false
	 */
	public boolean kingExposure(String playerTurn) {

		if (playerTurn == player1 && check[0].checkStatus()) {
			System.out.println("\n********* WRONG MOVE, YOU ARE EXPOSING YOUR KING *********\n"
					+ "Try Again...");
			return true;
		}else if (playerTurn == player2 && check[1].checkStatus()) {
			System.out.println("\n********* WRONG MOVE, YOU ARE EXPOSING YOUR KING *********\n"
					+ "Try Again...");
			return true;
		}else{
			return false;
		}

	}

	/**
	 * @param i
	 * @return
	 */
	public boolean checkVerify() {
		if (check[0].checkStatus()) {
			System.out.println("\n********* CHECK!!! *********");
			System.out.println("********* " + player1 + ", SAVE YOUR KING!*********\n");
			return true;
		}

		if (check[1].checkStatus()) {
			System.out.println("\n********* CHECK!!! *********");
			System.out.println("********* " + player2 + ", SAVE YOUR KING!*********\n");
			return true;
		}
		return false;
	}

	public boolean validatePiece(int row, int col, String playerTurn) {
		if (spot[row][col].piece == null) {
			System.out.println("********* SELECTED POSITION DOES NOT HAVE ANY PIECE *********\n");
			return false;
		}else if (playerTurn.equalsIgnoreCase(player1)) {
			if (player[0].white == spot[row][col].piece.white) {
				return true;
			}
		}else if (playerTurn.equalsIgnoreCase(player2)) {
			if (player[1].white == spot[row][col].piece.white) {
				return true;
			}
		}else{
			System.out.println("********* PIECE SELECTED BELONGS TO THE OTHER PLAYER *********\n");
			return false;
		}
		return false;
	}

	public void kingTracker(int row, int col) {

		if (spot[row][col].pieceName().equalsIgnoreCase("King")) {
			if (spot[row][col].piece.white) {
				check[0].setY(col);
				check[0].setX(row);
			} else if (spot[row][col].piece.white == false) {
				check[1].setY(col);
				check[1].setX(row);
			}
		}
	}

	public void printBoard(Spot[][] spot, Player[] player, String playerTurn) {
		File file = new File("Save.txt");
		FileWriter wr = null;
		try {
			wr = new FileWriter(file);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			saveGame(player1 + "\n", wr);
			saveGame(player2 + "\n", wr);
			saveGame(playerTurn + "\n", wr);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		String save;

		System.out.println("\n\t\t\t\t" + player1.toUpperCase());
		for (char c = 'a'; c < 'i'; c++) {
			System.out.print("\t" + c);
		}

		System.out.println("\n");

		for (int x = 0; x < 8; x++) {
			System.out.print(x + 1 + "\t");

			for (int y = 0; y < 8; y++) {

				if (spot[x][y].getPiece() == null) {
					int z = (y + x) % 2;
					if (z == 0) {
						System.out.print("----\t");
					} else {
						System.out.print("::::\t");
					}
					save = ("" + x + " " + y + " " + "null\n");

				} else {
					spot[x][y].value();
					boolean white = spot[x][y].piece.white;
					String pieceName = spot[x][y].pieceName();
					save = ("" + x + " " + y + " " + pieceName + " " + white + "\n");

				}
				try {
					saveGame(save, wr);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			System.out.println("\n");
		}

		System.out.println("\n\t\t\t\t" + player2.toLowerCase() + "\n");
		try {
			save = "Check1 " + check[0].getX() + " " + check[0].getY() + "\n" +
					"Check2 " + check[1].getX() + " " + check[1].getY();
			saveGame(save, wr);

			wr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveGame(String s, FileWriter wr) throws IOException {

		wr.write(s);
		wr.flush();
	}
}
