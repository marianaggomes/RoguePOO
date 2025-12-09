package pt.iscte.poo.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import pt.iscte.poo.gui.ImageMatrixGUI;
import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.observer.Observed;
import pt.iscte.poo.observer.Observer;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;



public class EngineExample implements Observer {

	public static final int GRID_HEIGHT = 11;
	public static final int GRID_WIDTH = 10;

	private static EngineExample INSTANCE = null;
	private ImageMatrixGUI gui = ImageMatrixGUI.getInstance();

	private Room currentRoom;
	private int roomNumber = 0;
	
	private StatusBar statusBar = new StatusBar();



	private Hero hero = new Hero (new Point2D(1,1));
	private int turns;

	public static EngineExample getInstance() {
		if (INSTANCE == null)
			INSTANCE = new EngineExample();
		return INSTANCE;
	}

	public Point2D getHeroPosition(){
		return hero.getPosition();
	}

	private EngineExample() {		
		gui.registerObserver(this);
		gui.setSize(GRID_WIDTH, GRID_HEIGHT);
		gui.go();
	}

	public void start() {
		try {
			currentRoom = new Room("rooms/room" + roomNumber + ".txt");
		} 
		catch(FileNotFoundException e) {
			System.err.println("Erro na abertura do ficheiro");
		}

		statusBar.createStatusBar();
		gui.addImages(currentRoom.getList());
		gui.setStatusMessage("ROGUE Starter Package - Turns:" + turns);
		gui.update();
	}


	@Override
	public void update(Observed source) {
		
		if (ImageMatrixGUI.getInstance().wasWindowClosed())
			System.out.println("Ending");		

		int key = ((ImageMatrixGUI) source).keyPressed();
		
		if(key == KeyEvent.VK_1 || key == KeyEvent.VK_2 || key == KeyEvent.VK_3) {
			statusBar.removeCatchable(key);
		}
		
		if(!Direction.isDirection(key) ) {
			return;
		}

		Direction keyPressed = Direction.directionFor(key);
		Point2D atualP = currentRoom.getHero().getPosition();

		currentRoom.getHero().move(keyPressed);
		
		for(MovableObject g : getCurrentRoom().getMovables()) {
			g.move(keyPressed);
		}
		
		if(!atualP.equals(currentRoom.getHero().getPosition()))
			turns++;

		gui.setStatusMessage("ROGUE Starter Package - Turns:" + turns);
		gui.update();
	}
	
	public StatusBar getStatus(){
		return statusBar;
	}
	
	public Hero getHero() {
		return hero;
	}
	public int getTurns() {
		return turns;
	}

	public Room getCurrentRoom() {
		return currentRoom;
	}
	
	public void setCurrentRoom(Room room) {
		this.currentRoom = room;
	}
	
	public void changeRoom(String room, Point2D p) {
		String[] s = room.split("");
		roomNumber = Integer.parseInt(s[4]);
		currentRoom.getHero().setPosition(p);
		start();
	}
}
