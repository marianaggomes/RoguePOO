package pt.iscte.poo.example;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import pt.iscte.poo.gui.ImageMatrixGUI;
import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class StatusBar {

	protected List<ImageTile> statusBar;
	protected List<ImageTile> inventarioBar;
	

	public StatusBar() {
		statusBar = new ArrayList<ImageTile>(5);
		inventarioBar = new ArrayList<ImageTile>();
	}

	public void createStatusBar() {
		int n = EngineExample.getInstance().getCurrentRoom().getHero().getLife() / 2;
		for(int i = 0; i < n ; i++) {
			statusBar.add(new Green(new Point2D(i,10)));
		}
		for(int i = 5 ; i < 10; i++) {
			inventarioBar.add(new Black(new Point2D(i,10)));
		}
		updateLife();
		updateInv();
		ImageMatrixGUI.getInstance().addImages(statusBar);
		ImageMatrixGUI.getInstance().addImages(inventarioBar);
	}

	
	public void updateInv() {
		List<CatchableObject> inventario = EngineExample.getInstance().getCurrentRoom().getHero().getInventario();
		int i = 7;
		for(int j = 0; j < inventario.size(); j++) {
			CatchableObject aux = inventario.get(j);
			aux.setPosition(new Point2D(i,10));
			inventarioBar.add((ImageTile) aux);
			
			i++;
			
		}
		ImageMatrixGUI.getInstance().addImages(inventarioBar);
	}

	public void updateLife() {
		statusBar.clear();
		Hero hero = EngineExample.getInstance().getCurrentRoom().getHero();
		int n = hero.getLife() / 2;
		for (int i = 0; i < n; i++) {
			statusBar.add(new Green(new Point2D(i, 10)));
		}
		if (hero.getLife() % 2 == 1) {
			statusBar.add(new RedGreen(new Point2D(n, 10)));
		} else {
			statusBar.add(new Red(new Point2D(n, 10)));
		}
		for (int i = n + 1; i < 5; i++) {
			statusBar.add(new Red(new Point2D(i, 10)));
		}
		ImageMatrixGUI.getInstance().addImages(statusBar);
	}
	

	public List<ImageTile> getStatusBar(){
		return statusBar;
	}
	
	public List<ImageTile> getInvBar(){
		return inventarioBar;
	}

	public void removeCatchable(int key) {
		List<CatchableObject> inventario = EngineExample.getInstance().getCurrentRoom().getHero().getInventario();
		Point2D inv1 = new Point2D(7,10);
		Point2D inv2 = new Point2D(8,10);
		Point2D inv3 = new Point2D(9,10);
		Point2D p;
		inventarioBar.removeAll(inventario);
		for(int i = 0; i < inventario.size();i++) {
			p = inventario.get(i).getPosition();
			if(key == KeyEvent.VK_1) {
				
				if(p.equals(inv1)) {
					if(inventario.get(i) instanceof HealingPotion) {
						EngineExample.getInstance().getCurrentRoom().getHero().takePotion();
						inventario.remove(i);
						EngineExample.getInstance().getStatus().updateInv();
					}else {
						CatchableObject aux = inventario.get(i);
						inventario.remove(i);
						Point2D posHero = EngineExample.getInstance().getCurrentRoom().getHero().getPosition();
						aux.setPosition(posHero);
						EngineExample.getInstance().getStatus().updateInv();
						EngineExample.getInstance().getCurrentRoom().getList().add(aux);
						ImageMatrixGUI.getInstance().addImages(EngineExample.getInstance().getCurrentRoom().getList());

					}
				}
			}
			if(key == KeyEvent.VK_2) {
				if(p.equals(inv2)) {
					if(inventario.get(i) instanceof HealingPotion) {
						EngineExample.getInstance().getCurrentRoom().getHero().takePotion();
						inventario.remove(i);
						EngineExample.getInstance().getStatus().updateInv();
					}else {
						CatchableObject aux = inventario.get(i);
						inventario.remove(i);
						Point2D posHero = EngineExample.getInstance().getCurrentRoom().getHero().getPosition();
						aux.setPosition(posHero);
						EngineExample.getInstance().getStatus().updateInv();
						EngineExample.getInstance().getCurrentRoom().getList().add(aux);
						ImageMatrixGUI.getInstance().addImages(EngineExample.getInstance().getCurrentRoom().getList());
					}
				}
			}
			if(key == KeyEvent.VK_3) {
				if(p.equals(inv3)) {
					if(inventario.get(i) instanceof HealingPotion) {
						EngineExample.getInstance().getCurrentRoom().getHero().takePotion();
						inventario.remove(i);
						EngineExample.getInstance().getStatus().updateInv();
					}else {
						CatchableObject aux = inventario.get(i);
						inventario.remove(i);
						Point2D posHero = EngineExample.getInstance().getCurrentRoom().getHero().getPosition();
						aux.setPosition(posHero);
						EngineExample.getInstance().getStatus().updateInv();
						EngineExample.getInstance().getCurrentRoom().getList().add(aux);
						ImageMatrixGUI.getInstance().addImages(EngineExample.getInstance().getCurrentRoom().getList());

					}
				}
			}
		}

	}
}
