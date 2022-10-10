package Entities;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import Main.*;


public class Bola extends Rectangle{
	

private static final long serialVersionUID = 1L;

public double xDouble,yDouble;
public double dx,dy;
public int spd = 6;

public void restart() {
	
	x = Game.WIDTH/2-(width/2);
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

//walls
Rectangle playerBorder = new Rectangle(0,0,1,Game.HEIGHT);
Rectangle enemyBorder = new Rectangle(Game.WIDTH,0,1,Game.HEIGHT);

//floor and ceiling
Rectangle teto = new Rectangle(0,1,Game.WIDTH,1);
Rectangle chao = new Rectangle(0,Game.HEIGHT,Game.WIDTH,1);

//entities
Rectangle playerRect = new Rectangle(Game.player.x,Game.player.y,0,Game.player.height);
Rectangle enemyRect = new Rectangle(Game.enemy.x,Game.enemy.y,0,Game.enemy.height);

 public void tick() {
	 
	 if(Game.ui.pause == true) return;
 
	 
	 xDouble = x;
	 yDouble = y; 
	 x = (int)(xDouble += spd*dx);
	 y = (int)(yDouble += spd*dy);
	 enemyRect.setRect(Game.enemy.x,Game.enemy.y,Game.enemy.width,Game.enemy.height);
	 playerRect.setRect(Game.player.x,Game.player.y,Game.player.width,Game.player.height);
	 
	
	 
	 //collisions
	 if(teto.intersects(this)) {		
		y+=1;
		dy=-dy;	    
	}else if(chao.intersects(this)) {
		y-=1;
		dy=-dy;
	}
	
	 
	if(playerBorder.intersects(this)) {
		restart();
		Game.pointsE++;
	}else if(enemyBorder.intersects(this)) {
		restart();
		Game.pointsP++;
	}
	
	if(playerRect.intersects(this)) {
		x+=(Game.player.x+Game.player.width)-x;
		dx = -dx;
		
		if(Game.player.curDir < 0) dy-=0.1023444332;
		else if(Game.player.curDir > 0) dy+=0.1034568946;
			
	}else if(enemyRect.intersects(this)) {
		x-=1;
		dx = -dx;
		if(Game.enemy.curDir < 0) dy-=0.1023444332;
		else if(Game.enemy.curDir > 0)dy+=0.1034568946;
		
	}
	
 }
 public void render(Graphics g) {
	 g.setColor(Color.white);
	 g.fillRect(x,y, width, height);
	 
 }
 
}
