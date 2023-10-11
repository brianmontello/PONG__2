import java.awt.*;
import java.util.*;

public class Pelota extends Rectangle{

	Random random;
	int xVelocity;
	int yVelocity;
	int initialSpeed = 2;
	
	Pelota(int x, int y, int width, int height){
	    super(x, y, width, height);
	    random = new Random();
	    setXDirection(-initialSpeed); 
	    int randomYDirection = random.nextInt(2);
	    if(randomYDirection == 0)
	        randomYDirection--;
	    setYDirection(randomYDirection * initialSpeed);
        setXVelocity(-initialSpeed);
        setYVelocity(random.nextBoolean() ? initialSpeed : -initialSpeed); 
        setBackground(Color.BLACK);
        setOpaque(true);
		
	
		
	}
	private void setOpaque(boolean b) {
		// TODO Auto-generated method stub
		
	}
	private void setBackground(Color black) {
		// TODO Auto-generated method stub
		
	}
	public void setXDirection(int randomXDirection) {
		xVelocity = randomXDirection;
	}
	public void setYDirection(int randomYDirection) {
		yVelocity = randomYDirection;
	}
	public void move() {
	    x += xVelocity;
	    y += yVelocity;
	    
	}

	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(x, y, height, width);
	}

	  public void setXVelocity(int xVelocity) {
	        this.xVelocity = xVelocity;
	    }

	    public void setYVelocity(int yVelocity) {
	        this.yVelocity = yVelocity;
	    }
}
