package pt.iscte.poo.example;

import pt.iscte.poo.gui.ImageMatrixGUI;
import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public abstract class MovableObject extends GameElement implements Movable{

	protected int life; 
	protected int damage;

	public MovableObject(Point2D position) {
		super(position);
	}

	public abstract void move(Direction dir);

	public abstract void attack(MovableObject o);

	protected int getDamage() {
		return damage;
	}


	public boolean isWall (Point2D p){
		for(ImageTile i : EngineExample.getInstance().getCurrentRoom().getList()) {
			if(i instanceof Wall) 
				if(i.getPosition().equals(p))
					return true;
		}
		return false;
	}
	
	public boolean isLockedDoor(Point2D p) {
		for(ImageTile g : EngineExample.getInstance().getCurrentRoom().getList()) {
			if(g instanceof LockedDoor)
				if(g.getPosition().equals(p))
					return true;
		}
		return false;
	}

	public boolean isUnlockedDoor(Point2D p) {
		for(ImageTile g : EngineExample.getInstance().getCurrentRoom().getList()) {
			if(g instanceof UnlockedDoor)
				if(g.getPosition().equals(p))
					return true;
		}
		return false;
	}

	public boolean canMoveHere(Point2D p) {
		for(ImageTile g : EngineExample.getInstance().getCurrentRoom().getList()) {
			if(g instanceof MovableObject)
				if(g.getPosition().equals(p))
					return false;
		}
		for(ImageTile g : EngineExample.getInstance().getCurrentRoom().getList()) {
			if(g instanceof LockedDoor)
				if(g.getPosition().equals(p))
					return false;
		}
		return true;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public boolean isDead() {
		if(life <= 0) {
			return true;
		}
		return false;
	}


	public void removeDead(MovableObject o) {
		if(o.isDead()) {
			if(!(o instanceof Hero)) {
				if(o instanceof Thief) {
					Thief aux = (Thief) o;
					aux.deadThief();
					System.out.println("Morreu thief");
					EngineExample.getInstance().getCurrentRoom().getList().remove(o);
					EngineExample.getInstance().getCurrentRoom().getMovables().remove(o);
					ImageMatrixGUI.getInstance().removeImage(o);
					ImageMatrixGUI.getInstance().addImages(EngineExample.getInstance().getCurrentRoom().getList());
				}else {
					EngineExample.getInstance().getCurrentRoom().getList().remove(o);
					EngineExample.getInstance().getCurrentRoom().getMovables().remove(o);
					ImageMatrixGUI.getInstance().removeImage(o);
					ImageMatrixGUI.getInstance().addImages(EngineExample.getInstance().getCurrentRoom().getList());
				}

			}else {
				ImageMatrixGUI.getInstance().setMessage("Game Over");
				System.exit(0);
				System.out.println("Game Over");
			}
		}
	}
}
