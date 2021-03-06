package com.sign.other;

import com.mysql.cj.jdbc.interceptors.ServerStatusDiffInterceptor;

public class PaymentHelp {

	/**
	 * 已保存状态
	 */
	public final static String SAVEPAYMENT="0";
	
	/**
	 * 提交待审批
	 */
	public final static String SUBPAYMENT="1";
	
	
	
	/**
	 * 提交待审批
	 */
	public final static String SUBPAYMENT2="9";
	
	/**
	 * 财务待审批
	 */
	public final static String FINACEPAYMENT="4";
	
	
	/**
	 * 审批通过
	 */
	public final static String APPROVEPAYMENT="2";
	
	/**
	 * 审批不通过
	 */
	public final static String REJECTPAYMENT="3";
	
	/**
	 * 作废状态
	 */
	public final static String INVALIDPAYMENT="5";
	
	
	/**
	 * 出纳退单
	 */
	public final static String FINANCEREJECTED="6";
	
	/**
	 * 总经理审批
	 */
	public final static String GMAPPROVE="7";
	
	/**
	 * 审批单完成
	 */
	public final static String PAYMENTCOMPLETED="8";
	/**
	 * 退回状态
	 */
	public final static String RETURNPAYMENT="0";
	
	public static String getState(String state) {
		if (state.equals("0")) {
			return "Submit Required";
		}else if (state.equals("1")) {
			return "Manager Approval";
		}else if (state.equals("2")) {
			return "Audit Approval";
		}else if (state.equals("3")) {
			return "Audit Rejected";
		}else if (state.equals("4")) {
			return "Finance Approval";
		}else if (state.equals("5")) {
			return "Invalid";
		}else if (state.equals("6")) {
			return "Finance Rejected";
		}else if (state.equals("7")) {
			return "GM Approval";
		}else if (state.equals("8")) {
			return "Payment Completed";
		}else {
			return "";
		}
		
	}
	
	public static String getPaymentSubject(String paymentSubject) {
		if (paymentSubject.equals("1")) {
			return "Fixed Asset 固定资产";
		}else if (paymentSubject.equals("2")) {
			return "Raw Material 原材料";
		}else if (paymentSubject.equals("3")) {
			return "Consumable 消耗品";
		}else if (paymentSubject.equals("4")) {
			return "Subcontractor 外包";
		}else if (paymentSubject.equals("5")) {
			return "Service 服务";
		}else if (paymentSubject.equals("6")) {
			return "Petty Cash备用金";
		}else if (paymentSubject.equals("7")) {
			return "Other 其他";
		}else if (paymentSubject.equals("8")) {
			return "Travel 差旅费";
		}else {
			return "";
		}
		
	}
	
	public final static String FIXEDASSET="1";
	public final static String RAWMATERIAL="2";
	public final static String CONSUMABLE="3";
	public final static String SUBCONTRACTOR="4";
	public final static String SERVICE="5";
	public final static String PETTYCASH="6";
	public final static String OTHER="7";
	public final static String TRAVEL="8";
	
	
	public final static String PAYMENT_TERM_ADVANCE="1";
	public final static String PAYMENT_TERM_PAYMENT_AT_SIGHT="2";
	public final static String PAYMENT_TERM_UPON_RECEIVING="3";
	public final static String PAYMENT_TERM_UPON_APPROVAL="4";
	public final static String PAYMENT_TERM_UPON_INVOICE="5";
	public final static String PAYMENT_TERM_OTHER="6";
	
	
	public final static String PAYTYPE_CASH="Cash";
	public final static String PAYTYPE_BANKING="Banking";
	public final static String PAYTYPE_ADVANCEWRITEOFF="AdvanceWriteoff";
}
