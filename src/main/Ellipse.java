package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Ellipse implements Mark {
	int beginX;
	int beginY;
	int endX;
	int endY;
	boolean isFinished;
	
	Color color;
	
	private int top(){
		return beginY < endY ? beginY : endY;
	}
	
	private int left(){
		return beginX < endX ? beginX : endX;
	}
	
	private int width(){
		return beginX > endX ? beginX-endX : endX-beginX;
	}
	
	private int height() {
		return beginY > endY ? beginY-endY : endY-beginY;
	}
		
	Ellipse(){
		beginX = beginY = endX = endY = 0;
		color = Color.BLACK;
	}
	
	Ellipse(int beginX, int beginY){
		this.beginX = this.endX = beginX;
		this.beginY = this.endY = beginY;
		color = Color.BLACK;
		isFinished = false;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(color);
		((Graphics2D)g).setStroke(new BasicStroke(3));
		g.drawOval(left(), top(), width(), height());		
	}

	@Override
	public String getType() {
		return ELLIPSE;
	}

	@Override
	public String getName() {
		return String.format("%s ( %d , %d )", ELLIPSE, beginX, beginY);
	}	
	public String toString(){
		return this.getName();
	}

	@Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
		return isFinished;
	}

	@Override
	public String saveAs() {
		return String.format("%s %d %d %d %d", getType(), beginX, beginY, endX, endY);
	}

	@Override
	public void setColor(Color c) {
		// TODO Auto-generated method stub
		this.color = c;
	}
}
