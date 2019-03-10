package gui;

import java.awt.HeadlessException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import players.Entity;

public class GameFrame extends JFrame{
	
	public GamePanel gamePanel;
	
	public GameFrame(String title,int w,int h, ArrayList<Entity> entityList) throws HeadlessException {
		super(title);//send title to super constractor
		setSize( w,h);//set frame width and height 
		
		//close window when user clicks the x
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		//entityList = new ArrayList<Entity>();
		if (entityList.size() == 0 || entityList == null) {
			JOptionPane.showMessageDialog(null, "JSON file was not found or no data was readed from file", "ERROR", JOptionPane.INFORMATION_MESSAGE);
			setVisible( false );
		}
		else {
			gamePanel = new GamePanel(entityList);
			add(gamePanel);
			setVisible( true );
		}
	}
	

}
