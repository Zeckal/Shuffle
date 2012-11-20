package com.example.shuffleboard;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Point;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.Toast;

public class GameActivity extends Activity {
	
	private Target[] Pucks;
	private Point PreviousLocation;
	private Target[] Board;
	private Target selected;
	private int currentPuck;
	public static final int height = 750;
	public static final int width = 480;
	public static final int numberofpucks = 8;
	public static final int friction = 10;
	public int currentBoard;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        
        FrameLayout frame = (FrameLayout) findViewById(R.id.graphics_holder);
        
        selected = null;
        currentPuck = 0;
        currentBoard = 0;
        PreviousLocation = new Point();
        Board = new Target[5];
        for(int i = 0; i < 5; i++){
        	Target T = new Target(this);
        	T.setX(0);
        	T.setY(5);
        	Board[i] = T;
        }
        System.out.println("break 1");
        Board[0].setBitmap(R.drawable.board1);
        Board[1].setBitmap(R.drawable.board2);
        Board[2].setBitmap(R.drawable.board3);
        Board[3].setBitmap(R.drawable.board4);
        Board[4].setBitmap(R.drawable.board5);
        System.out.println("break 2");
        Pucks = new Target[numberofpucks];
        for(int i = 0; i < numberofpucks; i++){
        	Target T = new Target(this);
        	
        	if(i%2 == 1){
        		T.setBitmap(R.drawable.blueslider);
        	} else {
        		T.setBitmap(R.drawable.redslider);
        	}
        	
    		T.setX(width/2 - T.getwidth()/2);
    		T.setY((float)(3.0/4.0)*height);
        	
    		Pucks[i] = T;
        }
        
        frame.addView(Board[0]);
        frame.addView(Pucks[0]);
        System.out.println("break 3");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_game, menu);
        return true;
    }
    
    @Override  
	public boolean onTouchEvent(MotionEvent event) { 
		FrameLayout frame = (FrameLayout) findViewById(R.id.graphics_holder);
		float X = event.getX();
		float Y = event.getY() - 48;
		
		if(event.getAction() == android.view.MotionEvent.ACTION_DOWN){
			if(Pucks[currentPuck].isInside(X, Y)){
				selected = Pucks[currentPuck];
				selected.setX(X - selected.getwidth()/2);
				selected.setY(Y - selected.getheight()/2);
			}
			return true;
		} else if (event.getAction() == android.view.MotionEvent.ACTION_UP){
			if(selected != null){
				System.out.println("action up triggered");
				selected.setXvelo((int)(selected.getX() - PreviousLocation.x));
				selected.setYvelo((int)(selected.getY() - PreviousLocation.y));
				RunSlide();
				selected = null;
			}
			return true;
		} else if (event.getAction() == android.view.MotionEvent.ACTION_MOVE){
			if(selected != null){
				System.out.println("action move triggered");
				PreviousLocation.x = (int)selected.getX();
				PreviousLocation.y = (int)selected.getY();
				selected.setX(X - selected.getwidth()/2);
				selected.setY(Y - selected.getheight()/2);
				frame.removeView(selected);
				frame.addView(selected);
				
				System.out.println("PLX: " + PreviousLocation.x + " PLY: " + PreviousLocation.y);
			}
			return true;
		}
		
		return false; 
	}
    
    public void RunSlide(){
		FrameLayout frame = (FrameLayout) findViewById(R.id.graphics_holder);
    	while(selected.getXvelo() != 0 || selected.getYvelo() != 0){
    		frame.removeView(selected);
    		frame.addView(selected);
    		System.out.println("SLIDE LOOP RUNNING VX: " + selected.getXvelo() + " VY: " + selected.getYvelo());
    		
    		selected.setX((float)selected.getX() + selected.getXvelo());
    		selected.setY((float)selected.getY() + selected.getYvelo());
    		
    		if(selected.getXvelo() > 0){
    			selected.setXvelo(selected.getXvelo() - friction);
    			if(selected.getXvelo() < 0){
    				selected.setXvelo(0);
    			}
    		}else if(selected.getXvelo() < 0){
    			selected.setXvelo(selected.getXvelo() + friction);
    			if(selected.getXvelo() > 0){
    				selected.setXvelo(0);
    			}
    		}
    		
    		if(selected.getYvelo() > 0){
    			selected.setYvelo(selected.getYvelo() - friction);
    			if(selected.getYvelo() < 0){
    				selected.setYvelo(0);
    			}
    		}else if(selected.getYvelo() < 0){
    			selected.setYvelo(selected.getYvelo() + friction);
    			if(selected.getYvelo() > 0){
    				selected.setYvelo(0);
    			}
    		}
    		
    		System.out.print("BG");
    		if(!selected.isContainedWithin(Board[currentBoard])){
    			if(			(selected.getX() > 0)
    					&& 	(selected.getX()+selected.getwidth() < Board[currentBoard].getwidth()) 
    					&&	(selected.getY() < 0)
    					&&  (currentBoard != 4)){
    				frame.removeView(Board[currentBoard]);
    				currentBoard++;
    				frame.addView(Board[currentBoard]);
    				selected.setY(height);
    				frame.removeView(selected);
    				frame.addView(selected);
    				Board[currentBoard].invalidate();
    				selected.invalidate();
    				
    			} else if (currentBoard == 4){ 
        			selected.setY(25);
        			selected.setXvelo(0);
        			selected.setYvelo(0);
    			} else {
    			selected.setX(width/2 - selected.getwidth()/2);
    			selected.setY(height/2 - selected.getheight()/2);
    			selected.setXvelo(0);
    			selected.setYvelo(0);
    			Toast.makeText(this, "OoB", Toast.LENGTH_SHORT).show();
    			}
    		}
    	}
    	
    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Pucks[currentPuck].setFrame(currentBoard);
    	
    	frame.removeView(Board[currentBoard]);
    	currentBoard = 0;
    	frame.addView(Board[currentBoard]);
    	for(int i = 0; i <= currentPuck; i++){
    		frame.removeView(Pucks[i]);
    		if(Pucks[i].getFrame() == 0){
    			frame.addView(Pucks[i]);
    		}
    	}
    	if(currentPuck != 7){
    		currentPuck++;
    		frame.addView(Pucks[currentPuck]);
    	} else {
    		// round over to be implemented;
    	}
    	
    	
    }
    
}
