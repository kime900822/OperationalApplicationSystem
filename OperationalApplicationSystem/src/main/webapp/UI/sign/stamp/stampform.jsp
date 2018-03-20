<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">

</script>
<div class="bjui-pageContent">
    <div class="bs-example" style="width:1000px">
        <form id="j_payment_form" data-toggle="ajaxform">
			<input type="hidden" name="id" id="j_payment_id" value="">
			<input type="hidden" name="state" id="j_payment_state" value="">
			<input type="hidden" name="isPrint" id="j_payment_isPrint" value="">
            <div class="bjui-row-0" align="center">
            <h2 class="row-label">Stamp Using Application System 借/用 章 申 请 系 统</h2><br> 
            </div>
			<table class="table" style="font-size:12px;">
				<tr>
					<td width="250px">Application Date<br>申请日期:</td>
					<td width="250px"><input type="text" size="19" name="applicationDate" data-nobtn="true" id="j_payment_applicationDate" value=""  readonly="" ></td>
					<td width="250px">Application Code<br>申请单号:</td>
					<td width="250px"><input type="text" size="19" name="requestPaymentDate" data-nobtn="true" id="j_payment_requestPaymentDate" value="" data-toggle="datepicker" ></td>					
				</tr>
				<tr>
					<td>
						Form Filler:<br>填单人:
					</td>
					<td>
						<input type="text" name="contacturalPaymentDate" size="19" value="" data-nobtn="true" id="j_payment_contacturalPaymentDate"  data-toggle="datepicker" >
					</td>
					<td>
						Department of Form Filler:<br>填单人部门:
					</td>
					<td>
						<input type="text" name="code" value="" readonly="" id="j_payment_code" size="19">
					</td>					
				</tr>
				<tr>
					<td>
						Applicant *<br>申请人 * :
					</td>
					<td>
						<input type="text" name="UID" value="${user.uid}-${user.name}" id="j_payment_UID" readonly="" data-rule="required" size="19">
					</td>
					<td>
						Department of Applicant:<br>申请人部门:
					</td>
					<td>
						<input type="text" name="departmentID" value="${user.department.name}-${user.department.did}" id="j_payment_departmentID" readonly="" data-rule="required" size="19">
					</td>					
				</tr>
				<tr>
					<td>
						Contact Number * :<br>联系方式 * :
					</td>
					<td>
						<input type="text" name="supplierCode" id="j_payment_supplierCode" value="" size="19" data-rule="required" onchange="checkSupplierCode(this);">
					</td>
					<td colspan="2">
					</td>
				</tr>
				<tr>
					<td>
						Stamp Type * :  <label style="color:red;font-size:12px"><b>*</b></label><br>
						印章种类 * :<label style="color:red;font-size:12px"><b>*</b></label>
					</td>
					<td>
						<input type="radio" name="payType" data-toggle="icheck" id="j_payment_cash" value="Cash" data-label="支付现金 <br>Cash">
					</td>
					<td>
						<input type="radio" name="payType" data-toggle="icheck" id="j_payment_banking" value="Banking" data-label="银行支付 <br>Banking">
					</td>	
					<td>
						<input type="radio" name="payType" data-toggle="icheck" id="j_payment_advanceWriteoff" value="AdvanceWriteoff" data-label="核销预付  <br>Advance Write-off">
					</td>				
				</tr>
				<tr>
					<td>
						Document Type * :<br>文件类型 * :
					</td>
					<td id="j_payment_beneficiary_tr">
						<input type="text" name="beneficiary" id="j_payment_beneficiary" value="" readonly=""  size="19">
					</td>
					<td>
						Project Responsible:<br>审批人选择 :
					</td>
					<td id="j_payment_beneficiaryAccountNO_tr">
						<input type="text" name="beneficiaryAccountNO" id="j_payment_beneficiaryAccountNO" value="" readonly=""  size="19">
					</td>					
				</tr>
				<tr>
					<td colspan="4">
					Remark: Stamp which lend in the same day need to give back, if need special usage need to make request again.
					<br>备注：原则上，印章当天借出，当天应该归还，若有特殊需求，请再提单
					</td>
				</tr>
				<tr>
				<td>
						Chop Date * <br>用印日期 * :
					</td>
					<td id="j_payment_beneficiaryE_tr">
						<input type="text" name="beneficiaryE" id="j_payment_beneficiaryE" value="" readonly=""  size="19">
					</td>
					<td colspan="2">
						用印日期与借印日期必须填一个<br>
						填了借印日期，一定要填归还日期
					</td>					
				</tr>
				<tr>
					<td>
						Lend Date * :<br>借印日期 * :
					</td>
					<td>
						<input  type="checkbox" name="beneficiaryChange" id="j_payment_beneficiaryChange" data-toggle="icheck" value="1" data-label="">
					</td>
					<td>
						Give Back Date * :<br>归还日期 * :
					</td>
					<td>
						<input  type="checkbox" name="beneficiaryAccountNOChange" id="j_payment_beneficiaryAccountNOChange" data-toggle="icheck" value="1" data-label="">
					</td>					
				</tr>
				<tr>
					<td>
						Chop Qty * :<br>用印份数 * :
					</td>
					<td>
						<input type="text" name="refNoofBank" value="" id="j_payment_refNoofBank" size="19"  readonly="">
					</td>
					<td colspan="2">
					</td>
				</tr>
				<tr>
					<td>
						Chop Object * :  <label style="color:red;font-size:12px"><b>*</b></label><br>受文单位 * :  <label style="color:red;font-size:12px"><b>*</b></label>
					</td>
					<td colspan="3">
						<textarea cols="80" rows="3" id="j_payment_usageDescription"  name="usageDescription" data-toggle="autoheight"></textarea>
					</td>
				</tr>
				<tr>
					<td>
						Urgent:<br>急件:
					</td>
					<td>
						<input type="text" name="refNoofBank" value="" id="j_payment_refNoofBank" size="19"  readonly="">
					</td>
					<td colspan="2">
					</td>
				</tr>
				<tr>
					<td>
						Usage Description:<br>申请原因:
					</td>
					<td colspan="3">
						<textarea cols="80" rows="3" id="j_payment_usageDescription"  name="usageDescription" data-toggle="autoheight"></textarea>
					</td>
				</tr>
				<tr>
					<td>
						Attacment Upload:<br>附件上传:
					</td>
					<td>
						<input type="file" id="file1" name="file" />
    					<input type="button" value="upload" id="upfile_invoice" />
					</td>
					<td colspan="2" >
						<table class="table" id="upfile_invoice_list" >	
							<tr>
								<th width="400px" align="center">File Name</th>
								<th width="100px" align="center">Delete</th>
							</tr>									
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="4">
					Mark: Please point the location where need chop
					<br>备注：请在附件中指出盖章的位置
					</td>
				</tr>

				
				
				<tr>
					<td colspan="4" align="center">
	            		<button type="button" id="payment-save" class="btn-default" data-icon="save" >Save</button>
	            		<button type="button" id="payment-submit" class="btn-default" data-icon="arrow-up" >Submit</button>
	            		<button type="button" id="payment-approve" class="btn-default" data-icon="check" >Approve(同意)</button>
	            		<button type="button" id="payment-reject" class="btn-default" data-icon="close" >Reject(拒绝)</button>
	            		<button type="button" id="payment-assign" class="btn-default" data-icon="undo" >Assign(转交)</button>
	            		<button type="button" id="payment-acc" class="btn-default" data-icon="check" >Approve(同意)</button>
	            		<button type="button" id="payment-print" class="btn-default" data-icon="print" >Print Out(打印)</button>
	            		<button type="button" id="payment-delete" class="btn-default" data-icon="close" >Delete(删除)</button>
            		</td>				
				</tr>
				<tr>
					
					
				</tr>				
			</table>		






        </form>
    </div>
</div>