package gui;

import java.util.ArrayList;

import players.Entity;

public class InitGameGUI {
	
	public InitGameGUI() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void initGui(ArrayList<Entity> entityList) {
		GameFrame gameFrame = new GameFrame("Eltel panel", 1000, 1000,entityList);
	}
	
}
