
import java.awt.FlowLayout;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.ObjectInputStream;

import javax.swing.JFrame;
import javax.swing.JButton;

public class GolfGuiStartScreen extends JFrame{

   private final JButton addCourse; // button to launch add course
   private final JButton newGame; // button to start new game
   private final JButton createGroup; // button launch create group
   private final FlowLayout layout; // layout object
   private final Container container; // container to set layout
   
   // constructor set up GUI and register button listeners
   public GolfGuiStartScreen(){
      super("GolfCalculator");
      
      layout = new FlowLayout(); 
      container = getContentPane(); // get container to layout
      setLayout(layout);
      
      // set up leftButton and register listener
      addCourse = new JButton("Add Course");
      add(addCourse); // add left button to frame
      addCourse.addActionListener(
         new ActionListener() // anonymous inner class
         {
            // process addCourse event
            @Override 
            public void actionPerformed(ActionEvent event){
            	
            	AddCourseGUI frame = new AddCourseGUI();
            	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        		frame.setSize(400,200);
        		frame.setVisible(true);
        		frame.setResizable(false);
            }
         }
      );
      
      // set up newGame button and register listener
      newGame = new JButton("New Game");
      add(newGame); // add center button to frame
      newGame.addActionListener(
         new ActionListener() // anonymous inner class
         {
            // process newGame button event
            @Override
            public void actionPerformed(ActionEvent event){
            	
            	
                NewGameGUI frame = new NewGameGUI();
               	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            	frame.setSize(400,200);
           		frame.setVisible(true);
           		frame.setResizable(false);
           		

               	
            }
         }
      ); 
      
      // set up createGroup button and register listener
      createGroup = new JButton("Create Group");
      add(createGroup); // add right button to frame
      createGroup.addActionListener(
         new ActionListener() // anonymous inner class
         {
            // process centerButton event
            @Override
            public void actionPerformed(ActionEvent event){
            	CreateGroupGUI frame = new CreateGroupGUI();
            	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        		frame.setSize(400,200);
        		frame.setVisible(true);
        		frame.setResizable(false);
             
            }
         }
      );     
   }
   
   public static void main(String[] args){
	   GolfGuiStartScreen mainFrame = new GolfGuiStartScreen();
	   mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   mainFrame.setSize(400,200);
	   mainFrame.setVisible(true);
	   mainFrame.setResizable(false);
   }
   
}