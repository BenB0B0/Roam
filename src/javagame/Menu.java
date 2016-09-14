package javagame;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Menu extends BasicGameState{
	
	Image playNow;
	Image exitGame;
	Image menuBG;
	Image intro;
	
	public String mouse = "N/A";
	
	public Menu(int state){
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		playNow = new Image("res/playNow.png");
		exitGame = new Image("res/exitGame.png");
		menuBG = new Image("res/menuBG.png");
		intro = new Image("res/intro.png");
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		menuBG.draw(0,0);
		intro.draw(150,10);
		playNow.draw(100,150);
		exitGame.draw(330,150);
		
		g.drawString(mouse, 50, 50);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		int posX = Mouse.getX();
		int posY = Mouse.getY();
		mouse = "X: "+posX+" Y: "+posY;
		
		if((posX>102 && posX<302) && (posY<201 && posY>169)){ //playNow
			if(Mouse.isButtonDown(0)){
				sbg.enterState(1);
			}
		}
		if((posX>333 && posX<533) && (posY<201 && posY>169)){ //exitGame
			if(Mouse.isButtonDown(0)){
				System.exit(0);
			}
		}
	}
	
	public int getID(){
		return 0;
	}
}
