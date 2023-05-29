package Pieces;
import Game.Spot;

public class Bishop extends Piece {
	Type type;
	
	public Bishop(boolean white) {
		super(white);
		type = Type.BISHOP;
	}


	@Override
	public boolean ValidMovement(int startRow, int startCol, int destRow, int destCol, Spot[][]sp) {
		int absRow = Math.abs(startRow-destRow);
		int absCol = Math.abs(startCol-destCol);
		
		if (absRow==absCol) {
			return true;
		}
		return false;
	}


	@Override
	public Type getType() {
		
		return type;
	}
}
