package Entities;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import Main.*;


public class Bola extends Rectangle{
	

private static final long serialVersionUID = 1L;

public double xDouble,yDouble;
public double dx,dy;
public int spd = 6;

public void restart() {
	
	x = Game.WHIDTH/2-(width/2);
	y = Game.HEIGHT/2-(height/2);
	yDouble = y;
	xDouble = x;
	
	dx = new Random().nextDouble(-1.5,1.5);
	dy = new Random().nextDouble(-1.5,1.5);
	
	if(dx < 0.6 && dx > -0.6) {
		dx = 1.4222344;
	}
	if(dy < 0.4 && dy > -0.4) {
		dy = 0.5;
	}
}

public Bola(double x, double y,int width, int height) {
	super((int)x,(int)y,width,height);
	restart();
}

//paredes(ponto)
Rectangle2D playerBorder = new Rectangle(0,0,1,Game.HEIGHT);
Rectangle2D enemyBorder = new Rectangle(Game.WHIDTH,0,1,Game.HEIGHT);

//chao e teto
Rectangle2D teto = new Rectangle(0,1,Game.WHIDTH,1);
Rectangle2D chao = new Rectangle(0,Game.HEIGHT,Game.WHIDTH,1);

//entities
Rectangle2D playerRect = new Rectangle(Game.player.x,Game.player.y,0,Game.player.height);
Rectangle2D enemyRect = new Rectangle(Game.enemy.x,Game.enemy.y,0,Game.enemy.height);

 public void tick() {if(Game.ui.pause == false) {
	 
	 xDouble = x;
	 yDouble = y; 
	 x = (int)(xDouble += spd*dx);
	 y = (int)(yDouble += spd*dy);
	 enemyRect.setRect(Game.enemy.x,Game.enemy.y,Game.enemy.width,Game.enemy.height);
	 playerRect.setRect(Game.player.x,Game.player.y,Game.player.width,Game.player.height);
	 
	
	 if(teto.intersects(this)) {
		
		y+=1;
		dy=-dy;
		System.out.println("pinga alto");
	    
	}else if(chao.intersects(this)) {

		y-=1;
		dy=-dy;
		System.out.println("pinga baixo");
	    
		
	}else if(playerBorder.intersects(this)) {
		
		restart();
		Game.pointsE++;
		System.out.println("ponto plauer");
		
	}else if(enemyBorder.intersects(this)) {
		restart();
		Game.pointsP++;
		System.out.println("ponto enemy");
	}else if(playerRect.intersects(this)) {
		
		x+=(Game.player.x+Game.player.width)-x;

		dx = -dx;
		
		if(Game.player.curDir < 0) dy-=0.1023444332;
		else if(Game.player.curDir > 0) dy+=0.1034568946;
			
		
		System.out.println("dir x"+dx+" y"+dy);
		System.out.println("speed"+spd);
	}else if(enemyRect.intersects(this)) {
		x-=1;
		dx = -dx;
		if(Game.enemy.curDir < 0) dy-=0.1023444332;
		else if(Game.enemy.curDir > 0)dy+=0.1034568946;
		
		System.out.println("dir x"+dx+" y"+dy);
		System.out.println("speed"+spd);
	}
	
 }}
 public void render(Graphics g) {
	 g.setColor(Color.white);
	 g.fillRect(x,y, width, height);
	 
 }
 
}
