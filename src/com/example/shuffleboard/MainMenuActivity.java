package com.example.shuffleboard;


import java.util.ArrayList;
import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.FrameLayout;

public class MainMenuActivity extends Activity {
	private Target selected = null;
	private Target MenuPuck;
	private Target Background;
	private ArrayList<Point> VelocityStack = new ArrayList<Point>(5);
	public static final int height = 775;
	public static final int heightOffset = 100;
	public static final int width = 500;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    	
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main_menu);
        FrameLayout frame = (FrameLayout) findViewById(R.id.graphics_holder);
        
    	MenuPuck = new Target(this);
    	Background = new Target(this);
        
		Background.setBitmap(R.drawable.mainmenu);
		Background.setX(0);
		Background.setY(0);
		frame.addView(Background);
		
		MenuPuck.setBitmap(R.drawable.menuslider);
		MenuPuck.setX(width/2 - MenuPuck.getwidth()/2);
		MenuPuck.setY(height/2 - MenuPuck.getheight()/2 + heightOffset);
		frame.addView(MenuPuck);
		
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
		float Y = event.getY()-48;
		
		if(event.getAction() == android.view.MotionEvent.ACTION_DOWN){
			System.out.println("action down triggered");
			if(MenuPuck.isInside(X,Y)){
				selected = MenuPuck;
				System.out.println("action down triggered");
			}
			return true;
		} else if (event.getAction() == android.view.MotionEvent.ACTION_UP){
			System.out.println("action up triggered");
			if(MenuPuck.isInside(X,Y)){
				selected = null;
				System.out.println("action up triggered");
			}
			return true;
		} else if (event.getAction() == android.view.MotionEvent.ACTION_MOVE){
			System.out.println("action move triggered");
			if(MenuPuck.isInside(X,Y)){
				MenuPuck.setX(X - MenuPuck.getwidth()/2);
				MenuPuck.setY(Y - MenuPuck.getheight()/2);
				System.out.println("action move triggered");
			}
			return true;
		}
		
		return false; 
	}
    
}
