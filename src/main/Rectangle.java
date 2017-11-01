package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Rectangle {
	int beginX;
	int beginY;
	int endX;
	int endY;
	
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
		
	Rectangle(){
		beginX = beginY = endX = endY = 0;
		color = Color.BLACK;
	}
	
	public void draw(Graphics g){
		g.setColor(color);
		((Graphics2D)g).setStroke(new BasicStroke(3));
		g.drawRect(left(), top(), width(), height());
	}

	
}
