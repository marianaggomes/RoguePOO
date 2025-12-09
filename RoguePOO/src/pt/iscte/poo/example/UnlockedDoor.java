package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public class UnlockedDoor extends Door{
	
//	private String room;
//	private Point2D position;

public UnlockedDoor(Point2D p, String roomTo, Point2D positionTo) {
		super(p);
		this.roomTo = roomTo;
		this.positionTo = positionTo;
		// TODO Auto-generated constructor stub
	}

//	public UnlockedDoor(Point2D p, String room, Point2D position) {
//		super(p, room, position, null);
//		//this.key = NULL;
//		// TODO Auto-generated constructor stub
//	}
	
	public String getName(){
		return "DoorOpen";
	}

	@Override
	public int getLayer() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
