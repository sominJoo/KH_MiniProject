package com.java.kiosk.view.cafeteria;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.java.kiosk.controller.CafeteriaController;
import com.java.kiosk.controller.cafeteria.Cafeteria;
import com.java.kiosk.view.pay.PayFrame;

public class PaymentPanel extends JPanel{
	public static JLabel totalLabel ;
	public static JLabel timerLabel ;
	
	public PaymentPanel(JFrame f, int w, int h, CafeteriaController c) {
		// TODO Auto-generated constructor stub
		//전체Panel
		JPanel pPanel = new JPanel();
		pPanel.setBounds(700, 500, w, h);						//패널 사이즈 설정
		pPanel.setBorder(new TitledBorder(
				new LineBorder(new Color(105, 59, 13),2),"결제"));		//패널 경계선 설정
		pPanel.setLayout(new GridLayout(3,0));	
		pPanel.setBackground(new Color(122, 207, 204));			
		
		
		//총액 panel
		JPanel tPanel = new JPanel();
		JLabel label = new JLabel("총액 : ");
		totalLabel = new JLabel("0");
		tPanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
		tPanel.add(label);
		tPanel.setBackground(new Color(255, 255, 255));
		tPanel.add(totalLabel);
		JPanel timePanel = new JPanel();
//				timePanel.setLayout(new BorderLayout());
		timerLabel = new JLabel("2분안에 결정해주세요!");
		timePanel.setBackground(new Color(255, 255, 255));
		timePanel.add(timerLabel);
		
		JPanel panel3 = new JPanel();//디자인용
		panel3.setBackground(new Color(122, 207, 204));
		
		//결제Button
		JPanel btnPanel = new JPanel();
		JButton payBtn = new JButton("결제");
		payBtn.setBackground(new Color(162, 208, 219));
		payBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JTable orderCall = new JTable(Cafeteria.model);
				if(orderCall.getRowCount() == 0) {
					JOptionPane.showMessageDialog(null, "메뉴를 선택해주세요", "알림", JOptionPane.ERROR_MESSAGE);
				}else {
					c.timerReset();
					new PayFrame(orderCall);
				}
			}
		});
		
		//취소Button : 전체 삭제
		JButton delectBtn = new JButton("취소");
		delectBtn.setBackground(new Color(162, 208, 219));
		delectBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				c.timerReset();
				Cafeteria.model.setNumRows(0);
				totalLabel.setText("0");
			}
		});
		btnPanel.setLayout(new GridLayout(0,2,30,30));
		btnPanel.setBackground(new Color(255, 255, 255));
		btnPanel.add(payBtn);
		btnPanel.add(delectBtn);
		
		pPanel.add(timePanel);
		pPanel.add(tPanel);
		pPanel.add(btnPanel);
		f.add(pPanel);
	}
}
