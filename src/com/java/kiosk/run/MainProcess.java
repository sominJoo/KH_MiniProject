package com.java.kiosk.run;

import com.java.kiosk.view.admin.AdminFrame;
import com.java.kiosk.view.admin.AdminLoginFrame;
import com.java.kiosk.view.cafeteria.CafeteriaFrame;

public class MainProcess {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new AdminFrame(1000,800,"학식 시스템").setVisible(true);
	}
}
