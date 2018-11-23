package com.sign.other;

public class PaymentVisitHelp {
	
	
	public final static String SAVE="Save";
	
	public final static String SUBMIT="Manager Approval";
	
	public final static String REJECT="Reject";
	
	public final static String INVALID="Invalid";
	
	public final static String COMPLETED="End Approval";
	
	public final static String CANCEL="Cancel";
	

	public static String getVisitPurpose(String type) {
		String result="";
		
		try {
			switch (Integer.parseInt(type)) {
			case 1:
				result= "Supplier Visit (供应商拜访)";
				break;
			case 2:
				result= "Customer Visit (客户拜访)";
				break;
			case 3:
				result= "New Customer Development (新客户开发)";
				break;
			case 4:
				result= "New Facility purchase (新设备采购)";
				break;
			case 5:
				result= "Training (培训)";
				break;
			case 6:
				result= "QRF Related (QRF 相关)";
				break;
			case 7:
				result= "New Factory Investment (新厂房投资)";
				break;
			default:
				result= "";
				break;
			}
			
			return result;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return "Error";
		}
		
	}
	
}
