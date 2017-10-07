package TankWar;

import java.awt.*;
/**
 * 这是墙
 * @author 11612431 赵禹开
 *@date 2017年9月24日 下午7:36:30
 */
public class Wall {
	int x;
	int y;
	TankWarFrame twf;
	int width;
	int height;
	
	public Wall(int x, int y,int width, int height, TankWarFrame twf) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.twf = twf;
	}
	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);
		g.setColor(c);
	}
	
	public Rectangle getRect() {
		return new Rectangle(x,y,width,height);
	}
}
