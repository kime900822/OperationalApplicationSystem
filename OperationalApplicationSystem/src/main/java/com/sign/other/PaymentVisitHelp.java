package com.sign.other;

public class PaymentVisitHelp {
	
	
	public final static String SAVE="SAVE";
	
	public final static String SUBMIT="SUBMIT";
	
	public final static String REJECT="REJECT";
	
	public final static String INVALID="INVALID";
	
	public final static String COMPLETED="COMPLETED";
	

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
				result= "Local Goverment Visit (当地政府相关拜访) ";
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
