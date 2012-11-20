package com.example.shuffleboard;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;

import com.example.shuffleboard.R;


public class Target extends View {
	private int xVelo;
	private int yVelo;
	private Bitmap bmp;
	private int frames;
	public Target(Context context) {
		super(context);
		xVelo = 0;
		yVelo = 0;
		frames = 0;
		bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_action_search);
	}

	public int getXvelo(){
		return xVelo;
	}
	public void setXvelo(int X){
		xVelo = X;
	}
	public int getYvelo(){
		return yVelo;
	}
	public void setYvelo(int Y){
		yVelo = Y;
	}
	public void setFrame(int f){
		frames = f;
	}
	public int getFrame(){
		return frames;
	}
	public float getwidth(){
		return (float) bmp.getWidth();
	}
	
	public float getheight(){
		return (float) bmp.getHeight();
	}
	
	protected void onDraw(Canvas canvas) { 
		canvas.drawBitmap(bmp, 0, 0, null);  
	}  
	
	public void setBitmap(int res){
		bmp = BitmapFactory.decodeResource(getResources(), res);
	}
	
	public void setBitmap(Bitmap img){
		bmp = img;
	}
	
	public Bitmap getBitmap(){ 
		return bmp; 
		}
	
	@TargetApi(11)
	public boolean isInside(float xIn, float yIn){
		/*
		System.out.println("Is " + xIn + "contained within" + getX() + " and " + (getX()+getwidth()));
		System.out.println("Is " + yIn + "contained within" + getY() + " and " + (getY()+getheight()));
		*/
			return (	   (xIn > getX())
						&& (xIn < (getX() + getwidth()))                                                                
						&& (yIn > getY())
						&& (yIn < (getY() + getheight())));	
	}
	
	@TargetApi(11)
	public boolean isContainedWithin(Target TRGT){
		/*
		System.out.println("X0        Y0        X1        Y1");
		System.out.println(getX() + "   " + getY() + "   " + (getX()+getwidth()) + "   " + (getY()+getheight()));
		System.out.println(TRGT.getX() + "   " + TRGT.getY() + "   " + (TRGT.getX()+TRGT.getwidth()) + "   " + (TRGT.getY()+TRGT.getheight()));
		System.out.println(		TRGT.isInside(getX(), getY()) + 
						"   " + TRGT.isInside(getX()+getwidth(), getY()) + 
						"   " + TRGT.isInside(getX(), getY()+getheight()) + 
						"   " + TRGT.isInside(getX()+getwidth(), getY()+getheight()));
		*/
		
		return (
					TRGT.isInside(getX(), getY())
				&&  TRGT.isInside(getX()+getwidth(), getY())
				&&  TRGT.isInside(getX(), getY()+getheight())
				&&  TRGT.isInside(getX()+getwidth(), getY()+getheight())
				);
				
	}
}