package Pieces;
import java.math.*;
import Game.*;

public abstract class Piece {
//	public int x,y;
	public boolean white = true;
	

	public Piece(boolean white) {
		
		this.white = white;	
	}
	

	public abstract boolean ValidMovement (int startRow, int startCol, int destRow, int destCol, Spot[][] sp);
	
	
	public abstract Type getType();
}
