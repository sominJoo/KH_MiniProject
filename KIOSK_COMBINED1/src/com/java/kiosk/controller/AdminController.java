package com.java.kiosk.controller;

import com.java.kiosk.controller.io.AdminIO;

 
//입출력 일시키기
public class AdminController {
	
	private AdminIO adminIO = new  AdminIO();
	
	/**
	 * 메뉴 추가 버튼 
	 */
	public void insertMenu(String[] row) {
		 adminIO.insertdMenu(row);
		
	}

	/**
	 * 메뉴 불러오기 버튼
	 */
	public void loadMenuList() {
		adminIO.loadMenu();
	}
	
	/**
	 * 메뉴 저장
	 */
	public void saveMenuList(String money) {
		adminIO.saveMenu(money);
	}
	
}
