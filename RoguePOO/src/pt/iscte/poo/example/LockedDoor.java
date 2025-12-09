package pt.iscte.poo.example;

import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public class LockedDoor extends Door {
	
	private Key key;
	
	public LockedDoor(Point2D p, String roomTo, Point2D positionTo, Key key) {
		super(p);
		this.roomTo = roomTo;
		this.positionTo = positionTo;
		this.key = key;
	}
	
	public Key getKey(){
		return key;
	}
	
	public String getName(){
		return "DoorClosed";
	}

	@Override
	public int getLayer() {
		return 0;
	}
}
