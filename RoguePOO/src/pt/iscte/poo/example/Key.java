package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public class Key extends CatchableObject{
	
	private String id;

	public Key(Point2D position, String id) {
		super(position);
		this.id = id;
	}

	@Override
	public int getLayer() {
		return 0;
	}

	@Override
	public String getName() {
		return "Key";
	}
	
	public String getId(){
		return id;
	}
}
