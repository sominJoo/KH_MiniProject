package com.java.kiosk.view.cafeteria;

import java.awt.Color;
//import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.java.kiosk.controller.AdminController;
import com.java.kiosk.controller.CafeteriaController;
import com.java.kiosk.controller.cafeteria.Cafeteria;
import com.java.kiosk.model.vo.Menu;
import com.java.kiosk.view.admin.AdminFrame;
import com.java.kiosk.view.pay.PayFrame;

public class MenuPanel extends JPanel{
	public static final int MAX = 16;
	public static int first=0;
	public static List<Menu> list =  AdminFrame.menuList;
//	public static int quantity =0;
//	public static int price =0;
//	public static String menu=null;
	public static JButton[] menuBtn = new JButton[MAX];
	
	public MenuPanel(JFrame f, int w, int h, CafeteriaController c) {
		JPanel mPanel = new JPanel();
		mPanel.setBounds(0, 0, w, h);					//패널 사이즈 설정
		mPanel.setBorder(new TitledBorder(new LineBorder(new Color(105, 59, 13),2),"메뉴"));	//패널 경계선 설정		
		mPanel.setLayout(new GridLayout(4,4,13, 13));		//flowlayout으로 왼쪽부터 정력, 10픽셀씩 띄움
		mPanel.setBackground(new Color(122, 207, 204));	//flowlayout으로 왼쪽부터 정력, 10픽셀씩 띄움
		

		for(int i = 0; i<MAX; i++) {
			//메뉴버튼 text 설정
			if(i >= list.size()) {
				menuBtn[i] = new JButton("-준비중-");
				menuBtn[i].setSize(170, 170);
				menuBtn[i].setBackground(new Color(255, 255, 252));
				menuBtn[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						JOptionPane.showMessageDialog(null, "준비중은 선택하실 수 없습니다.");
					}
				});
				menuBtn[i].setFont(new Font("SeoulNamsanB", Font.BOLD, 20));
				mPanel.add(menuBtn[i]);
				
			}
			else {
				String menu = list.get(i).getMenu();
				int price = list.get(i).getPrice();
				int quantity = list.get(i).getQuantity();
				String btnText = "<HTML><body><center>"+menu + "<br>"+ Integer.toString(price)+"원<br>" +Integer.toString(quantity)+"개</center><body></HTML>";
				menuBtn[i] = new JButton(btnText);
				
//				String name = MenuPanel.menuBtn[i].getText();
				
				//페널에 메뉴 버튼 추가
				menuBtn[i].setSize(160, 160);
				menuBtn[i].setBackground(new Color(255, 255, 252));
				menuBtn[i].addActionListener(new  ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						JButton btn = (JButton)e.getSource();
						String name = btn.getText();
						String[] text1 = name.split("<br>");
						String quantityStr = text1[2].replaceAll("개</center><body></HTML>", "");
						if(quantityStr.equals("0")) {
							JOptionPane.showMessageDialog(null, "재고 부족! 구매하실 수 없습니다.");
						}
						else {
							c.add(menu,price,quantityStr);
							PaymentPanel.totalLabel.setText(c.total());		//버튼 눌릴시 총액 라벨 갱신
						}

						if(first == 0)
							c.timer();
						first++;
					}
					
				});
				} 
			menuBtn[i].setFont(new Font("SeoulNamsanB", Font.BOLD, 20));
			mPanel.add(menuBtn[i]);
			}
			
		f.add(mPanel);
	}

	
}
