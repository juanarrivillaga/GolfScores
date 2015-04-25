import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class AddCourseGUI extends JFrame {
	
	// instance variables for GUI
	private final JPanel panel1;
	private final JPanel panel2;
	private final JButton[] button;
	private final JLabel label;
	private JTextField text;
	private String courseName;
	private int[] par = new int[18];
	private int[] handicap = new int[18];
	private int parCounter = 18;
	private int handiCounter = 18;
	
	public AddCourseGUI() {
		super("ADD GOLF COURSE");
		label = new JLabel("Par Hole 1");
		text = new JTextField("",5);
		text.setHorizontalAlignment(JTextField.CENTER);
		text.setEditable(false);
		button = new JButton[12];
		panel1 = new JPanel();
		panel2 = new JPanel();
		
		// set Layout for  panel and frame
		setLayout(new BorderLayout());
		panel1.setLayout(new GridLayout(4, 3));
		panel2.setLayout(new FlowLayout());
		
		// create and add buttons to  panel1 using an admittedly clumsy conditional
		for(int count = 0; count < button.length; count++){
			if (count <= 8){
				button[count] = new JButton(""+ (count + 1));
			} else if (count == 9){
				button[count] = new JButton("Clear");
			} else if (count == 10){
				button[count] = new JButton("0");
			} else 
				button[count] = new JButton("Enter");
			panel1.add(button[count]);
		}
		
		// add label and prompt textField to container panel2 
		panel2.add(label);
		panel2.add(text);
		
		// add panels to frame and validate
		add(panel2, BorderLayout.NORTH);
		add(panel1, BorderLayout.SOUTH);
		validate();
		
		
		// create handler objects and register listeners
		ButtonAction numberHandler = new ButtonAction();
		ClearAction clearHandler = new ClearAction();
		EnterAction enterHandler = new EnterAction();
		for (int i = 0; i < 9; i++){
			button[i].addActionListener(numberHandler);
		}
		button[9].addActionListener(clearHandler);
		button[10].addActionListener(numberHandler);
		button[11].addActionListener(enterHandler);
		
		courseName = 
				JOptionPane.showInputDialog("Course Name?");
		
	}
		
	private  class ButtonAction implements ActionListener{
		String string ="";
		@Override
		public void actionPerformed(ActionEvent e) {
			String string2 = new String(text.getText());
			string = string2  + e.getActionCommand();
			text.setText(string);	
		}
			
    }
	
	private class ClearAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			text.setText("");
		}
	}
	
	private class EnterAction implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (parCounter > 0){
				int buffer = Integer.parseInt(text.getText());
				par[18 - parCounter] = buffer;
				String str = String.format("Par Hole %d", 20 - parCounter);
				text.setText("");
				label.setText(str);
				parCounter -= 1;
				if (parCounter == 0){
					
					label.setText("Handicap Hole 1");
				}
			} else if (handiCounter > 0){
				int buffer = Integer.parseInt(text.getText());
				handicap[18 - handiCounter] = buffer;
				String str = String.format("Handicap Hole %d", 20 - handiCounter);
				text.setText("");
				label.setText(str);
				handiCounter -= 1;
				if (handiCounter == 0){
					
					label.setText("Save Course?");
				}
			} else {
				GolfCourse course = new GolfCourse(courseName, par, handicap);
				ObjectOutputStream output;
				try{
				    output = new ObjectOutputStream(
							Files.newOutputStream(Paths.get("course.ser")));
					output.writeObject(course);
					if (output!= null){
						output.close();
					}

				} catch (IOException exception){
					System.err.println("Error opening or writing to file. Terminating");
					System.exit(1);
				}
				
				setVisible(false);
				dispose();

			}
			
			
			
		}

	 }
	

	/**
	 * @return the courseName
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * @param courseName the courseName to set
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	
}