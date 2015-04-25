
import java.awt.FlowLayout;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class NewGameGUI extends JFrame{

   
   
   
   // constructor set up GUI and register button listeners
   public NewGameGUI(){
	  
      super("Start New Game");
      try{
      
      ObjectInputStream groupInput;
      ObjectInputStream courseInput;
      
      
    	  groupInput = new ObjectInputStream(
    			  Files.newInputStream(Paths.get("group.ser")));
    	  courseInput = new ObjectInputStream(
    			  Files.newInputStream(Paths.get("course.ser")));
    	  Group group = (Group) groupInput.readObject();
    	  GolfCourse course = (GolfCourse) courseInput.readObject();
    	  if (groupInput != null && courseInput != null){
    		  groupInput.close();
    		  courseInput.close();
    	  }
    		  
    	  
      
     
      String[] stringArray = new String[group.getPlayers().size()];
      
      for(int i = 0; i < group.getPlayers().size(); i++){
    	  String str = String.format("Please enter %s 's scores seperated by a space", group.getPlayers().get(i).getName());
    	  stringArray[i] = JOptionPane.showInputDialog(str);
      }
      
      
      FlowLayout layout = new FlowLayout(); 
      Container container = getContentPane(); // get container to layout
      setLayout(layout);
      
      
      // set up nassau and register listener
      JButton nassau = new JButton("Score Nassau");
      add(nassau); // add left button to frame
      nassau.addActionListener(
         new ActionListener() // anonymous inner class
         {
            // process nassau event
            @Override 
            public void actionPerformed(ActionEvent event){
            	
            	Nassau game = new Nassau(course, group);
            	
            	for (int i = 0; i < stringArray.length; i++){
            		int[] integerArray = new int[18];
            		Scanner input = new Scanner(stringArray[i]);
            		for (int x = 0; x < 18; x++){
            			integerArray[x] = input.nextInt();
            		}
            		game.setIndividualScore(i, integerArray);
            	}	
            	
            	game.calculateGameScore();
            	int[][] points= game.getNassauPointsPerPlayer();
            	String string ="";
            	for(int i = 0; i < group.getPlayers().size(); i++){
            		string = string + String.format("Player %s points: Front Nine: %d Back Nine: %d %n",
            				group.getPlayers().get(i).getName(), points[i][0], points[i][1] );
            	}
            	JOptionPane.showMessageDialog(null, string);
            }
         }
      );
      
      // set up stableFord button and register listener
      JButton stableFord = new JButton("Score Stableford Game");
      add(stableFord); // add center button to frame
      stableFord.addActionListener(
         new ActionListener() // anonymous inner class
         {
            // process newGame button event
            @Override
            public void actionPerformed(ActionEvent event){
            	
            	StableFord game = new StableFord(course, group);
            	
            	for (int i = 0; i < stringArray.length; i++){
            		int[] integerArray = new int[18];
            		Scanner input = new Scanner(stringArray[i]);
            		for (int x = 0; x < 18; x++){
            			integerArray[x] = input.nextInt();
            		}
            		game.setIndividualScore(i, integerArray);
            	} 
            	
            	game.calculateGameScore();
            	int [] points = game.getArrayScore();
            	String string ="";
            	for(int i = 0; i < group.getPlayers().size(); i++){
            		string = string + String.format("Player %s points:  %d %n",
            				group.getPlayers().get(i).getName(), points[i] );
            	}
            	JOptionPane.showMessageDialog(null, string);
            }
         }
      ); 
      
     }
      catch(IOException e){
    	  System.err.println("Error accessing file");
    	  System.exit(1);
      }
      catch(ClassNotFoundException cnf){
    	  System.err.println("Invalid Object type. Terminating");
      }
   }
      
  
   
}