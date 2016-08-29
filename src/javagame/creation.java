package javagame;

public class creation {
	int x1 = -1034 + (int) (Math.random() * ((-74 - (-1032)) + 1));
	int y1 = -848 + (int) (Math.random() * ((-4 - (-848)) +1));
	int x2;
	int y2;
	int treeCheck;
	int HintY1;
	int HintY2;
	int HintX1;
	int HintX2;
	
	public void generateKey(){
		while(treeCheck==0){
			//random placement of objects logic
			if(x1<-1000){
				x2 = x1+50; 
			}
			if(x1>=-1000){
				x2 = x1-50;
			}
			if(y1<-800){
				y2 = y1+50; 
			}
			if(y1>=-800){
				y2 = y1-50;
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
			HintY1 = y1+100;
			HintY2 = y2-100;
		}
		else{
			HintY2 = y2+100;
			HintY1 = y1-100;
		}
		if (x1>x2){
			HintX1 = x1+100;
			HintX2 = x2-100;
		}
		else{
			HintX2 = x2+100;
			HintX1 = x1-100;
		}
	}
	
	public int getX1(){
		return this.x1;
	}
	public int getX2(){
		return this.x2;
	}
	public int getY1(){
		return this.y1;
	}
	public int getY2(){
		return this.y2;
	}
	public int getHintX1(){
		return this.HintX1;
	}
	public int getHintX2(){
		return this.HintX2;
	}
	public int getHintY1(){
		return this.HintY1;
	}
	public int getHintY2(){
		return this.HintY2;
	}
	public void print(){
		System.out.println("Wumpus Location:");
		System.out.println(y1);
		System.out.println(y2);
		System.out.println(x1);
		System.out.println(x2);
		System.out.println("Wumpus Hint:");
		System.out.println(HintY1);
		System.out.println(HintY2);
		System.out.println(HintX1);
		System.out.println(HintX2);
	}
}
