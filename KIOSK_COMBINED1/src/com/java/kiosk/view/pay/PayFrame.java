package com.java.kiosk.view.pay;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

import com.java.kiosk.controller.PriceCalculation;
import com.java.kiosk.controller.SalesMenuController;
import com.java.kiosk.controller.cafeteria.Cafeteria;
import com.java.kiosk.model.vo.SalesMenu;


public final class PayFrame extends JFrame {
	public static ArrayList<SalesMenu> salesHistoryList= new ArrayList<>();
	
	
	private JTable orderCall;
	private int insertMoney;
	private JTable orderDisplay;
	boolean paymentState;

	public PayFrame(JTable orderCall) {
		this.orderCall = orderCall;
		this.insertMoney=0;
		this.paymentState=false;
		
		setTitle("주문 리스트");
		setSize(1000,1000);
		setLocationRelativeTo(null);
		setResizable(false);
		
		makeUI();
		
		setVisible(true);
	}
		
	
	private void makeUI() {
		/*
		 * paneCenter 작성
		 */
		JPanel panelCenter= new JPanel();
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		panelCenter.setLayout(gbl);
		
		int row=orderCall.getRowCount();
		String[] header = {"이름", "수량", "단가", "금액"};
		String[][] contents= new String[row][4];
		orderDisplay = new JTable(contents, header);
		
		for(int i=0; i<orderCall.getRowCount(); i++) {
			orderDisplay.setValueAt((String)orderCall.getValueAt(i, 0), i, 0);
			orderDisplay.setValueAt(String.valueOf(orderCall.getValueAt(i, 1)), i, 1);
			orderDisplay.setValueAt( String.valueOf(((int)orderCall.getValueAt(i, 2)/(int)orderCall.getValueAt(i, 1))),
					i, 2);
			orderDisplay.setValueAt(String.valueOf(orderCall.getValueAt(i, 2)), i, 3);
		}
		
		
		//구매 목력 출력
		orderDisplay.setRowHeight(50);
		
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer celAlignLeft = new DefaultTableCellRenderer();
		celAlignLeft.setHorizontalAlignment(JLabel.LEFT);
		
		orderDisplay.getColumn(orderDisplay.getColumnName(0)).setPreferredWidth(300);
		orderDisplay.getColumn(orderDisplay.getColumnName(0)).setCellRenderer(celAlignLeft);
		orderDisplay.getColumn(orderDisplay.getColumnName(1)).setPreferredWidth(10);
		orderDisplay.getColumn(orderDisplay.getColumnName(1)).setCellRenderer(celAlignCenter);
		orderDisplay.getColumn(orderDisplay.getColumnName(2)).setPreferredWidth(10);
		orderDisplay.getColumn(orderDisplay.getColumnName(2)).setCellRenderer(celAlignCenter);
		orderDisplay.getColumn(orderDisplay.getColumnName(3)).setPreferredWidth(13);
		orderDisplay.getColumn(orderDisplay.getColumnName(3)).setCellRenderer(celAlignCenter);
		
		orderDisplay.setFont(new Font("SeoulNamsanB", Font.BOLD, 30));
		orderDisplay.getTableHeader().setFont(new Font("SeoulNamsanB", Font.BOLD, 30));
		orderDisplay.getTableHeader().setReorderingAllowed(false);
		orderDisplay.getTableHeader().setResizingAllowed(false);
		orderDisplay.setShowVerticalLines(false);
		orderDisplay.setShowHorizontalLines(false);
		JScrollPane jscp = new JScrollPane(orderDisplay);
		
		gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 2;
        gbc.fill = GridBagConstraints.BOTH;
        panelCenter.setBackground(new Color(122, 207, 204));
        panelCenter.add(jscp, gbc);
        
        
        /*
         * panelCenterDown 작성
         */
        JPanel panelCenterDown = new JPanel();
        panelCenterDown.setLayout(new BorderLayout());
        
        //합계 내역
        PriceCalculation pCalc = new PriceCalculation(orderCall);
        int[] totalResult = pCalc.priceCalc();
        
        //좌측 수량
        JLabel resultSum = new JLabel();
        resultSum.setText("  총 수량 : " + totalResult[0] + "개  ");
        resultSum.setFont(new Font(null , Font.PLAIN, 30));
        panelCenterDown.add(resultSum, BorderLayout.WEST);
        
        //우측 가격
        JLabel resultPrice = new JLabel();
        resultPrice.setText("  총 결제 금액 : " + totalResult[1] + "원  ");
        resultPrice.setFont(new Font(null , Font.PLAIN, 30));
        panelCenterDown.add(resultPrice, BorderLayout.EAST);
        
    	gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panelCenter.add(panelCenterDown, gbc);
        
		add(panelCenter,BorderLayout.CENTER);

		/*
		 * panelNorth 작성
		 */
		JPanel panelNorth= new JPanel();
		panelNorth.setLayout(new FlowLayout(FlowLayout.CENTER, 10,20));
		
		JLabel label1 = new JLabel();
		label1.setText("주문 리스트를 확인해 주세요");
		label1.setFont(new Font("SeoulNamsanB", Font.BOLD, 20));
		panelNorth.add(label1);
		add(panelNorth, BorderLayout.NORTH);
		
		
		/*
		 * panelSouth
		 */
		JPanel panelSouth = new JPanel();
		panelSouth.setLayout(new FlowLayout(FlowLayout.CENTER, 100,30));
		JButton confirmButton = new JButton();
		confirmButton.setText("결제하기");
		confirmButton.setPreferredSize(new Dimension(200,130));
		confirmButton.setFont(new Font("SeoulNamsanB", Font.BOLD, 30));
		confirmButton.setBackground(new Color(162, 208, 219));
		panelSouth.add(confirmButton);
		JButton cancelButton = new JButton();
		cancelButton.setText("취소하기");
		cancelButton.setPreferredSize(new Dimension(200,130));
		cancelButton.setFont(new Font("SeoulNamsanB", Font.BOLD, 30));
		cancelButton.setBackground(new Color(162, 208, 219));
		panelSouth.add(cancelButton);
		add(panelSouth, BorderLayout.SOUTH);
		
		/*
		 * 결제하기 버튼 액션
		 * 
		 * 버튼 클릭시
		 * PayFrame상에 돈 결제 화면 출력
		 * 
		 */
		confirmButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				makeUI_Payment(panelNorth, panelCenter, panelCenterDown, panelSouth,totalResult);
			}
		});
		
		/*
		 * 취소하기 버튼 액션
		 * 
		 * 버튼 클릭시
		 * PayFrame 소멸
		 * 
		 */
		cancelButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "결제를 취소하였습니다", 
						"알림", JOptionPane.ERROR_MESSAGE);
				Cafeteria.model.setNumRows(0);
				dispose();
			}
		});
	}
	

	private void makeUI_Payment(JPanel panelNorth,JPanel panelCenter, JPanel panelCenterDown, JPanel panelSouth, int[] totalResult) {
		setTitle("결제창");

		panelNorth.removeAll();
		panelCenter.removeAll();
		panelCenterDown.removeAll();
		panelSouth.removeAll();	
		
		panelNorth.validate();
		panelNorth.repaint();
		panelCenter.validate();
		panelCenter.repaint();
		panelCenterDown.validate();
		panelCenterDown.repaint();
		panelSouth.validate();
		panelSouth.repaint();

		
		/*
		 * panelNorth 작성
		 */
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		panelNorth.setLayout(gbl);
		
		//좌측 수량
		JLabel resultSum = new JLabel();
        resultSum.setText("결제 금액 ");
        resultSum.setFont(new Font("SeoulNamsanB", Font.BOLD, 40));
      
      
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panelNorth.add(resultSum, gbc);
        
        //우측 가격
        JLabel resultPrice = new JLabel();
        resultPrice.setText(totalResult[1] + "원");
        resultPrice.setFont(new Font("SeoulNamsanB", Font.BOLD, 40));
        
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panelNorth.add(resultPrice, gbc);
        
        //좌측 알림
        JLabel jlabel1= new JLabel();
        jlabel1.setText("투입 금액");
        jlabel1.setFont(new Font("SeoulNamsanB", Font.BOLD, 40));
        jlabel1.setBackground(new Color(122, 207, 204));
        
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panelNorth.add(jlabel1, gbc);
        
        //우측 투입 금액창
        JTextField jtf1= new JTextField(10);
        jtf1.setFont(new Font("SeoulNamsanB", Font.BOLD, 40));
        jtf1.setEditable(false);
        
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.weightx = 2;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panelNorth.add(jtf1, gbc);
        
        //빈레이블
        JLabel jbl1 =new JLabel();
       
        gbc.gridx = 6;
        gbc.gridy = 3;
        gbc.weightx = 3;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panelNorth.add(jbl1, gbc);
        
        
        add(panelNorth, BorderLayout.NORTH);
		
        
        /*
		 * panelCenter 작성
		 */
        FlowLayout fl = new FlowLayout();
        fl.setHgap(60);
        fl.setVgap(60);
        panelCenter.setLayout(fl);
        
        
        JButton billA= new JButton();
        billA.setText("50000원");
        billA.setFont(new Font("SeoulNamsanB", Font.BOLD, 30));
        billA.setPreferredSize(new Dimension(250, 250));
        billA.setPreferredSize(new Dimension(250, 250));
        billA.setBackground(new Color(255, 255, 252));
        panelCenter.add(billA);
        
        JButton billB = new JButton();
        billB.setText("10000원");
        billB.setFont(new Font("SeoulNamsanB", Font.BOLD, 30));
        billB.setPreferredSize(new Dimension(250, 250));
        billB.setBackground(new Color(255, 255, 252));
        panelCenter.add(billB);
        
        JButton billC = new JButton();
        billC.setText("5000원");
        billC.setFont(new Font("SeoulNamsanB", Font.BOLD, 30));
        billC.setPreferredSize(new Dimension(250, 250));
        billC.setBackground(new Color(255, 255, 252));
        panelCenter.add(billC);
        
        JButton billD = new JButton();
        billD.setText("1000원");
        billD.setFont(new Font("SeoulNamsanB", Font.BOLD, 30));
        billD.setPreferredSize(new Dimension(250, 250));
        billD.setBackground(new Color(255, 255, 252));
        panelCenter.add(billD);
        
        JButton coinA = new JButton();
        coinA.setText("500원");
        coinA.setFont(new Font("SeoulNamsanB", Font.BOLD, 30));
        coinA.setPreferredSize(new Dimension(250, 250));
        coinA.setBackground(new Color(255, 255, 252));
        panelCenter.add(coinA);
        
        JButton coinB = new JButton();
        coinB.setText("100원");
        coinB.setFont(new Font("SeoulNamsanB", Font.BOLD, 20));
        coinB.setPreferredSize(new Dimension(250, 250));
        coinB.setBackground(new Color(255, 255, 252));
        panelCenter.add(coinB);
        add(panelCenter, BorderLayout.CENTER);
        
        
        /*
		 * panelSouth 작성
		 */
		panelSouth.setLayout(new FlowLayout(FlowLayout.CENTER, 100,30));
		JButton confirmButton = new JButton();
		confirmButton.setText("결제하기");
		confirmButton.setPreferredSize(new Dimension(200,130));
		confirmButton.setFont(new Font("SeoulNamsanB", Font.BOLD, 20));
		confirmButton.setBackground(new Color(162, 208, 219));
		panelSouth.add(confirmButton);
		
		JButton refundButton = new JButton();
		refundButton.setText("반환하기");
		refundButton.setPreferredSize(new Dimension(200,130));
		refundButton.setFont(new Font("SeoulNamsanB", Font.BOLD, 20));
		refundButton.setBackground(new Color(162, 208, 219));
		panelSouth.add(refundButton);
		
		JButton cancelButton = new JButton();
		cancelButton.setText("취소하기");
		cancelButton.setPreferredSize(new Dimension(200,130));
		cancelButton.setBackground(new Color(162, 208, 219));
		cancelButton.setFont(new Font("SeoulNamsanB", Font.BOLD, 20));
		panelSouth.add(cancelButton);
		add(panelSouth, BorderLayout.SOUTH);
        
		validate();
		repaint();
		
		/*
		 * 5만원 버튼 액션
		 * 
		 * 버튼 클릭시
		 * 투입 금액 화면 표시 업데이트
		 * 
		 */
		billA.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				insertMoney+=50000;
				jtf1.setText(insertMoney +"원");
				
			}
		});
		

		/* 
		 * 만원 버튼 액션
		 * 
		 * 버튼 클릭시
		 * 투입 금액 화면 표시 업데이트
		 * 
		 */
		billB.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				insertMoney+=10000;
				jtf1.setText(insertMoney +"원");
				
			}
		});
		
		/* 
		 * 오천원 버튼 액션
		 * 
		 * 버튼 클릭시
		 * 투입 금액 화면 표시 업데이트
		 * 
		 */
		billC.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				insertMoney+=5000;
				jtf1.setText(insertMoney +"원");
				
			}
		});
		
		/* 
		 * 천원 버튼 액션
		 * 
		 * 버튼 클릭시
		 * 투입 금액 화면 표시 업데이트
		 * 
		 */
		billD.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				insertMoney+=1000;
				jtf1.setText(insertMoney +"원");
				
			}
		});
		
		/* 
		 * 오백원 버튼 액션
		 * 
		 * 버튼 클릭시
		 * 투입 금액 화면 표시 업데이트
		 * 
		 */
		coinA.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				insertMoney+=500;
				jtf1.setText(insertMoney +"원");
				
			}
		});
		
		/* 
		 * 백원 버튼 액션
		 * 
		 * 버튼 클릭시
		 * 투입 금액 화면 표시 업데이트
		 * 
		 */
		coinB.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				insertMoney+=100;
				jtf1.setText(insertMoney +"원");
				
			}
		});
		
		/* 
		 * 결제하기 버튼 액션 - 버전 1
		 * 
		 * 버튼 클릭시
		 * 주문 완료시 
		 * TicketFrame 새성
		 * 리스트에 구매내역 저장
		 * PayFrame 소멸
		 * 
		 * 
		 */
		confirmButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(insertMoney == totalResult[1]) {
					SalesMenuController.add(orderCall);
					JOptionPane.showMessageDialog(null, "결제가 완료되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
					paymentState=true;
					new TicketFrame(orderCall);
					Cafeteria.model.setNumRows(0);
					dispose();
				}else if(insertMoney > totalResult[1]) {
					SalesMenuController.add(orderCall);
					JOptionPane.showMessageDialog(null, "결제가 완료되었습니다. 거스드름 돈 " + 
							(insertMoney - totalResult[1]) + "원 지급하였습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
					paymentState=true;
					new TicketFrame(orderCall);
					Cafeteria.model.setNumRows(0);
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "투입 금액이 부족합니다.", "알림", JOptionPane.ERROR_MESSAGE);
				}
			
			}
		});
		
		
		/* 
		 * 반환하기 버튼 액션
		 * 
		 * 버튼 클릭시
		 * insertMoney =0
		 * 
		 */
		refundButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				insertMoney=0;
				jtf1.setText(insertMoney +"원");
				JOptionPane.showMessageDialog(null, "금액을 반환했습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
				
			
			}
		});
		
		/* 
		 * 취소하기 버튼 액션
		 * 
		 * 버튼 클릭시
		 * PayFrame 소멸
		 * 
		 */
		cancelButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(null, "결제를 취소하였씁니다", "알림", JOptionPane.ERROR_MESSAGE);
				Cafeteria.model.setNumRows(0);
				dispose();
			
			}
		});
	}
	
	
}

