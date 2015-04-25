
/**
 * @author juan
 *
 */
public abstract class Game {
	
	/**
	 * The golf course where the game is played
	 */
	private GolfCourse course;
	/**
	 * The group playing the game.
	 */
	private Group group;
	/**
	 * An multidimensional array that will hold the player scores.
	 */
	private int[][] scores;
	
	/**
	 * Creates a new Game played on the given GolfCourse by the given
	 * Group.
	 * @param course
	 * @param group
	 */
	public Game(GolfCourse course, Group group) {
		this.course = course;
		this.group = group;
		
		scores = new int[group.getPlayers().size()][18]; 
	}	
	/**
	 * Method allows construction of multidimensional array that will contain
	 * the scores of each player.
	 * @param playerNumber Specifies the number of the player.
	 * @param array An array of the specified player's scores.
	 */
	public void setIndividualScore(int playerNumber, int[] array){
		scores[playerNumber] = array;
	}
	
	/**
	 * @return a multidimensional array of player scores
	 */
	public int[][] getScores() {
		return scores;
	}
	
	public abstract void calculateGameScore();
	
	
	/**
	 * @return the group
	 */
	public Group getGroup() {
		return group;
	}
	/**
	 * @return the course
	 */
	public GolfCourse getCourse() {
		return course;
	}

}
