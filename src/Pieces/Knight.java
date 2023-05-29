package Pieces;
import Game.Spot;

public class Knight extends Piece{
	Type type;

	public Knight(boolean white) {
		super(white);
		type = Type.KNIGHT;
	}




	@Override
	public boolean ValidMovement(int startRow, int startCol, int destRow, int destCol, Spot[][]sp) {

		int absRow = Math.abs(startRow-destRow);
		int absCol = Math.abs(startCol-destCol);
		
		if (absRow==1 && absCol==2 || absRow==2 && absCol==1) {
			return true;
		}
		else
			return false;
	}


	@Override
	public Type getType() {
		 
		return type;
	}


	

	
}


