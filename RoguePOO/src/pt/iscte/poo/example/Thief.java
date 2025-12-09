package pt.iscte.poo.example;

import java.util.ArrayList;
import java.util.List;

import pt.iscte.poo.gui.ImageMatrixGUI;
import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Thief extends MovableObject{

	List<CatchableObject> thiefInv;

	public Thief(Point2D position) {
		super(position);
		life = 5;
		thiefInv = new ArrayList<>(1);
	}

	@Override
	public int getLayer() {
		return 0;
	}

	@Override
	public void move(Direction dir) {
		Vector2D v = Vector2D.movementVector(getPosition(), EngineExample.getInstance().getCurrentRoom().getHero().getPosition());
		Point2D nextPosition = getPosition().plus(v);
		if(thiefInv.isEmpty()) {

			if((nextPosition.getY() < 10 && nextPosition.getX() < 10 && nextPosition.getY() >= 0 && nextPosition.getX() >= 0) 
					&& !isWall(nextPosition)) {
				if(canMoveHere(nextPosition))
					setPosition(nextPosition);
				else {
					Hero hero = EngineExample.getInstance().getCurrentRoom().getHero();
					if(hero.getPosition().equals(nextPosition)) 
						attack(hero);
				}
			}

		}else {
			List<Point2D> neighbours = getPosition().getNeighbourhoodPoints();
			int max = 0;
			Point2D posi=null;
			for (Point2D p: neighbours) {
				if((p.getY() < 10 && p.getX() < 10 && p.getY() >= 0 && p.getX() >= 0) 
						&& !isWall(p)&& canMoveHere(p)) {
				
				int distance=p.distanceTo(EngineExample.getInstance().getCurrentRoom().getHero().getPosition());
				
				
				if ( distance > max) {
					max = distance;
					posi=p;
				}
				}
				}
					if((posi.getY() < 10 && posi.getX() < 10 && posi.getY() >= 0 && posi.getX() >= 0) 
							&& !isWall(posi) && canMoveHere(posi)) {

				

					setPosition(posi);
				}
			}
		}
	


	@Override
	public String getName() {
		return "Thief";
	}

	@Override
	public void attack(MovableObject hero) {
		if(thiefInv.size() == 0) {
			CatchableObject aux = ((Hero)hero).stealCatchable();
			thiefInv.add(aux);
			EngineExample.getInstance().getStatus().updateInv();
		}
	}

	public List<CatchableObject> getThiefInv(){
		return thiefInv;
	}

	public void deadThief() {
		for(int i = 0; i < thiefInv.size();i++) {
			CatchableObject aux = thiefInv.get(i);
			thiefInv.remove(i);
			Point2D posThief = getPosition();
			aux.setPosition(posThief);
			EngineExample.getInstance().getStatus().updateInv();
			EngineExample.getInstance().getCurrentRoom().getList().add(aux);
			ImageMatrixGUI.getInstance().addImages(EngineExample.getInstance().getCurrentRoom().getList());
		}
	}
}
