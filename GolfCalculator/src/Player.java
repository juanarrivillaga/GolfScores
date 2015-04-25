import java.io.Serializable;

public class Player implements Serializable{
	
	private String name;
	private int handicap;
	
	
	public Player(String name, int handicap) {
		this.name = name;
		this.handicap = handicap;
	}
	
	public Player(){
		this("No-Name-Yet", 0);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHandicap() {
		return handicap;
	}

	public void setHandicap(int handicap) {
		this.handicap = handicap;
	}

}
