package main;

import java.awt.Graphics;
import java.util.ArrayList;

public class Polygon implements Mark {
	ArrayList<Point> vertices;
	boolean isFinished;
	Polygon(int initX, int initY){
		isFinished = false;
		vertices = new ArrayList<>();
		vertices.add(new Point(initX, initY));
	}
	
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		Point lastPoint = vertices.get(0);
		for (Point p : vertices) {
			g.drawLine(lastPoint.x, lastPoint.y, p.x, p.y);
			lastPoint = p;
		}
		g.drawLine(lastPoint.x, lastPoint.y, vertices.get(0).x, vertices.get(0).y);
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return Mark.POLYGON;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return String.format("polygon %d-v ( %d , %d )", vertices.size(), vertices.get(0).x, vertices.get(0).y);
	}
	
	public String toString() {
		return getName();
	}
	
	public void addVertex(int x, int y) {
		vertices.add(new Point(x, y));
	}
	
	class Point{
		int x, y;
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}

	@Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
		return this.isFinished;
	}

	@Override
	public String saveAs() {
		String save = getType() + " ";
		for (Point p : vertices) {
			save += String.format("%d %d ", p.x, p.y);
		}
		return save;
	}

}
