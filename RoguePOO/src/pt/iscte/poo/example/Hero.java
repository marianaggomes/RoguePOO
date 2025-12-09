package pt.iscte.poo.example;

import java.util.ArrayList;
import java.util.List;

import pt.iscte.poo.gui.ImageMatrixGUI;
import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Hero extends MovableObject {

	List<CatchableObject> inventario;
	boolean tookPotion;
	boolean isPoison;

	public Hero(Point2D position) {
		super(position);
		this.life = 10;
		this.damage = 1;
		this.inventario = new ArrayList<>(3);
	}

	@Override
	public String getName() {
		return "Hero";
	}

	public void move(Direction dir) {
		Point2D nextPosition = getPosition().plus(dir.asVector());
		poison();
		if ((nextPosition.getY() < 10 && nextPosition.getX() < 10 && nextPosition.getY() >= 0
				&& nextPosition.getX() >= 0) && !isWall(nextPosition)) {
			if (canMoveHere(nextPosition)) {
				setPosition(nextPosition);
				if (isCatchable(nextPosition)) {
					//setPosition(nextPosition);
					for (CatchableObject g : EngineExample.getInstance().getCurrentRoom().getCatchables()) {
						if (g instanceof CatchableObject)
							if (g.getPosition().equals(getPosition()))
								grabObject(g);
					}
				} else if(isUnlockedDoor(nextPosition)) {
					UnlockedDoor d = (UnlockedDoor) checkDoor(nextPosition);
					EngineExample.getInstance().changeRoom(d.getRoomTo(),d.positionTo);
					
				}
				
			} else if(isLockedDoor(nextPosition)) {
				if(hasKey()) {
					Key k = checkKey();
					LockedDoor d = (LockedDoor) checkDoor(nextPosition);
					if(k.getId().equals(d.getKey().getId())) {
						inventario.remove(k);
						EngineExample.getInstance().changeRoom(d.getRoomTo(),d.positionTo);
					}
				}
				System.out.println("Esta porta está trancada. Procure a chave certa!");

			}else {
				MovableObject g = enemyAt(nextPosition);
				if (g != null)
					attack(g);
			}
		}
	}

	private MovableObject enemyAt(Point2D nextPosition) {
		for (MovableObject g : EngineExample.getInstance().getCurrentRoom().getMovables()) {
			if (g instanceof MovableObject)
				if (g.getPosition().equals(nextPosition))
					return g;
		}
		return null;
	}

	private boolean isCatchable(Point2D nextPosition) {
		for (ImageTile g : EngineExample.getInstance().getCurrentRoom().getList()) {
			if(g instanceof Treasure) {
				if (g.getPosition().equals(nextPosition)) {
					ImageMatrixGUI.getInstance().setMessage("YOU WIN!! :)");
					System.exit(0);
					System.out.println("You Win");
				}
			}
			if (g instanceof CatchableObject)
				if (g.getPosition().equals(nextPosition))
					return true;
		}
		return false;
	}

	private void grabObject(CatchableObject g) {
		if (inventario.size() < 3) {
			inventario.add(g);
			EngineExample.getInstance().getCurrentRoom().getList().remove(g);
			ImageMatrixGUI.getInstance().addImages(EngineExample.getInstance().getCurrentRoom().getList());
			EngineExample.getInstance().getStatus().updateInv();
		}
	}

	@Override
	public int getLayer() {
		return 0;
	}

	@Override
	public void attack(MovableObject enemy) {
		if (hasSword()) {
			System.out.println("Vida antes: " + enemy.getLife());
			int life = enemy.getLife();
			enemy.setLife(life - (getDamage()) * 2);
			System.out.println("Vida depois: " + enemy.getLife());
			removeDead(enemy);

		} else {
			System.out.println("Vida antes: " + enemy.getLife());
			int life = enemy.getLife();
			enemy.setLife(life - getDamage());
			System.out.println("Vida depois: " + enemy.getLife());
			removeDead(enemy);

		}

	}

	private boolean hasSword() {
		for (int i = 0; i < inventario.size(); i++)
			if (inventario.get(i) instanceof Sword)
				return true;
		return false;
	}
	
	public boolean hasArmor() {
		for (int i = 0; i < inventario.size(); i++)
			if (inventario.get(i) instanceof Armor)
				return true;
		return false;
	}

	private boolean hasKey() {
		for (int i = 0; i < inventario.size(); i++)
			if (inventario.get(i) instanceof Key)
				return true;
		return false;
	}

	private Key checkKey() {
		for (CatchableObject g : inventario) {
			if (g instanceof Key)
				return (Key)g;
		}
		return null;
	}

	public Door checkDoor(Point2D p) {
		for (ImageTile g : EngineExample.getInstance().getCurrentRoom().getList()) {
			if (g instanceof NonMovableObject)
				if (g.getPosition().equals(p))
					return (Door)g;
		}
		return null;

	}

	public List<CatchableObject> getInventario() {
		return inventario;
	}

	CatchableObject stealCatchable() {

		if (inventario.size() == 0)
			return null;

		//		List<CatchableObject> inventarioH = EngineExample.getInstance().getHero().getInventario();
		int n = (int) Math.random() * inventario.size();

		CatchableObject o = inventario.get(n);
		EngineExample.getInstance().getStatus().getInvBar().removeAll(inventario);
		//		for (int i = 0; i < inventarioH.size(); i++) {
		inventario.remove(o);
		//		EngineExample.getInstance().getStatus().getInvBar().remove((ImageTile) o);


		System.out.println("roubei");

		return o;
	}


	public void takePotion() {
		EngineExample.getInstance().getCurrentRoom().getHero().setLife(getLife() + 5);
		if(getLife() > 10) {
			EngineExample.getInstance().getCurrentRoom().getHero().setLife(10);
		}
		tookPotion = true;
		EngineExample.getInstance().getStatus().updateLife();
	}

	public void isPoison(boolean b) {
		isPoison = b;
	}

	public void poison() {
		if(tookPotion == false) {
			if(isPoison == true) {
				Hero hero =EngineExample.getInstance().getCurrentRoom().getHero();
				if(!hero.isDead()) {
					setLife(life - getDamage());
					EngineExample.getInstance().getStatus().updateLife();
					removeDead(hero);
				}
			}
		}
	}

}
