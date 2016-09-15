package javagame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.Sound;

import javagame.Game;
 
public class Play extends BasicGameState{

	Animation guy, movingUp, movingDown, movingLeft, movingRight;
	
	//PICTURES***********
	Image worldMap, espBG, marketBG, monster, wumpusDeath, wumpusHint, key, keyGlow, pit, pitDeath, dialogPit, 
		  dialogWumpus, dialogShuttle, interactBack;
	//*******************
	
	private Music BGmusic; 
	private Sound keyNoise;
	private Sound wumpusNoise;
	private Sound pitNoise;
	private Sound shipNoise;
	
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
	boolean dialogPitShow = false;
	boolean dialogWumpusShow = false;
	boolean marketShow = false;
	boolean fly = false;
	boolean pit4Accept = false;
	int notTwice=1;
	double speedBoost=0;
	boolean guyExist = true; 
	boolean mute = false;
	int keyNoisePlay = 1;
	int pitNoisePlay = 1;
	int wumpusNoisePlay = 1;
	int wizardVisit = 0;
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
		dialogPit = new Image("res/dialogPit.png");
		dialogWumpus = new Image("res/dialogWumpus.png");
		dialogShuttle = new Image("res/dialogShuttle.png");
		interactBack = new Image("res/interactBack.png");
		
		BGmusic = new Music("res/backGroundMusic.ogg");
		BGmusic.loop();
		keyNoise = new Sound("res/key.ogg");
		pitNoise = new Sound("res/pitSound.ogg");
		wumpusNoise = new Sound("res/wumpusSound.ogg");
		shipNoise = new Sound("res/shipSound.ogg");
		
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
		if(guyExist==true){
			guy.draw(shiftX,shiftY);
			g.drawString("X: "+guyPositionX+"\nY: "+guyPositionY, 400, 20);
		}
		else{
			guy.draw(1000,1000);
			dialogShuttle.draw(200,50);
		}
		
		if(marketShow==true){
			if(market==false){
				interactBack.draw(260,305);
				g.setColor(Color.red);
				g.drawString("Interact (I)", 260, 307);
			}
		}
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
		
		if(market==true){
			marketBG.draw(175,30);
			g.drawString("Why Hello... Can I help you?", 207, 38);
			g.drawString("Ability to Fly (F)", 207, 75);
			g.drawString("Increase your Speed (S)", 207, 105);
			g.drawString("Resume (R)", 207, 135);
			if(market==false){
				g.clear();
			}
			if(dialogWumpusShow==true){
				dialogWumpus.draw(70, 200);
			}
				
			if(dialogPitShow==true){
				dialogPit.draw(335,200);
			}
		}
		
		if(pitShowHint==true){
			pit.draw(540, 178);
		}
		if(pitShowHint2==true){
			pit.draw(540, 178);
		}
		if(pitShowHint3==true){
			pit.draw(540, 178);
		}
		if(pitShowHint4==true){
			pit.draw(540, 178);
		}
		//wumpus green hint
		if(hint==true){
			wumpusHint.draw(540,25);
		}
		//puts the key on the screen
		if(keyShow==true){
			key.draw(530,110);
			if(keyNoisePlay == 1){
				keyNoise.play();
				keyNoisePlay++;
			}
			keyGlowShow=false;
		}
		//glow for the key hint
		if(keyGlowShow==true){
			keyGlow.draw(495,90);
		}
		//end of the game death screen - pit
		if(pitDeadShow==true){
			if(pitNoisePlay == 1){
				pitNoise.play();
				pitNoisePlay++;
			}
			pitDeath.draw(0,0);
		}
		//end of the game death screen - wumpus
		if(wumpusDeathShow==true){
			if(wumpusNoisePlay == 1){
				wumpusNoise.play();
				wumpusNoisePlay++;
			}
			wumpusDeath.draw(0,0);
		}
		
		if(mute==true){
			BGmusic.stop();
		}
		g.drawString("Restart The Game (P)", 220, 335);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		Input input = gc.getInput();

		//Resets Game
        if(input.isKeyPressed( Input.KEY_P ))
        {
            sbg.getState( Game.play ).init(gc, sbg);
            sbg.enterState( Game.play );
            guyPositionX = 0;
        	guyPositionY = 0;
        	wumpusDeathShow = false;
        	market = false;
        	quit = false;
        	hint = false;
        	keyShow = false;
        	keyGlowShow = false;
        	pitShowHint = false;
        	pitShowHint2 = false;
        	pitShowHint3 = false;
        	pitShowHint4 = false;
        	pitDeadShow = false;
        	dialogPitShow = false;
        	dialogWumpusShow = false;
        	marketShow = false;
        	fly = false;
        	pit4Accept = false;
        	notTwice=1;
        	speedBoost=0;
        	guyExist = true; 
        	pitNoisePlay = 1; 
        	keyNoisePlay = 1;
        	wumpusNoisePlay = 1;
        	wizardVisit = 0;
        }		
        
		if(guyExist==true){
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
				}
				if((guyPositionY<-155 && guyPositionY>-215) && (guyPositionX<-1008)){
					if(keyShow==true){
						guyExist = false;  
					}
					else
						guyPositionY -= delta *.1f + speedBoost;
				}
				//market
				if((guyPositionY>-330 && guyPositionY<-223) && (guyPositionX>-485 && guyPositionX<-417)){
					guyPositionY -= delta *.1f + speedBoost;
					wizardVisit++;
					marketShow = true;
				}
				else
					marketShow = false;
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
				if(pit4Accept==true){
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
				}
				if((guyPositionY<-155 && guyPositionY>-215) && (guyPositionX<-1008)){
					if(keyShow==true){
						guyExist = false;
					}
					else
						guyPositionY += delta *.1f + speedBoost;
				}
				//market
				if((guyPositionY>-330 && guyPositionY<-223) && (guyPositionX>-485 && guyPositionX<-417)){
					guyPositionY += delta *.1f + speedBoost;
					marketShow = true;
				}
				else
					marketShow = false;
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
				if(pit4Accept==true){
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
				}
				if((guyPositionY<-155 && guyPositionY>-215) && (guyPositionX<-1008)){
					if(keyShow==true){
						guyExist = false;
					}
					else
						guyPositionX -= delta *.1f + speedBoost;
				}
				//market
				if((guyPositionY>-330 && guyPositionY<-223) && (guyPositionX>-485 && guyPositionX<-417)){
					guyPositionX -= delta *.1f + speedBoost;
					marketShow = true;
				}
				else
					marketShow = false;
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
				if(pit4Accept==true){
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
				}
				if((guyPositionY<-155 && guyPositionY>-215) && (guyPositionX<-1008)){
					if(keyShow==true){
						guyExist = false;
					}
					else
						guyPositionX += delta *.1f + speedBoost;
				}
				//market
				if((guyPositionY>-330 && guyPositionY<-223) && (guyPositionX>-485 && guyPositionX<-417)){
					guyPositionX += delta *.1f + speedBoost;
					marketShow = true;
				}
				else 
					marketShow = false;
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
				if(pit4Accept==true){
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
		}
		
		//Space Ship Takes Off
		if(input.isKeyDown(Input.KEY_SPACE)){	
			if(guyExist==false){
				shipNoise.play();
				sbg.enterState(4, new FadeOutTransition(Color.white, 1000), new FadeInTransition(Color.white, 1000)); 
			}
		}
		
		if(marketShow==true){
			if(input.isKeyDown(Input.KEY_I)){
				market=true;
			}
		}
		else
			market=false;
		
		//market menu
		if(market==true){
			if(input.isKeyDown(Input.KEY_F)){
				fly = true;
				dialogWumpusShow = true;
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
				dialogPitShow = true;
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
