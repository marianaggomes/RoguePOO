package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public class Red extends GameElement{

	public Red(Point2D position) {
		super(position);
	}

	@Override
	public int getLayer() {
		return 0;
	}

	@Override
	public String getName() {
		return "Red";
	}
}
