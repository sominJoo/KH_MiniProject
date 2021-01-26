package com.java.kiosk.view.admin;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.java.kiosk.util.MyUtil;

public class CalculateFrame extends JFrame {
	public static final int RESERVE_PRICE = AdminFrame.RESERVE_PRICE;	//준비금
	JPanel right;
	JLabel inputLabel;
	JLabel resultLabel;
	JLabel label50000;
	JLabel label10000;
	JLabel label5000;
	JLabel label1000;
	JLabel Label;
	int totalPrice =0;
	int resultPrice =0;
	boolean checking = true;
	public CalculateFrame() {
		MyUtil.init(this, 1000, 800, "정산");
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		new leftPanel(this).setVisible(true);
		new rightPanel(this).setVisible(true);

	}
	
	public class leftPanel extends JPanel {
		public leftPanel(CalculateFrame f) {
			// TODO Auto-generated method stub
			
			JPanel mainPanel = new JPanel();
			mainPanel.setBounds(0, 0, 500, 770);
			mainPanel.setBorder(new LineBorder(Color.BLACK));
			mainPanel.setLayout(new GridLayout(0,1,20,20));
			
			JPanel panel1 = new JPanel();
			panel1.setLayout(new FlowLayout(FlowLayout.LEFT));
			JLabel label1 = new JLabel("준비금 : ");
			label1.setFont(new Font("맑은  고딕",Font.BOLD,20));
			JLabel reserveLabel = new JLabel(Integer.toString(RESERVE_PRICE));
			reserveLabel.setFont(new Font("맑은  고딕",Font.BOLD,20));
			panel1.add(label1);
			panel1.add(reserveLabel);
			mainPanel.add(panel1);
			
			JPanel panel2 = new JPanel();
			panel2.setLayout(new FlowLayout(FlowLayout.LEFT));
			JLabel label2 = new JLabel("판매금액 : ");
			label2.setFont(new Font("맑은  고딕",Font.BOLD,20));
			JLabel totalPriceLabel = new JLabel(Integer.toString(ResultFrame.totalSalesPrice));
			totalPriceLabel.setFont(new Font("맑은  고딕",Font.BOLD,20));
			panel2.add(label2);
			panel2.add(totalPriceLabel);
			mainPanel.add(panel2);
			
			JPanel panel3 = new JPanel();
			panel3.setLayout(new FlowLayout(FlowLayout.LEFT));
			JLabel label3 = new JLabel("회수금 : ");
			label3.setFont(new Font("맑은  고딕",Font.BOLD,20));
			JButton returnBtn = new JButton("입력하기");
			returnBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO
				}
			});
			panel3.add(label3);
			panel3.add(returnBtn);
			mainPanel.add(panel3);
			
			
			JPanel panel4 = new JPanel();
			panel4.setLayout(new FlowLayout(FlowLayout.LEFT));
			JLabel label4 = new JLabel("총 판매금액 : ");
			label4.setFont(new Font("맑은  고딕",Font.BOLD,20));
			Label = new JLabel("0");
			Label.setFont(new Font("맑은  고딕",Font.BOLD,20));
			panel4.add(label4);
			panel4.add(Label);
			mainPanel.add(panel4);
			
			JPanel panel5 = new JPanel();
			JButton finishBtn = new JButton("정산 완료");
			finishBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					JOptionPane.showMessageDialog(null	, "정산이 완료되었습니다. 프로그램을 종료합니다.");
					System.exit(0);
				}
			});
			panel5.add(finishBtn);
			mainPanel.add(panel5);
			
			f.add(mainPanel);
		}
	}

	
	public class rightPanel extends JPanel {
		public rightPanel(CalculateFrame f) {
			// TODO Auto-generated method stub
			inputLabel = new JLabel(Integer.toString(totalPrice));
			inputLabel.setFont(new Font("맑은  고딕",Font.BOLD,20));
			resultLabel  = new JLabel("0");
			resultLabel.setFont(new Font("맑은  고딕",Font.BOLD,20));

			label50000 = new JLabel("0");
			label10000 = new JLabel("0");
			label5000 = new JLabel("0");
			label1000 = new JLabel("0");
						
			JPanel mainPanel = new JPanel();
			mainPanel.setBounds(500, 0, 500, 770);
			mainPanel.setBorder(new LineBorder(Color.BLACK));
			mainPanel.setLayout(new GridLayout(0,1,20,20));
			
			JPanel panel0 = new JPanel();
			panel0.setLayout(new FlowLayout(FlowLayout.LEFT,60,10));
			JLabel label0_1 = new JLabel("권종");
			label0_1.setFont(new Font("맑은  고딕",Font.BOLD,20));
			JLabel label0_2 = new JLabel("매수");
			label0_2.setFont(new Font("맑은  고딕",Font.BOLD,20));
			JLabel label0_2_1 = new JLabel();
			label0_2_1.setFont(new Font("맑은  고딕",Font.BOLD,20));
			JLabel label0_3 = new JLabel("금액");
			label0_3.setFont(new Font("맑은  고딕",Font.BOLD,20));
			panel0.add(label0_1);
			panel0.add(label0_2);
			panel0.add(label0_2_1);
			panel0.add(label0_3);
			mainPanel.add(panel0);
			
			JPanel panel1 = new JPanel();
			panel1.setLayout(new FlowLayout(FlowLayout.LEFT,40,0));
			JLabel label1 = new JLabel("50000 : ");
			label1.setFont(new Font("맑은  고딕",Font.BOLD,20));
			JTextField txt50000 = new JTextField(5);
			label50000.setFont(new Font("맑은  고딕",Font.BOLD,20));
			JButton checkBtn1  =new JButton("확인");
			checkBtn1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String str = txt50000.getText();
					if(str.equals(""))
						JOptionPane.showMessageDialog(null, "매수를 입력해주세요");
					else {
						char check = str.charAt(0);
						//입력값이 숫자인지 검사
						if(check >= 48 && check <= 58) {
							//50000원 * 매수
							int price = Integer.parseInt(str)*50000;
							label50000.setText(Integer.toString(price));
							//입력한 매수의 총 합계
							totalPrice =price
									+ (Integer.parseInt(label10000.getText()))
									+ (Integer.parseInt(label5000.getText()))
									+ (Integer.parseInt(label1000.getText()));
							inputLabel.setText(Integer.toString(totalPrice));
							//차이금액
							resultPrice = ResultFrame.totalSalesPrice -totalPrice;
							resultLabel.setText(Integer.toString(resultPrice));
							
						}
						else {
							JOptionPane.showMessageDialog(null, "숫자만 입력하세요");
							label50000.setText("");
						}
					}
				}
			});
			panel1.add(label1);
			panel1.add(txt50000);
			panel1.add(checkBtn1);
			panel1.add(label50000);
			mainPanel.add(panel1);
			
			JPanel panel2 = new JPanel();
			panel2.setLayout(new FlowLayout(FlowLayout.LEFT,40,0));
			JLabel label2 = new JLabel("10000 : ");
			label2.setFont(new Font("맑은  고딕",Font.BOLD,20));
			JTextField txt10000 = new JTextField(5);
			label10000.setFont(new Font("맑은  고딕",Font.BOLD,20));
			JButton checkBtn2  =new JButton("확인");
			checkBtn2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String str = txt10000.getText();
					if(str.equals(""))
						JOptionPane.showMessageDialog(null, "매수를 입력해주세요");
					else {
						char check = str.charAt(0);
						//입력값이 숫자인지 검사
						if(check >= 48 && check <= 58) {
							int price = Integer.parseInt(str)*10000;
							label10000.setText(Integer.toString(price));
							//입력한 매수의 총 합계
							totalPrice = price
									+ (Integer.parseInt(label50000.getText()))
									+ (Integer.parseInt(label5000.getText()))
									+ (Integer.parseInt(label1000.getText()));
							inputLabel.setText(Integer.toString(totalPrice));
							//차이금액
							resultPrice = ResultFrame.totalSalesPrice -totalPrice;
							resultLabel.setText(Integer.toString(resultPrice));						
							}
						else {
							JOptionPane.showMessageDialog(null, "숫자만 입력하세요");
							label10000.setText("");
						}
					}
				}
			});
			panel2.add(label2);
			panel2.add(txt10000);
			panel2.add(checkBtn2);
			panel2.add(label10000);
			mainPanel.add(panel2);
			
			JPanel panel3 = new JPanel();
			panel3.setLayout(new FlowLayout(FlowLayout.LEFT,40,0));
			JLabel label3 = new JLabel("5000 :  ");
			label3.setFont(new Font("맑은  고딕",Font.BOLD,20));
			JTextField txt5000 = new JTextField(5);
			label5000.setFont(new Font("맑은  고딕",Font.BOLD,20));
			JButton checkBtn3  =new JButton("확인");
			checkBtn3.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String str = txt5000.getText();
					if(str.equals(""))
						JOptionPane.showMessageDialog(null, "매수를 입력해주세요");
					else {
						char check = str.charAt(0);
						//입력값이 숫자인지 검사
						if(check >= 48 && check <= 58) {
							int price = Integer.parseInt(str)*5000;
							label5000.setText(Integer.toString(price));
							//입력한 매수의 총 합계
							int totalPrice = price
									+ (Integer.parseInt(label50000.getText()))
									+ (Integer.parseInt(label10000.getText()))
									+ (Integer.parseInt(label1000.getText()));
							inputLabel.setText(Integer.toString(totalPrice));
							//차이금액
							resultPrice = ResultFrame.totalSalesPrice -totalPrice;
							resultLabel.setText(Integer.toString(resultPrice));
						}
						else {
							JOptionPane.showMessageDialog(null, "숫자만 입력하세요");
							label5000.setText("");
						}
						
					}
				}
			});
			panel3.add(label3);
			panel3.add(txt5000);
			panel3.add(checkBtn3);
			panel3.add(label5000);
			mainPanel.add(panel3);
			
			JPanel panel4 = new JPanel();
			panel4.setLayout(new FlowLayout(FlowLayout.LEFT,40,0));
			JLabel label4 = new JLabel("1000 :  ");
			label4.setFont(new Font("맑은  고딕",Font.BOLD,20));
			JTextField txt1000 = new JTextField(5);;
			label1000.setFont(new Font("맑은  고딕",Font.BOLD,20));
			JButton checkBtn4  =new JButton("확인");
			checkBtn4.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String str = txt1000.getText();
					if(str.equals(""))
						JOptionPane.showMessageDialog(null, "매수를 입력해주세요");
					else {
						char check = str.charAt(0);
						//입력값이 숫자인지 검사
						if(check >= 48 && check <= 58) {
							int price = Integer.parseInt(str)*1000;
							label1000.setText(Integer.toString(price));
							//입력한 매수의 총 합계
							int totalPrice =price
									+ (Integer.parseInt(label50000.getText()))
									+ (Integer.parseInt(label10000.getText()))
									+ (Integer.parseInt(label5000.getText()));
							inputLabel.setText(Integer.toString(totalPrice));
							//차이금액
							resultPrice = ResultFrame.totalSalesPrice -totalPrice;
							resultLabel.setText(Integer.toString(resultPrice));
						}
						else {
							JOptionPane.showMessageDialog(null, "숫자만 입력하세요");
							label1000.setText("");
						}
						
					}
				}
			});
			panel4.add(label4);
			panel4.add(txt1000); 
			panel4.add(checkBtn4); 
			panel4.add(label1000);
			mainPanel.add(panel4);
			
			JPanel panel5 = new JPanel();
			panel5.setLayout(new FlowLayout(FlowLayout.LEFT,40,0));
			JLabel label6 = new JLabel("합계 금액 : ");
			label6.setFont(new Font("맑은  고딕",Font.BOLD,20));
			panel5.add(label6);
			panel5.add(inputLabel);
			mainPanel.add(panel5);
			
			JPanel panel6 = new JPanel();
			panel6.setLayout(new FlowLayout(FlowLayout.LEFT,40,0));
			JLabel label7  = new JLabel("차이 금액 : ");
			label7.setFont(new Font("맑은  고딕",Font.BOLD,20));
			panel6.add(label7);
			panel6.add(resultLabel);
			mainPanel.add(panel6);
			
			JPanel panel7 = new JPanel();
			panel7.setLayout(new FlowLayout(FlowLayout.CENTER,40,0));
			JButton finishBtn = new JButton("확인");
			finishBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					Label.setText(Integer.toString(Integer.parseInt(inputLabel.getText())-RESERVE_PRICE));
				}
			});
			panel7.add(finishBtn);
			mainPanel.add(panel7);
			
			
			f.add(mainPanel);
			
		}
		
	}
}
