package Game;
import java.math.*;
import java.util.Scanner;
import Pieces.Piece;
import Pieces.Type;

public class moves {
	int startCol, startRow,destCol,destRow;
	boolean white;
	public Spot [][] spot;
	
	
	public moves(int startRow, int startCol, int destRow, int destCol, Spot[][]sp) {
		this.startRow = startRow;
		this.startCol = startCol;
		this.destRow = destRow;
		this.destCol = destCol;
		spot = sp;
	}
		

	public boolean verifyMovement() {	
		if (spot[startRow][startCol].getPiece()!=null) {
			
			if(availableSpot()) {
				if(validateMove()) {
					return true;
				}
			}
		}
		
		else {
			System.out.println("\n********* THE SELECTED PIECE IS NOT AVAILABLE *********\n"
					+ "SELECT AGAIN...\n");
		}
		return false;
	}

	
	public boolean availableSpot() {
		boolean white = spot[startRow][startCol].piece.white;
		if(spot[destRow][destCol].getPiece()==null){
			
						
			if(availablePath()) {
				return true;
			}			
		}			
		else if (white != spot[destRow][destCol].getPiece().white){
			
			if(availablePath()) {
				return true;
			}			
		}
		System.out.println("\n********* MOVEMENT NOT AVAILABLE, SELECT AGAIN *********\n");			
		return false;
	}
	
	
	
	
	public boolean availablePath() {
		int colDiff, rowDiff;
		
		
		if (spot[startRow][startCol].pieceName().equalsIgnoreCase("Knight")) {
			return true;
		}
		
		colDiff =  Math.abs(startCol-destCol);
		rowDiff =  Math.abs(startRow-destRow);
		
		if(colDiff==0) {
			
			if(startRow>destRow) {
				
				for(int row = startRow-1; row > destRow; row--) {
					 
					if (spot[row][startCol].getPiece()!=null) {
						 return false;
					}								
				}
				return true;
			}
			
			if(startRow<destRow) {
				for(int row = startRow+1; row < destRow; row++) {
					 
					if (spot[row][startCol].getPiece()!=null) {
						 return false;
					}								
				}
				return true;
			}		
		}
		
		if(rowDiff==0) {
			
			if (startCol>destCol) {
				
				for(int x = startCol-1; x > destCol; x--) {
					 
					if (spot[destRow][x].getPiece()!=null) {
						 return false;
					}								
				}
				return true;
			}
			
			if (startCol<destCol) {
				
				for(int x = startCol+1; x < destCol; x++) {
					 
					if (spot[destRow][x].getPiece()!=null) {
						 return false;
					}								
				}
				return true;
			}
		}
		
		
		
		if(colDiff == rowDiff){
			
			if(colDiff == 1) {
				return true;
			}
			
			if (startCol<destCol && startRow<destRow) {
				for(int x = startCol+1, y=startRow+1; x < destCol; x++, y++) {
					 
					if (spot[y][x].getPiece()!=null) {
						 return false;
					}
				}
				return true;
			}
			
			
			if (startCol>destCol && startRow>destRow) {
				for(int x = startCol-1, y=startRow-1; x > destCol; x--, y--) {
								 
					if (spot[y][x].getPiece()!=null) {
						 return false;
					}
				}
				return true;
			}
				
			if (startCol<destCol && startRow>destRow) {
				for(int x = startCol+1, y=startRow-1; x < destCol; x++, y--) {
								 
					if (spot[y][x].getPiece()!=null) {
						 return false;
					}
				}
				return true;				
			}
				
			if (startCol>destCol && startRow<destRow) {
				for(int x = startCol-1, y=startRow+1; x > destCol; x--, y++) {
								 
					if (spot[y][x].getPiece()!=null) {
						 return false;
					}
				}
				return true;
			}
		}
		return false;	
	}
	
	public boolean validateMove() {
		if (spot[startRow][startCol].getPiece().ValidMovement(startRow, startCol, destRow, destCol, spot)) {
			return true;
		}
		System.out.println("\n********* INVALID MOVEMENT, SELECT AGAIN *********\n");
		return false;
	}
	
	
	public void setMoves () {	
				
			Piece p = spot[startRow][startCol].getPiece();
			castling(p);
				
			spot[destRow][destCol].setPiece(p);
			spot[destRow][destCol].setX(destCol);
			spot[destRow][destCol].setY(destRow);
			spot[destRow][destCol].setCount();
			
		
			pawnPromotion(destRow, destCol);
		
			spot[startRow][startCol].setPiece(null);
			spot[startRow][startCol].setX(startCol);
			spot[startRow][startCol].setY(startRow);		
	}
	
	
	public void pawnPromotion(int destRow, int destCol) {
		
		Scanner scnr = new Scanner(System.in);
		if((spot[destRow][destCol].pieceName()=="Pawn") &&
			(destRow ==7 || destRow ==0)){
		
			boolean color = true;
			if(destRow==7) {
				color = true;
			}
			
			else if(destRow==0) {
				color = false;
				
			}
								
			boolean correctPiece = false;
			
			while(correctPiece == false) {
				
				System.out.println("Pawn Promotion: Please type which piece you would like to change the pawn to...\n"
						+ "Rock, Knight, Bishop, Queen, or Pawn");
				String promoPiece = scnr.nextLine();
				
				correctPiece = true;	
				if(promoPiece.equalsIgnoreCase("Rock")) {
					Piece p = new Pieces.Rock(color);
					spot[destRow][destCol].setPiece(p);
				}
				else if(promoPiece.equalsIgnoreCase("Knight")) {
					Piece p = new Pieces.Knight(color);
					spot[destRow][destCol].setPiece(p);
				}
				else if(promoPiece.equalsIgnoreCase("Bishop")) {
					Piece p = new Pieces.Bishop(color);
					spot[destRow][destCol].setPiece(p);
				}
				else if(promoPiece.equalsIgnoreCase("Queen")) {
					Piece p = new Pieces.Queen(color);
					spot[destRow][destCol].setPiece(p);				
				}
				else if(promoPiece.equalsIgnoreCase("Pawn")) {
					Piece p = new Pieces.Pawn(color);
					spot[destRow][destCol].setPiece(p);				
				}
				else {
					System.out.println("Wrong piece selected, type again...");
					correctPiece = false;
				}						
			}	
		}
	}
	
	
	public void castling(Piece p) {
		if (p.getType()==Type.KING){
			int absCol = Math.abs(startCol-destCol);
			if(absCol==2) {
				if(startCol > destCol) {
					p = spot[startRow][0].getPiece();
					spot[destRow][3].setPiece(p);
					spot[destRow][3].setX(3);
					spot[destRow][3].setY(destRow);
					spot[destRow][0].setPiece(null);
					spot[destRow][0].setX(0);
					spot[destRow][0].setY(startRow);
				}
				
				if(startCol < destCol) {
					p = spot[startRow][7].getPiece();
					spot[destRow][5].setPiece(p);
					spot[destRow][5].setX(5);
					spot[destRow][5].setY(destRow);
					spot[destRow][7].setPiece(null);
					spot[destRow][7].setX(7);
					spot[destRow][7].setY(startRow);
				}
			}
		}
	}
}
