package main;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Panel extends JPanel implements ActionListener, MouseListener, MouseMotionListener {
	private static final long serialVersionUID = 1L;
	BufferedImage bgImage;
	Rectangle mark;
	Panel(){
		try {
			bgImage = ImageIO.read(new File("mango.bmp"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mark = new Rectangle();
		addMouseListener(this);
		addMouseMotionListener(this);
		
	}	

	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(bgImage, 0, 0, null);
		mark.draw(g);
	}
	
	//OBS£UGA EVENTÓW DALEJ
	
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		//System.out.printf("Dragged to ( %d , %d ) %n", arg0.getX(), arg0.getY());
		mark.endX = arg0.getX();
		mark.endY = arg0.getY();
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		mark.beginX = arg0.getX();
		mark.beginY = arg0.getY();
		//System.out.printf("Click at ( %d , %d ) %n", arg0.getX(), arg0.getY());
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
