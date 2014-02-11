/*************************************************************************
	> File Name: Missile.java
	> Author: Bslin
	> Mail:  
	> Created Time: 2014年02月10日 星期一 19时55分57秒
 ************************************************************************/

import java.awt.*;

public class Missile {
	int x, y;
	public static final int XSPEED = 10;
	public static final int YSPEED = 10;
	public static final int WIDTH = 10;
	public static final int HEIGHT = 10;

	Tank.Direction ptDir;

	public Missile(int x, int y, Tank.Direction ptDir) {
		this.x = x;
		this.y = y;
		this.ptDir = ptDir;
	}

	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.BLACK);
		g.fillOval(x, y, WIDTH, HEIGHT);
		g.setColor(c);
		move();
	}

	private void move() {
		switch(ptDir) {
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
		}
	}

}