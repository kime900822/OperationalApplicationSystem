package com.sign.other;

public class StampState {


	/**
	 * 保存
	 */
	public final static String SAVE="Submit Required";
	
	/**
	 * 提交待审批
	 */
	public final static String SUBMIT="Level1 Approval";
	
	/**
	 * 一级审批结束
	 */
	public final static String LEVEL1="Level2 Approval";
	
	/**
	 * 一级审批退回
	 */
	public final static String LEVEL1_REJECT="Level1 Rejected";
	
	/**
	 * 二级审批结束
	 */
	public final static String LEVEL2="Level3 Approval";
	
	/**
	 * 二级审批退回
	 */
	public final static String LEVEL2_REJECT="Level2 Rejected";
	
	/**
	 * 三级审批结束
	 */
	public final static String LEVEL3="Inform Approval";
	
	/**
	 * 三级审批退回
	 */
	public final static String LEVEL3_REJECT="Level3 Rejected";
	
	/**
	 * 审批结束
	 */
	public final static String INFORM="END Approve";
	
	/**
	 * 审批返回
	 */
	public final static String INFORM_REJECT="Inform Rejected";
	
}
