import java.util.Arrays;

public class Nassau extends Game {
	int[][] nassauPointsPerPlayer;
	
	/**
	 * Creates a new Game played on the given GolfCourse by the given
	 * Group.
	 * @param course
	 * @param group
	 */
	public Nassau(GolfCourse course, Group group) {
		super(course, group);
	}
	
	@Override
	public void calculateGameScore() {
		// make array to store the number of points won by each player in frontNine, backNine,
		int[][] playerPoints = new int[getGroup().getPlayers().size()][2];
		
		// first find lowest handicap:
		int[] handicaps = new int[getGroup().getPlayers().size()];
		
		for(int i = 0; i < getGroup().getPlayers().size(); i++){
			handicaps[i] = getGroup().getPlayers().get(i).getHandicap();
		}
		
		Arrays.sort(handicaps);
		
		int lowestHandicap = handicaps[0];
		
		// copy scores into new multi-dim array
		int[][] nassauScores = new int [getGroup().getPlayers().size()][18];
		for (int row = 0; row < getScores().length; row++){
			for (int column = 0; column < getScores()[row].length; column++){
				nassauScores[row][column] = getScores()[row][column];
			}
		}
		
		// modify each of the scores by lowestHandicap
		for (int r = 0; r < nassauScores.length; r++){
			// nassauHandicap = playerHandicap - lowestHandicap
			int nassauHandicap = getGroup().getPlayers().get(r).getHandicap() - lowestHandicap;
			// how many times does 18 fit into handicap? Deal with handicaps greater than 18
			int handiQuotient = nassauHandicap / 18;
			for(int i = 0; i <= handiQuotient; i++);{ 
				for (int c = 0; c < nassauScores[r].length; c++){
					if ((getCourse().getHandicap()[c]) <= nassauHandicap){
						nassauScores[r][c] -= 1; // take away a stroke from that players stroke-score
					}
				}
			}
			
			// now we deal with the remainder
			if (handiQuotient >= 1){
				int remainderHandi = nassauHandicap % 18;
				for (int c = 0; c < nassauScores[r].length; c++){
					if ((getCourse().getHandicap()[c]) <= remainderHandi){
						nassauScores[r][c] -= 1; // take away a stroke from that players stroke-score
					}
				}
			}
		}
		
		outputArray(nassauScores);
		
		// now add up points - if it's a tie no points are awarded
		// first find the lowest stroke at each hole
		int[] minimum = new int[18];
		for (int hole = 0; hole < 18; hole++){
			int minStroke = 100; // large value that will never be recorded
			for (int i = 0; i < nassauScores.length; i++){
				minStroke = Math.min(minStroke, nassauScores[i][hole]);
			}
			minimum[hole] = minStroke;
		}
		System.out.println();
		for(int i: minimum){
			System.out.printf("%d ", i);
		}
		
		for (int hole = 0; hole < 18; hole++){
			int [] counting = new int[nassauScores.length];
			int count = 0; 
			for (int i = 0; i< nassauScores.length; i++){
				counting[i] = nassauScores[i][hole];
			}

			// count the number of times min appears in counting
			for(int i : counting){
				if(i == minimum[hole]){
					count++;
				}
			}
			// if there is only one instance of the minimum score
			int location = 0;
			for (int i = 0; i < counting.length; i++){
				if (counting[i] == minimum[hole]){
					location = i;
					break;
				}
				
			}
			
			// front nine
			if (count == 1 && hole < 9 ){
				playerPoints [location][0] += 1;
			}
			
			// back nine
			if (count == 1 && hole >= 9){
				playerPoints[location][1] += 1;
			}
			
			
			
			
		}
		System.out.println();
		outputArray(playerPoints);
		
		nassauPointsPerPlayer = playerPoints;
		
		
		
	}
	
	public int[][] getNassauPointsPerPlayer(){
		return nassauPointsPerPlayer;
	}
	
	private static void outputArray(int[][] array){
		for (int row = 0; row < array.length; row++){
			for (int column = 0; column < array[row].length; column++){
				System.out.printf("%d ", array[row][column]);
			}
			System.out.println();
		}
	}

}
