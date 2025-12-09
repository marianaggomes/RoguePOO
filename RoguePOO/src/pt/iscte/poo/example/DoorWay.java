package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public class DoorWay extends Door {
	
	private Key key;

	public DoorWay(Point2D p, String room, Point2D position, Key key) {
		super(p);
		this.roomTo = room;
		this.positionTo = position;
		this.key = key;
	}

	public String getName(){
		return "DoorWay";
	}

	@Override
	public int getLayer() {
		return 0;
	}

}
