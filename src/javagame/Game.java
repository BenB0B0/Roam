package javagame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Game extends StateBasedGame{

	public static final String gamename = "Roam";
	public static final int menu = 0;
	public static final int play = 1;
	public static final int ninja = 2;
	public static final int secondstage = 3;
	public static final int takeoff = 4;
	public static final int cloudtakeoff = 5;
	public static final int secondStage1 = 6;
	public static final int secondStage2 = 7;
	public static final int secondStage3 = 8;
	public static final int secondStage4 = 9;
	public static final int marioWorld = 10;
	
	public Game(String gamename){
		super(gamename);
		this.addState(new Menu(menu));
		this.addState(new Play(play));
		this.addState(new Ninja(ninja));
		this.addState(new secondStage(secondstage));
		this.addState(new takeOff(takeoff));
		this.addState(new cloudtakeOff(cloudtakeoff));
		this.addState(new secondStage1(secondStage1));
		this.addState(new secondStage2(secondStage2));
		this.addState(new secondStage3(secondStage3));
		this.addState(new secondStage4(secondStage4));
		this.addState(new marioWorld(marioWorld));
	}
	
	public void initStatesList(GameContainer gc) throws SlickException{
		this.getState(menu).init(gc, this);
		this.getState(play).init(gc, this);
		this.getState(ninja).init(gc, this);
		this.getState(secondstage).init(gc, this);
		this.getState(takeoff).init(gc, this);
		this.getState(cloudtakeoff).init(gc, this);
		this.getState(secondStage1).init(gc, this);
		this.getState(secondStage2).init(gc, this);
		this.getState(secondStage3).init(gc, this);
		this.getState(secondStage4).init(gc, this);
		this.getState(marioWorld).init(gc, this);
		this.enterState(menu);
	}
	
	public static void main(String[] args) {
		AppGameContainer appgc;
		try{
			appgc = new AppGameContainer(new Game(gamename));
			appgc.setDisplayMode(640, 360, false);
			appgc.start();
		}catch(SlickException e){
			e.printStackTrace();
		}
	}

}
