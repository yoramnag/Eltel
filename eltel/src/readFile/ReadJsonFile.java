package readFile;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import enums.Color;
import enums.Shape;
import enums.Size;
import players.Entity;

public class ReadJsonFile {
	// Map JSON file
	public static ArrayList<Entity> readfile(ArrayList<Entity> entityList) {
		ArrayList<Entity> entityListToCheck = new ArrayList<Entity>();
		JSONParser parser = new JSONParser();
		try {     
            Object obj = parser.parse(new FileReader("entityData.json"));
            JSONArray array = new JSONArray();
            array.add(obj); 
            JSONArray array2 = (JSONArray) array.get(0);
			for (int i = 0; i < array2.size(); i++) {
				JSONObject enObj = (JSONObject) array2.get(i);
				entityListToCheck.add(mapJsonData(enObj));
			}
			dataValidation(entityList,entityListToCheck);
			removeExtraEntitys(entityList);
				
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
		return entityList;
	}
	
	
	// Remove extra elements  
	private static void removeExtraEntitys(ArrayList<Entity> entityList) {
		//System.out.println(entityList.size());
		if (entityList.size() > 10) {
			for (int i = 10; i < entityList.size(); i++) {
				entityList.remove(i);
			}
		}
	}

	private static void dataValidation(ArrayList<Entity> entityList, ArrayList<Entity> entityListToCheck) {
		boolean flag;
		for (int i = 0; i < entityListToCheck.size(); i++) {
			Entity entityToCheck = entityListToCheck.get(i);
			flag = true;
			if (entityToCheck.getId() < 0) {
				flag = false;
			}
			if (isEmpty(entityToCheck.getName())) {
				flag = false;
			}
			if (entityToCheck.getColor() == null) {
				flag = false;
			}
			if (entityToCheck.getShape() == null) {
				flag = false;
			}
			if (entityToCheck.getSize() == null) {
				flag = false;
			}
			if (entityToCheck.getX() < 0 || entityToCheck.getX() > 100) {
				flag = false;
			}
			if (entityToCheck.getY() < 0 || entityToCheck.getY() > 100) {
				flag = false;
			}
			if (flag) {
				entityList.add(entityToCheck);
			}
		}
	}

	private static boolean isEmpty(String name) {
		if (name.length() == 0 || name == null) {
			return true;
		}
		return false;
	}

	private static Entity mapJsonData(JSONObject enObj) {
		Entity entity = new Entity();
		String id = (String) enObj.get("entity_ID");
		entity.setId(Integer.valueOf(id));
		String name = (String) enObj.get("name");
		entity.setName(name);
		String color = (String) enObj.get("color");
		entity.setColor(mapColor(color));
		String shape = (String) enObj.get("shape");
		entity.setShape(mapShape(shape));
		String size = (String) enObj.get("size");
		entity.setSize(mapSize(size));
		String x = (String) enObj.get("X");
		entity.setX(Integer.valueOf(x));
		String y = (String) enObj.get("Y");
		entity.setY(Integer.valueOf(y));
		return entity;
	}

	private static Size mapSize(String size) {
		if ("small".equals(size)) {
			return Size.small;
		}
		else if ("medium".equals(size)) {
			return Size.medium;
		}
		else if ("large".equals(size)) {
			return Size.large;
		}
		else {
			return null;
		}
	}

	private static Shape mapShape(String shape) {
		if ("circle".equals(shape)) {
			return Shape.circle;
		}
		else if ("square".equals(shape)) {
			return Shape.square;
		}
		else if("triangle".equals(shape)){
			return Shape.triangle;
		}
		else {
			return null;
		}
	}

	private static Color mapColor(String color) {
		if ("red".equals(color)) {
			return Color.red;
		}
		else if ("green".equals(color)) {
			return Color.green;
		}
		else if("blue".equals(color)){
			return Color.blue;
		}
		else {
			return null;
		}
	}
}
