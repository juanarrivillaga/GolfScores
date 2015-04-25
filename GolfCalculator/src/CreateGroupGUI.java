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


public class CreateGroupGUI extends JFrame {
	
	// instance variables for GUI
	private final JPanel panel1;
	private final JPanel panel2;
	private final JButton[] button;
	private final JLabel label;
	private JTextField text;
	private String groupName;
	private int playerNumber;
	private int counter = 1;
	private Group group;
	
	
	public CreateGroupGUI() {
		super("CREATE GROUP");
		label = new JLabel("Player 1 Handicap");
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
		
		groupName = 
				JOptionPane.showInputDialog("Group Name?");
		playerNumber = Integer.parseInt(
				JOptionPane.showInputDialog("How many people in group?"));
		
		group = new Group(groupName);
		
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
			if (playerNumber > 0){
				
				int buffer = Integer.parseInt(text.getText());
				String name = 
						JOptionPane.showInputDialog("Player Name?");
				
				Player player = new Player(name, buffer);
				
				group.addPlayer(player);
				
				counter += 1;
				String str = String.format("Player %d Handicap", counter);
				text.setText("");
				label.setText(str);
				playerNumber -= 1;
				if (playerNumber == 0){
					
					label.setText("Save Group?");
				}
			
			} else {
				
				ObjectOutputStream output;
				try{
				    output = new ObjectOutputStream(
							Files.newOutputStream(Paths.get("group.ser")));
					output.writeObject(group);
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
	
	 //main method
	public static void main(String[] args){
		CreateGroupGUI frame = new CreateGroupGUI();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,200);
		frame.setVisible(true);
		frame.setResizable(false);
	}

	
}