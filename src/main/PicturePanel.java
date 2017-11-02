package main;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class PicturePanel extends JPanel implements ActionListener, MouseListener, MouseMotionListener {
	Window appWindow;
	private static final long serialVersionUID = 1L;
	BufferedImage bgImage;
	public ArrayList<Mark> marks;
	public String markingMode;
	public Mark lastMark;
	PicturePanel(Window appWindow){
		this.appWindow = appWindow;
		try {
			bgImage = ImageIO.read(new File("img1.bmp"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		marks = new ArrayList<>();
		addMouseListener(this);
		addMouseMotionListener(this);
		markingMode = Mark.RECTANGLE;
	}	

	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(bgImage, 0, 0, null);
		if (lastMark != null)
			lastMark.draw(g);
		for (Mark m : marks)
			m.draw(g);
	}
	
	//OBS£UGA EVENTÓW

	@Override
	public void mouseDragged(MouseEvent arg0) {
		//System.out.printf("Dragged to ( %d , %d ) %n", arg0.getX(), arg0.getY());
		if(lastMark.getType() == Mark.RECTANGLE){
			Rectangle rect = (Rectangle)lastMark;
			rect.endX = arg0.getX();
			rect.endY = arg0.getY();
		}
		else if(lastMark.getType() == Mark.ELLIPSE){
			Ellipse ell = (Ellipse)lastMark;
			ell.endX = arg0.getX();
			ell.endY = arg0.getY();
		}
		else if(lastMark.getType() == Mark.POLYGON){
			//STUFF
		}
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (markingMode == Mark.POLYGON) {
			if(lastMark == null || lastMark.getType() != Mark.POLYGON || lastMark.isFinished()) {
				lastMark = new Polygon(arg0.getX(), arg0.getY());
				marks.add(lastMark);
			}
			else if (!lastMark.isFinished() && arg0.getButton() == MouseEvent.BUTTON1){
				Polygon currentPolygon = (Polygon)lastMark;
				currentPolygon.addVertex(arg0.getX(), arg0.getY());
			}
			else /*if (arg0.getButton() == MouseEvent.BUTTON2) */ {
				Polygon currentPolygon = (Polygon)lastMark;
				currentPolygon.isFinished = true;
				appWindow.displayedMarkList.addElement(currentPolygon);
			}
		}
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		if (markingMode == Mark.RECTANGLE){
			lastMark = new Rectangle(arg0.getX(), arg0.getY());
		}
		else if (markingMode == Mark.ELLIPSE){
			lastMark = new Ellipse(arg0.getX(), arg0.getY());
		}
		//System.out.printf("Click at ( %d , %d ) %n", arg0.getX(), arg0.getY());
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		if (markingMode == Mark.RECTANGLE){
			((Rectangle)lastMark).isFinished = true;
			appWindow.displayedMarkList.addElement(lastMark);
			marks.add(lastMark);
//			lastMark

		}
		else if (markingMode == Mark.ELLIPSE){
			//marks.add(lastMark);
			((Ellipse)lastMark).isFinished = true;
			appWindow.displayedMarkList.addElement(lastMark);
			marks.add(lastMark);	
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
