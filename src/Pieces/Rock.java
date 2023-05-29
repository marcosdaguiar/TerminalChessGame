package Pieces;
import Game.Spot;


public class Rock extends Piece {
	Type type;


	public Rock(boolean white) {
		super(white);
		type = Type.ROCK;		
	}


	public boolean ValidMovement(int startRow, int startCol, int destRow, int destCol, Spot[][]sp) {

		int absRow = Math.abs(startRow-destRow);
		int absCol = Math.abs(startCol-destCol);
		
		if ((absRow==0)&&(absCol>0)||(absCol==0)&&(absRow>0)) {
			return true;
		}
		else
			return false;		
	}

	
	public Type getType() {
		
		return type;
	}
}
