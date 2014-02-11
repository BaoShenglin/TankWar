/*************************************************************************
	> File Name: Tank.java
	> Author: Bslin
	> Mail:  
	> Created Time: 2014年02月10日 星期一 18时28分33秒
 ************************************************************************/

import java.awt.*;
import java.awt.event.*;

public class Tank {
	int x, y;
	TankClient tc = null;
	public static final int XSPEED = 5;
	public static final int YSPEED = 5;
	public static final int WIDTH = 30;
	public static final int HEIGHT = 30;

	private boolean  bL = false, bU = false, bR = false, bD = false;
	enum Direction {L, LU, U, RU, R, RD, D, LD, STOP};
	private Direction dir = Direction.STOP;
	private Direction ptDir = Direction.D;

	public Tank(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Tank(int x, int y, TankClient tc) {
		this(x, y);
		this.tc = tc;  // * 持有对方引用
	}

	public void draw(Graphics g) {
		Color c = g.getColor();  // *
		g.setColor(Color.RED);
		g.fillRect(x, y, WIDTH, HEIGHT);
		g.setColor(c);  // *
		switch(ptDir) {
			case L:
				g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x, y + Tank.HEIGHT / 2);
				break;
			case LU:
				g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x, y);
				break;
			case U:
				g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x + Tank.WIDTH / 2, y);
				break;
			case RU:
				g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x + Tank.WIDTH, y);
				break;
			case R:
				g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x + Tank.WIDTH, y + Tank.HEIGHT / 2);
				break;
			case RD:
				g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x + Tank.WIDTH, y + Tank.HEIGHT);
				break;
			case D:
				g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x + Tank.WIDTH / 2, y + Tank.HEIGHT);
				break;
			case LD:
				g.drawLine(x + Tank.WIDTH / 2, y + Tank.HEIGHT / 2, x, y + Tank.HEIGHT);
				break;
		}
		move();
	}

	private void move() {
		switch(dir) {
			case L:
				x -= XSPEED;
				break;
			case LU:
				x -= XSPEED;
				y -= YSPEED;
				break;
			case U:
				y -= YSPEED;
				break;
			case RU:
				x += XSPEED;
				y -= YSPEED;
				break;
			case R:
				x += XSPEED;
				break;
			case RD:
				x += XSPEED;
				y += YSPEED;
				break;
			case D:
				y += YSPEED;
				break;
			case LD:
				x -= XSPEED;
				y += YSPEED;
				break;
			case STOP:
				break;
		}
		if(dir != Direction.STOP) ptDir = dir;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key) {
			case KeyEvent.VK_CONTROL :
				fire();
				break;
			case KeyEvent.VK_LEFT :
				bL = true;
				break;
			case KeyEvent.VK_UP :
				bU = true;
				break;
			case KeyEvent.VK_RIGHT :
				bR = true;
				break;
			case KeyEvent.VK_DOWN :
				bD = true;
				break;
		}
		locateDirection();
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key) {
			case KeyEvent.VK_LEFT :
				bL = false;
				break;
			case KeyEvent.VK_UP :
				bU = false;
				break;
			case KeyEvent.VK_RIGHT :
				bR = false;
				break;
			case KeyEvent.VK_DOWN :
				bD = false;
				break;
		}
		locateDirection();
	}

	void locateDirection() {
		if(bL && !bU && !bR && !bD) dir = Direction.L;
		else if(bL && bU && !bR && !bD) dir = Direction.LU;
		else if(!bL && bU && !bR && !bD) dir = Direction.U;
		else if(!bL && bU && bR && !bD) dir = Direction.RU;
		else if(!bL && !bU && bR && !bD) dir = Direction.R;
		else if(!bL && !bU && bR && bD) dir = Direction.RD;
		else if(!bL && !bU && !bR && bD) dir = Direction.D;
		else if(bL && !bU && !bR && bD) dir = Direction.LD;
		else if(!bL && !bU && !bR && !bD) dir = Direction.STOP;
	}

	public Missile fire() {
		int x = this.x + Tank.WIDTH / 2 - Missile.WIDTH / 2;
		int y = this.y + Tank.HEIGHT / 2 - Missile.HEIGHT / 2;
		Missile m = new Missile(x, y, ptDir);
		tc.missiles.add(m);
		return m;
	}

}