
package com.java.kiosk.controller;

import javax.swing.JOptionPane;
import javax.swing.JTable;

public final class PriceCalculation {
	private JTable orderCall;

	public PriceCalculation(JTable orderCall) {
		super();
		this.orderCall = orderCall;
	}
	
	public int[] priceCalc() {
		int totalNum=0;
		int totalPrice=0;
		for(int i=0; i < orderCall.getRowCount(); i++) {
				totalNum+= (int)orderCall.getValueAt(i, 1);
				totalPrice+=(int)orderCall.getValueAt(i, 2);
		}
		int[] totalResult = {totalNum, totalPrice};
	    return  totalResult;
	}
}