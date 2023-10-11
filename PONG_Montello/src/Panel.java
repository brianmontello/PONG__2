import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Panel extends JPanel implements Runnable{

	static final int GAME_WIDTH = 1000;
	static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555));
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);
	static final int Pelota_DIAMETER = 20;
	static final int Paleta_WIDTH = 25;
	static final int Paleta_HEIGHT = 100;

	Thread gameThread;
	Image image;
	Graphics graphics;
	Random random;
	Paleta Paleta1;
	Paleta Paleta2;
	Pelota Pelota;
	Marcador Marcador;
	static final int initialSpeed = 2;
	Panel(){
        setFocusable(true);
        addKeyListener(new AL());
        setPreferredSize(SCREEN_SIZE);
        setBackground(Color.BLACK);
        setOpaque(true);
		newPaletas();
		newPelota();
	    Marcador = new Marcador(GAME_WIDTH, GAME_HEIGHT, 3); 
		gameThread = new Thread(this);
		gameThread.start();

	}
	
	public void newPelota() {
		random = new Random();
		Pelota = new Pelota((GAME_WIDTH/2)-(Pelota_DIAMETER/2),random.nextInt(GAME_HEIGHT-Pelota_DIAMETER),Pelota_DIAMETER,Pelota_DIAMETER);
	}
	public void newPaletas() {
		Paleta1 = new Paleta(0,(GAME_HEIGHT/2)-(Paleta_HEIGHT/2),Paleta_WIDTH,Paleta_HEIGHT,1);
	}
	  @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);

	        setBackground(Color.BLACK);

	        Paleta1.draw(g);
	        Pelota.draw(g);
	        Marcador.draw(g);
	    }
	public void paint(Graphics g) {
        setBackground(Color.BLACK);

		image = createImage(getWidth(),getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image,0,0,this);
	}
	public void draw(Graphics g) {
		Paleta1.draw(g);
		Pelota.draw(g);
		Marcador.draw(g);
        setBackground(Color.BLACK);
Toolkit.getDefaultToolkit().sync(); 

	}
	public void move() {
		Paleta1.move();
		Pelota.move();
	}
	public void checkCollision() {
		
		if (Pelota.intersects(Paleta1)) {
	        Pelota.xVelocity = Math.abs(Pelota.xVelocity);
	        Pelota.xVelocity++;
	        if (Pelota.yVelocity > 0)
	            Pelota.yVelocity++;
	        else
	            Pelota.yVelocity--;
	        Pelota.setXDirection(Pelota.xVelocity);
	        Pelota.setYDirection(Pelota.yVelocity);

	    }
		
		if (Pelota.x <= 0) {
	        Marcador.playerLosesLife();
	        if (Marcador.playerLives > 0) {
	            Pelota.x = (GAME_WIDTH / 2) - (Pelota_DIAMETER / 2);
	            Pelota.y = random.nextInt(GAME_HEIGHT - Pelota_DIAMETER);
	            Paleta1.y = (GAME_HEIGHT / 2) - (Paleta_HEIGHT / 2);
	            Pelota.setXVelocity(-initialSpeed);
	            Pelota.setYVelocity(random.nextBoolean() ? initialSpeed : -initialSpeed);


	        } else {
	        	JOptionPane.showMessageDialog(this, "Perdiste todas tus vidas! Tu puntuacion fue: " + Marcador.playerScore, "Fin del Juego", JOptionPane.INFORMATION_MESSAGE);
	        	System.exit(0); 	    }
		}

		if(Pelota.y <=0) {
			Pelota.setYDirection(-Pelota.yVelocity);
		}
		   if (Pelota.x >= GAME_WIDTH - Pelota_DIAMETER) {
		        Pelota.setXDirection(-Pelota.xVelocity);
		    }

		if(Pelota.y >= GAME_HEIGHT-Pelota_DIAMETER) {
			Pelota.setYDirection(-Pelota.yVelocity);
		}
		if(Pelota.intersects(Paleta1)) {
			Pelota.xVelocity = Math.abs(Pelota.xVelocity);
			Pelota.xVelocity++; 
			if(Pelota.yVelocity>0)
				Pelota.yVelocity++; 
			else
				Pelota.yVelocity--;
			Pelota.setXDirection(Pelota.xVelocity);
			Pelota.setYDirection(Pelota.yVelocity);
			Marcador.increaseScore(); 
		}
		if(Pelota.x <=0) {
			Marcador.player2++;
			newPaletas();
			newPelota();
			System.out.println("Player 2: "+Marcador.player2);
		}
		if(Paleta1.y<=0)
			Paleta1.y=0;
		if(Paleta1.y >= (GAME_HEIGHT-Paleta_HEIGHT))
			Paleta1.y = GAME_HEIGHT-Paleta_HEIGHT;
		
		
	}
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks =60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		while(true) {
			long now = System.nanoTime();
			delta += (now -lastTime)/ns;
			lastTime = now;
			if(delta >=1) {
				move();
				checkCollision();
				repaint();
				delta--;
			}
		}
	}
	public class AL extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			Paleta1.keyPressed(e);
		}
		public void keyReleased(KeyEvent e) {
			Paleta1.keyReleased(e);
		}
	}
    public static void main(String[] args) {
        JFrame frame = new JFrame("Pong Game");
        Panel Panel = new Panel();
        frame.add(Panel);
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
