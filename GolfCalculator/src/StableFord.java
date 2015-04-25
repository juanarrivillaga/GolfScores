
public class StableFord extends Game implements CalculateScores {
	private int[] arrayScore;
	public StableFord(GolfCourse course, Group group) {
		super(course, group);
	}

	@Override
	public void calculateGameScore() {
		int numberOfPlayers = getGroup().getPlayers().size();
		int[] playerPoints = new int[numberOfPlayers];

		int[][] modScores = modifyPerHoleHandicap(getGroup(), getCourse(), getScores());
		
		
		// now calculate stableford points using perHoleHandicap modified scores
		playerPoints = calculateStableFord(modScores);
		this.arrayScore = playerPoints;
		
	
	}
	
	public int[] calculateStableFord(int[][] scores){
		int numberOfPlayers = getGroup().getPlayers().size();
		int[] playerPoints = new int[numberOfPlayers];
		for (int n = 0; n < numberOfPlayers; n++){
			for (int hole = 0; hole < 18; hole++){
				switch(getCourse().getPar()[hole] - scores[n][hole]){
					case 4:
						playerPoints[n] += 6;
						break;
					case 3:
						playerPoints[n] += 5;
						break;
					case 2:
						playerPoints[n] += 4;
						break;
					case 1:
						playerPoints[n] += 3;
						break;
					case 0:
						playerPoints[n] += 2;
						break;
					case -1:
						playerPoints[n] += 1;
						break;
						
				}
			}
		}
		return playerPoints;
	}

	/**
	 * @return the arrayScore
	 */
	public int[] getArrayScore() {
		return arrayScore;
	}
	

}
