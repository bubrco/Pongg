package Main;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import Entities.*;


public class Game extends Canvas implements Runnable, KeyListener, MouseListener{
		private static final long serialVersionUID = 1L;
		public static int WIDTH = 620, HEIGHT = 480;
		public static Player player;
		public static Bola ball;
		public static Player2 enemy;
		public static UI ui;
		public static int pointsP = 0;
		public static int pointsE = 0;
		public static int rectx;
		static String placar = Game.pointsP+" - "+Game.pointsE;
		public static final String name = "PONG 3.0";
		public static JFrame frame;
		public static Point loc;
		private static Game game;
		
	
		public Game() {
			this.addKeyListener(this);
			this.addMouseListener(this);
			this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
			
			ui = new UI();
			player = new Player(0,HEIGHT/2-(60/2), 22, 60);
			enemy = new Player2(WIDTH-22,HEIGHT/2-(60/2),22,60);
			ball = new Bola(WIDTH/2,HEIGHT/2,22,22);
		}
		
		
		public void tick() {
			if(ui.startGame == true) {
				player.tick();
				enemy.tick();
				ball.tick();
			}
			ui.tick();
		}
		
		public void render() {
			BufferStrategy bs = this.getBufferStrategy();
			
			if(bs == null) {
				this.createBufferStrategy(3);
				return;
			}
			
			Graphics g = bs.getDrawGraphics();
			
			
			rectx = WIDTH/2-((36/2)*placar.length()/2);
			
			g.setColor(Color.black);
			g.fillRect(0,0,WIDTH,HEIGHT);
			//g.drawImage(ui.bg,0,0,null);
			
			
			if(ui.startGame == true) {
				player.render(g);
				enemy.render(g);
				ball.render(g);
			}
			
			ui.render(g);
			
			g.dispose();
			bs.show();
				
		}
		
		public static void main(String[] args) {
			game = new Game();
			new Thread(game).start();
		}

		private static void initFrame() {
				//Game game = new Game();
				JFrame frame = new JFrame();
				frame.add(game);
				frame.setTitle(name);
				frame.setIconImage(UI.icon);  
				
				frame.pack();
				frame.setResizable(false);
				
				frame.setLocationRelativeTo(null);
				
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				
				loc = frame.getLocation();
				System.out.println("Window");
		}


		@Override
		public void run() {
			initFrame();
			ui.initHoverHitboxes(loc);
			requestFocus();
				while(true) {
					tick();
					render();
					try {
						Thread.sleep(1000/60);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}	
				}
		}

		boolean is = true, it = true;
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_W) {				
				player.up = true;
			
			}else if(e.getKeyCode() == KeyEvent.VK_S) {				
				player.down = true;	
			}
			if(e.getKeyCode() == KeyEvent.VK_UP) {				
				enemy.up = true;
				
			}else if(e.getKeyCode() == KeyEvent.VK_DOWN) {	
				enemy.down = true;
				
			}
			if(e.getKeyCode() == KeyEvent.VK_R) {
				ball.restart();
			}
			if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				ui.startGame = true;
			}
			if(e.getKeyCode() == KeyEvent.VK_T) {
				if(it == true){
					Player2.AI = true;
					it = false;
				}else {
					Player2.AI = false;
					it = true;
				}
				
			}
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE && ui.startGame) {
				if(is == true) {
					ui.pause(is);
					is = false;
				}else {
				ui.pause(is);
				is = true;
			}}
		}
		

		@Override
		public void keyReleased(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_W) {
				player.up = false;
				
			}else if(e.getKeyCode() == KeyEvent.VK_S) {
				player.down = false;	
			}
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				enemy.up = false;
			}else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				enemy.down = false;	
			}
		}


		@Override
		public void keyTyped(KeyEvent e) {
		}
		

		@Override
		public void mouseClicked(MouseEvent e) {
		}


		@Override
		public void mousePressed(MouseEvent e) {
			if(e.getButton() == MouseEvent.BUTTON1) {
				if(UI.hoverMenu2 == true) {
					ui.settings = true;
				}else if(UI.hoverMenu1 == true) {
					UI.hoverMenu1 = false;
					ui.startGame = true;
					player.reset();
					enemy.reset();
					ball.restart();
				}
				
				if(UI.hoverPause2 == true && ui.pause == true) {
					ui.startGame = false;
				}else if(UI.hoverPause1 == true  && ui.pause == true) {
					ui.pause(false);
					is = true;
				}
				if(UI.hoverSettings1 == true) {
					ui.settings = false;
				}
				if(UI.hoverSettings2 == true) {				
					ui.Frame("Set ball speed");
				}else if(UI.hoverSettings3 == true) {
					ui.Frame("Set player speed");
				}else if(UI.hoverSettings4 == true) {
					ui.Frame("Set enemy speed");
				}
			}
			
		}


		@Override
		public void mouseReleased(MouseEvent e) {
		}


		@Override
		public void mouseEntered(MouseEvent e) {
		
		}


		@Override
		public void mouseExited(MouseEvent e) {
			
		}



}