package com.java.kiosk.view.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.java.kiosk.controller.AdminController;
import com.java.kiosk.model.vo.Menu;
import com.java.kiosk.run.MainProcess;
import com.java.kiosk.util.MyUtil;




public class AdminFrame extends JFrame{
	public AdminController adminControll = new AdminController();
	public static List<Menu> menuList = new ArrayList<>();
	public static DefaultTableModel adminModel;
	public static int RESERVE_PRICE;
	public static final int MAX =15;
	boolean check = true;
	
	public AdminFrame(int w, int h, String title) {
		MyUtil.init(this, w, h, title);
		
		//컬럼 정보
		Object[] columnNames = {"메뉴", "수량", "가격"};
		//빈 테이블을 만들기 위한  데이터 관리 객체
		adminModel = new DefaultTableModel(columnNames, 0);
		JTable menuTable = new JTable(adminModel);
		JScrollPane scrollPane = new JScrollPane(menuTable);
		
		add(scrollPane);
		
		
		// 테이블 아래쪽에 데이터 입력 할수있는 패널
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new GridLayout(4, 0));//버튼크기 조절가능
		JPanel panel = new JPanel();
		JTextField menuText = new JTextField(20);
		JTextField quantityText = new JTextField(15);
		JTextField priceText = new JTextField(15);
		JTextField moneyText = new JTextField(15);
		panel.add(new JLabel("메뉴"));
		panel.add(menuText);
		panel.add(new JLabel("수량"));
		panel.add(quantityText);
		panel.add(new JLabel("가격"));
		panel.add(priceText);
		panel.add(new JLabel("준비금"));
		panel.add(moneyText);
		panel.setBackground(new Color(122, 207, 204));
		btnPanel.add(panel);
		
		
		//추가  삭제 버튼 
		JPanel panel2 = new JPanel();
		JButton btnload = new JButton("불러오기");
		JButton btnAdd = new JButton("Add");
		JButton btnDel = new JButton("Delete");
		JButton btnStudent = new JButton("학생용");
		btnload.setBackground(new Color(255, 255, 252));
		btnAdd.setBackground(new Color(255, 255, 252));
		btnDel.setBackground(new Color(255, 255, 252));
		btnStudent.setBackground(new Color(255, 255, 252));
		panel2.setBackground(new Color(122, 207, 204));
		panel2.add(btnload);
		panel2.add(btnAdd);
		panel2.add(btnDel);
		panel2.add(btnStudent);
		btnPanel.add(panel2);

		JPanel panel3 = new JPanel();//디자인용
		panel3.setBackground(new Color(122, 207, 204));
		btnPanel.add(panel3);
		JPanel panel4 = new JPanel();//디자인용
		panel4.setBackground(new Color(105, 59, 13));
		btnPanel.add(panel4);
		//불러오기 button
		btnload.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(check) {
					adminControll.loadMenuList();
					//첫번재 불러오기 확인
					check = false;
		        }
				//2번째 이후 불러오기
				else {
					//처음 불러온 list와 model을 초기화 해줌
					menuList.clear();
					adminModel.setNumRows(0);
					adminControll.loadMenuList();
				}
			}
		});
			
		//table에 메뉴 추가 button
		btnAdd.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				if(menuList.size() < 16 && menuList.size() >= 0 ) {
					String[] rows = new String[3];
					rows[0] = menuText.getText();
					rows[1] = quantityText.getText();
					rows[2] = priceText.getText();
					//모든칸이 채워져잇는지 검사
					if(menuText.getText().equals("") || quantityText.getText().equals("") ||quantityText.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "모든 칸을 채워주세요");
					}
					else {
						//숫자인지 검사
						if(!isNumber(quantityText.getText())) {
							JOptionPane.showMessageDialog(null, "수량에는 숫자만 입력하세요");
						}
						else if(!isNumber(priceText.getText())) {
							JOptionPane.showMessageDialog(null, "가격에는 숫자만 입력하세요");
						}
						else {
							adminControll.insertMenu(rows);
						}
					}
					
					//입력후 필드 값 제거
					menuText.setText("");
					quantityText.setText("");
					priceText.setText("");
				
				//메뉴 16개 넘어가면 더는 추가 할 수 없다는 문구 띄우기 완료
				}else {
					if(menuList.size() > MAX)
						JOptionPane.showMessageDialog(null, "메뉴 입력을 초과 하셨습니다!",
							"경고",JOptionPane.ERROR_MESSAGE);
				}
			}	
		});
		
		//메뉴 삭제 Button
		btnDel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 선택한 줄(row)의 번호 알아내기
				int rowIndex = menuTable.getSelectedRow();
				if (rowIndex == -1) return;
				menuList.remove(rowIndex); // 데이터 지우기
				adminModel.removeRow(rowIndex);
				rowIndex = menuList.size();//의논하기 
			}
		});
		
		//CafeteriaFrame으로 이동 + menu Txt파일로 저장
		btnStudent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//여기 버튼을 눌러서 학생용으로 이동
				adminControll.saveMenuList(moneyText.getText());
			}
		}); 
		
		
		add(btnPanel, BorderLayout.SOUTH);		
	}
	public boolean isNumber(String str) {
        for (int i = 0; i < str.length(); i++) {
            if(!(
            str.charAt(i) == '0' || str.charAt(i) == '1' || str.charAt(i) == '2'
                        || str.charAt(i) == '3' || str.charAt(i) == '4' || str.charAt(i) == '5'
                        || str.charAt(i) == '6' || str.charAt(i) == '7' || str.charAt(i) == '8'
                        || str.charAt(i) == '9'
                    )) {
                return false;
            }
        }
        return true;
    } 
	
}
	
	

