package runGame;



import java.util.ArrayList;

import gui.GameFrame;
import gui.InitGameGUI;
import players.Entity;
import readFile.ReadJsonFile;

public class StartGame {

	public static void main(String[] args) {
		
		ArrayList<Entity> entityList = new ArrayList<Entity>();
		ReadJsonFile.readfile(entityList);
		System.out.println("load Json data");
		//GameFrame gameFrame = new GameFrame("Eltel panel", 1000, 1000,entityList);
		InitGameGUI initGameGUI = new InitGameGUI();
		initGameGUI.initGui(entityList);
		
    }

	
}

