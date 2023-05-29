package Pieces;

import Game.Spot;

public class Queen extends Piece {
	Type type;

	public Queen(boolean white) {
		super(white);
		type = Type.QUEEN;
	}


	@Override
	public boolean ValidMovement(int startRow, int startCol, int destRow, int destCol, Spot[][]sp) {
		
		int absRow = Math.abs(startRow-destRow);
		int absCol = Math.abs(startCol-startCol);

		if ((absRow==0)&&(absCol>0)||(absCol==0)&&(absRow>0)) {
			return true;
		}
		
		if(absRow==absCol) {
			return true;
		}
		return false;
	}

	@Override
	public Type getType() {
		return type;
	}
}
