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
	Image dialog;
	//*******************
	
	//BOOLEANS***********
	boolean wumpusDeathShow = false;
	boolean market = false;
	boolean quit = false;
	boolean hint = false;
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
	int x1 = -1034 + (int) (Math.random() * ((-74 - (-1032)) + 1));
	int y1 = -848 + (int) (Math.random() * ((-4 - (-848)) +1));
	int x2=0;
	int y2=0;
	int treeCheck=0;
	int wumpusHintY1;
	int wumpusHintY2;
	int wumpusHintX1;
	int wumpusHintX2;
	//***********************
	
	public Play(int state){
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		worldMap = new Image("res/world.png");
		espBG = new Image("res/espBG.png");
		marketBG = new Image("res/marketBG.png");
		monster = new Image("res/monster.png");
		wumpusDeath = new Image("res/wumpusDeath.jpg");
		dialog = new Image("res/dialog.png");
		
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
		
		generateWumpus();
		
		//wumpus location printed to console
		System.out.println(y1);
		System.out.println(y2);
		System.out.println(x1);
		System.out.println(x2);
		System.out.println();
		System.out.println(wumpusHintY1);
		System.out.println(wumpusHintY2);
		System.out.println(wumpusHintX1);
		System.out.println(wumpusHintX2);
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
		
		if(hint==true){
			dialog.draw(100,300);
			g.drawString("You smell something funny...", 205, 315);
		}
		
		if(wumpusDeathShow==true){
			g.clear();
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
		if(wumpusDeathShow==true){
			if(input.isKeyDown(Input.KEY_Q)){
				sbg.enterState(0);
			}
		}
	}
	
	public void generateWumpus(){
		while(treeCheck==0){
			//random placement of objects logic
			if(x1<-1000){
				x2 = x1+25; 
			}
			if(x1>=-1000){
				x2 = x1-25;
			}
			if(y1<-800){
				y2 = y1+25; 
			}
			if(y1>=-800){
				y2 = y1-25;
			}
			treeCheck=1;
			if((y1>-467 && y1<-274) && (x1>-151 && x1<-1)){
				treeCheck=0;
			}
			if((y1>-152 && y1<-9) && (x1>-341 && x1<-151)){
				treeCheck=0;
			}
			if((y1>-622 && y1<-424) && (x1>-409 && x1<-212)){
				treeCheck=0;
			}
			if((y1>-195 && y1<-2) && (x1>-766 && x1<-564)){
				treeCheck=0;
			}
			if((y1>-120) && (x1>-1013 && x1<-828)){
				treeCheck=0;
			}
			if((y1>-604 && y1<-420) && (x1>-517 && x1<-631)){
				treeCheck=0;
			}
			if((y1>-413 && y1<-224) && (x1>-982 && x1<-797)){
				treeCheck=0;
			}
			if((y1>-613 && y1<-422) && (x1>-819 && x1<-628)){
				treeCheck=0;
			}
			if((y1>-385 && y1<-165) && (x1>-647 && x1<-363)){
				treeCheck=0;
			}
			
			if(y1<y2){
				int temp; 
				temp=y1;
				y1=y2;
				y2=temp;
			}
			if(x1<x2){
				int temp; 
				temp=x1;
				x1=x2;
				x2=temp;
			}
		}
		
		if (y1>y2){
			wumpusHintY1 = y1+100;
			wumpusHintY2 = y2-100;
		}
		else{
			wumpusHintY2 = y2+100;
			wumpusHintY1 = y1-100;
		}
		if (x1>x2){
			wumpusHintX1 = x1+100;
			wumpusHintX2 = x2-100;
		}
		else{
			wumpusHintX2 = x2+100;
			wumpusHintX1 = x1-100;
		}
	}
	
	public int getID(){
		return 1;
	}
}
