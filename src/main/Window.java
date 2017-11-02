package main;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Window extends JFrame {
	private static final long serialVersionUID = 1L;
	
	JButton rectangle;
	JButton ellipse;
	JButton polygon;
	JButton save;
	JButton load;
	JButton choose_pic;
	JButton delete;
	PicturePanel pict;
	JList<Mark> marks;
	JScrollPane scrollPane;
 	public DefaultListModel<Mark> displayedMarkList;
 	int selectedMarkIndex;
	Window(){
		setGUI();
		marks.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		marks.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub
				if(!arg0.getValueIsAdjusting()) {
					selectedMarkIndex = marks.getSelectionModel().getLeadSelectionIndex();
					for(Mark m : pict.marks) {
						m.setColor(Color.BLACK);
					}
					if(pict.marks.size() > selectedMarkIndex)
						 pict.marks.get(selectedMarkIndex).setColor(Color.RED);
					repaint();
				}
			}
			
		});
		
		//button listeners
		rectangle.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				pict.markingMode = Mark.RECTANGLE;
			}
		});
		
		ellipse.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				pict.markingMode = Mark.ELLIPSE;
			}
		});
		
		polygon.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				pict.markingMode = Mark.POLYGON;
			}
		});
		
		save.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				File saveTo = new File("grafika2-save.txt");
				try {
					PrintWriter pw = new PrintWriter(saveTo);
					for (Mark m : pict.marks) {
						pw.write(m.saveAs() + "\n");
					}
					pw.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//TODO!
				
			}
		});
		
		load.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Scanner sc = new Scanner(new File("grafika2-save.txt"));
					pict.marks.clear();
					displayedMarkList.clear();
					while(sc.hasNextLine()) {
						String loadedLine = sc.nextLine();
						Scanner lineSc = new Scanner(loadedLine);
						String loadedMarkType = lineSc.next();
						if (loadedMarkType.equals(Mark.RECTANGLE)) {
							Rectangle rect = new Rectangle();
							rect.beginX = lineSc.nextInt();
							rect.beginY = lineSc.nextInt();
							rect.endX = lineSc.nextInt();
							rect.endY = lineSc.nextInt();
							pict.marks.add(rect);
							displayedMarkList.addElement(rect);
						}
						else if (loadedMarkType.equals(Mark.ELLIPSE)) {
							Ellipse ell = new Ellipse();
							ell.beginX = lineSc.nextInt();
							ell.beginY = lineSc.nextInt();
							ell.endX = lineSc.nextInt();
							ell.endY = lineSc.nextInt();
							pict.marks.add(ell);
							displayedMarkList.addElement(ell);
						}
						else if (loadedMarkType.equals(Mark.POLYGON)) {
							Polygon poly = new Polygon(lineSc.nextInt(), lineSc.nextInt());
							while (lineSc.hasNextInt()) {
								poly.addVertex(lineSc.nextInt(), lineSc.nextInt());
							}
							pict.marks.add(poly);
							displayedMarkList.addElement(poly);
						}
						else {
							System.out.println("UNRECOGNIZED TYPE IN SAVE FILE");
						}
						lineSc.close();
					}
					sc.close();
					pict.repaint();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		choose_pic.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.print("data:  ");
				for(Mark m : pict.marks)
					System.out.print(m + " ");
				System.out.println("");
				System.out.print("displ: ");
				for(int i = 0 ; i < displayedMarkList.size() ; i++)
					System.out.print(displayedMarkList.get(i) + " ");
				System.out.println("\n\n");
			}
		});
		
		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stubs
				displayedMarkList.remove(selectedMarkIndex);
				pict.marks.remove(selectedMarkIndex);
				marks.clearSelection();
				pict.lastMark = null;
				//selectedMarkIndex = 0;
				pict.repaint();
			}
			
		});

	}

	private void setGUI() {
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

		choose_pic = new JButton("");
		c.gridx = 5;
		c.gridy = 0;
		contents.add(choose_pic, c);
		
		pict = new PicturePanel(this);
		c.weighty = 10.0;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 5;
		c.gridheight = 2;
		contents.add(pict, c);
		
		displayedMarkList = new DefaultListModel<>();
		marks = new JList<Mark>(displayedMarkList);
		scrollPane = new JScrollPane(marks);
		c.gridx = 5;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		contents.add(scrollPane, c);
		
		delete = new JButton("Delete");
		c.gridx = 5;
		c.gridy = 2;
		c.weighty = 0.1;
		contents.add(delete, c);
	}
	
	
}
