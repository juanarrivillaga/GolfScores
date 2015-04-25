import java.io.Serializable;


public class GolfCourse implements Serializable{
	
	private String courseName;
	private int[] par;
	private int[] handicap; 
	
	public GolfCourse(String courseName, int[] par, int[] handicap) {
		this.courseName = courseName;
		this.par = par;
		this.handicap = handicap;
		
	}
	
	public GolfCourse(){
		this.courseName = "No name yet";
		this.par = new int[18];
		this.handicap = new int[18];
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int[] getPar() {
		return par;
	}

	public void setPar(int[] par) {
		this.par = par;
	}

	public int[] getHandicap() {
		return handicap;
	}

	public void setHandicap(int[] handicap) {
		this.handicap = handicap;
	}

}
