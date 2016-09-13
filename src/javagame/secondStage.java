package javagame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;


public class secondStage extends BasicGameState{

	Animation guy, movingUp, movingDown, movingLeft, movingRight;
	Image worldMap;
	Image espBG;
	boolean quit = false; 
	int[] duration = {200,200};
	float guyPositionX = 0;
	float guyPositionY = 0;
	float shiftX = guyPositionX + 320;
	float shiftY = guyPositionY + 160;
	Image Front;
	Image Back;
	Image Left;
	Image Right;
	
	public secondStage(int state){
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		worldMap = new Image("res/secondWorld.png");
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
		Input input = gc.getInput();
		
		//up
		if(input.isKeyDown(Input.KEY_UP)){
			guy = movingUp;
			guyPositionY += delta *.1f;
			if(guyPositionY>-3.4){
				guyPositionY -= delta *.1f;
			}
		}
		
		//down
		if(input.isKeyDown(Input.KEY_DOWN)){
			guy = movingDown;
			guyPositionY -= delta *.1f;
			if(guyPositionY<-850){
				guyPositionY += delta *.1f; 
			}
		}
		
		//left
		if(input.isKeyDown(Input.KEY_LEFT)){
			guy = movingLeft;
			guyPositionX += delta *.1f;
			if(guyPositionX>-1.4){
				guyPositionX -= delta *.1f;
			}
		}
		
		//right
		if(input.isKeyDown(Input.KEY_RIGHT)){
			guy = movingRight;
			guyPositionX -= delta *.1f;
			if(guyPositionX<-1050){
				guyPositionX += delta *.1f;
			}
		}
		//escape
		if(input.isKeyDown(Input.KEY_ESCAPE)){
			quit = true;
		}
		
		//when the menu is up
		if(quit==true){
			if(input.isKeyDown(Input.KEY_R)){
				quit = false;
			}
			if(input.isKeyDown(Input.KEY_M)){
				sbg.enterState(0);
			}
			if(input.isKeyDown(Input.KEY_Q)){
				System.exit(0);
			}
		}
	}
	
	public int getID(){
		return 3;
	}
}

