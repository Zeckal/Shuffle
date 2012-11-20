/*
package com.example.shuffleboard;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.Toast;

public class GameActivity extends Activity  {

    
	

	
	private Target[] Pucks;
	private Point PreviousLocation;
	private Target Board;
	private Target selected;
	private int currentPuck;
	public static final int height = 775;
	public static final int width = 500;
	public static final int numberofpucks = 8;
	public static final int friction = 1;
	
	
    public void onCreate(Bundle savedInstanceState) {
    	
    	System.out.println("create GameActivity 1");
    	
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        
        FrameLayout frame = (FrameLayout) findViewById(R.id.graphics_holder);
        
        selected = null;
        currentPuck = 0;
        
        System.out.println("create GameActivity 2");
        
        Pucks = new Target[numberofpucks];
        for(int i = 0; i < numberofpucks; i++){
        	Target T = new Target(this);
        	
        	if(i%2 == 1){
        		T.setBitmap(R.drawable.blueslider);
        	} else {
        		T.setBitmap(R.drawable.redslider);
        	}
        	
    		T.setX(width/2 - T.getwidth()/2);
    		T.setY(height/2 - T.getheight()/2);
        	
    		Pucks[i] = T;
        }
        
        System.out.println("create GameActivity 3");
        
        Board.setBitmap(R.drawable.board);
        Board.setX(0);
        Board.setY(height - Board.getheight());
        frame.addView(Board);
        
        frame.addView(Pucks[0]);
        
        System.out.println("create GameActivity 4");
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
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
    		if(!selected.isContainedWithin(Board)){
    			
    			if(			(selected.getX() > 0)
    					&& 	(selected.getX()+selected.getwidth() < Board.getwidth()) 
    					&&	(selected.getY() < 0)){
    				Board.setY(Board.getheight()/2 + Math.abs(selected.getY()));
    				frame.removeView(Board);
    				frame.addView(Board);
    				selected.setY(Board.getheight()/2);
    				
    			} else {
    			selected.setX(width/2 - selected.getwidth()/2);
    			selected.setY(height/2 - selected.getheight()/2);
    			selected.setXvelo(0);
    			selected.setYvelo(0);
    			Toast.makeText(this, "OoB", Toast.LENGTH_SHORT).show();
    			}
    		}
    	}
    }
}
    */

