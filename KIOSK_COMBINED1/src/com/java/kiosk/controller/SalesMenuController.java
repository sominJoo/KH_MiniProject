package com.java.kiosk.controller;


import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import com.java.kiosk.model.vo.Menu;
import com.java.kiosk.model.vo.SalesMenu;
import com.java.kiosk.view.admin.AdminFrame;
import com.java.kiosk.view.cafeteria.MenuPanel;
import com.java.kiosk.view.pay.PayFrame;

public final class SalesMenuController {
	public static String quantityStr;
	public static void add(JTable orderCall) {
		String foodName;
		int sum;
		int money;
		

		//저장된 리스트에서 메뉴 이름이 같을 시 totalPrice에 저장
		int num = MenuPanel.list.size();
		String[] menuName = new String[num];
		//버튼 메뉴이름 확인용 배열
		for(int i=0 ; i< num; i++) {
			menuName[i] = MenuPanel.list.get(i).getMenu();
		}

		List<SalesMenu> quantityList = new ArrayList<>();
		
		for(int i=0; i< orderCall.getRowCount(); i++) {
			foodName= (String)orderCall.getValueAt(i, 0);
			sum = (int)orderCall.getValueAt(i, 1);
			money = (int)orderCall.getValueAt(i, 2);
			PayFrame.salesHistoryList.add(new SalesMenu(foodName, sum, money));
			quantityList.add(new SalesMenu(foodName, sum, money));
		}
		
		for(int i =0 ; i < num ;i++) {
			String name = MenuPanel.menuBtn[i].getText();
			String[] text1 = name.split("<br>");
			String menuStr = text1[0].replaceAll("<HTML><body><center>", "");
			String priceStr = text1[1].replaceAll("원","");
			String quantityStr = text1[2].replaceAll("개</center><body></HTML>", "");
			int quantity = Integer.parseInt(quantityStr);
			for(int j =0 ; j< quantityList.size() ; j++) {
				//결제된 리스트의 메뉴와 버튼 메뉴이름이 같다면
				if(menuName[i].equals(quantityList.get(j).getMenu())) {
					quantity -= quantityList.get(j).getQuantity();
					String text = "<HTML><body><center>"+menuStr + "<br>"+priceStr+"원<br>" +Integer.toString(quantity)+"개</center><body></HTML>";
					MenuPanel.menuBtn[i].setText(text);
				}
			}
		}
		
	}

}

