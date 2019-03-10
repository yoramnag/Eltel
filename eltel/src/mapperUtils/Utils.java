package mapperUtils;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import players.Entity;
import players.PaintInfo;
import players.PointInfo;

public class Utils {
	
	public static String getCheckBoxNameFromList(ArrayList<String> checkBoxNames,String name) {
		for (int i = 0; i < checkBoxNames.size(); i++) {
			if (name.equals(checkBoxNames.get(i))) {
				return checkBoxNames.get(i);
			}
		}
		return null;
	}
	
	public static void removePointFromPaintInfoList(ArrayList<PaintInfo> paintInfoList , String checkBoxName, ArrayList<PaintInfo> removedPaintInfoList) {
		for (int i = 0; i < paintInfoList.size(); i++) {
			if (checkBoxName.equals(paintInfoList.get(i).getCheckBoxName())) {
				removedPaintInfoList.add(paintInfoList.get(i));
				paintInfoList.remove(i);
			}
		}
	}
	
	public static void addRemovePaintInfo(ArrayList<PaintInfo> paintInfoList , String checkBoxName, ArrayList<PaintInfo> removedPaintInfoList) {
		for (int i = 0; i < removedPaintInfoList.size(); i++) {
			if (checkBoxName.equals(removedPaintInfoList.get(i).getCheckBoxName())) {
				paintInfoList.add(removedPaintInfoList.get(i));
				removedPaintInfoList.remove(i);
			}
		}
	}
	
	public static void mapPaintInfo(ArrayList<PaintInfo> paintInfoList,ArrayList<Entity> entityList, ArrayList<String> checkBoxNames) {
		for (int i = 0; i < entityList.size(); i++) {
			Entity entity = entityList.get(i);
			PaintInfo paintInfo = new PaintInfo();
			paintInfo.setColor(mapColor(String.valueOf(entity.getColor())));
			paintInfo.setShape(String.valueOf(entity.getShape()));
			paintInfo.setSize(String.valueOf(entity.getSize()));
			paintInfo.setCheckBoxName(checkBoxNames.get(i));
			paintInfo.setX(entity.getX());
			paintInfo.setY(entity.getY());
			paintInfoList.add(paintInfo);
		}
	}
	
	private static Color mapColor(String color) {
		if ("red".equals(color)) {
			return Color.RED;
		}
		else if ("green".equals(color)) {
			return Color.GREEN;
		}
		return Color.BLUE;
	}
	
	public static void calculatePolygon(PaintInfo paintInfo) {
		paintInfo.setY(paintInfo.getY()+paintInfo.getW());
		calculatePolygonCor(paintInfo);
	}
	
	public static void calculatePolygonCor(PaintInfo paintInfo) {
		int x1=paintInfo.getX()+paintInfo.getW()/2;
		int x2=paintInfo.getX();
		int x3=paintInfo.getX()+paintInfo.getW();
		int y1=paintInfo.getY()-paintInfo.getH();
		int y2=paintInfo.getY();
		int y3=paintInfo.getY();
		int[] xCor = {x1, x2, x3};
		int[] yCor = {y1, y2, y3};
		paintInfo.setxArray(xCor);
		paintInfo.setyArray(yCor);
	}
	
	public static void setRandomNumberList(ArrayList<Integer> randomNumberList) {
		for (int i = 0; i < 4; i++) {
			randomNumberList.add(0);
		}
	}
	
	public static void moveEntitys(ArrayList<PaintInfo> paintInfoList, ArrayList<Integer> randomNumberList) {
		int dir = getRandomNumber(randomNumberList);
		for (int i = 0; i < paintInfoList.size(); i++) {
			//right
			if (dir ==0) {
				paintInfoList.get(i).setX(paintInfoList.get(i).getX()+5);
				if (istriangle(paintInfoList.get(i).getShape())) {
					calculatePolygonCor(paintInfoList.get(i));
				}
				if (isOutofBoundary(paintInfoList.get(i))) {
					paintInfoList.get(i).setX(paintInfoList.get(i).getX()-5);
					if (istriangle(paintInfoList.get(i).getShape())) {
						calculatePolygonCor(paintInfoList.get(i));
					}
				}
			}
			//down
			else if (dir ==1) {
				paintInfoList.get(i).setY(paintInfoList.get(i).getY()-5);
				if (istriangle(paintInfoList.get(i).getShape())) {
					calculatePolygonCor(paintInfoList.get(i));
				}
				if (isOutofBoundary(paintInfoList.get(i))) {
					paintInfoList.get(i).setY(paintInfoList.get(i).getY()+5);
					if (istriangle(paintInfoList.get(i).getShape())) {
						calculatePolygonCor(paintInfoList.get(i));
					}
				}
			}
			//left
			else if (dir ==2) {
				paintInfoList.get(i).setX(paintInfoList.get(i).getX()-5);
				if (istriangle(paintInfoList.get(i).getShape())) {
					calculatePolygonCor(paintInfoList.get(i));
				}
				if (isOutofBoundary(paintInfoList.get(i))) {
					paintInfoList.get(i).setX(paintInfoList.get(i).getX()+5);
					if (istriangle(paintInfoList.get(i).getShape())) {
						calculatePolygonCor(paintInfoList.get(i));
					}
				}
			}
			//up
			else {
				paintInfoList.get(i).setY(paintInfoList.get(i).getY()+5);
				if (istriangle(paintInfoList.get(i).getShape())) {
					calculatePolygonCor(paintInfoList.get(i));
				}
				if (!isOutofBoundary(paintInfoList.get(i))) {
					paintInfoList.get(i).setY(paintInfoList.get(i).getY()-5);
					if (istriangle(paintInfoList.get(i).getShape())) {
						calculatePolygonCor(paintInfoList.get(i));
					}
				}
			}
		}
	}

	private static boolean isOutofBoundary(PaintInfo paintInfo) {
		if (istriangle(paintInfo.getShape())) {
			if (paintInfo.getxArray()[1] < 683 || paintInfo.getxArray()[2] > 783 ||
					paintInfo.getyArray()[1] > 560 || paintInfo.getyArray()[2] > 560 ) {
				return true;
			}
			else if (paintInfo.getxArray()[0] < 683 || paintInfo.getxArray()[0] > 783 ||
					paintInfo.getyArray()[0] < 460 || paintInfo.getyArray()[0] >  560 ) {
				return true;
			}
		}
		else {
			if (paintInfo.getX() < 683 || paintInfo.getX()+paintInfo.getW() > 783 ) {
				return true;
			}
			if (paintInfo.getY() < 460 || paintInfo.getY()+paintInfo.getH() > 560) {
				return true;
			}
		}
		return false;
	}

	private static int getRandomNumber(ArrayList<Integer> randomNumberList) {
		Random rand = new Random();
		int dir = rand.nextInt(3);
		boolean flag = true;
		while (flag) {
			if (randomNumberList.get(dir) == 1) {
				dir = rand.nextInt(3);
			}
			else {
				resetRandomNumberList(randomNumberList);
				randomNumberList.set(dir, 1);
				flag = false;
			}
		}
		return dir;
	}

	private static void resetRandomNumberList(ArrayList<Integer> randomNumberList) {
		for (int i = 0; i < randomNumberList.size(); i++) {
			randomNumberList.set(i, 0);
		}
		
	}

	private static boolean istriangle(String shape) {
		if ("triangle".equals(shape)) {
			return true;
		}
		return false;
	}
	
	public static void creatreCsv(ArrayList<PaintInfo> paintInfoList) {
		ArrayList<PointInfo> pointInfoList = new ArrayList<PointInfo>();
		calculatePoints(paintInfoList,pointInfoList);
		PrintWriter pw = null;
		try {
		    pw = new PrintWriter(new File("NewData.csv"));
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		}
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < pointInfoList.size(); i++) {
			PointInfo pointInfo = pointInfoList.get(i);
			builder.append(pointInfo.getId() + ",");
			builder.append(pointInfo.getName() + ",");
			builder.append(pointInfo.getX() + ",");
			builder.append(pointInfo.getY());
			builder.append('\n');
		}
		pw.write(builder.toString());
		pw.close();
		System.out.println("done!");
		
	}
	
	public static void calculatePoints(ArrayList<PaintInfo> paintInfoList, ArrayList<PointInfo> pointInfoList) {
		for (int i = 0; i < paintInfoList.size(); i++) {
			PointInfo pointInfo = new PointInfo();
			PaintInfo paintInfo = paintInfoList.get(i);
			if (istriangle(paintInfo.getShape())) {
				pointInfo.setX(paintInfo.getxArray()[1] - 683);
				pointInfo.setY(Math.abs(paintInfo.getyArray()[1] - 560));
			}
			else {
				pointInfo.setX(paintInfo.getX()-683);
				pointInfo.setY(Math.abs(paintInfo.getY()+paintInfo.getH()-560));
			}
			String[] parts = paintInfo.getCheckBoxName().split("_");
			pointInfo.setId(parts[0]+parts[1]);
			pointInfo.setName(parts[2]);
			pointInfoList.add(pointInfo);
		}
		
	}
	
}
