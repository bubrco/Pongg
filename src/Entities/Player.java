package Entities;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import Main.*;

public class Player extends Rectangle{

	
	private static final long serialVersionUID = 1L;
	public boolean up,down;
	public int spd = 8;
	public int pause = 0;
	public int curDir = spd;
	
	
	public Player(int x, int y,int width,int height) {
		super(x,y,width,height);
	}
	
	public void tick() {		
		if(up == true && y-spd > 0) {
			curDir = -1;
			y-=spd - pause;
		}else if(down && y < 420){
			curDir = 1;
			y+=spd - pause;
		}else {
			curDir = 0;
		}
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);
		g.setColor(Color.gray);
		g.fillRect(x+4, y+4, width-8, height-8);
	}
	
	public void reset() {
		this.setLocation(0,Game.HEIGHT/2-(height/2));
	}
}