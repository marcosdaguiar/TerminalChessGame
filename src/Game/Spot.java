package Game;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import Pieces.*;

public class Spot {
	Piece piece;
	int x, y, count=0;
	

	public Spot(int y, int x, Piece piece) {
		this.setPiece(piece);
		this.setX(x);
		this.setY(y);
	}

	public Piece getPiece() {		
		return this.piece;		
	}

	public void setPiece(Piece p) {
		this.piece = p;
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void setCount() {
		count ++;
	}
	public int getCount() {
		return count;
	}
	
	
	public void value() {
		
		String name = "" + getPiece().getType();
		if (this.piece.white) {
			System.out.print(name.toUpperCase() +"\t");
		}
		else {
			System.out.print(name.toLowerCase() + "\t");
		}
		
			
	}
	
	
	
	public String pieceName() {
		
		String name =getPiece().getType().name();
		
		
		return name;
	}

}
