import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Marcador extends Rectangle {
    static int GAME_WIDTH;
    static int GAME_HEIGHT;
    int playerLives;
    int player1;
    int player2;
    int playerScore;
   
    Marcador(int GAME_WIDTH, int GAME_HEIGHT, int initialLives) {
    	Marcador.GAME_WIDTH = GAME_WIDTH;
    	Marcador.GAME_HEIGHT = GAME_HEIGHT;
        this.playerLives = initialLives;
        playerScore = 0;
        setBackground(Color.BLACK);
        setOpaque(true);

    }

    private void setOpaque(boolean b) {
		// TODO Auto-generated method stub
		
	}

	private void setBackground(Color black) {
		// TODO Auto-generated method stub
		
	}

	public void playerLosesLife() {
        playerLives--;
    }
    
    public void increaseScore() {
        playerScore++;
    }
    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Consolas", Font.PLAIN, 30));
        g.drawString("Vidas: " + playerLives, GAME_WIDTH / 2 - 50, 50);
        g.drawString("Score: " + playerScore, GAME_WIDTH / 2 - 50, 75);
    }
}