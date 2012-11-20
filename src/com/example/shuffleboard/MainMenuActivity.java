package com.example.shuffleboard;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainMenuActivity extends Activity {
	private Target selected;
	private Target MenuPuck;
	private Target Background;
	private Target NewGame;
	private Target Options;
	private Target HowToPlay;
	private Target EXIT;
	private Point PreviousLocation;
	public static final int height = 750;
	public static final int heightOffset = 100;
	public static final int width = 480;
	public static final int friction = 10;
	public static final int titleSpaceHeight = 150;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    	
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main_menu);
        FrameLayout frame = (FrameLayout) findViewById(R.id.graphics_holder);
        
        selected = null;
    	MenuPuck = new Target(this);
    	Background = new Target(this);
    	NewGame = new Target(this);
    	Options = new Target(this);
    	HowToPlay = new Target(this);
    	EXIT = new Target(this);
    	PreviousLocation = new Point();
        
		Background.setBitmap(R.drawable.mainmenu);
		Background.setX(0);
		Background.setY(0);
		frame.addView(Background);
		
		MenuPuck.setBitmap(R.drawable.menuslider);
		MenuPuck.setX(width/2 - MenuPuck.getwidth()/2);
		MenuPuck.setY(height/2 - MenuPuck.getheight()/2 + heightOffset);
		frame.addView(MenuPuck);
		
		NewGame.setBitmap(R.drawable.vishitbox100x200); //this doesn't actually need to be displayed, it is to be used as the bounds for the hitbox
		NewGame.setX(0);
		NewGame.setY(titleSpaceHeight);
		frame.addView(NewGame);
		
		Options.setBitmap(R.drawable.vishitbox100x200);
		Options.setX(width/2 + 40);
		Options.setY(titleSpaceHeight);
		frame.addView(Options);
		
		HowToPlay.setBitmap(R.drawable.vishitbox100x200);
		HowToPlay.setX(0);
		HowToPlay.setY(height - HowToPlay.getheight());
		frame.addView(HowToPlay);
		
		EXIT.setBitmap(R.drawable.vishitbox100x200);
		EXIT.setX(width/2 + 50);
		EXIT.setY(height - HowToPlay.getheight());
		frame.addView(EXIT);
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
		float Y = event.getY()-(MenuPuck.getheight()/2);
		
		if(event.getAction() == android.view.MotionEvent.ACTION_DOWN){
			System.out.println("X: " + X + " Y: " + Y);
			System.out.println("PX: " + MenuPuck.getX() + " PY: " + MenuPuck.getY());
			if(MenuPuck.isInside(X,Y)){
				System.out.println("action down triggered");
				selected = MenuPuck;
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
				checkHits();
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
    		if(!selected.isContainedWithin(Background)){
    			selected.setX(width/2 - MenuPuck.getwidth()/2);
    			selected.setY(height/2 - MenuPuck.getheight()/2 + heightOffset);
    			selected.setXvelo(0);
    			selected.setYvelo(0);
    			Toast.makeText(this, "OoB", Toast.LENGTH_SHORT).show();
    		}
    	}
    }
    
    public void checkHits(){
    	System.out.print("NG");
    	if(selected.isContainedWithin(NewGame)){
    		Toast.makeText(this, "TIME TO PLAY", Toast.LENGTH_SHORT).show();
    		System.out.println("new game triggered");
    		Intent myIntent = new Intent(this, GameActivity.class);
    		startActivity(myIntent); 
    	}
    	System.out.print("OP");
    	if(selected.isContainedWithin(Options)){
    		Toast.makeText(this, "OPTIONS", Toast.LENGTH_SHORT).show();
    		System.out.println("OPTIONS triggered");
    	}
    	System.out.print("HT");
    	if(selected.isContainedWithin(HowToPlay)){
    		Toast.makeText(this, "HOW TO SHUFFLE", Toast.LENGTH_SHORT).show();
    		System.out.println("HOW triggered");
    	}
    	System.out.print("EX");
    	if(selected.isContainedWithin(EXIT)){
    		Toast.makeText(this, "EXIT", Toast.LENGTH_SHORT).show();
    		System.out.println("EXIT triggered");
    	} else {
    	}
    }
    
}
