package Pieces;
import Game.Spot;

public class King extends Piece{
	Type type;
	
	public King(boolean white) {
		super(white);
		type = Type.KING;
		Spot[][]sp;
	}

	
	@Override
	public boolean ValidMovement(int startRow, int startCol, int destRow, int destCol, Spot[][]sp) {
		
		int absRow = Math.abs(startRow-destRow);
		int absCol = Math.abs(startCol-destCol);
		
		if (absRow==0 && absCol==1){
			return true;
		}
		
		if (absRow==1 && absCol==0){
			return true;
		}
		
		if (absRow==1 && absCol==1){
			return true;
		}
		
		if(absRow ==0 && absCol==2 &&
			sp[startRow][startCol].getCount()==0){
			
			if(white && startRow == 0 && startCol ==4) {
				if(castlingShort(startRow, startCol, destCol, sp)) {
					return true;
				}
			}
			
			if(white==false && startRow == 7 && startCol ==4) {
				if(castlingShort(startRow, startCol, destCol, sp)) {
					return true;
				}
			}
		}
		return false;	
	}

	@Override
	public Type getType() {
		
		return type;
	}
	

	public boolean castlingShort(int startRow, int startCol, int destCol, Spot[][]sp) {
		if(startCol > destCol &&
			sp[startRow][3].getPiece()==null &&
			sp[startRow][2].getPiece()==null &&
			sp[startRow][1].getPiece()==null &&
			(sp[startRow][0].pieceName()=="Rock" &&
			sp[startRow][0].getCount()==0))
		{
			return true;
		}
								
		if(startCol < destCol &&
			sp[startRow][5].getPiece()==null &&
			sp[startRow][6].getPiece()==null &&
			(sp[startRow][7].pieceName()=="Rock" &&
			sp[startRow][7].getCount()==0))
						
		{
			return true;
		}	
		return false;		
	}
}
