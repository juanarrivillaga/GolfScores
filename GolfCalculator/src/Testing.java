
public class Testing {

	public static void main(String[] args) {
		GolfCourse international = new GolfCourse();
		int[] par = {5,4,4,3,4,4,4,3,4,4,3,4,4,4,5,5,3,4};
		international.setPar(par);
		int[] handi = {13,11,7,15,9,1,3,17,5,8,18,14,4,12,6,2,16,10};
		international.setHandicap(handi);
		
		
		
		Group group = new Group("Retired Men's");
		group.addPlayer(new Player("Jon", 5));
		group.addPlayer(new Player("Jack", 10));
		
		
		
		
		StableFord game = new StableFord(international, group);
		int[] jonScore = {4,4,6,5,4,4,4,5,3,4,3,4,3,5,6,5,4,4};
		int[] jackScore = {5,5,6,3,4,5,5,4,5,4,4,5,4,5,6,5,3,3};
		game.setIndividualScore(0, jonScore);
		game.setIndividualScore(1, jackScore);
		for (int x: game.getCourse().getPar()){
			System.out.printf("%d ", x);
		}
		System.out.println();
		outputArray(game.modifyPerHoleHandicap(game.getGroup(), game.getCourse(), game.getScores()));
		System.out.println();
		System.out.println();
		game.calculateGameScore();
		for(int element : game.getArrayScore()){
			System.out.printf("%d ", element);
		}
		

	}
	
	public static void outputArray(int[][] array){
		for (int row = 0; row < array.length; row++){
			for (int column = 0; column < array[row].length; column++){
				System.out.printf("%d ", array[row][column]);
			}
			System.out.println();
		}
	}

}
