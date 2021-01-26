package com.java.kiosk.util;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class MyUtil extends JFrame{
	public static void init(JFrame f, int w, int h, String title) {
		// TODO Auto-generated method stub
		f.setTitle(title);
		f.setSize(w,h);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
