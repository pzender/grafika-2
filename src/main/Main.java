package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		Window wind = new Window();
		wind.pack();
		wind.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		wind.setVisible(true);
		wind.setBounds(50, 50, 800, 600);
		wind.setTitle("Grafika - lab2");
	}
}