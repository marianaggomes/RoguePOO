package pt.iscte.poo.example;

import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public interface Movable {

	void move(Direction dir);
	boolean isWall(Point2D p);
}
