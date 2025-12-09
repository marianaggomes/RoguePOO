package pt.iscte.poo.example;

import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Scorpio extends MovableObject{

	public Scorpio(Point2D position) {
		super(position);
		life = 2;
		damage = 1;
	}

	@Override
	public int getLayer() {
		return 0;
	}

	@Override
	public void move(Direction dir) {
		Vector2D v = Vector2D.movementVector(getPosition(), EngineExample.getInstance().getCurrentRoom().getHero().getPosition());
		Point2D nextPosition = getPosition().plus(v);
		if((nextPosition.getY() < 10 && nextPosition.getX() < 10 && nextPosition.getY() >= 0 && nextPosition.getX() >= 0) 
				&& !isWall(nextPosition)) {
			if(canMoveHere(nextPosition))
				setPosition(nextPosition);
			else {
				Hero hero = EngineExample.getInstance().getCurrentRoom().getHero();
						if(hero.getPosition().equals(nextPosition)) {
							attack(hero);
						}	
			}
		}
	}

	@Override
	public String getName() {
		return "Scorpio";
	}

	@Override
	public void attack(MovableObject hero) {
		Hero h = (Hero) hero;
		if(h.hasArmor()){
			if(Math.random() > 0.5) {
				EngineExample.getInstance().getCurrentRoom().getHero().isPoison(true);
				System.out.println("Scorpio atacou");
			}
		} else {
			EngineExample.getInstance().getCurrentRoom().getHero().isPoison(true);
			System.out.println("Scorpio atacou");
		}
	}

}
