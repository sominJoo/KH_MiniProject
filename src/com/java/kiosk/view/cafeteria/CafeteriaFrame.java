package com.java.kiosk.view.cafeteria;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.java.kiosk.controller.CafeteriaController;
import com.java.kiosk.controller.cafeteria.Cafeteria;
import com.java.kiosk.model.vo.SalesMenu;
import com.java.kiosk.run.MainProcess;
import com.java.kiosk.util.MyUtil;
import com.java.kiosk.view.admin.AdminLoginFrame;

public class CafeteriaFrame extends JFrame{
	private CafeteriaController c = new CafeteriaController();
	public CafeteriaFrame() {
		MyUtil.init(this, 1000, 800, "구내식당");
		this.setLayout(null);
		new MenuPanel(this, 700,760,c);
		new TablePanel(this,280,500,c);
		new PaymentPanel(this,280,230,c);
		
		
		//관리자페이지 연결 Button
		JButton adminBtn  = new JButton("관리자 페이지");
		adminBtn.setBounds(702, 730, 276, 28);
		adminBtn.setBackground(Color.lightGray);
		adminBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				// 타이머 초기화
				c.timerReset();
				//로그인 페이지 연결
				new AdminLoginFrame().setVisible(true);
			}
		});
		this.add(adminBtn);
	}
}
