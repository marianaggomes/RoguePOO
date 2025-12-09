package pt.iscte.poo.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pt.iscte.poo.gui.ImageMatrixGUI;
import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public class Room {

	public static final int GRID_HEIGHT = 11;
	public static final int GRID_WIDTH = 10;
	
	private Hero hero = EngineExample.getInstance().getHero();
		
	private List<ImageTile> tileList = new ArrayList<>();
	private List<MovableObject> movableObjects = new ArrayList<>();	
	private List<CatchableObject> catchableObjects = new ArrayList<>();
	

	public Room(String file) throws FileNotFoundException {
		readRoom(file);
	}

	private void readRoom(String file) {
		MovableObject auxM;
		CatchableObject auxC;
		try {
			Scanner fileScanner = new Scanner (new File(file));
			int y = 0;
			while(fileScanner.hasNext()) {
				String line = fileScanner.nextLine();
				for(int x = 0; x < line.length(); x++) {
					if(line.charAt(x) == '#') {
						tileList.add(new Wall(new Point2D(x,y)));
					}else
						tileList.add(new Floor(new Point2D(x,y)));
				}
				if(y >= GRID_HEIGHT){
					String [] s = line.split(",");
					int i = Integer.parseInt(s[1]);
					int j = Integer.parseInt(s[2]);
					switch(s[0]) {
					case "Skeleton":
						auxM = new Skeleton(new Point2D(i,j));
						tileList.add(auxM);
						movableObjects.add(auxM);
						break;
					case "Bat":
						auxM = new Bat(new Point2D(i,j));
						tileList.add(auxM);
						movableObjects.add(auxM);
						break;
					case "Scorpio":
						auxM = new Scorpio(new Point2D(i,j));
						tileList.add(auxM);
						movableObjects.add(auxM);
						break;
					case "Thief":
						auxM = new Thief(new Point2D(i,j));
						tileList.add(auxM);
						movableObjects.add(auxM);
						break;
					case "Sword":
						auxC = new Sword(new Point2D(i,j));
						tileList.add(auxC);
						catchableObjects.add(auxC);
						break;
					case "Thug":
						auxM = new Thug(new Point2D(i,j));
						tileList.add(auxM);
						movableObjects.add(auxM);
						break;
					case "Key":
						auxC = new Key(new Point2D(i,j), s[3]);
						tileList.add(auxC);
						catchableObjects.add(auxC);
						break;	
					case "Treasure":
						auxC = new Treasure(new Point2D(i,j));
						tileList.add(auxC);
						catchableObjects.add(auxC);
						break;	
					case "HealingPotion":
						auxC = new HealingPotion(new Point2D(i,j));
						tileList.add(auxC);
						catchableObjects.add(auxC);
						break;	
					case "Armor":
						auxC = new Armor(new Point2D(i,j));
						tileList.add(auxC);
						catchableObjects.add(auxC);
						break;
					case "Door":
						int a = Integer.parseInt(s[4]);
						int b = Integer.parseInt(s[5]);

						if (s.length>6)
							tileList.add(new LockedDoor(new Point2D(i,j), s[3], new Point2D(a,b), new Key(new Point2D(i,j),s[6])));
						else
							tileList.add(new UnlockedDoor(new Point2D(i,j), s[3], new Point2D(a,b)));
						break;
					}
					ImageMatrixGUI.getInstance().addImages(tileList);
				}
				y++;				
			}
			tileList.add(hero);
			fileScanner.close();
		}
		
		catch(FileNotFoundException e) {
			System.err.println("Erro na abertura do ficheiro");
		}
		
	}
	
	public Hero getHero() {
		return hero;
	}
	
	public List<ImageTile> getList(){
		return tileList;
	}
	
	public List<MovableObject> getMovables(){
		return movableObjects;
	}

	public List<CatchableObject> getCatchables(){
		return catchableObjects;
	}
}
