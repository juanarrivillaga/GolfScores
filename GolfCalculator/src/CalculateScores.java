
public interface CalculateScores {
	
	/**
	 * @param group  a group object
	 * @param course a course object
	 * @param scores array of scores to adjust
	 * @return a multidimensional array of scores that have been modified based on the hole handicap
	 */
	public default int[][] modifyPerHoleHandicap(Group group, GolfCourse course, int[][] scores){
		// copy scores into new multi-dim array
		int[][] scoreBuffer = new int [group.getPlayers().size()][18];
		for (int row = 0; row < scores.length; row++){
			for (int column = 0; column < scores[row].length; column++){
				scoreBuffer[row][column] = scores[row][column];
			}
		}
				
		// modify each of the scores by handicap
		for (int r = 0; r < scoreBuffer.length; r++){
			int handi = group.getPlayers().get(r).getHandicap();
			// how many times does 18 fit into handicap? Deal with handicaps greater than 18
			int handiQuotient = handi / 18;
			for(int i = 0; i <= handiQuotient; i++);{ 
				for (int c = 0; c < scoreBuffer[r].length; c++){
					if (course.getHandicap()[c] <= handi){
						scoreBuffer[r][c] -= 1; // take away a stroke from that players stroke-score
					}
				}
			}
					
			// now we deal with the remainder
			if (handiQuotient >= 1){
				int remainderHandi = handi % 18;
				for (int c = 0; c < scoreBuffer[r].length; c++){
					if (course.getHandicap()[c] <= remainderHandi){
						scoreBuffer[r][c] -= 1; // take away a stroke from that players stroke-score
					}
				}
			}
		}
		
		return scoreBuffer;
		
	}
}
