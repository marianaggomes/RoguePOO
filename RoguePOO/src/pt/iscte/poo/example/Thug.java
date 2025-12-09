package pt.iscte.poo.example;

import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;


public class Thug extends MovableObject{

	public Thug(Point2D position) {
		super(position);
		life = 10;
		damage = 3;
	}

	@Override
	public int getLayer() {
		return 0;
	}

	@Override
	public String getName() {
		return "Thug";
	}

	@Override
	public void move(Direction dir) {
		Vector2D I = Vector2D.movementVector(getPosition(), EngineExample.getInstance().getCurrentRoom().getHero().getPosition());
		Point2D nextPosition = getPosition().plus(I);
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
	public void attack(MovableObject hero) {
		Hero h = (Hero) hero;
		if (Math.random() <= 0.30){
			if(h.hasArmor()){
				if(Math.random() > 0.5) {
					System.out.println("Vida do heroi antes: " + hero.getLife());
					int life = hero.getLife();
					hero.setLife(life - getDamage());
					EngineExample.getInstance().getStatus().updateLife();
					removeDead(hero);
					System.out.println("Vida do heroi depois: " + hero.getLife());
				}
			} else {
				System.out.println("Vida do heroi antes: " + hero.getLife());
				int life = hero.getLife();
				hero.setLife(life - getDamage());
				EngineExample.getInstance().getStatus().updateLife();
				removeDead(hero);
				System.out.println("Vida do heroi depois: " + hero.getLife());
			}
		} else {
			System.out.println("O Thug não atacou");
		}

	}


}
