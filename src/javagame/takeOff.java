package javagame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.FadeOutTransition;


public class takeOff extends BasicGameState{

	Animation guy, movingUp, movingDown, movingLeft, movingRight;
	Image worldMap;
	Image espBG;
	boolean quit = false; 
	int[] duration = {200,200};
	float guyPositionX = -1034;
	float guyPositionY = -185;
	float shiftX = guyPositionX + 1354;
	float shiftY = guyPositionY + 345;
	Image Front;
	Image Back;
	Image Left;
	Image Right;
	
	public takeOff(int state){
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		worldMap = new Image("res/takeoffWorld.png");
		espBG = new Image("res/espBG.png");
		Front = new Image("res/ship.png");
		Back = new Image("res/ship.png");
		Left = new Image("res/ship.png");
		Right = new Image("res/ship.png");

		Image[] walkUp = {Back,Back};
		Image[] walkDown = {Front,Front};
		Image[] walkLeft = {Left,Left};
		Image[] walkRight = {Right,Right};
		
		movingUp = new Animation(walkUp, duration, false);
		movingDown = new Animation(walkDown, duration, false);
		movingLeft = new Animation(walkLeft, duration, false);
		movingRight = new Animation(walkRight, duration, false);
		guy = movingDown;
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		worldMap.draw(guyPositionX, guyPositionY);
		guy.draw(shiftX,shiftY);
		g.drawString("X: "+guyPositionX+"\nY: "+guyPositionY, 400, 20);
		
		if(quit==true){
			espBG.draw(210,50);
			g.drawString("Resume (R)", 250, 100);
			g.drawString("Main Menu (M)", 250, 150);
			g.drawString("Quit Game (Q)", 250, 200);
			if(quit==false){
				g.clear();
			}
		}
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
	
			guy = movingUp;
			guyPositionY += delta *.1f;
			if(guyPositionY>-3.4){
				guyPositionY -= delta *.1f;
			}
			
			if(guyPositionY > -3.5){
				sbg.enterState(5, new FadeOutTransition(Color.white, 1000), null);  
			}
	}
	
	public int getID(){
		return 4;
	}
}
