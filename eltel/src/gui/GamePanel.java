package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import enums.Size;
import mapperUtils.Utils;
import players.Entity;
import players.PaintInfo;

public class GamePanel extends JPanel implements ActionListener{
	
	public JButton startBtn, endBtn;
	public GridBagConstraints gc;
	public ArrayList<JCheckBox> checkBoxList = new ArrayList<JCheckBox>();
	public ArrayList<PaintInfo> paintInfoList = new ArrayList<PaintInfo>();
	public ArrayList<PaintInfo> removedPaintInfoList = new ArrayList<PaintInfo>();
	public ArrayList<String> checkBoxNames = new ArrayList<String>();
	static Timer t;
	public ArrayList<Integer> randomNumberList = new ArrayList<Integer>();
	
	public GamePanel(ArrayList<Entity> entityList) {
		super();
		setSize( 900, 900 );//set banel size
		setLayout(new GridBagLayout());
		gc = new GridBagConstraints();
		//setBackground(Color.DARK_GRAY);//set panel background color
		init(entityList);
		Utils.mapPaintInfo(paintInfoList, entityList, checkBoxNames);
		//getPaintInfoList(paintInfoList,entityList,checkBoxNames);
		getPaintInfoList(paintInfoList);
		checlPointsForOutofBoundary(paintInfoList);
		Utils.setRandomNumberList(randomNumberList);
	}
	
	private void checlPointsForOutofBoundary(ArrayList<PaintInfo> paintInfoList) {
		for (int i = 0; i < paintInfoList.size(); i++) {
			if (Utils.isOutofBoundary(paintInfoList.get(i))) {
				paintInfoList.remove(i);
			}
		}
		if (paintInfoList.size() == 0) {
			JOptionPane.showMessageDialog(null, "there NO entity's to show , all the entity's are Out of Boundary", "ERROR", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}

	private void getPaintInfoList(ArrayList<PaintInfo> paintInfoList) {
		int zeroX=683;
		int smallY=555;
		int mediumY=550;
		int largeY=545;
			for (int i = 0; i < paintInfoList.size(); i++) {
				//PaintInfo paintInfo = paintInfoList.get(i);
				if ("small".equals(paintInfoList.get(i).getSize())) {
					paintInfoList.get(i).setX(zeroX+paintInfoList.get(i).getX());
					paintInfoList.get(i).setY(smallY-paintInfoList.get(i).getY());
					paintInfoList.get(i).setW(5);
					paintInfoList.get(i).setH(5);
				}
				else if ("medium".contentEquals(paintInfoList.get(i).getSize())) {
					paintInfoList.get(i).setX(zeroX+paintInfoList.get(i).getX());
					paintInfoList.get(i).setY(mediumY-paintInfoList.get(i).getY());
					paintInfoList.get(i).setW(10);
					paintInfoList.get(i).setH(10);
				}
				else {
					paintInfoList.get(i).setX(zeroX+paintInfoList.get(i).getX());
					paintInfoList.get(i).setY(largeY-paintInfoList.get(i).getY());
					paintInfoList.get(i).setW(15);
					paintInfoList.get(i).setH(15);
				}
				if ("triangle".equals(paintInfoList.get(i).getShape())) {
					Utils.calculatePolygon(paintInfoList.get(i));
				}
			}	
	}


	private void init(ArrayList<Entity> entityList) {
		initChackBox(entityList,checkBoxList, checkBoxNames);
		initJButton();
		add_components();
	}
	
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    // Draw main window
	    g.setColor(Color.WHITE);
	    g.drawRect(683, 460, 100, 100);
	    g.fillRect(683, 460, 100, 100);
	    
	    for (int i = 0; i < paintInfoList.size(); i++) {
	    	PaintInfo paintInfo = paintInfoList.get(i);
	    	if ("square".equals(paintInfo.getShape())) {
	    		Graphics2D g2d = (Graphics2D) g.create();
		    	g2d.setColor(paintInfo.getColor());
		    	g2d.drawRect(paintInfo.getX(), paintInfo.getY(), paintInfo.getW(), paintInfo.getH());
		    	g2d.fillRect(paintInfo.getX(), paintInfo.getY(), paintInfo.getW(), paintInfo.getH());
		    	g2d.dispose();
			}
	    	else if ("circle".equals(paintInfo.getShape())) {
	    		Graphics2D g2d = (Graphics2D) g.create();
	    		g2d.setColor(paintInfo.getColor());
	    		g2d.drawOval(paintInfo.getX(), paintInfo.getY(), paintInfo.getW(), paintInfo.getH());
	    		g2d.fillOval(paintInfo.getX(), paintInfo.getY(), paintInfo.getW(), paintInfo.getH());
	    		g2d.dispose();
			}
	    	else {
	    		Graphics2D g2d = (Graphics2D) g.create();
	    		g2d.setColor(paintInfo.getColor());
	    		g2d.drawPolygon(paintInfo.getxArray(), paintInfo.getyArray(), 3);
	    		g2d.fillPolygon(paintInfo.getxArray(), paintInfo.getyArray(), 3);
	    		g2d.dispose();
	    	}
	    	
		}
	}
	
	

	private void add_components() {
		
		Insets i = new Insets(0, 0, 0, 0);
		gc.insets = i;
		
		gc.ipady = 32;
		
		int gridy = 1;
		for (int j = 0; j < checkBoxList.size(); j++) {
			gc.gridx = 0;
			gc.gridy = gridy;
			gc.insets = i;
			if (j == checkBoxList.size() ) {
				i = new Insets(0, 0, 20, 0);//bottom margin 10px
				gc.insets = i;
			}
			add(checkBoxList.get(j),gc);
			gridy++;
		}
		
		i = new Insets(0, 0, 10, 0);//bottom margin 10px
		gc.insets = i;
		
		gc.gridx = 0;
		gc.gridy = gridy;
		gridy++;
		add(startBtn,gc);
		
		gc.gridx = 0;
		gc.gridy = gridy;
		add(endBtn,gc);
	}



	private void initJButton() {
		startBtn = new JButton("start");
		startBtn.addActionListener(this);
		endBtn = new JButton("end");
		endBtn.addActionListener(this);
	}

	private void initChackBox(ArrayList<Entity> entityList, ArrayList<JCheckBox> checkBoxList, ArrayList<String> checkBoxNames2) {
		for (int i = 0; i < entityList.size(); i++) {
			JCheckBox checkBox;
			Entity entityToCheck = entityList.get(i);
			String checkBoxName = "eld_" + entityToCheck.getId() + "_" +entityToCheck.getName();
			checkBox = new JCheckBox(checkBoxName);
			checkBox.setSelected(true);
			checkBox.setName(checkBoxName);
			checkBoxNames2.add(checkBoxName);
			checkBox.addActionListener(this);
			checkBoxList.add(checkBox);
		}
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == startBtn) {
			//System.out.println("Start");
			t = new Timer();
			t.schedule(new TimerTask() {
			    @Override
			    public void run() {
			    	Utils.moveEntitys(paintInfoList,randomNumberList);
			    	repaint();
			    }
			}, 0, 1000);
		}
		else if (e.getSource() == endBtn) {
			//System.out.println("end");
			t.cancel();
			Utils.creatreCsv(paintInfoList);
		}
		else {
			JCheckBox checkBox = (JCheckBox) e.getSource();
			String checkBoxName = Utils.getCheckBoxNameFromList(checkBoxNames, checkBox.getName());
			if (!checkBox.isSelected()) {
				Utils.removePointFromPaintInfoList(paintInfoList, checkBoxName,removedPaintInfoList);
				repaint();
			}
			else if(checkBox.isSelected()){
				Utils.addRemovePaintInfo(paintInfoList, checkBoxName, removedPaintInfoList);
				repaint();
			}
		}
		
		

	}

}
