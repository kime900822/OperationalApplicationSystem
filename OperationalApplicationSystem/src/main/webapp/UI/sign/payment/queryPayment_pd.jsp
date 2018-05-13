<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript">
function downPaymentPO(){
	var o=$.CurrentNavtab.find("#form_download_payment_po").serializeJson();
	
	  BJUI.ajax('ajaxdownload', {
		    url:'downloadOfPD.action',
		    data: {applicationDate_f:o.applicationDate_f,applicationDate_t:o.applicationDate_t,code:o.code,UID:o.UID,urgent:o.urgent,paymentSubject:o.paymentSubject,departmentID:o.departmentID,state:o.state,downloadType:o.downloadType},
		    loadingmask: true
		})	
	
}
</script>

<div class="bjui-pageContent">
  <div class="bs-example" >
        <form action="downloadOfPD.action" data-toggle="ajaxform" id="form_download_payment_po">
            <div class="bjui-row col-2">
                <label class="row-label">Application Date：</label>
                <div class="row-input ">
            		<input type="text" name="applicationDate_f"  data-nobtn="true"  placeholder="点击选择日期" data-nobtn="true" id="q_payment_applicationDate_f" value="" data-toggle="datepicker" size="19" data-rule="date">
                </div>
                <label class="row-label">to:</label>
                <div class="row-input ">
            		<input type="text" name="applicationDate_t"  data-nobtn="true"  placeholder="点击选择日期" data-nobtn="true" id="q_payment_applicationDate_t" value="" data-toggle="datepicker" size="19" data-rule="date">
                </div>
            	<label class="row-label">Sequential Code：</label>
                <div class="row-input ">
            		<input type="text" name="code" value="" id="q_payment_code" size="19">
                </div>
            	<label class="row-label">Cimtas ID：</label>
                <div class="row-input ">
        			<input type="text" name="UID" value="" id="q_payment_UID" size="19">
                </div>
            	<label class="row-label">Urgent：</label>
                <div class="row-input ">
        			<select name="urgent" data-toggle="selectpicker" id="q_payment_urgent"  data-width="190px">
		              <option value=""></option>
		              <option value="1">Y</option>
		              <option value="0">N</option>
	            	</select>
                </div>
            	<label class="row-label">Payment Subject：</label>
                <div class="row-input ">
        			<select name="paymentSubject" data-toggle="selectpicker" id="q_payment_paymentSubject"  data-width="190px">
		              <option value=""></option>
		              <option value="1">Fixed Asset 固定资产</option>
		              <option value="2">Raw Material 原材料</option>
		              <option value="3">Consumable 消耗品</option>
		              <option value="4">Subcontractor 外包</option>
		              <option value="5">Service 服务</option>
		              <option value="6">Petty Cash备用金</option>
		              <option value="7">Other 其他</option>
	            	</select>
                </div>
            	<label class="row-label">BU NO：</label>
                <div class="row-input ">
            		<input type="text" name="departmentID" class="form-control" size="19" data-rule="number" >
                </div>
                <label class="row-label">Status：</label>
                <div class="row-input ">
            		<select name="state" data-toggle="selectpicker" id="q_payment_state"  data-width="190px">
		              <option value="" >all</option>
		              <option value="0">Submit Required</option>
		              <option value="1">Manager Approval</option>
		              <option value="2">Audit Approval</option>
		              <option value="3">Audit Rejected</option>
		              <option value="4">Finance Approval</option>
		              <option value="5">Invalid</option>
		              <option value="6">Finance Rejected</option>
		              <option value="7">GM Approval</option>
		              <option value="8">Payment Completed</option>
	            	</select>
                </div>
                <label class="row-label">Type:</label>
                <div class="row-input ">
            		<select name="downloadType" data-toggle="selectpicker" id="q_payment_download_type"  data-width="190px">
		              <option value="1" selected>PO</option>
		              <option value="2">Supplier Code</option>
	            	</select>
                </div>
            </div>
         	<table width="100%">
         		<tr>
					<td align="center">
						<button type="button" id="payment-print" class="btn-default" data-icon="print" onclick="downPaymentPO()">Download</button>
					</td>
         		</tr>
         	</table>
         </form>
         
         
   </div>

  
</div>