package Pieces;

import Game.Spot;

public class Pawn extends Piece{
	Type type;
	Spot[][]sp;
	public Pawn(boolean white) {
		super(white);
		type = Type.PAWN;		
	}

	
	public boolean ValidMovement(int startRow, int startCol, int destRow, int destCol, Spot[][]sp) {
		
		if (startRow>destRow  && white ==true) {
			return false;
		}
		
		if (startRow<destRow  && white ==false) {
			return false;
		}
		
		int absRow = Math.abs(startRow-destRow);
		int absCol = Math.abs(startCol-destCol);
		
		
		if (startRow == 1 && white==true || startRow == 6 && white==false) {
			if(absRow==2 && absCol==0 || absRow==1 && absCol ==0) {
				return true;
			}
		}
		
		else if(absRow==1 && absCol ==0) {
			return true;			
		}
		
		else if(absRow==1 && absCol==1)
			if (sp[destRow][destCol].getPiece() != null && white != sp[destRow][destCol].getPiece().white)  {
			return true;
		}		
		return false;
	}

	
	public Type getType() {
		return type;
	}
}
