package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public class Black extends GameElement{

	public Black(Point2D position) {
		super(position);
	}

	@Override
	public int getLayer() {
		return 0;
	}

	@Override
	public String getName() {
		return "Black";
	}

}
