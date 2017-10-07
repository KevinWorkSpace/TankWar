package TankWar;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

import java.util.*;
/**
 * 这是游戏的主窗口
 * @author 11612431 赵禹开
 *@date 2017年9月24日 下午7:35:39
 */
public class TankWarFrame extends Frame {
	/**
	 * 游戏的宽度
	 */
	public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 600;

	Tank myTank = new Tank(650,700,true,direction.STOP,this);
	Wall w1 = new Wall(300,300,80,200,this);
	Wall w2 = new Wall(400,100,200,80,this);
	Blood b = new Blood();
	List<Tank> enemyTanks = new ArrayList<Tank>();
	List<Explode> explodes = new ArrayList<Explode>();
	List<Missile> missiles = new ArrayList<Missile>();
	

	Image OffScreenImage;
	public void LanuchFrame() {
		this.setBounds(400,0,GAME_WIDTH,GAME_HEIGHT);
		setVisible(true);
		setResizable(false);
		setTitle("TankWar");
		this.setBackground(Color.BLACK);
		for(int i=0; i<10; i++) {
			Tank t = new Tank(50+(i+1)*40,50,false,direction.D,this);
			enemyTanks.add(t);
		}
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				 setVisible(false);
				 System.exit(0);
			 }
		});
		addKeyListener(new KeyMonitor());
		TankThread tt = new TankThread();
		new Thread(tt).start();
	}
	
	@Override
	public void update(Graphics g) {
		if(OffScreenImage == null) {
			OffScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);
		}
		Graphics gOffScreen = OffScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.BLACK);
		gOffScreen.fillRect(0, 0, GAME_WIDTH,GAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(OffScreenImage, 0, 0, null);
	}
	
	public void paint(Graphics g) {
		g.drawString("The missiles number is :" + missiles.size(), 10, 50);
		g.drawString("The Explode number is :" + explodes.size(), 10, 70);
		g.drawString("The enemy tanks number is :" + enemyTanks.size(), 10, 90);
		g.drawString("My tank life :" + myTank.life, 10, 110);
		if(enemyTanks.size() == 0) g.drawString("You Win !!!", 200, 200);
		myTank.draw(g);
		myTank.hitWall(w1);
		myTank.hitWall(w2);
		myTank.hitTank(enemyTanks);
		myTank.eatBlood(b);
		w1.draw(g);
		w2.draw(g);
		b.draw(g);
		if(enemyTanks.size() <= 0) {
			for(int i=0; i<10; i++) {
				Tank t = new Tank(50+(i+1)*40,50,false,direction.D,this);
				enemyTanks.add(t);
			}
		}
		for(int i=0; i<enemyTanks.size(); i++) {
			Tank t = enemyTanks.get(i);
			t.hitWall(w1);
			t.hitWall(w2);
			t.hitTank(enemyTanks);
			t.draw(g);
		}
	
		for(int i=0; i<explodes.size(); i++) {
			Explode e = explodes.get(i);
			e.draw(g);
		}
		for(int i=0; i<missiles.size(); i++) {
			Missile m = missiles.get(i);
			m.hitTanks(enemyTanks);
			m.hitTank(myTank);
			m.hitWall(w1);
			m.hitWall(w2);
			m.draw(g);
		}
		
	}
	
	
	public static void main(String[] args) {
		TankWarFrame twf = new TankWarFrame();
		twf.LanuchFrame();
	}
	
	class TankThread implements Runnable {
		@Override
		public void run() {
			while(true) {
				repaint();	
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
  
	private class KeyMonitor extends KeyAdapter {

		@Override
		public void keyReleased(KeyEvent e) {
			myTank.keyReleased(e);
		}

		@Override
		public void keyPressed(KeyEvent e) {
			myTank.keyPressed(e);
		}
	}
		
}



