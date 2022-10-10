package Entities;
import java.awt.Color;
import Main.*;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
//import java.util.concurrent.ThreadLocalRandom;


public class Player2 extends Rectangle{
	

	private static final long serialVersionUID = 1L;
	public int pause = 0;
	private int rand;
	private int i= 0;
	boolean AIup,AIdown;

	Random r = new Random();
	
	
	public static boolean AI;
	
	public boolean up, down;
	public int spd = 8;
	public int curDir = 0;
	
	public Player2(int x, int y,int width, int height) {
		super(x,y,width,height);
		
	}
	
	public void tick() {
		if(!AI) {
			if(up == true && y > 0) {
			
				curDir = -1;
				y-=spd - pause; 
			}else if(down && y < 420){
			
				curDir = 1;
				y+=spd - pause;
			}else {
			
				curDir = 0;
			}
		}else {
			
			if(AIup == true && y > 0) {
			
				curDir = -1;
				y-=spd - pause;
			}else if(AIdown && y < 420){
			
				curDir = 1;
				y+=spd - pause;
			}else {
			
				curDir = 0;
			}
			
			if(Game.ball.x >= Game.WIDTH-(200) && Game.ball.dx > 0 ) {			
							if(y < Game.ball.y) {
								if(Game.ball.y - y > 50-(height/2))
								AIdown = true; AIup = false;
							}else {
								if(y - Game.ball.y  > 20-(height/2))
								AIup = true; AIdown = false; 
							}
			}else {
				
				i++;
				if(i<30) {
					
				}else{
					i = 0;
					rand = r.nextInt(50,Game.HEIGHT- 50);
					if(!(rand % 5 == 2)) {
						if(y < rand) {
							AIdown = true; AIup = false;
						
						}else if(y > rand) {
							AIup = true; AIdown = false; 
						}
					}
					
					
				}
				
				
			}
			}
		}
			
		
			
			
					
			
			
			//y = (int) game.ball.getY()+rand;
			
		
	
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int)x,(int)y,width,height);
		g.setColor(Color.gray);
		g.fillRect(x+4, y+4, width-8, height-8);
	}
	
	public void reset() {
		this.setLocation(Game.WIDTH-width,Game.HEIGHT/2-(height/2));
	}
}
