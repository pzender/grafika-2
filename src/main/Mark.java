package main;

import java.awt.Color;
import java.awt.Graphics;

public interface Mark {
	public void draw(Graphics g);
	public String getType();
	public String getName();
	public String toString();
	public String saveAs();
	public boolean isFinished();
	public void setColor(Color c);
	
	public static final String RECTANGLE = "rectangle";
	public static final String ELLIPSE = "ellipse";
	public static final String POLYGON = "polygon";
}
