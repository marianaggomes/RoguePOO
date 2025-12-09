package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public abstract class Door extends NonMovableObject {
	
	protected String roomTo;
	protected Point2D positionTo;
	
	public Door(Point2D position) {
		super(position);
	}
	
	public String getRoomTo(){
		return roomTo;
	}
	
	public Point2D getPositionTo(){
		return positionTo;
	}
	
}
