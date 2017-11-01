package main;

import java.awt.Container;

import javax.swing.JFrame;

public class Window extends JFrame {
	private static final long serialVersionUID = 1L;
	Window(){
		Container contents = getContentPane();
		contents.add(new Panel());
	}
}
