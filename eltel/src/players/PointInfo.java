package players;

public class PointInfo {
	private int x;
	private int y;
	private String name;
	private String id;
	
	public PointInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PointInfo(int x, int y, String name, String id) {
		super();
		this.x = x;
		this.y = y;
		this.name = name;
		this.id = id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
