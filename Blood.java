package TankWar;

import java.awt.*;
public class Blood {
	int x,y,w,h;
	int step;
	boolean live = true;
	int[][] pos = {
					{270,300},{275,300},{280,305},{280,310},{275,303},{270,312},{275,304},{280,300}
				  };
	public void draw(Graphics g) {
		if(!live) return;
		Color c = g.getColor();
		g.setColor(Color.MAGENTA);
		g.fillRect(x, y, w, h);
		g.setColor(c);
		step ++;
		if(step == pos.length) step = 0;
		x = pos[step][0];
		y = pos[step][1];
	}
	
	public Blood() {
		x = pos[0][0];
		y = pos[0][1];
		w = h = 15;
	}
	
	public Rectangle getRect() {
		return new Rectangle(x,y,w,h);
	}
}
