package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public class HealingPotion extends CatchableObject{

	public HealingPotion(Point2D position) {
		super(position);
	}

	@Override
	public int getLayer() {
		return 0;
	}

	@Override
	public String getName() {
		return "HealingPotion";
	}

}
