package com.java.kiosk.controller.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.annotation.processing.Filer;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.java.kiosk.model.vo.Menu;
import com.java.kiosk.view.admin.AdminFrame;
import com.java.kiosk.view.cafeteria.CafeteriaFrame;

public class AdminIO {
	
	/**
	 * 메뉴를 추가
	 * @param rows
	 */
	public void insertdMenu(String[] rows) {
		AdminFrame.adminModel.addRow(rows);// 한줄 단위로만 대입 가능
		AdminFrame.menuList.add(new Menu(rows[0], Integer.parseInt(rows[1]), Integer.parseInt(rows[2])));//메뉴리스트에 추가하기
	}

	/**
	 * 파일탐색기를 열어 메뉴를 불러오기
	 */
	public void loadMenu() {
		//파일 탐색기 
		JFileChooser chooser  = new JFileChooser();
		int returnVal = chooser.showOpenDialog(null);//파일 다이얼로그 열기
		if(returnVal == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile(); 
            try(ObjectInputStream ois =
    				new ObjectInputStream(
    						new BufferedInputStream(
    								new FileInputStream(f))); ){
    			while(true) {
    				Menu menu = (Menu)ois.readObject();				
    				AdminFrame.menuList.add(menu);					//객체를 읽어서 menulist에 저장함
    			}
			}catch(EOFException eof) {		//더이상 읽을 객체가 없으면 발생하는 예외
				JOptionPane.showMessageDialog(null, "파일읽어오기 완료");
			}catch (Exception e1) {
				e1.printStackTrace();
			}
            
			//객체 읽기 완료 후 menuList에 저장된 데이터를 model에 저장
			for(int i =0 ; i < AdminFrame.menuList.size(); i++) {
				String menu = AdminFrame.menuList.get(i).getMenu();
   				int quantity = AdminFrame.menuList.get(i).getQuantity();
   				int price = AdminFrame.menuList.get(i).getPrice();
   				Object[] rowData  = {menu,quantity,price};
   				AdminFrame.adminModel.addRow(rowData); 
       		}
        }
		else
           JOptionPane.showMessageDialog(null, "파일 열기를 취소하였습니다.");
	}

	/**
	 * 메뉴를 저장
	 */
	public void saveMenu(String money) {
		// TODO Auto-generated method stub
		if(!AdminFrame.adminModel.equals(AdminFrame.menuList)) {
			if(money.equals(""))
				JOptionPane.showMessageDialog(null, "준비금을 입력하세요");
			else {
				JFileChooser chooser  = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "txt"); // filter 확장자 추가
		        chooser.setFileFilter(filter); // 파일 필터를 추가
				int returnVal1 = chooser.showSaveDialog(null);					//파일 다이얼로그 열기
				
				if(returnVal1 == JFileChooser.APPROVE_OPTION) {	// 열기 클릭
					String path = chooser.getSelectedFile().toString();
		            File file = new File(path); 
		            //객체로 저장
		    		try(ObjectOutputStream oos = 
		    				new ObjectOutputStream(
		    						new BufferedOutputStream(
		    								new FileOutputStream(file,false)));){
		    			for(Menu m : AdminFrame.menuList)
		    				oos.writeObject(m); 
						
		    		} catch (Exception e1) {
		    			e1.printStackTrace();
		    		}
				}
				AdminFrame.RESERVE_PRICE = Integer.parseInt(money);
				JOptionPane.showMessageDialog(null, "정상 등록 되었습니다");
				new CafeteriaFrame().setVisible(true);
			}
		}
	}
	
}




