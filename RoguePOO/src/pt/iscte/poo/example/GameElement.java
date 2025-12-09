package pt.iscte.poo.example;

import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public abstract class GameElement implements ImageTile{
	 
	private Point2D position;
	
	public GameElement(Point2D position) {
		this.position = position;
	}
	
	public void setPosition (Point2D position) {
		this.position = position;
	}
	
	public Point2D getPosition() {
		return position;
	}
	
	public abstract String getName();

}
