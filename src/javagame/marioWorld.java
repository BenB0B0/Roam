package javagame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;


public class marioWorld extends BasicGameState{
	Animation guy, movingUp, movingDown, movingLeft, movingRight;
	Animation laserBeam, shootRight;
	Image worldMap,interactBack,marketBG;
	Image espBG;
	boolean quit = false; 
	int[] duration = {200,200};
	float guyPositionX = 0;
	float guyPositionY = -400;
	float shiftX = guyPositionX + 110;
	float shiftY = guyPositionY + 520;
	Image Front;
	Image Back;
	Image Left;
	Image Right;
	private Music BGmusic; 
	boolean mute = false;
	boolean jump = false;
	float jumpCount=0;
	int checkJump=0;
	
	public marioWorld(int state){
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		worldMap = new Image("res/marioWorld.png");
		espBG = new Image("res/espBG.png");
		Front = new Image("res/ship.png");
		Back = new Image("res/ship.png");
		Left = new Image("res/ship.png");
		Right = new Image("res/ship.png");
		interactBack = new Image("res/interactBack.png");
		marketBG = new Image("res/marketBG.png");
		
		Image[] walkUp = {Back,Back};
		Image[] walkDown = {Front,Front};
		Image[] walkLeft = {Left,Left};
		Image[] walkRight = {Right,Right};
		
		BGmusic = new Music("res/backGroundMusic.ogg");
		//BGmusic.loop();
		
		movingUp = new Animation(walkUp, duration, false);
		movingDown = new Animation(walkDown, duration, false);
		movingLeft = new Animation(walkLeft, duration, false);
		movingRight = new Animation(walkRight, duration, false);
		guy = movingRight;
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		worldMap.draw(guyPositionX, guyPositionY);
		guy.draw(shiftX,shiftY);
		g.drawString("X: "+guyPositionX+"\nY: "+guyPositionY, 400, 20);
		
		
		g.setColor(Color.white);;
		if(quit==true){
			espBG.draw(210,50);
			g.drawString("Resume (R)", 225, 65);
			g.drawString("Main Menu (Backspace)", 225, 115);
			g.drawString("Mute Background Music (M)", 225, 165);
			g.drawString("Quit Game (Q)", 225, 215);
			if(quit==false){
				g.clear();
			}
		}
		
		if(mute==true){
			BGmusic.stop();
		}
		
		if(guyPositionY>jumpCount+30){
			jump=false;
			checkJump=0;
		}
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		Input input = gc.getInput();
		if(checkJump==0){
			jumpCount=guyPositionY;
		}
		if(jump==true){
			guyPositionY += delta *.15f;
			checkJump=1;
		}
		if(checkJump==0){
			//if(guyPositionY<-275){}
		//	else
				//guyPositionY -= delta *.1f; //MAKES YOU FALL
		}
		
		//up
		if(input.isKeyDown(Input.KEY_UP)){
			guy = movingUp;
			guyPositionY += delta *.1f;
			//if(guyPositionY>-3.4){
			//	guyPositionY -= delta *.1f;
			//}
		}
		
		//down
		if(input.isKeyDown(Input.KEY_DOWN)){
			guy = movingDown;
			guyPositionY -= delta *.1f;
			//if(guyPositionY<-850){
			//	guyPositionY += delta *.1f; 
			//}
		}
		
		//left
		if(input.isKeyDown(Input.KEY_LEFT)){
			guy = movingLeft;
			guyPositionX += delta *.1f;
			if(guyPositionX>0){
				guyPositionX -= delta *.1f;
			}
		}
		
		//right
		if(input.isKeyDown(Input.KEY_RIGHT)){
		guy = movingRight;
		guyPositionX -= delta *.1f;
		}
		if(guyPositionX>-297 && guyPositionX<-209){
			if(guyPositionY<-557 || guyPositionY>-494){
				guyPositionX = 0;
				guyPositionY = -400;
				shiftX = guyPositionX + 110;
				shiftY = guyPositionY + 520;
			}
		}
		if(guyPositionY>-214 || guyPositionY<-772){
			 guyPositionX = 0;
			 guyPositionY = -400;
			 shiftX = guyPositionX + 110;
			 shiftY = guyPositionY + 520;
		}
		
		if(input.isKeyDown(Input.KEY_SPACE)){
			jump=true;
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
			if(input.isKeyDown(Input.KEY_BACK)){
				sbg.enterState(0);
			}
			if(input.isKeyDown(Input.KEY_Q)){
				System.exit(0);
			}
			if(input.isKeyDown(Input.KEY_M)){
				mute = true;
			}
			if(input.isKeyDown(Input.KEY_N)){
				sbg.enterState(2);
			}
		}
	}
	
	public int getID(){
		return 10;
	}
}