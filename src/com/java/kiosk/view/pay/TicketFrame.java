package com.java.kiosk.view.pay;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


public final class TicketFrame extends JFrame {
	JTable ticket;
	
	public TicketFrame(JTable orderCall) {	
		int row=orderCall.getRowCount();
		String[] header = {"이름", "수량", "단가", "금액"};
		String[][] contents= new String[row][4];
		ticket = new JTable(contents, header);

		for(int i=0; i<orderCall.getRowCount(); i++) {
			ticket.setValueAt((String)orderCall.getValueAt(i, 0), i, 0);
			ticket.setValueAt(String.valueOf(orderCall.getValueAt(i, 1)), i, 1);
			ticket.setValueAt( String.valueOf(((int)orderCall.getValueAt(i, 2)/(int)orderCall.getValueAt(i, 1))),
					i, 2);
			ticket.setValueAt(String.valueOf(orderCall.getValueAt(i, 2)), i, 3);
		}
		
		
		setTitle("식권");
		setSize(500,300);
		setBackground(new Color(122, 207, 204));
		setResizable(false);
		/*
		 * panelSouth 작성
		 */
		JPanel panelSouth = new JPanel();
		JLabel label2= new JLabel();
		label2.setBackground(new Color(122, 207, 204));
		label2.setText("이용해 주셔서 감사합니다.");
		label2.setFont(new Font("SeoulNamsanB", Font.BOLD, 20));
		label2.setHorizontalAlignment(JLabel.CENTER);
		panelSouth.add(label2);

		add(panelSouth, BorderLayout.SOUTH);
		
		// table 작성
		JPanel panelCenter = new JPanel();
		FlowLayout al = new FlowLayout();
		al.setHgap(20);
		al.setVgap(20);
		panelCenter.setBackground(new Color(255, 255, 252));
		panelCenter.setLayout(al);
		
		ticket.setRowHeight(50);
		
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer celAlignLeft = new DefaultTableCellRenderer();
		celAlignLeft.setHorizontalAlignment(JLabel.LEFT);
		
		ticket.getColumn(ticket.getColumnName(0)).setPreferredWidth(130);
		ticket.getColumn(ticket.getColumnName(0)).setCellRenderer(celAlignLeft);
		ticket.getColumn(ticket.getColumnName(1)).setPreferredWidth(13);
		ticket.getColumn(ticket.getColumnName(1)).setCellRenderer(celAlignCenter);
		ticket.getColumn(ticket.getColumnName(2)).setPreferredWidth(13);
		ticket.getColumn(ticket.getColumnName(2)).setCellRenderer(celAlignCenter);
		ticket.getColumn(ticket.getColumnName(3)).setPreferredWidth(13);
		ticket.getColumn(ticket.getColumnName(3)).setCellRenderer(celAlignCenter);
		
		ticket.setFont(new Font("SeoulNamsanB", Font.BOLD, 20));
		ticket.getTableHeader().setFont(new Font("SeoulNamsanB", Font.BOLD, 20));
		ticket.getTableHeader().setReorderingAllowed(false);
		ticket.getTableHeader().setResizingAllowed(false);
		ticket.setShowVerticalLines(false);
		ticket.setShowHorizontalLines(false);
		ticket.setShowVerticalLines(false);
		ticket.setShowHorizontalLines(false);
		JScrollPane jscp = new JScrollPane(ticket);
		
		
		panelCenter.add(jscp);
		add(panelCenter,BorderLayout.CENTER);
		pack();
		
		setVisible(true);
	}
}

