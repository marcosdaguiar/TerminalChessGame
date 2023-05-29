package Game;
import Pieces.King;
import Pieces.Piece;


public class Check {
	int row;
	int col;
	boolean white;
	public Spot [][] spot; 
	


	public Check(int row, int col, boolean white, Spot [][]spot) {
		this.row=row;
		this.col=col;
		this.white=white;
		this.spot = spot;

	}

	public int getX() {
		return row;
	}

	public void setX(int row) {
		this.row = row;
	}

	public int getY() {
		return col;
	}

	public void setY(int col) {
		this.col = col;
	}

	
	public boolean checkStatus() {
		if (knightCombination()==true) {						
			return true;
		}
		
		if(diagonalCombination()==true) {			
			return true;
		}
		
		if(straightCombination()==true) {
			return true;
		}
		return false;
	}
	
	public boolean rangeCheck(int destX, int destY) {
		if(destX < 0 || destX > 7) {
			return false;
		}
		
		if  (destY <  0 || destY > 7) {
			return false;
		}
		
		return true;
	}
	
	public boolean KnightCheck(int checkRow, int checkCol) {

			if (spot[checkRow][checkCol].getPiece()!=null) {
				if(spot[checkRow][checkCol].pieceName()== "Knight") {
					if (spot[checkRow][checkCol].piece.white != white) {
						return true;
					}
				}				
			}
	
		return false;
	}
	
	public boolean knightCombination() {
		int checkRow = row+2;
		int checkCol = col+1;
		
		if(rangeCheck(checkRow = row+2, checkCol = col+1)) {
			if(KnightCheck(checkRow, checkCol)) {
				return true;	
			}		
		}		
		if(rangeCheck(checkRow = row+2, checkCol = col-1)) {
			if(KnightCheck(checkRow, checkCol)) {
				return true;	
			}	
		}
		if(rangeCheck(checkRow = row-2, checkCol = col+1)) {
			if(KnightCheck(checkRow, checkCol)){
				return true;	
			}	
		}
		if(rangeCheck(checkRow = row-2, checkCol = col-1)) {
			if(KnightCheck(checkRow, checkCol)){
				return true;	
			}	
		}
		if(rangeCheck(checkRow = row+1, checkCol = col+2)) {
			if(KnightCheck(checkRow, checkCol)){
				return true;	
			}	
		}
		if(rangeCheck(checkRow = row+1, checkCol = col-2)) {
			if(KnightCheck(checkRow, checkCol)){
				return true;	
			}	
		}
		if(rangeCheck(checkRow = row-1, checkCol = col+2)) {
			if(KnightCheck(checkRow, checkCol)){
				return true;	
			}	
		}
		if(rangeCheck(checkRow = row-1, checkCol = col-2)) {
			if(KnightCheck(checkRow, checkCol)){
				return true;	
			}	
		}
		
		return false;
	}
	
	public boolean diagonalCombination() {
		String piece;
		for(int checkRow=row+1, checkCol=col+1; checkRow <=7 ; checkRow++,checkCol++) {
			if(rangeCheck(checkRow, checkCol)) {

				if(spot[checkRow][checkCol].getPiece()!=null) {
					
					if(spot[checkRow][checkCol].piece.white==white) {
						break;
					}
					
					piece = spot[checkRow][checkCol].pieceName();
				
					if (piece.equalsIgnoreCase("Queen") ||
						piece.equalsIgnoreCase("Bishop") ||
						(piece.equalsIgnoreCase("Pawn") && 
						checkRow == row+1)) {
							return true;
					}
					if (piece.equalsIgnoreCase("Rock") ||
						piece.equalsIgnoreCase("Knight")) {
							break;
					}
				}				
			}
		}
		
		for(int checkRow=row+1, checkCol=col-1; checkRow <=7 ; checkRow++,checkCol--) {
			if(rangeCheck(checkRow, checkCol)) {

				if(spot[checkRow][checkCol].getPiece()!=null) {
										
					if(spot[checkRow][checkCol].piece.white==white) {
						break;
					}
					
					piece = spot[checkRow][checkCol].pieceName();
				
					if (piece.equalsIgnoreCase("Queen") ||
							piece.equalsIgnoreCase("Bishop") ||
							(piece.equalsIgnoreCase("Pawn") && 
							checkRow == row+1)) {
								return true;
					}
					if (piece.equalsIgnoreCase("Rock") ||
						piece.equalsIgnoreCase("Knight")) {
							break;
					}
				}				
			}
		}	
		
		for(int checkRow=row-1, checkCol=col+1; checkRow >=0 ; checkRow--,checkCol++) {
			if(rangeCheck(checkRow, checkCol)) {

				if(spot[checkRow][checkCol].getPiece()!=null) {
				
					if(spot[checkRow][checkCol].piece.white==white) {
						break;
					}
					
					piece = spot[checkRow][checkCol].pieceName();
				
					if (piece.equalsIgnoreCase("Queen") ||
						piece.equalsIgnoreCase("Bishop") ||
						(piece.equalsIgnoreCase("Pawn") && 
						checkRow == row-1)) {
							return true;
					}
					if (piece.equalsIgnoreCase("Rock") ||
						piece.equalsIgnoreCase("Knight")) {
							break;
					}
				}				
			}
		}	
		
		for(int checkRow=row-1, checkCol=col-1; checkRow >=0 ; checkRow--,checkCol--) {
			if(rangeCheck(checkRow, checkCol)) {
			
				if(spot[checkRow][checkCol].getPiece()!=null) {
					
					if(spot[checkRow][checkCol].piece.white==white) {
						break;
					}
					
					piece = spot[checkRow][checkCol].pieceName();
				
					if (piece.equalsIgnoreCase("Queen") ||
						piece.equalsIgnoreCase("Bishop") ||
						(piece.equalsIgnoreCase("Pawn") && 
						checkRow == row-1)) {
							return true;
					}
					if (piece.equalsIgnoreCase("Rock") ||
						piece.equalsIgnoreCase("Knight")) {
							break;
					}
				}				
			}
		}	
			
		return false;
	}
	
	public boolean straightCombination() {
		String piece;

		for(int checkRow=row+1; checkRow <= 7 ; checkRow++) {
			if(rangeCheck(checkRow, col)) {

				if(spot[checkRow][col].getPiece()!=null) {
					if(spot[checkRow][col].piece.white==white) {
						break;
					}
					piece = spot[checkRow][col].pieceName();
									
					if (piece.equalsIgnoreCase("Queen") ||
						piece.equalsIgnoreCase("Rock")) {
						return true;
					}
					else if(piece.equalsIgnoreCase("Pawn")||
							piece.equalsIgnoreCase("Bishop")||
							piece.equalsIgnoreCase("Knight")){
							break;
					}					
				}				
			}
		}	
		
		for(int checkRow=row-1; checkRow >= 0 ; checkRow--) {
			if(rangeCheck(checkRow, col)) {

				if(spot[checkRow][col].getPiece()!=null) {
					
					if(spot[checkRow][col].piece.white==white) {
						break;
					}
					piece = spot[checkRow][col].pieceName();
					if (piece.equalsIgnoreCase("Queen") ||
						piece.equalsIgnoreCase("Rock")) {
						return true;
					}
					else if(piece.equalsIgnoreCase("Pawn")||
							piece.equalsIgnoreCase("Bishop")||
							piece.equalsIgnoreCase("Knight")){
							break;
					}					
				}				
			}
		}	
		
		for(int checkCol=col+1; checkCol <= 7 ; checkCol++) {
			if(rangeCheck(row, checkCol)) {

				if(spot[row][checkCol].getPiece()!=null) {
					
					if(spot[row][checkCol].piece.white==white) {
						break;
					}
					piece = spot[row][checkCol].pieceName();
					if (piece.equalsIgnoreCase("Queen") ||
						piece.equalsIgnoreCase("Rock")) {
						return true;
					}
					else if(piece.equalsIgnoreCase("Pawn")||
							piece.equalsIgnoreCase("Bishop")||
							piece.equalsIgnoreCase("Knight")){
							break;
					}								
				}				
			}
		}	
		
		for(int checkCol=col-1; checkCol >=0 ; checkCol--) {
			if(rangeCheck(row, checkCol)) {

				if(spot[row][checkCol].getPiece()!=null) {
					
					if(spot[row][checkCol].piece.white==white) {
						break;
					}
					piece = spot[row][checkCol].pieceName();
					if (piece.equalsIgnoreCase("Queen") ||
						piece.equalsIgnoreCase("Rock")) {
						return true;
					}
					else if(piece.equalsIgnoreCase("Pawn")||
							piece.equalsIgnoreCase("Bishop")||
							piece.equalsIgnoreCase("Knight")){
							break;
					}						
				}				
			}
		}	
		return false;
	}
	
}

