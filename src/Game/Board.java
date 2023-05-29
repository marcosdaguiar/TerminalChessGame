package Game;
import java.util.InputMismatchException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import Pieces.Piece;

public class Board {
	Spot[][] spot =  new Spot [8][8];
	Player [] player;
	String player1;
	String player2;
	Check [] check = new Check[2];

	public Board(Player []player) {
		this.player = player;		
		player1 = player[0].getName();
		player2 = player[1].getName();
	}
	
	// break white and black in different methods
	public void newBoard() {
		spot[0][0] = new Spot(0, 0, new Pieces.Rock(true));
		spot[0][1] = new Spot(0, 1, new Pieces.Knight(true));
		spot[0][2] = new Spot(0, 2, new Pieces.Bishop(true));
		spot[0][3] = new Spot(0, 3, new Pieces.Queen(true)); 
		spot[0][4] = new Spot(0, 4, new Pieces.King(true));
		spot[0][5] = new Spot(0, 5, new Pieces.Bishop(true)); 
		spot[0][6] = new Spot(0, 6, new Pieces.Knight(true));
		spot[0][7] = new Spot(0, 7, new Pieces.Rock(true)); 
		spot[1][0] = new Spot(1, 0, new Pieces.Pawn(true));
		spot[1][1] = new Spot(1, 1, new Pieces.Pawn(true)); 
		spot[1][2] = new Spot(1, 2, new Pieces.Pawn(true));
		spot[1][3] = new Spot(1, 3, new Pieces.Pawn(true)); 
		spot[1][4] = new Spot(1, 4, new Pieces.Pawn(true));
		spot[1][5] = new Spot(1, 5, new Pieces.Pawn(true)); 
		spot[1][6] = new Spot(1, 6, new Pieces.Pawn(true));
		spot[1][7] = new Spot(1, 7, new Pieces.Pawn(true));
		spot[6][0] = new Spot(6, 0, new Pieces.Pawn(false));
		spot[6][1] = new Spot(6, 1, new Pieces.Pawn(false)); 
		spot[6][2] = new Spot(6, 2, new Pieces.Pawn(false));
		spot[6][3] = new Spot(6, 3, new Pieces.Pawn(false)); 
		spot[6][4] = new Spot(6, 4, new Pieces.Pawn(false));
		spot[6][5] = new Spot(6, 5, new Pieces.Pawn(false)); 
		spot[6][6] = new Spot(6, 6, new Pieces.Pawn(false));
		spot[6][7] = new Spot(6, 7, new Pieces.Pawn(false));
		spot[7][0] = new Spot(7, 0, new Pieces.Rock(false));
		spot[7][1] = new Spot(7, 1, new Pieces.Knight(false));
		spot[7][2] = new Spot(7, 2, new Pieces.Bishop(false));
		spot[7][3] = new Spot(7, 3, new Pieces.Queen(false)); 
		spot[7][4] = new Spot(7, 4, new Pieces.King(false));
		spot[7][5] = new Spot(7, 5, new Pieces.Bishop(false)); 
		spot[7][6] = new Spot(7, 6, new Pieces.Knight(false));
		spot[7][7] = new Spot(7, 7, new Pieces.Rock(false));
		
		
		for (int i=2; i<6; i++) {
			for (int j=0; j < 8; j++) {
				spot[i][j]=new Spot (i,j,null);
			}				
		}
		
		check[0] = new Check(0,4, true, spot);
		check[1] = new Check(7,4, false, spot);
		Game game = new Game(player, spot, check);
		game.pieceSelector(player1, player2, player1);		
	}
	
	
	public void loadBoard() {
		int row, col;
		boolean white = false;
		String piece, player1 = null, player2 = null, playerTurn=null;
		
		try {
			File file = new File("Save.txt");
			Scanner myReader = new Scanner(file);			
			
			//break each while in methods
			while (myReader.hasNextLine()) {
				player1= myReader.nextLine();
				player2= myReader.nextLine();
				playerTurn= myReader.nextLine();
				break;
			}
			
			while (myReader.hasNextLine() && myReader.hasNextInt()) {
			
				row = myReader.nextInt();
				col = myReader.nextInt();
				piece = myReader.next();
				if (piece.equalsIgnoreCase("null")){
					spot[row][col] = new Spot(row, col, null);					
				}else if(myReader.next().equalsIgnoreCase("true")) {
						white=true;
				}else {
						white=false;
				}				
				
					// move to new method
				if(piece.equalsIgnoreCase("Rock")) {
					spot[row][col] = new Spot(row, col, new Pieces.Rock(white));
				}else if(piece.equalsIgnoreCase("Knight")) {
					spot[row][col] = new Spot(row, col, new Pieces.Knight(white));
				}else if(piece.equalsIgnoreCase("Bishop")) {
					spot[row][col] = new Spot(row, col, new Pieces.Bishop(white));
				}else if(piece.equalsIgnoreCase("Queen")) {
					spot[row][col] = new Spot(row, col, new Pieces.Queen(white));
				}else if(piece.equalsIgnoreCase("King")) {
					spot[row][col] = new Spot(row, col, new Pieces.King(white));
				}else if(piece.equalsIgnoreCase("Pawn")) {
					spot[row][col] = new Spot(row, col, new Pieces.Pawn(white));
				}
			}
		
		
			int i=0;
			while (myReader.hasNextLine()){
				boolean whiteKing=false;
				myReader.next();
				int rowKing = myReader.nextInt(), colKing = myReader.nextInt();
				if(i==0) {
					whiteKing=true;
				}
				check[i] = new Check(rowKing,colKing, whiteKing, spot);				
				i++;
			}
			myReader.close();
		
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred. Unable to load a Game, lets start a new game!");
			main mn = new main();
			mn.newGame();
			//e.printStackTrace();	
		}
		player[0].setName(player1);
		player[1].setName(player2);
		
		Game game = new Game(player, spot, check);
		game.pieceSelector(player1, player2, playerTurn);
	}	
}
