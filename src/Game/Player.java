package Game;

public class Player {
	String name;
	boolean white;
	int num;
	
	public Player(String name, boolean white) {
		this.name = name;
		this.white = white;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean getWhite() {
		return white;
	}
}

