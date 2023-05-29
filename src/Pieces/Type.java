package Pieces;

public enum Type {
	ROCK("Rock"), 
	KNIGHT("Knight"), 
	BISHOP("Bishop"), 
	QUEEN("Queen"), 
	KING("King"), 
	PAWN("Pawn");
	
	
	
	
	String name;
	
	Type (String n){
		name = n;
	}
}
