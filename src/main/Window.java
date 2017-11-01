package main;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class Window extends JFrame {
	private static final long serialVersionUID = 1L;
	
	JButton rectangle;
	JButton ellipse;
	JButton polygon;
	JButton save;
	JButton load;
	JButton choose_pic;
	PicturePanel pict;
	JList<String> marks;
	JScrollPane scrollPane;
	String[] example = {"rectangle 1", "ellipse 1", "rectangle 2"};
 	
	Window(){
		Container contents = getContentPane();
		contents.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.weighty = 0.1;
		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;


		rectangle = new JButton("Rectangle");
		c.gridx = 0;
		c.gridy = 0;
		contents.add(rectangle, c);
		
		ellipse = new JButton("Ellipse");
		c.gridx = 1;
		c.gridy = 0;
		contents.add(ellipse, c);
		
		polygon = new JButton("Polygon");
		c.gridx = 2;
		c.gridy = 0;
		contents.add(polygon, c);

		save = new JButton("Save");
		c.gridx = 3;
		c.gridy = 0;
		contents.add(save, c);

		load = new JButton("Load");
		c.gridx = 4;
		c.gridy = 0;
		contents.add(load, c);

		choose_pic = new JButton("Picture");
		c.gridx = 5;
		c.gridy = 0;
		contents.add(choose_pic, c);
		
		pict = new PicturePanel();
		c.weighty = 10.0;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 5;
		contents.add(pict, c);
		
		marks = new JList<String>(example);
		for (int i = 0 ; i < marks.getModel().getSize() ; i++)
			System.out.println(marks.getModel().getElementAt(i));
				
		scrollPane = new JScrollPane(marks);
		c.gridx = 5;
		c.gridy = 1;
		c.gridwidth = 1;
		contents.add(scrollPane, c);
	}
}
