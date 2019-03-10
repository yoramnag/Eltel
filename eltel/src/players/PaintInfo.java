package players;

import java.awt.Color;
import java.util.ArrayList;

public class PaintInfo {
	private int x;
	private int y;
	private Color color;
	private String shape;
	private String size;
	private int w;
	private int h;
	private String checkBoxName;
	private int[] xArray;
	private int[] yArray;


	public PaintInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public PaintInfo(int x, int y, Color color, String shape, String size, int w, int h, String checkBoxName,
			int[] xArray, int[] yArray, int lastPosX, int lastPosY) {
		super();
		this.x = x;
		this.y = y;
		this.color = color;
		this.shape = shape;
		this.size = size;
		this.w = w;
		this.h = h;
		this.checkBoxName = checkBoxName;
		this.xArray = xArray;
		this.yArray = yArray;
	}



	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
	
	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}
	
	public String getCheckBoxName() {
		return checkBoxName;
	}

	public void setCheckBoxName(String checkBoxName) {
		this.checkBoxName = checkBoxName;
	}

	public int[] getxArray() {
		return xArray;
	}

	public void setxArray(int[] xArray) {
		this.xArray = xArray;
	}

	public int[] getyArray() {
		return yArray;
	}

	public void setyArray(int[] yArray) {
		this.yArray = yArray;
	}
}
