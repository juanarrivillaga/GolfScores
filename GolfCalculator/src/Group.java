import java.io.Serializable;
import java.util.ArrayList;

public class Group implements Serializable {
	
	private String groupName;
	private ArrayList<Player> players;
	
	public Group(String groupName) {
		this.setGroupName(groupName);
		players = new ArrayList<>();
	}
	
	public void addPlayer(Player player){
		players.add(player);
	}
	
	public ArrayList<Player> getPlayers(){
		return players;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

}
