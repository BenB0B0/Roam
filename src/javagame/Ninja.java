package javagame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;


public class Ninja extends BasicGameState{

	Animation guy, movingUp, movingDown, movingLeft, movingRight;
	Image worldMap;
	Image espBG;
	Image marketBG;
	boolean quit = false; 
	boolean market = false;
	int[] duration = {200,200};
	float guyPositionX = 0;
	float guyPositionY = 0;
	float shiftX = guyPositionX + 320;
	float shiftY = guyPositionY + 160;
	Image Front;
	Image Back;
	Image Left;
	Image Right;
	Image Ninja;
	
	public Ninja(int state){
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		worldMap = new Image("res/world.png");
		espBG = new Image("res/espBG.png");
		marketBG = new Image("res/marketBG.png");
		Ninja = new Image("res/ninjaLogo.png");
		Front = new Image("res/frontNin1.png");
		Back = new Image("res/backNin2.png");
		Left = new Image("res/leftNin2.png");
		Right = new Image("res/rightNin2.png");

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
		Ninja.draw(100,10);
		
		if(quit==true){
			espBG.draw(210,50);
			g.drawString("Resume (R)", 250, 100);
			g.drawString("Main Menu (M)", 250, 150);
			g.drawString("Quit Game (Q)", 250, 200);
			if(quit==false){
				g.clear();
			}
		}
		
		if(market==true){
			marketBG.draw(205,50);
			g.drawString("Welcome to the Market!", 260, 65);
			g.drawString("Buy Some Arrows (A)", 260, 120);
			g.drawString("Buy Some Markers (M)", 260, 170);
			g.drawString("Resume (R)", 260, 220);
			if(market==false){
				g.clear();
			}
		}
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		Input input = gc.getInput();
		
		//up
		if(input.isKeyDown(Input.KEY_UP)){
			guy = movingUp;
			guyPositionY += delta *.5f;
			if(guyPositionY>-3.4){
				guyPositionY -= delta *.5f;
			}
			
			//trees
			if((guyPositionY>-442 && guyPositionY<-299) && (guyPositionX>-126 && guyPositionX<-1)){
				guyPositionY -= delta *.5f;
			}
			if((guyPositionY>-152 && guyPositionY<-9) && (guyPositionX>-316 && guyPositionX<-176)){
				guyPositionY -= delta *.5f;
			}
			if((guyPositionY>-597 && guyPositionY<-449) && (guyPositionX>-384 && guyPositionX<-237)){
				guyPositionY -= delta *.5f;
			}
			if((guyPositionY>-172 && guyPositionY<-27) && (guyPositionX>-741 && guyPositionX<-589)){
				guyPositionY -= delta *.5f;
			}
			if((guyPositionY>-95) && (guyPositionX>-988 && guyPositionX<-853)){
				guyPositionY -= delta *.5f;
			}
			if((guyPositionY>-579 && guyPositionY<-445) && (guyPositionX>-492 && guyPositionX<-656)){
				guyPositionY -= delta *.5f;
			}
			if((guyPositionY>-388 && guyPositionY<-249) && (guyPositionX>-957 && guyPositionX<-822)){
				guyPositionY -= delta *.5f;
			}
			if((guyPositionY>-588 && guyPositionY<-447) && (guyPositionX>-794 && guyPositionX<-653)){
				guyPositionY -= delta *.5f;
			}
			//market
			if((guyPositionY>-360 && guyPositionY<-190) && (guyPositionX>-622 && guyPositionX<-388)){
				guyPositionY -= delta *.5f;
				market = true;
			}
		}
		
		//down
		if(input.isKeyDown(Input.KEY_DOWN)){
			guy = movingDown;
			guyPositionY -= delta *.5f;
			if(guyPositionY<-850){
				guyPositionY += delta *.5f; 
			}
			
			//trees
			if((guyPositionY>-442 && guyPositionY<-299) && (guyPositionX>-126 && guyPositionX<-1)){
				guyPositionY += delta *.5f;
			}
			if((guyPositionY>-152 && guyPositionY<-9) && (guyPositionX>-316 && guyPositionX<-176)){
				guyPositionY += delta *.5f;
			}
			if((guyPositionY>-597 && guyPositionY<-449) && (guyPositionX>-384 && guyPositionX<-237)){
				guyPositionY += delta *.5f;
			}
			if((guyPositionY>-172 && guyPositionY<-27) && (guyPositionX>-741 && guyPositionX<-589)){
				guyPositionY += delta *.5f;
			}
			if((guyPositionY>-95) && (guyPositionX>-988 && guyPositionX<-853)){
				guyPositionY += delta *.5f;
			}
			if((guyPositionY>-579 && guyPositionY<-445) && (guyPositionX>-492 && guyPositionX<-656)){
				guyPositionY += delta *.5f;
			}	
			if((guyPositionY>-388 && guyPositionY<-249) && (guyPositionX>-957 && guyPositionX<-822)){
				guyPositionY += delta *.5f;
			}
			if((guyPositionY>-588 && guyPositionY<-447) && (guyPositionX>-794 && guyPositionX<-653)){
				guyPositionY += delta *.5f;
			}
			//market
			if((guyPositionY>-360 && guyPositionY<-190) && (guyPositionX>-622 && guyPositionX<-388)){
				guyPositionY += delta *.5f;
				market = true;
			}
		}
		
		//left
		if(input.isKeyDown(Input.KEY_LEFT)){
			guy = movingLeft;
			guyPositionX += delta *.5f;
			if(guyPositionX>-1.4){
				guyPositionX -= delta *.5f;
			}
			
			//trees
			if((guyPositionY>-442 && guyPositionY<-299) && (guyPositionX>-126 && guyPositionX<-1)){
				guyPositionX -= delta *.5f;
			}
			if((guyPositionY>-152 && guyPositionY<-9) && (guyPositionX>-316 && guyPositionX<-176)){
				guyPositionX -= delta *.5f;
			}
			if((guyPositionY>-597 && guyPositionY<-449) && (guyPositionX>-384 && guyPositionX<-237)){
				guyPositionX -= delta *.5f;
			}
			if((guyPositionY>-172 && guyPositionY<-27) && (guyPositionX>-741 && guyPositionX<-589)){
				guyPositionX -= delta *.5f;
			}
			if((guyPositionY>-95) && (guyPositionX>-988 && guyPositionX<-853)){
				guyPositionX -= delta *.5f;
			}
			if((guyPositionY>-579 && guyPositionY<-445) && (guyPositionX>-492 && guyPositionX<-656)){
				guyPositionX -= delta *.5f;
			}
			if((guyPositionY>-388 && guyPositionY<-249) && (guyPositionX>-957 && guyPositionX<-822)){
				guyPositionX -= delta *.5f;
			}
			if((guyPositionY>-588 && guyPositionY<-447) && (guyPositionX>-794 && guyPositionX<-653)){
				guyPositionX -= delta *.5f;
			}
			//market
			if((guyPositionY>-360 && guyPositionY<-190) && (guyPositionX>-622 && guyPositionX<-388)){
				guyPositionX -= delta *.5f;
				market = true;
			}
		}
		
		//right
		if(input.isKeyDown(Input.KEY_RIGHT)){
			guy = movingRight;
			guyPositionX -= delta *.5f;
			if(guyPositionX<-1050){
				guyPositionX += delta *.5f;
			}
			
			//trees
			if((guyPositionY>-442 && guyPositionY<-299) && (guyPositionX>-126 && guyPositionX<-1)){
				guyPositionX += delta *.5f;
			}
			if((guyPositionY>-152 && guyPositionY<-9) && (guyPositionX>-316 && guyPositionX<-176)){
				guyPositionX += delta *.5f;
			}
			if((guyPositionY>-597 && guyPositionY<-449) && (guyPositionX>-384 && guyPositionX<-237)){
				guyPositionX += delta *.5f;
			}
			if((guyPositionY>-172 && guyPositionY<-27) && (guyPositionX>-741 && guyPositionX<-589)){
				guyPositionX += delta *.5f;
			}
			if((guyPositionY>-95) && (guyPositionX>-988 && guyPositionX<-853)){
				guyPositionX += delta *.5f;
			}
			if((guyPositionY>-579 && guyPositionY<-445) && (guyPositionX>-492 && guyPositionX<-656)){
				guyPositionX += delta *.5f;
			}
			if((guyPositionY>-388 && guyPositionY<-249) && (guyPositionX>-957 && guyPositionX<-822)){
				guyPositionX += delta *.5f;
			}
			if((guyPositionY>-588 && guyPositionY<-447) && (guyPositionX>-794 && guyPositionX<-653)){
				guyPositionX += delta *.5f;
			}
			//market
			if((guyPositionY>-360 && guyPositionY<-190) && (guyPositionX>-622 && guyPositionX<-388)){
				guyPositionX += delta *.5f;
				market = true;
			}
		}
		
		//market menu
		if(market==true){
			if(input.isKeyDown(Input.KEY_B)){
				//replenish arrows
			}
			if(input.isKeyDown(Input.KEY_M)){
				//replenish markers
			}
			if(input.isKeyDown(Input.KEY_R)){
				market = false;
			}
			if(input.isKeyDown(Input.KEY_N)){
				market = false;
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
		return 2;
	}
}
