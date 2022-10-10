package Main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;


public class UI extends JFrame implements MouseListener, ActionListener{
	
	
	/*
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String Bstart = "-start-";
	private static String pause1 = "-resume-",pause2 = "-menu-";
	private static String[] Bsettings= {
	"-settings-","-back-"," Ball speed: X","Player speed: X","Enemy speed: X"
	};
	private static int size;
			
	public int x = Game.WIDTH/2;
	public int y = Game.HEIGHT/2;
	public boolean startGame = false;
	public boolean pause = false;
	public boolean settings = false;
	private String curB;
	public static boolean hoverMenu1 = false,hoverMenu2 = false, hoverPause1 = false
	,hoverSettings1 = false,hoverSettings2 = false,hoverSettings3 = false,hoverSettings4 = false,hoverPause2 = false;

	 
	static Font Ftitle;
	static Font font1;
	static Font font2;
	static Font font3;
	static Font font4;
	static Font font5;
	static Font font6;
	static Font font7;
	
	Rectangle pause1HitBox,pause2HitBox,menu1HitBox,Menu2HitBox,settings1HitBox,settings2HitBox,settings3HitBox,settings4HitBox;
	
	JButton button;
	JTextField textfield;
	public static BufferedImage icon;
	public static BufferedImage bg;
	
	
	public UI() {
		try {
			icon = ImageIO.read(getClass().getResource("/ICON.png"));
		} catch (IOException e) {
			System.out.println("Error on reading icon");
		}
	}
	
	public void initHoverHitboxes(Point windowPos) {
		int X = (int) windowPos.getX();
		int Y = (int) windowPos.getY();
		pause1HitBox = new Rectangle(Game.WIDTH/2 -(int)(pause1.length()*7.0143)+X,(Game.HEIGHT/2-85)+Y+37,(int)(pause1.length()*7.0143)*2,30);
		pause2HitBox = new Rectangle(Game.WIDTH/2 -(int)(pause2.length()*8.0143)+X,(Game.HEIGHT/2-55)+Y+37,(int)(pause2.length()*8.0143)*2,27);
		menu1HitBox = new Rectangle(Game.WIDTH/2 - (int)(Bstart.length()*9.0843)+X,(Game.HEIGHT/2-36)+Y+37,(int)(Bstart.length()*9.0843)*2,32);
		Menu2HitBox = new Rectangle(Game.WIDTH/2-(int)(Bsettings[0].length()*9.0843)+X,(Game.HEIGHT/2)+Y+37,(int)(Bsettings[0].length()*9.08)*2,32);
		settings1HitBox = new Rectangle(Game.WIDTH/2-(int)(Bsettings[1].length()*9.0843)+X,(Game.HEIGHT/2-130)+Y+37,(int)(Bsettings[1].length()*9.08)*2,32);
		settings2HitBox = new Rectangle(Game.WIDTH/2-(int)(Bsettings[2].length()*9.0843)+X,(Game.HEIGHT/2-72)+Y+37,(int)(Bsettings[2].length()*9.08)*2,32);
		settings3HitBox = new Rectangle(Game.WIDTH/2-(int)(Bsettings[3].length()*9.0843)+X,(Game.HEIGHT/2-30)+Y+37,(int)(Bsettings[3].length()*9.08)*2,32);
		settings4HitBox = new Rectangle(Game.WIDTH/2-(int)(Bsettings[4].length()*9.0843)+X,(Game.HEIGHT/2)+Y+37,(int)(Bsettings[4].length()*9.08)*2,32);
	}
	
	public void restartP() {
		Game.pointsE = 0;
		Game.pointsP = 0;
	}
	 
	public void render(Graphics g) {
		size = 36;
		
		//background
		g.setColor(Color.white);
		
		//g.fillRect(Game.WIDTH/2 -(int)(pause1.length()*7.0143),(Game.HEIGHT/2-80),(int)(pause1.length()*7.0143)*2,23);
		//g.fillRect(Game.WIDTH/2 -(int)(pause2.length()*8.0143),(Game.HEIGHT/2-47),(int)(pause2.length()*8.0143)*2,23);
		
		if(startGame == false) {
			
			//settings
			if(settings) {
				hoverMenu1 = false;
				hoverMenu2 = false;
				g.setFont(Ftitle);
				g.drawString(Game.name,Game.WIDTH/2- (int)(Game.name.length()*10.9843),Game.HEIGHT/2-170);
				
				g.setFont(new Font(font1.getFontName(),font1.getStyle(),font1.getSize()+6));
				g.drawString(Bsettings[1],Game.WIDTH/2- (int)(Bsettings[1].length()*9.0843)-9,Game.HEIGHT/2-90);
				
				g.setFont(font2);
				g.drawString(Bsettings[2].replaceAll("X",String.valueOf(Game.ball.spd)),Game.WIDTH/2- (int)(Bsettings[2].length()*9.0843),Game.HEIGHT/2-36);
				
				g.setFont(font3);
				g.drawString(Bsettings[3].replaceAll("X",String.valueOf(Game.player.spd)),Game.WIDTH/2- (int)(Bsettings[3].length()*9.0843),Game.HEIGHT/2);
				
				g.setFont(font4);
				g.drawString(Bsettings[4].replaceAll("X",String.valueOf(Game.enemy.spd)),Game.WIDTH/2- (int)(Bsettings[4].length()*10.0843),Game.HEIGHT/2+36);
			}else {
				g.setFont(Ftitle);
				g.drawString(Game.name,Game.WIDTH/2- (int)(Game.name.length()*10.9843),Game.HEIGHT/2-100);
				g.setFont(font1);
				g.drawString(Bstart,Game.WIDTH/2- (int)(Bstart.length()*9.0843),Game.HEIGHT/2);
				g.setFont(font2);
				g.drawString(Bsettings[0],Game.WIDTH/2- (int)(Bsettings[0].length()*9.0843),Game.HEIGHT/2+36);
				
				
			}
			
		}else {
			
			g.drawRect((int)Game.rectx,1,(36/2)*Game.placar.length(), 36);
			
			g.fillRect(0, 0,Game.WIDTH,4);
			g.fillRect(0, Game.HEIGHT-4,Game.WIDTH,4);
			
			
			g.fillRect(Game.WIDTH/2-2,37,1,Game.HEIGHT);
			g.fillRect(Game.WIDTH/2+2,37,1,Game.HEIGHT);
			
			
			size = 26;
			Ftitle = new Font("Arial",Font.BOLD,size);
			g.setFont(Ftitle);
			//placar
			Game.placar = Game.pointsP+" - "+Game.pointsE;
			g.drawString(Game.placar,Game.rectx+18,25);
			//pause menu
			
			if(pause == true) {
					g.setColor(new Color(20,20,20,99));
					g.fillRect(0,0,Game.WIDTH,Game.HEIGHT);
					g.setColor(Color.white);
					
					g.setFont(font3);
					g.drawString(pause1,Game.WIDTH/2- (int)(pause1.length()*7.0143),Game.HEIGHT/2-60);
					
					g.setFont(font4);
					g.drawString(pause2,Game.WIDTH/2- (int)(pause2.length()*8.0143),Game.HEIGHT/2-30);
				}
					
		
		}
		
		Ftitle = new Font("Arial",Font.BOLD,size);
		font1 = new Font("Arial",Font.BOLD,size);
		font2= new Font("Arial",Font.BOLD,size);
		font3= new Font("Arial",Font.BOLD,size);
		font4 = new Font("Arial",Font.BOLD,size);
		font5= new Font("Arial",Font.BOLD,size);
		font6= new Font("Arial",Font.BOLD,size);
		font7= new Font("Arial",Font.BOLD,size);

		Bstart = "-start-";
		pause1 = "-resume-";
		pause2 = "-menu-";
		
		Bsettings[0] = "-settings-";
		Bsettings[1] = "-back-";
		Bsettings[2] = "Ball speed: X";
		Bsettings[3] = "Player speed: X";
		Bsettings[4] = "Enemy speed: X";
	}
	
	public void telma() {
		
	}
	
	public void mouseHover(String Button) {
		if(Button.equals("Bstart") && startGame == false && settings == false) {
			font1 = new Font("Arial",Font.BOLD,size+10);
			Bstart = "-start-  ";
			hoverMenu1 = true;
			return;
		}
		if(Button.equals("Bsettings[0]") && startGame == false && settings == false){
			font2 = new Font("Arial",Font.BOLD,size+10);
			Bsettings[0] = "-settings-  ";
			hoverMenu2 = true;
		}
		
		if(settings == false) return;
			
		if(Button.equals("Bsettings[1]")) {
			hoverSettings1 = true;
			Bsettings[1] = "-back-  ";
			font1 = new Font("Arial",Font.BOLD,size+10);
		}else if(Button.equals("Bsettings[2]")) {
			hoverSettings2 = true;
			Bsettings[2] = "Ball speed: X ";
			font2 = new Font("Arial",Font.BOLD,size+2);
		}else if(Button.equals("Bsettings[3]")) {
			hoverSettings3 = true;
			Bsettings[3] = "Player speed: X ";
			font3 = new Font("Arial",Font.BOLD,size+2);
		}else if(Button.equals("Bsettings[4]")) {
			hoverSettings4 = true;
			Bsettings[4] = "Enemy speed: X ";
			font4 = new Font("Arial",Font.BOLD,size+2);
		}
		
	}
	
	
	public void Frame(String name){
		if(this.isShowing() == true || this.isVisible() ) {
			return;
			
		}
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setLayout(new FlowLayout());
		this.setTitle(name);
		this.setName(name);
		curB = name;
		
		
	    
		
		button = new JButton("Submit");
		button.addActionListener(this);
		
		textfield = new JTextField();
		textfield.setPreferredSize(new Dimension(250,40));
		
		this.add(button);
		this.add(textfield);
		
		
		this.pack();
		this.setVisible(true);
	}
	
	private void setter(int I) {
		if(curB.equals("Set ball speed")) {
			if(I > 12) {
				Game.ball.spd = 11;
			}else 
			Game.ball.spd = I;
		}else if(curB.equals("Set player speed")){
			if(I > 12) {
				Game.player.spd = 11;
			}else 
			Game.player.spd = I;
			
		}else if(curB.equals("Set enemy speed")) {
			if(I > 12) {
				Game.enemy.spd = 11;
			}else 
			Game.enemy.spd = I;
		}
	}
	
	
	public void pause(boolean a) {
		pause = a;
		System.out.println("pause"+a+pause);
		if(a == true) {
			
			Game.enemy.pause = Game.enemy.spd;
			Game.player.pause = Game.player.spd;
			
		}else {
		
			Game.enemy.pause = 0;
			Game.player.pause = 0;
		}
	}
	
	public void tick() {
		
		PointerInfo a = MouseInfo.getPointerInfo();
		Point b = a.getLocation();
		//int x = (int) b.getX();
		///int y = (int) b.getY();
		//int X = (int) Game.loc.getX();
		//int Y = (int) Game.loc.getY();
		hoverMenu1 = false; hoverMenu2 = false; 
		hoverSettings1 = false; hoverSettings2 = false; hoverSettings3 = false; hoverSettings4 = false;
		hoverPause2 = false;
		
		//hitboxses
		//pause1HitBox = new Rectangle(Game.WIDTH/2 -(int)(pause1.length()*7.0143)+X,(Game.HEIGHT/2-85)+Y+37,(int)(pause1.length()*7.0143)*2,30);
		//pause2HitBox = new Rectangle(Game.WIDTH/2 -(int)(pause2.length()*8.0143)+X,(Game.HEIGHT/2-55)+Y+37,(int)(pause2.length()*8.0143)*2,27);
		
		//CHECK HOVER
		if(pause1HitBox.contains(b) && pause && startGame) {
			font3 = new Font("Arial",Font.BOLD,size+5);
			pause1 = "-resume-  ";
			hoverPause1 = true;
		}else 
			hoverPause1 = false;
		if(pause2HitBox.contains(b) && pause && startGame) {
			font4 = new Font("Arial",Font.BOLD,size+5);
			pause2 = "-menu- ";
			hoverPause2 = true;
		}

		if(menu1HitBox.contains(b) &&!startGame) {
			mouseHover("Bstart");
			hoverMenu1 = true;
		}else
		if(Menu2HitBox.contains(b) &&!startGame) {
			mouseHover("Bsettings[0]");
			hoverMenu2 = true;
		}
		
		if(settings1HitBox.contains(b) && settings && !startGame) {
			mouseHover("Bsettings[1]");
			hoverSettings1 = true;
		}else
		if(settings2HitBox.contains(b) && settings && !startGame) {
			mouseHover("Bsettings[2]");
			hoverSettings2 = true;
		}else
		if(settings3HitBox.contains(b) && settings && !startGame) {
			mouseHover("Bsettings[3]");
			hoverSettings3 = true;
		}else
		if(settings4HitBox.contains(b) && settings && !startGame) {
			mouseHover("Bsettings[4]");
			hoverSettings4 = true;
		}
		
		/*
		if(!settings && !startGame && x > (Game.WIDTH/2 - (int)(Bstart.length()*9.0843))+X &&
				x < ((Game.WIDTH/2 - (int)(Bstart.length()*9.0843)+Bstart.length()*9.0843*1.70843))+X &&
				y > Game.HEIGHT/2-36+Y+37 &&
				y < Game.HEIGHT/2+Y+37){
			mouseHover("Bstart");
			hoverMenu1 = true;
		}else if(!settings && !startGame && x > (Game.WIDTH/2 - (int)(Bsettings[0].length()*9.0843))+X &&
				x < ((Game.WIDTH/2 - (int)(Bsettings[0].length()*9.0843)+Bsettings[0].length()*9.0843*1.70843))+X &&
				y > Game.HEIGHT/2+Y+37 &&
				y < Game.HEIGHT/2+Y+37+36) {
			
			mouseHover("Bsettings[0]");
			hoverMenu2 = true;
		}else if ( settings == true && !startGame) {
			if(x > (Game.WIDTH/2 - (int)(Bsettings[1].length()*9.0843))+X &&
				x <= ((Game.WIDTH/2 - (int)(Bsettings[1].length()*9.0843)+Bsettings[1].length()*9.0843*2.0843))+X &&
				y > Game.HEIGHT/2-76+Y+37-60 &&
				y < Game.HEIGHT/2-76+Y+37-20) {
				
				mouseHover("Bsettings[1]");
				hoverSettings1 = true;
			}else if(x > (Game.WIDTH/2 - (int)(Bsettings[2].length()*9.0843))+X &&
					x <= ((Game.WIDTH/2 - (int)(Bsettings[2].length()*9.0843)+Bsettings[2].length()*9.0843*2.0843))+X &&
					y > Game.HEIGHT/2-76+Y+37 &&
					y < Game.HEIGHT/2-38+Y+37 ) {
				mouseHover("Bsettings[2]");
				hoverSettings2 = true;
			}else if(x > (Game.WIDTH/2 - (int)(Bsettings[3].length()*9.0843))+X &&
					x < ((Game.WIDTH/2 - (int)(Bsettings[3].length()*9.0843)+Bsettings[3].length()*9.0843*2.0843))+X &&
					y > (Game.HEIGHT/2)+Y+37-36 &&
					y < (Game.HEIGHT/2)+Y+37 ) {
				mouseHover("Bsettings[3]");
				hoverSettings3 = true;
			}else if(x > (Game.WIDTH/2 - (int)(Bsettings[4].length()*9.0843))+X &&
					x < ((Game.WIDTH/2 - (int)(Bsettings[4].length()*9.0843)+Bsettings[4].length()*9.0843*2.0843))+X &&
					y > (Game.HEIGHT/2)+Y+37 &&
					y < (Game.HEIGHT/2)+Y+37+36 ) {
				mouseHover("Bsettings[4]");
				hoverSettings4 = true;
			}
			
			
			
		}
		
		if(pause == true && x > (game.WIDTH/2- (int)(pause1.length()*7.0143)+X) &&
				x <= (game.WIDTH/2- (int)(pause1.length()*7.0143))+pause1.length()*7.0843*2.0843+X &&
				y > game.HEIGHT/2+72-36+Y &&
				y < game.HEIGHT/2+72-36+Y+36) {
			
			mouseHover("pause1");
			hoverPause1 = true;
			
		}
		*/
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button) {
			
			try {
			setter(Integer.parseInt(textfield.getText()));
			}
			catch(NumberFormatException ex){
					System.out.println("Error on setting button");
			}
			this.remove(button);
			this.remove(textfield);
			this.setVisible(false);
			
		}
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {

		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

	
	
}
