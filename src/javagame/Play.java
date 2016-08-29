package javagame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Play extends BasicGameState{

	Animation guy, movingUp, movingDown, movingLeft, movingRight;
	
	//PICTURES***********
	Image worldMap;
	Image espBG;
	Image marketBG;
	Image monster;
	Image wumpusDeath;
	Image wumpusHint;
	Image key;
	Image keyGlow;
	Image pit;
	Image pitDeath;
	//*******************
	
	//BOOLEANS***********
	boolean wumpusDeathShow = false;
	boolean market = false;
	boolean quit = false;
	boolean hint = false;
	boolean keyShow = false;
	boolean keyGlowShow = false;
	boolean pitShowHint = false;
	boolean pitDeadShow = false;
	//********************
	
	//AVATAR INFO*********
	int[] duration = {200,200};
	float guyPositionX = 0;
	float guyPositionY = 0;
	float shiftX = guyPositionX + 320;
	float shiftY = guyPositionY + 160;
	Image Front;
	Image Back;
	Image Left;
	Image Right;
	//**********************
	
	//Wumpus Generation*****
	int x1, x2, y1, y2, wumpusHintY1, wumpusHintY2, wumpusHintX1, wumpusHintX2;
	//Key Generation*********
	int keyx1, keyy1, keyx2, keyy2, keyHintY1, keyHintY2, keyHintX1, keyHintX2;
	//Pit Generation*********
	int pitx1, pitx2, pity1, pity2, pitHintX1, pitHintX2, pitHintY1, pitHintY2;
	//***********************
	
	public Play(int state){
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		worldMap = new Image("res/world.png");
		espBG = new Image("res/espBG.png");
		marketBG = new Image("res/marketBG.png");
		monster = new Image("res/monster.png");
		wumpusDeath = new Image("res/wumpusDeath.jpg");
		wumpusHint = new Image("res/wumpusHint.png");
		key = new Image("res/keyHint.png");
		keyGlow = new Image("res/keyGlow.png");
		pit = new Image("res/pit.png");
		pitDeath = new Image("res/pitdeath.jpg");
		
		Front = new Image("res/Front.png");
		Back = new Image("res/Back.png");
		Left = new Image("res/Left.png");
		Right = new Image("res/Right.png");

		Image[] walkUp = {Back,Back};
		Image[] walkDown = {Front,Front};
		Image[] walkLeft = {Left,Left};
		Image[] walkRight = {Right,Right};
		
		movingUp = new Animation(walkUp, duration, false);
		movingDown = new Animation(walkDown, duration, false);
		movingLeft = new Animation(walkLeft, duration, false);
		movingRight = new Animation(walkRight, duration, false);
		guy = movingDown;
		
		//OBJECT CREATIONS*******************************************************************************
		creation wumpus[] = new creation[1];
		creation keyObject[] = new creation[1];
		creation pit[] = new creation[1];
		wumpus[0] = new creation();
		keyObject[0] = new creation();
		pit[0] = new creation();
		
		wumpus[0].generateKey();
		x1 = wumpus[0].getX1(); x2 = wumpus[0].getX2(); y1 = wumpus[0].getY1(); y2 = wumpus[0].getY2();
		wumpusHintY1 = wumpus[0].getHintY1(); wumpusHintY2 = wumpus[0].getHintY2();
		wumpusHintX1 = wumpus[0].getHintX1(); wumpusHintX2 = wumpus[0].getHintX2();
		
		keyObject[0].generateKey();
		keyx1 = keyObject[0].getX1(); keyx2 = keyObject[0].getX2(); keyy1 = keyObject[0].getY1(); keyy2 = keyObject[0].getY2();
		keyHintY1 = keyObject[0].getHintY1(); keyHintY2 = keyObject[0].getHintY2();
		keyHintX1 = keyObject[0].getHintX1(); keyHintX2 = keyObject[0].getHintX2();
		
		pit[0].generateKey();
		pitx1 = pit[0].getX1(); pitx2 = pit[0].getX2(); pity1 = pit[0].getY1(); pity2 = pit[0].getY2();
		pitHintY1 = pit[0].getHintY1(); pitHintY2 = pit[0].getHintY2();
		pitHintX1 = pit[0].getHintX1(); pitHintX2 = pit[0].getHintX2();
		//**************************************************************************************************
		
		//wumpus location printed to console
		System.out.println("Wumpus Location:");
		System.out.println(y1);
		System.out.println(y2);
		System.out.println(x1);
		System.out.println(x2);
		System.out.println("Wumpus Hint:");
		System.out.println(wumpusHintY1);
		System.out.println(wumpusHintY2);
		System.out.println(wumpusHintX1);
		System.out.println(wumpusHintX2);
		System.out.println("Key Location:");
		System.out.println(keyy1);
		System.out.println(keyy2);
		System.out.println(keyx1);
		System.out.println(keyx2);
		System.out.println("p Hint:");
		System.out.println(keyHintY1);
		System.out.println(keyHintY2);
		System.out.println(keyHintX1);
		System.out.println(keyHintX2);
		System.out.println("Pit Location:");
		System.out.println(pity1);
		System.out.println(pity2);
		System.out.println(pitx1);
		System.out.println(pitx2);
		System.out.println("Pit Hint:");
		System.out.println(pitHintY1);
		System.out.println(pitHintY2);
		System.out.println(pitHintX1);
		System.out.println(pitHintX2);
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
		if(pitShowHint==true){
			pit.draw(540, 165);
		}
		//wumpus green hint
		if(hint==true){
			wumpusHint.draw(540,25);
		}
		//puts the key on the screen
		if(keyShow==true){
			key.draw(530,110);
			keyGlowShow=false;
		}
		//glow for the key hint
		if(keyGlowShow==true){
			keyGlow.draw(495,90);
		}
		//end of the game death screen - pit
		if(pitDeadShow==true){
			pitDeath.draw(0,0);
		}
		//end of the game death screen - wumpus
		if(wumpusDeathShow==true){
			wumpusDeath.draw(0,0);
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
			
			//trees
			if((guyPositionY>-442 && guyPositionY<-299) && (guyPositionX>-126 && guyPositionX<-1)){
				guyPositionY -= delta *.1f;
			}
			if((guyPositionY>-152 && guyPositionY<-9) && (guyPositionX>-316 && guyPositionX<-176)){
				guyPositionY -= delta *.1f;
			}
			if((guyPositionY>-597 && guyPositionY<-449) && (guyPositionX>-384 && guyPositionX<-237)){
				guyPositionY -= delta *.1f;
			}
			if((guyPositionY>-172 && guyPositionY<-27) && (guyPositionX>-741 && guyPositionX<-589)){
				guyPositionY -= delta *.1f;
			}
			if((guyPositionY>-95) && (guyPositionX>-988 && guyPositionX<-853)){
				guyPositionY -= delta *.1f;
			}
			if((guyPositionY>-579 && guyPositionY<-445) && (guyPositionX>-492 && guyPositionX<-656)){
				guyPositionY -= delta *.1f;
			}
			if((guyPositionY>-388 && guyPositionY<-249) && (guyPositionX>-957 && guyPositionX<-822)){
				guyPositionY -= delta *.1f;
			}
			if((guyPositionY>-588 && guyPositionY<-447) && (guyPositionX>-794 && guyPositionX<-653)){
				guyPositionY -= delta *.1f;
			}
			//market
			if((guyPositionY>-360 && guyPositionY<-190) && (guyPositionX>-622 && guyPositionX<-388)){
				guyPositionY -= delta *.1f;
				market = true;
			}
			else
				market = false;
			//wump
			if((guyPositionY>y2 && guyPositionY<y1) && (guyPositionX>x2 && guyPositionX<x1)){
				wumpusDeathShow = true;				
				guyPositionY -= delta *.1f;
			}
			if((guyPositionY>wumpusHintY2 && guyPositionY<wumpusHintY1) && (guyPositionX>wumpusHintX2 && guyPositionX<wumpusHintX1)){
				hint = true;				
			}
			else
				hint = false;
			//key
			if((guyPositionY>keyy2 && guyPositionY<keyy1) && (guyPositionX>keyx2 && guyPositionX<keyx1)){
				keyShow = true;				
				//guyPositionY -= delta *.1f;
			}
			if((guyPositionY>keyHintY2 && guyPositionY<keyHintY1) && (guyPositionX>keyHintX2 && guyPositionX<keyHintX1)){
				keyGlowShow = true;				
			}
			else
				keyGlowShow = false;
			//pit
			if((guyPositionY>pity2 && guyPositionY<pity1) && (guyPositionX>pitx2 && guyPositionX<pitx1)){
				pitDeadShow = true;				
			}
			if((guyPositionY>pitHintY2 && guyPositionY<pitHintY1) && (guyPositionX>pitHintX2 && guyPositionX<pitHintX1)){
				pitShowHint = true;				
			}
			else
				pitShowHint = false;
		}
		
		//down
		if(input.isKeyDown(Input.KEY_DOWN)){
			guy = movingDown;
			guyPositionY -= delta *.1f;
			if(guyPositionY<-850){
				guyPositionY += delta *.1f; 
			}
			
			//trees
			if((guyPositionY>-442 && guyPositionY<-299) && (guyPositionX>-126 && guyPositionX<-1)){
				guyPositionY += delta *.1f;
			}
			if((guyPositionY>-152 && guyPositionY<-9) && (guyPositionX>-316 && guyPositionX<-176)){
				guyPositionY += delta *.1f;
			}
			if((guyPositionY>-597 && guyPositionY<-449) && (guyPositionX>-384 && guyPositionX<-237)){
				guyPositionY += delta *.1f;
			}
			if((guyPositionY>-172 && guyPositionY<-27) && (guyPositionX>-741 && guyPositionX<-589)){
				guyPositionY += delta *.1f;
			}
			if((guyPositionY>-95) && (guyPositionX>-988 && guyPositionX<-853)){
				guyPositionY += delta *.1f;
			}
			if((guyPositionY>-579 && guyPositionY<-445) && (guyPositionX>-492 && guyPositionX<-656)){
				guyPositionY += delta *.1f;
			}	
			if((guyPositionY>-388 && guyPositionY<-249) && (guyPositionX>-957 && guyPositionX<-822)){
				guyPositionY += delta *.1f;
			}
			if((guyPositionY>-588 && guyPositionY<-447) && (guyPositionX>-794 && guyPositionX<-653)){
				guyPositionY += delta *.1f;
			}
			//market
			if((guyPositionY>-360 && guyPositionY<-190) && (guyPositionX>-622 && guyPositionX<-388)){
				guyPositionY += delta *.1f;
				market = true;
			}
			else
				market = false;
			//wump
			if((guyPositionY>y2 && guyPositionY<y1) && (guyPositionX>x2 && guyPositionX<x1)){
				wumpusDeathShow = true;
				guyPositionY += delta *.1f;
			}
			if((guyPositionY>wumpusHintY2 && guyPositionY<wumpusHintY1) && (guyPositionX>wumpusHintX2 && guyPositionX<wumpusHintX1)){
				hint = true;				
			}
			else
				hint = false;
			//key
			if((guyPositionY>keyy2 && guyPositionY<keyy1) && (guyPositionX>keyx2 && guyPositionX<keyx1)){
				keyShow = true;				
				//guyPositionY += delta *.1f;
			}
			if((guyPositionY>keyHintY2 && guyPositionY<keyHintY1) && (guyPositionX>keyHintX2 && guyPositionX<keyHintX1)){
				keyGlowShow = true;				
			}
			else
				keyGlowShow = false;
			//pit
			if((guyPositionY>pity2 && guyPositionY<pity1) && (guyPositionX>pitx2 && guyPositionX<pitx1)){
				pitDeadShow = true;				
			}
			if((guyPositionY>pitHintY2 && guyPositionY<pitHintY1) && (guyPositionX>pitHintX2 && guyPositionX<pitHintX1)){
				pitShowHint = true;				
			}
			else
				pitShowHint = false;
		}
		
		//left
		if(input.isKeyDown(Input.KEY_LEFT)){
			guy = movingLeft;
			guyPositionX += delta *.1f;
			if(guyPositionX>-1.4){
				guyPositionX -= delta *.1f;
			}
			
			//trees
			if((guyPositionY>-442 && guyPositionY<-299) && (guyPositionX>-126 && guyPositionX<-1)){
				guyPositionX -= delta *.1f;
			}
			if((guyPositionY>-152 && guyPositionY<-9) && (guyPositionX>-316 && guyPositionX<-176)){
				guyPositionX -= delta *.1f;
			}
			if((guyPositionY>-597 && guyPositionY<-449) && (guyPositionX>-384 && guyPositionX<-237)){
				guyPositionX -= delta *.1f;
			}
			if((guyPositionY>-172 && guyPositionY<-27) && (guyPositionX>-741 && guyPositionX<-589)){
				guyPositionX -= delta *.1f;
			}
			if((guyPositionY>-95) && (guyPositionX>-988 && guyPositionX<-853)){
				guyPositionX -= delta *.1f;
			}
			if((guyPositionY>-579 && guyPositionY<-445) && (guyPositionX>-492 && guyPositionX<-656)){
				guyPositionX -= delta *.1f;
			}
			if((guyPositionY>-388 && guyPositionY<-249) && (guyPositionX>-957 && guyPositionX<-822)){
				guyPositionX -= delta *.1f;
			}
			if((guyPositionY>-588 && guyPositionY<-447) && (guyPositionX>-794 && guyPositionX<-653)){
				guyPositionX -= delta *.1f;
			}
			//market
			if((guyPositionY>-360 && guyPositionY<-190) && (guyPositionX>-622 && guyPositionX<-388)){
				guyPositionX -= delta *.1f;
				market = true;
			}
			else
				market = false;
			//wump
			if((guyPositionY>y2 && guyPositionY<y1) && (guyPositionX>x2 && guyPositionX<x1)){
				wumpusDeathShow = true;
				guyPositionX -= delta *.1f;
			}
			if((guyPositionY>wumpusHintY2 && guyPositionY<wumpusHintY1) && (guyPositionX>wumpusHintX2 && guyPositionX<wumpusHintX1)){
				hint = true;				
			}
			else
				hint = false;
			//key
			if((guyPositionY>keyy2 && guyPositionY<keyy1) && (guyPositionX>keyx2 && guyPositionX<keyx1)){
				keyShow = true;				
				//guyPositionX -= delta *.1f;
			}
			if((guyPositionY>keyHintY2 && guyPositionY<keyHintY1) && (guyPositionX>keyHintX2 && guyPositionX<keyHintX1)){
				keyGlowShow = true;				
			}
			else
				keyGlowShow = false;
			//pit
			if((guyPositionY>pity2 && guyPositionY<pity1) && (guyPositionX>pitx2 && guyPositionX<pitx1)){
				pitDeadShow = true;				
			}
			if((guyPositionY>pitHintY2 && guyPositionY<pitHintY1) && (guyPositionX>pitHintX2 && guyPositionX<pitHintX1)){
				pitShowHint = true;				
			}
			else
				pitShowHint = false;
		}
		
		//right
		if(input.isKeyDown(Input.KEY_RIGHT)){
			guy = movingRight;
			guyPositionX -= delta *.1f;
			if(guyPositionX<-1050){
				guyPositionX += delta *.1f;
			}
			
			//trees
			if((guyPositionY>-442 && guyPositionY<-299) && (guyPositionX>-126 && guyPositionX<-1)){
				guyPositionX += delta *.1f;
			}
			if((guyPositionY>-152 && guyPositionY<-9) && (guyPositionX>-316 && guyPositionX<-176)){
				guyPositionX += delta *.1f;
			}
			if((guyPositionY>-597 && guyPositionY<-449) && (guyPositionX>-384 && guyPositionX<-237)){
				guyPositionX += delta *.1f;
			}
			if((guyPositionY>-172 && guyPositionY<-27) && (guyPositionX>-741 && guyPositionX<-589)){
				guyPositionX += delta *.1f;
			}
			if((guyPositionY>-95) && (guyPositionX>-988 && guyPositionX<-853)){
				guyPositionX += delta *.1f;
			}
			if((guyPositionY>-579 && guyPositionY<-445) && (guyPositionX>-492 && guyPositionX<-656)){
				guyPositionX += delta *.1f;
			}
			if((guyPositionY>-388 && guyPositionY<-249) && (guyPositionX>-957 && guyPositionX<-822)){
				guyPositionX += delta *.1f;
			}
			if((guyPositionY>-588 && guyPositionY<-447) && (guyPositionX>-794 && guyPositionX<-653)){
				guyPositionX += delta *.1f;
			}
			//market
			if((guyPositionY>-360 && guyPositionY<-190) && (guyPositionX>-622 && guyPositionX<-388)){
				guyPositionX += delta *.1f;
				market = true;
			}
			else 
				market = false;
			//wump
			if((guyPositionY>y2 && guyPositionY<y1) && (guyPositionX>x2 && guyPositionX<x1)){
				wumpusDeathShow = true;
				guyPositionX += delta *.1f;
			}
			if((guyPositionY>wumpusHintY2 && guyPositionY<wumpusHintY1) && (guyPositionX>wumpusHintX2 && guyPositionX<wumpusHintX1)){
				hint = true;				
			}
			else
				hint = false;
			//key
			if((guyPositionY>keyy2 && guyPositionY<keyy1) && (guyPositionX>keyx2 && guyPositionX<keyx1)){
				keyShow = true;				
				//guyPositionX += delta *.1f;
			}
			if((guyPositionY>keyHintY2 && guyPositionY<keyHintY1) && (guyPositionX>keyHintX2 && guyPositionX<keyHintX1)){
				keyGlowShow = true;				
			}
			else
				keyGlowShow = false;
			//pit
			if((guyPositionY>pity2 && guyPositionY<pity1) && (guyPositionX>pitx2 && guyPositionX<pitx1)){
				pitDeadShow = true;				
			}
			if((guyPositionY>pitHintY2 && guyPositionY<pitHintY1) && (guyPositionX>pitHintX2 && guyPositionX<pitHintX1)){
				pitShowHint = true;				
			}
			else
				pitShowHint = false;
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
				sbg.enterState(2);
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
			if(input.isKeyDown(Input.KEY_N)){
				sbg.enterState(2);
			}
		}
		
		//when game is over  
		if(wumpusDeathShow==true || pitDeadShow==true){
			if(input.isKeyDown(Input.KEY_Q)){
				System.exit(0);
			}
		}
	}
	
	public int getID(){
		return 1;
	}
}
