package com.example.shuffleboard;


import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.widget.FrameLayout;

public class MainMenuActivity extends Activity {
	//public static final int TARGETS=1;
	//private int[] images = new int[TARGETS];
	//private ArrayList<Target> targets = new ArrayList<Target>(TARGETS);
	//Puck SliderPuck = new Puck(this);
	public static final int height = 775;
	public static final int heightOffset = 100;
	public static final int width = 500;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        FrameLayout frame = (FrameLayout) findViewById(R.id.graphics_holder);
        /*
			images[0] = R.drawable.ic_mainmenu;            
				for (int i = 0;i< TARGETS;i++){
				targets.add(new Target(this));
				targets.get(i).setBitmap(images[i]);
				targets.get(i).setx(0);
				targets.get(i).sety(0);
				frame.addView(targets.get(i));
			}
		*/
		Target Background = new Target(this);
		Background.setBitmap(R.drawable.mainmenu);
		Background.setX(0);
		Background.setY(0);
		frame.addView(Background);
		
		Target MenuPuck = new Target(this);
		MenuPuck.setBitmap(R.drawable.menuslider);
		MenuPuck.setX(width/2 - MenuPuck.getwidth()/2);
		MenuPuck.setY(height/2 - MenuPuck.getheight()/2 + heightOffset);
		System.out.println("Height: " + height);
		System.out.println("Width: " + width);
		System.out.println("puck height: " + MenuPuck.getheight());
		System.out.println("puck width: " + MenuPuck.getwidth());
		frame.addView(MenuPuck);
		
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return true;
    }
}
