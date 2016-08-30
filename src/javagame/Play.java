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
	
	//VARIABLES***********
	boolean wumpusDeathShow = false;
	boolean market = false;
	boolean quit = false;
	boolean hint = false;
	boolean keyShow = false;
	boolean keyGlowShow = false;
	boolean pitShowHint = false;
	boolean pitShowHint2 = false;
	boolean pitShowHint3 = false;
	boolean pitShowHint4 = false;
	boolean pitDeadShow = false;
	boolean fly = false;
	boolean pit4Accept = false;
	int notTwice=1;
	double speedBoost=0;
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
	int pit2x1, pit2x2, pit2y1, pit2y2, pit2HintX1, pit2HintX2, pit2HintY1, pit2HintY2;
	int pit3x1, pit3x2, pit3y1, pit3y2, pit3HintX1, pit3HintX2, pit3HintY1, pit3HintY2;
	int pit4x1, pit4x2, pit4y1, pit4y2, pit4HintX1, pit4HintX2, pit4HintY1, pit4HintY2;
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
		creation pit[] = new creation[4];
		wumpus[0] = new creation();
		keyObject[0] = new creation();
		pit[0] = new creation();
		pit[1] = new creation();
		pit[2] = new creation();
		pit[3] = new creation();
		
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
		
		pit[1].generateKey();
		pit2x1 = pit[1].getX1(); pit2x2 = pit[1].getX2(); pit2y1 = pit[1].getY1(); pit2y2 = pit[1].getY2();
		pit2HintY1 = pit[1].getHintY1(); pit2HintY2 = pit[1].getHintY2();
		pit2HintX1 = pit[1].getHintX1(); pit2HintX2 = pit[1].getHintX2();
		
		pit[2].generateKey();
		pit3x1 = pit[2].getX1(); pit3x2 = pit[2].getX2(); pit3y1 = pit[2].getY1(); pit3y2 = pit[2].getY2();
		pit3HintY1 = pit[2].getHintY1(); pit3HintY2 = pit[2].getHintY2();
		pit3HintX1 = pit[2].getHintX1(); pit3HintX2 = pit[2].getHintX2();
		
		pit[3].generateKey();
		pit4x1 = pit[3].getX1(); pit4x2 = pit[3].getX2(); pit4y1 = pit[3].getY1(); pit4y2 = pit[3].getY2();
		pit4HintY1 = pit[3].getHintY1(); pit4HintY2 = pit[3].getHintY2();
		pit4HintX1 = pit[3].getHintX1(); pit4HintX2 = pit[3].getHintX2();
		//**************************************************************************************************
		
		//wumpus location printed to console
		wumpus[0].print();
		keyObject[0].print();
		pit[0].print();
		pit[1].print();
		pit[2].print();
		pit[3].print();
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
			g.drawString("Ability to Fly (F)", 260, 120);
			g.drawString("Increase your Speed (S)", 260, 170);
			g.drawString("Resume (R)", 260, 220);
			if(market==false){
				g.clear();
			}
		}
		if(pitShowHint==true){
			pit.draw(540, 165);
		}
		if(pitShowHint2==true){
			pit.draw(540, 165);
		}
		if(pitShowHint3==true){
			pit.draw(540, 165);
		}
		if(pitShowHint4==true){
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
			guyPositionY += delta *.1f + speedBoost;
			if(guyPositionY>-3.4){
				guyPositionY -= delta *.1f + speedBoost;
			}
			if(fly==false){
				//trees
				if((guyPositionY>-442 && guyPositionY<-299) && (guyPositionX>-126 && guyPositionX<-1)){
					guyPositionY -= delta *.1f + speedBoost;
				}
				if((guyPositionY>-152 && guyPositionY<-9) && (guyPositionX>-316 && guyPositionX<-176)){
					guyPositionY -= delta *.1f + speedBoost;
				}
				if((guyPositionY>-597 && guyPositionY<-449) && (guyPositionX>-384 && guyPositionX<-237)){
					guyPositionY -= delta *.1f + speedBoost;
				}
				if((guyPositionY>-172 && guyPositionY<-27) && (guyPositionX>-741 && guyPositionX<-589)){
					guyPositionY -= delta *.1f + speedBoost;
				}
				if((guyPositionY>-95) && (guyPositionX>-988 && guyPositionX<-853)){
					guyPositionY -= delta *.1f + speedBoost;
				}
				if((guyPositionY>-579 && guyPositionY<-445) && (guyPositionX>-492 && guyPositionX<-656)){
					guyPositionY -= delta *.1f + speedBoost;
				}
				if((guyPositionY>-388 && guyPositionY<-249) && (guyPositionX>-957 && guyPositionX<-822)){
					guyPositionY -= delta *.1f + speedBoost;
				}
				if((guyPositionY>-588 && guyPositionY<-447) && (guyPositionX>-794 && guyPositionX<-653)){
					guyPositionY -= delta *.1f + speedBoost;
				}
				if((guyPositionY<-142 && guyPositionY>-213) && (guyPositionX<-1049)){
					if(keyShow=true){
						sbg.enterState(0);
					}
				}
			}
			//market
			if((guyPositionY>-360 && guyPositionY<-190) && (guyPositionX>-622 && guyPositionX<-388)){
				guyPositionY -= delta *.1f + speedBoost;
				market = true;
			}
			else
				market = false;
			//wump
			if((guyPositionY>y2 && guyPositionY<y1) && (guyPositionX>x2 && guyPositionX<x1)){
				wumpusDeathShow = true;				
				guyPositionY -= delta *.1f + speedBoost;
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
			//pit2
			if((guyPositionY>pit2y2 && guyPositionY<pit2y1) && (guyPositionX>pit2x2 && guyPositionX<pit2x1)){
				pitDeadShow = true;				
			}
			if((guyPositionY>pit2HintY2 && guyPositionY<pit2HintY1) && (guyPositionX>pit2HintX2 && guyPositionX<pit2HintX1)){
				pitShowHint2 = true;				
			}
			else
				pitShowHint2 = false;
			//pit3
			if((guyPositionY>pit3y2 && guyPositionY<pit3y1) && (guyPositionX>pit3x2 && guyPositionX<pit3x1)){
				pitDeadShow = true;				
			}
			if((guyPositionY>pit3HintY2 && guyPositionY<pit3HintY1) && (guyPositionX>pit3HintX2 && guyPositionX<pit3HintX1)){
				pitShowHint3 = true;				
			}
			else
				pitShowHint3 = false;
			//pit4
			if(pit4Accept=true){
				if((guyPositionY>pit4y2 && guyPositionY<pit4y1) && (guyPositionX>pit4x2 && guyPositionX<pit4x1)){
					pitDeadShow = true;				
				}
				if((guyPositionY>pit4HintY2 && guyPositionY<pit4HintY1) && (guyPositionX>pit4HintX2 && guyPositionX<pit4HintX1)){
					pitShowHint4 = true;				
				}
				else
					pitShowHint4 = false;
			}
		}
		
		//down
		if(input.isKeyDown(Input.KEY_DOWN)){
			guy = movingDown;
			guyPositionY -= delta *.1f + speedBoost;
			if(guyPositionY<-850){
				guyPositionY += delta *.1f + speedBoost; 
			}
			
			if(fly==false){
				//trees
				if((guyPositionY>-442 && guyPositionY<-299) && (guyPositionX>-126 && guyPositionX<-1)){
					guyPositionY += delta *.1f + speedBoost;
				}
				if((guyPositionY>-152 && guyPositionY<-9) && (guyPositionX>-316 && guyPositionX<-176)){
					guyPositionY += delta *.1f + speedBoost;
				}
				if((guyPositionY>-597 && guyPositionY<-449) && (guyPositionX>-384 && guyPositionX<-237)){
					guyPositionY += delta *.1f + speedBoost;
				}
				if((guyPositionY>-172 && guyPositionY<-27) && (guyPositionX>-741 && guyPositionX<-589)){
					guyPositionY += delta *.1f + speedBoost;
				}
				if((guyPositionY>-95) && (guyPositionX>-988 && guyPositionX<-853)){
					guyPositionY += delta *.1f + speedBoost;
				}
				if((guyPositionY>-579 && guyPositionY<-445) && (guyPositionX>-492 && guyPositionX<-656)){
					guyPositionY += delta *.1f + speedBoost;
				}	
				if((guyPositionY>-388 && guyPositionY<-249) && (guyPositionX>-957 && guyPositionX<-822)){
					guyPositionY += delta *.1f + speedBoost;
				}
				if((guyPositionY>-588 && guyPositionY<-447) && (guyPositionX>-794 && guyPositionX<-653)){
					guyPositionY += delta *.1f + speedBoost;
				}
				if((guyPositionY<-142 && guyPositionY>-213) && (guyPositionX<-1049)){
					if(keyShow=true){
						sbg.enterState(0);
					}
				}
			}
			//market
			if((guyPositionY>-360 && guyPositionY<-190) && (guyPositionX>-622 && guyPositionX<-388)){
				guyPositionY += delta *.1f + speedBoost;
				market = true;
			}
			else
				market = false;
			//wump
			if((guyPositionY>y2 && guyPositionY<y1) && (guyPositionX>x2 && guyPositionX<x1)){
				wumpusDeathShow = true;
				guyPositionY += delta *.1f + speedBoost;
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
			//pit2
			if((guyPositionY>pit2y2 && guyPositionY<pit2y1) && (guyPositionX>pit2x2 && guyPositionX<pit2x1)){
				pitDeadShow = true;				
			}
			if((guyPositionY>pit2HintY2 && guyPositionY<pit2HintY1) && (guyPositionX>pit2HintX2 && guyPositionX<pit2HintX1)){
				pitShowHint2 = true;				
			}
			else
				pitShowHint2 = false;
			//pit3
			if((guyPositionY>pit3y2 && guyPositionY<pit3y1) && (guyPositionX>pit3x2 && guyPositionX<pit3x1)){
				pitDeadShow = true;				
			}
			if((guyPositionY>pit3HintY2 && guyPositionY<pit3HintY1) && (guyPositionX>pit3HintX2 && guyPositionX<pit3HintX1)){
				pitShowHint3 = true;				
			}
			else
				pitShowHint3 = false;
			//pit4
			if(pit4Accept=true){
				if((guyPositionY>pit4y2 && guyPositionY<pit4y1) && (guyPositionX>pit4x2 && guyPositionX<pit4x1)){
					pitDeadShow = true;				
				}
				if((guyPositionY>pit4HintY2 && guyPositionY<pit4HintY1) && (guyPositionX>pit4HintX2 && guyPositionX<pit4HintX1)){
					pitShowHint4 = true;				
				}
				else
					pitShowHint4 = false;
			}
		}
		
		//left
		if(input.isKeyDown(Input.KEY_LEFT)){
			guy = movingLeft;
			guyPositionX += delta *.1f + speedBoost;
			if(guyPositionX>-1.4){
				guyPositionX -= delta *.1f + speedBoost;
			}
			
			if(fly==false){
				//trees
				if((guyPositionY>-442 && guyPositionY<-299) && (guyPositionX>-126 && guyPositionX<-1)){
					guyPositionX -= delta *.1f + speedBoost;
				}
				if((guyPositionY>-152 && guyPositionY<-9) && (guyPositionX>-316 && guyPositionX<-176)){
					guyPositionX -= delta *.1f + speedBoost;
				}
				if((guyPositionY>-597 && guyPositionY<-449) && (guyPositionX>-384 && guyPositionX<-237)){
					guyPositionX -= delta *.1f + speedBoost;
				}
				if((guyPositionY>-172 && guyPositionY<-27) && (guyPositionX>-741 && guyPositionX<-589)){
					guyPositionX -= delta *.1f + speedBoost;
				}
				if((guyPositionY>-95) && (guyPositionX>-988 && guyPositionX<-853)){
					guyPositionX -= delta *.1f + speedBoost;
				}
				if((guyPositionY>-579 && guyPositionY<-445) && (guyPositionX>-492 && guyPositionX<-656)){
					guyPositionX -= delta *.1f + speedBoost;
				}
				if((guyPositionY>-388 && guyPositionY<-249) && (guyPositionX>-957 && guyPositionX<-822)){
					guyPositionX -= delta *.1f + speedBoost;
				}
				if((guyPositionY>-588 && guyPositionY<-447) && (guyPositionX>-794 && guyPositionX<-653)){
					guyPositionX -= delta *.1f + speedBoost;
				}
				if((guyPositionY<-142 && guyPositionY>-213) && (guyPositionX<-1049)){
					if(keyShow=true){
						sbg.enterState(0);
					}
				}
			}
			//market
			if((guyPositionY>-360 && guyPositionY<-190) && (guyPositionX>-622 && guyPositionX<-388)){
				guyPositionX -= delta *.1f + speedBoost;
				market = true;
			}
			else
				market = false;
			//wump
			if((guyPositionY>y2 && guyPositionY<y1) && (guyPositionX>x2 && guyPositionX<x1)){
				wumpusDeathShow = true;
				guyPositionX -= delta *.1f + speedBoost;
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
			//pit2
			if((guyPositionY>pit2y2 && guyPositionY<pit2y1) && (guyPositionX>pit2x2 && guyPositionX<pit2x1)){
				pitDeadShow = true;				
			}
			if((guyPositionY>pit2HintY2 && guyPositionY<pit2HintY1) && (guyPositionX>pit2HintX2 && guyPositionX<pit2HintX1)){
				pitShowHint2 = true;				
			}
			else
				pitShowHint2 = false;
			//pit3
			if((guyPositionY>pit3y2 && guyPositionY<pit3y1) && (guyPositionX>pit3x2 && guyPositionX<pit3x1)){
				pitDeadShow = true;				
			}
			if((guyPositionY>pit3HintY2 && guyPositionY<pit3HintY1) && (guyPositionX>pit3HintX2 && guyPositionX<pit3HintX1)){
				pitShowHint3 = true;				
			}
			else
				pitShowHint3 = false;
			//pit4
			if(pit4Accept=true){
				if((guyPositionY>pit4y2 && guyPositionY<pit4y1) && (guyPositionX>pit4x2 && guyPositionX<pit4x1)){
					pitDeadShow = true;				
				}
				if((guyPositionY>pit4HintY2 && guyPositionY<pit4HintY1) && (guyPositionX>pit4HintX2 && guyPositionX<pit4HintX1)){
					pitShowHint4 = true;				
				}
				else
					pitShowHint4 = false;
			}
		}
		
		//right
		if(input.isKeyDown(Input.KEY_RIGHT)){
			guy = movingRight;
			guyPositionX -= delta *.1f + speedBoost;
			if(guyPositionX<-1050){
				guyPositionX += delta *.1f + speedBoost;
			}
			
			if(fly==false){
				//trees
				if((guyPositionY>-442 && guyPositionY<-299) && (guyPositionX>-126 && guyPositionX<-1)){
					guyPositionX += delta *.1f + speedBoost;
				}
				if((guyPositionY>-152 && guyPositionY<-9) && (guyPositionX>-316 && guyPositionX<-176)){
					guyPositionX += delta *.1f + speedBoost;
				}
				if((guyPositionY>-597 && guyPositionY<-449) && (guyPositionX>-384 && guyPositionX<-237)){
					guyPositionX += delta *.1f + speedBoost;
				}
				if((guyPositionY>-172 && guyPositionY<-27) && (guyPositionX>-741 && guyPositionX<-589)){
					guyPositionX += delta *.1f + speedBoost;
				}
				if((guyPositionY>-95) && (guyPositionX>-988 && guyPositionX<-853)){
					guyPositionX += delta *.1f + speedBoost;
				}
				if((guyPositionY>-579 && guyPositionY<-445) && (guyPositionX>-492 && guyPositionX<-656)){
					guyPositionX += delta *.1f + speedBoost;
				}
				if((guyPositionY>-388 && guyPositionY<-249) && (guyPositionX>-957 && guyPositionX<-822)){
					guyPositionX += delta *.1f + speedBoost;
				}
				if((guyPositionY>-588 && guyPositionY<-447) && (guyPositionX>-794 && guyPositionX<-653)){
					guyPositionX += delta *.1f + speedBoost;
				}
				if((guyPositionY<-142 && guyPositionY>-213) && (guyPositionX<-1049)){
					if(keyShow=true){
						sbg.enterState(0);
					}
				}
			}
			//market
			if((guyPositionY>-360 && guyPositionY<-190) && (guyPositionX>-622 && guyPositionX<-388)){
				guyPositionX += delta *.1f + speedBoost;
				market = true;
			}
			else 
				market = false;
			//wump
			if((guyPositionY>y2 && guyPositionY<y1) && (guyPositionX>x2 && guyPositionX<x1)){
				wumpusDeathShow = true;
				guyPositionX += delta *.1f + speedBoost;
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
			//pit2
			if((guyPositionY>pit2y2 && guyPositionY<pit2y1) && (guyPositionX>pit2x2 && guyPositionX<pit2x1)){
				pitDeadShow = true;				
			}
			if((guyPositionY>pit2HintY2 && guyPositionY<pit2HintY1) && (guyPositionX>pit2HintX2 && guyPositionX<pit2HintX1)){
				pitShowHint2 = true;				
			}
			else
				pitShowHint2 = false;
			//pit3
			if((guyPositionY>pit3y2 && guyPositionY<pit3y1) && (guyPositionX>pit3x2 && guyPositionX<pit3x1)){
				pitDeadShow = true;				
			}
			if((guyPositionY>pit3HintY2 && guyPositionY<pit3HintY1) && (guyPositionX>pit3HintX2 && guyPositionX<pit3HintX1)){
				pitShowHint3 = true;				
			}
			else
				pitShowHint3 = false;
			//pit4
			if(pit4Accept=true){
				if((guyPositionY>pit4y2 && guyPositionY<pit4y1) && (guyPositionX>pit4x2 && guyPositionX<pit4x1)){
					pitDeadShow = true;				
				}
				if((guyPositionY>pit4HintY2 && guyPositionY<pit4HintY1) && (guyPositionX>pit4HintX2 && guyPositionX<pit4HintX1)){
					pitShowHint4 = true;				
				}
				else
					pitShowHint4 = false;
			}
		}
		
		//market menu
		if(market==true){
			if(input.isKeyDown(Input.KEY_F)){
				fly = true;
				market = false;
				//If the player chooses to fly the wumpus grows in size 
				//notTwice makes it so if they choose fly option again it doens't grow anymore
				if(notTwice==1){
					y1 = y1+100;
					y2 = y2-100;
					x1 = x1+100;
					x2 = x2-100;
					notTwice++;
				}
			}
			if(input.isKeyDown(Input.KEY_S)){
				speedBoost = .2;
				market = false;
				pit4Accept = true;
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
