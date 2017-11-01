package main;

import java.awt.Graphics;

public interface Mark {
	public void draw(Graphics g);
	public MarkType getType();
	enum MarkType {RECTANGLE, ELLIPSE, POLYGON}; 
}
