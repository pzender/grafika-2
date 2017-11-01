package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Window wind = new Window();
		wind.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		wind.setVisible(true);
		wind.setBounds(50, 50, 640, 480);
		wind.setTitle("Grafika - lab2");
	}
}