package com.java.kiosk.view.admin;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.java.kiosk.util.MyUtil;

public class AdminLoginFrame extends JFrame {
	JPasswordField pw;
	JTextField id;
	
	public AdminLoginFrame() {
		MyUtil.init(this, 500, 400, "관리자 로그인");
		setDefaultCloseOperation(AdminLoginFrame.DISPOSE_ON_CLOSE);	//exit시 다른 프레임까지 종료된다. dispose사용
		setLayout(null);
		
		//ID
		JPanel idPanel = new JPanel();
		JLabel label1 = new JLabel("관리자 아이디 : ");
		id = new JTextField(10);
		idPanel.setBounds(130, 100, 250, 30);
		idPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		idPanel.add(label1);
		idPanel.add(id);
		
		add(idPanel);
		
		//Password
		JPanel pwPanel = new JPanel();
		JLabel label2 = new JLabel("비밀번호          : ");
		pw = new JPasswordField(10);
		pwPanel.setBounds(130, 150, 250, 30);
		pwPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		pwPanel.add(label2);
		pwPanel.add(pw);
		add(pwPanel);
		
		//버튼
		JButton loginBtn = new JButton("확인");
		loginBtn.setBounds(200, 200, 80, 40);
		loginBtn.setBackground(Color.LIGHT_GRAY);
		loginBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//로그인확인
				checkLogin(id.getText(), pw.getText());
				contentSet();
				
			}
		});
		add(loginBtn);
	}
	public void checkLogin(String id, String pw) {
		//변경사항 : 임시로 관리자 줌 (test용)
		String adminId= "00";
		String adminPw = "00";
		if((id.equals(adminId)) && (pw.equals(adminPw))) {
			JOptionPane.showMessageDialog(null, "로그인 성공");
			dispose();
			//성공 시 정산Frame으로 연결
			new ResultFrame().setVisible(true);;
		}
		else {
			JOptionPane.showMessageDialog(null, "로그인 실패! 아이디나 비밀번호를 확인하세요");
		}
	}
	public void contentSet() {
		id.setText("");
		pw.setText("");
	}
}
