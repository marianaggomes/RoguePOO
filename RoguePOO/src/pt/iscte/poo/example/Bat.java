package pt.iscte.poo.example;

import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Bat extends MovableObject{


	public Bat(Point2D position) {
		super(position);
		life = 3;
		damage = 1;
	}

	@Override
	public String getName() {
		return "Bat";
	}

	public void move(Direction dir) {

		if (Math.random() > 0.50){
			Direction randDirection = Direction.random();
			Vector2D vector = randDirection.asVector(); 
			Point2D nextPosition = getPosition().plus(vector);
			if((nextPosition.getY() < 10 && nextPosition.getX() < 10 && nextPosition.getY() >= 0 && nextPosition.getX() >= 0) 
					&& !isWall(nextPosition)) {
				if(canMoveHere(nextPosition))
					setPosition(getPosition().plus(vector));
				else {
					Hero hero = EngineExample.getInstance().getCurrentRoom().getHero();
					if(hero.getPosition().equals(nextPosition)) { 
						attack(hero);
					}		
				}
			}
		} else{
			Vector2D v = Vector2D.movementVector(getPosition(), EngineExample.getInstance().getCurrentRoom().getHero().getPosition());
			Point2D nextPosition = getPosition().plus(v);
			if((nextPosition.getY() < 10 && nextPosition.getX() < 10 && nextPosition.getY() >= 0 && nextPosition.getX() >= 0) 
					&& !isWall(nextPosition)) {
				if(canMoveHere(nextPosition)) {
					setPosition(nextPosition);
				}else {
					Hero hero = EngineExample.getInstance().getCurrentRoom().getHero();
					if(hero.getPosition().equals(nextPosition)) { 
						attack(hero);
					}		
				}
			}
		}

	}


	@Override
	public int getLayer() {
		return 0;
	}

	@Override
	public void attack(MovableObject hero) {
		Hero h = (Hero) hero;
		
		if (Math.random() > 0.50){
			if(h.hasArmor()){
				if(Math.random() > 0.5) {
					System.out.println("Vida do heroi antes: " + hero.getLife());
					int life = hero.getLife();
					hero.setLife(life - getDamage());
					EngineExample.getInstance().getStatus().updateLife();
					System.out.println("O bat atacou (armadura)");
					removeDead(hero);
					System.out.println("Vida do heroi depois: " + hero.getLife());
					System.out.println("Vida do bat: " + getLife());
					
					setLife(getLife() + 1);
					if(getLife() > 3) {
						setLife(3);
					}
					System.out.println("Vida do bat: " + getLife());
				}
			}else {
				System.out.println("Vida do heroi antes: " + hero.getLife());
				int life = hero.getLife();
				hero.setLife(life - getDamage());
				EngineExample.getInstance().getStatus().updateLife();
				System.out.println("O bat atacou");
				removeDead(hero);
				System.out.println("Vida do heroi depois: " + hero.getLife());
				System.out.println("Vida do bat: " + getLife());
				
				setLife(getLife() + 1);
				if(getLife() > 3) {
					setLife(3);
				}
				System.out.println("Vida do bat: " + getLife());
			}

		}else {
			System.out.println("O bat não atacou");
		}
	}
}
