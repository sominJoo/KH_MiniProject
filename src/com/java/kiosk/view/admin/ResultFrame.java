package com.java.kiosk.view.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import com.java.kiosk.controller.cafeteria.Cafeteria;
import com.java.kiosk.model.vo.Menu;
import com.java.kiosk.model.vo.SalesMenu;
import com.java.kiosk.util.MyUtil;
import com.java.kiosk.view.cafeteria.CafeteriaFrame;
import com.java.kiosk.view.pay.PayFrame;

public class ResultFrame extends JFrame{	
	public static final int MAX = 16;
	public static ArrayList<SalesMenu> resultList = new ArrayList<>();
	public static int totalSalesPrice=0;
	JLabel menuName;
	JLabel menuQuantity;
	JLabel menuPrice;
	ResultFrame f;
	
	public ResultFrame() {
		MyUtil.init(this, 1000,800,"학식 시스템");
		setLayout(null);
		
		//판매된메뉴 총계
		salesMenu();
		
		//통계Label
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		panel1.setBounds(0, 0, 1000, 70);
		panel1.setBackground(new Color(122, 207, 204));
		JLabel label = new JLabel("통계");
		label.setFont(new Font("SeoulNamsanB", Font.BOLD, 25));
		label.setHorizontalAlignment(JLabel.CENTER);	//라벨을 센터에
		panel1.add(label);
		add(panel1);
		
		
		//list
		JScrollPane scroll = new JScrollPane();
		JPanel total = new JPanel();
		total.setLayout(new GridLayout(0,1,10,10));
		//total.setBounds(0, 70, 1000, 600);
		JPanel[] menupanel = new JPanel[MAX];	
		for(int i =0 ; i< resultList.size() ;i++) {
			//menuPanel setting
			menupanel[i] = new JPanel();
			menupanel[i].setBorder(new LineBorder(Color.BLACK));
			menupanel[i].setSize(1000,25);
			menupanel[i].setLayout(new FlowLayout(FlowLayout.LEFT, 60	,30));
			
			String name = resultList.get(i).getMenu();
			menuName = new JLabel(name);
			menuName.setFont(new Font("SeoulNamsanB", Font.BOLD, 25));
			menuName.setHorizontalAlignment(JLabel.CENTER);
			
			String quantity ="판매수량 :"+Integer.toString(resultList.get(i).getQuantity());
			menuQuantity = new JLabel(quantity);
			menuQuantity.setFont(new Font("SeoulNamsanB", Font.BOLD, 25));
			menuQuantity.setHorizontalAlignment(JLabel.CENTER);
			
			String price ="총 판매금액 :"+Integer.toString(resultList.get(i).getPrice());
			menuPrice = new JLabel(price);
			menuPrice.setFont(new Font("SeoulNamsanB", Font.BOLD, 25));
			menuPrice.setHorizontalAlignment(JLabel.CENTER);
			
			menupanel[i].add(menuName);
			menupanel[i].add(menuQuantity);
			menupanel[i].add(menuPrice);
			total.add(menupanel[i]);			//총 panel에 각 메뉴 패널 추가
		}
		scroll = new JScrollPane(total);		//총 panel을  scroll패널로 설정
		scroll.setBounds(0, 70, 980, 600);
		add(scroll);
		
		//정산 btn
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
		//크기 맞추기위해 아무 의미없음
		panel2.setBounds(0, 670, 1000, 70);
		JLabel n = new JLabel();
		JLabel n2 = new JLabel();
		JButton resultBtn = new JButton("정산");	
		resultBtn.setFont(new Font("SeoulNamsanB", Font.BOLD, 15));
		resultBtn.setBackground(new Color(171, 183, 235));
		panel2.setBackground(new Color(122, 207, 204));
		resultBtn.setSize(100,25);
		
		resultBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 정산Frame 로 변경
				dispose();
				new CalculateFrame().setVisible(true);
			}
		});
		panel2.add(n);
		panel2.add(resultBtn);
		panel2.add(n2);
		add(panel2);
	}

	public void salesMenu() {			
		//저장된 리스트에서 메뉴 이름이 같을 시 totalPrice에 저장
		int num = AdminFrame.menuList.size();
		String[] menuName = new String[num];
		//메뉴이름 확인용 배열
		for(int i=0 ; i< num; i++) {
			menuName[i] = AdminFrame.menuList.get(i).getMenu();
		}
		
		//결과값
		for(int i =0 ;i<AdminFrame.menuList.size(); i++) {
			int totalPrice = 0;
			int totalQuantity =0;
			//menuName[0]일때 salesList 검사
			for(int j =0 ; j<PayFrame.salesHistoryList.size() ; j++) {
				String salesMenuName = PayFrame.salesHistoryList.get(j).getMenu();
				if(menuName[i].equals(salesMenuName)) {
					totalPrice += PayFrame.salesHistoryList.get(j).getPrice();
					totalQuantity += PayFrame.salesHistoryList.get(j).getQuantity();
				}
			}
			resultList.add(new SalesMenu(menuName[i],totalQuantity,totalPrice));
		}
		
		for(int i =0 ;i<resultList.size() ;i++) {
			totalSalesPrice += resultList.get(i).getPrice();
		}
	}
}
	