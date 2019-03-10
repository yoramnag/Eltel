package players;

import enums.Color;
import enums.Shape;
import enums.Size;

public class Entity {
	private int id ;
	private String name;
	private Color color;
	private Shape shape;
	private Size size;
	private int x;
	private int y;
	
	public Entity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Entity(int id, String name, Color color, Shape shape, Size size, int x, int y) {
		super();
		this.id = id;
		this.name = name;
		this.color = color;
		this.shape = shape;
		this.size = size;
		this.x = x;
		this.y = y;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
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
	
	
	
}
