<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">
$(function(){
	var today = new Date().formatDate('yyyy-MM-dd');
	var chopDate=new Date(new Date().setDate(new Date().getDate()+5)).formatDate('yyyy-MM-dd');
	$.CurrentNavtab.find('#j_stamp_visit_applicationDate').val(today);


	
})


function paymentVisitCheckSave(o){
	var err="";
	if(o.visitPurpose==null||o.visitPurpose==''){
		err+=' Visit Purpose can`t be empty!<br>'
	}
	if(o.projectNo==null||o.projectNo==''){
		err+=' Project No can`t be empty!<br>'
	}
	if(o.visitDateFrom==null||o.visitDateFrom==''||o.visitDateTo==null||o.visitDateTo==''){
		err+=' Visit Date can`t be empty!<br>'
	}
	if(o.totalLevelWorkHours==null||o.totalLevelWorkHours==''){
		err+=' Total Leave Work Hours can`t be empty!<br>'
	}
	if(o.businessTrip==null||o.businessTrip==''){
		err+=' Domestic/Oversea can`t be empty!<br>'
	}
	if(o.visitDetailPlace==null||o.visitDetailPlace==''){
		err+=' Visit Detail Place can`t be empty!<br>'
	}
	if(o.visitDetailPurpose==null||o.visitDetailPurpose==''){
		err+=' Visit Detail Purpose can`t be empty!<br>'
	}
	return err;
	
}

function paymentVisitFaceToDate(){
	var o=$.CurrentNavtab.find("#j_payment_visit_form").serializeJson();
	o.employees=$("#datagrid-payment-visit").data('allData');
	o.uId='${user.uid}';
	o.uName='${user.name}';
	
	return o;
}

function paymentVisitDateToFace(){
	
	
}

function paymentVisitSave(){
	var o=paymentVisitFaceToDate();	
	var err=paymentVisitCheckSave(o);
	if(err!=''){
		BJUI.alertmsg('warn', err); 
		return false;
	}
	

		
	BJUI.ajax('doajax', {
	    url: 'savePaymentVisit.action',
	    loadingmask: true,
	    data:{json:JSON.stringify(o)},	    
	    okCallback: function(json, options) {
            if(json.status='200'){
            	 BJUI.alertmsg('info', json.message); 
            	 $.CurrentNavtab.find("#j_stamp_visit_id").val(json.params.id);
            	 $.CurrentNavtab.find("#j_stamp_applicationCode").val(json.params.applicationCode);

            }else{
            	 BJUI.alertmsg('error', json.message); 
            }
	    }
	});		
	
	
	
}

function paymentVisitSubmit(){
	
	
}

function paymentVisitDelete(){
	BJUI.alertmsg('confirm', 'Delete?', {
		   okCall: function() {
				BJUI.ajax('doajax', {
				    url: 'deletePaymentVisit.action',
				    loadingmask: true,
				    data:{id:$.CurrentNavtab.find("#j_stamp_visit_id").val()},	    
				    okCallback: function(json, options) {
			            if(json.status='200'){
			            	 BJUI.navtab('closeCurrentTab'); 
			            }else{
			            	 BJUI.alertmsg('error', json.message); 
			            }
				    }
				});	
			  			   
		   }
	})
	
}

function paymentVisitApprove(){
	
	
}

function paymentVisitReject(){
	
	
}

function visitPurposeChange(){
	var val=$.CurrentNavtab.find("#j_stamp_visit_visitPurpose").val();
	if(val=='3'){
		$.CurrentNavtab.find("#j_stamp_visit_projectNo").val('8200-1');
	}else if (val=='4'){
		$.CurrentNavtab.find("#j_stamp_visit_projectNo").val('8200-1');
	}else if (val=='5'){
		$.CurrentNavtab.find("#j_stamp_visit_projectNo").val('8302-1');
	}else if (val=='6'){
		$.CurrentNavtab.find("#j_stamp_visit_projectNo").val('61721-999');
	}else if (val=='7'){
		$.CurrentNavtab.find("#j_stamp_visit_projectNo").val('8400-1');
	}else{
		$.CurrentNavtab.find("#j_stamp_visit_projectNo").val('');
	}
	
}

function paymentVisitShowButton(state){
	
	
	
}


function checkTotalLevelWorkHours(){
	
	var o = $.CurrentNavtab.find("#j_stamp_visit_totalLevelWorkHours");
	var ret =  /^[0-9]*[1-9][0-9]*$/;
	if(!ret.test(o.val())){
	  	 o.val("");
	  	 BJUI.alertmsg('error', 'Plese Enter Right Type'); 
	   }
	
	
}

</script>




<div class="bjui-pageContent">
    <div class="bs-example" style="width:1700px">
        <form id="j_payment_visit_form" data-toggle="ajaxform">
			<input type="hidden" name="id" id="j_stamp_visit_id" value="${param.id}">
            <div class="bjui-row-0" align="center">
            <h2 class="row-label">出差单申请</h2><br> 
            </div>
			<table class="table" style="font-size:12px;">
				<tr>
					<td width="200px">Reference No.<br>单号</td>
					<td width="200px"><input type="text" size="19" name="referenceNo" data-nobtn="true" id="j_stamp_visit_referenceNo" value="" placeholder="保存或者送审后生成"  readonly=""></td>					
					<td width="200px"></td>
					<td width="200px"></td>	
					<td width="900px"></td>				
				</tr>
				<tr>
					<td >Applicant Date<br>申请日期</td>
					<td ><input type="text" size="19" name="applicantDate" id="j_stamp_visit_applicantDate"  data-toggle="datepicker" placeholder="点击选择日期" data-nobtn="true" ></td>
					<td ></td>
					<td ></td>
					<td></td>				
				</tr>
				<tr>
					<td >Visit Purpose <label style="color:red;font-size:12px"><b>*</b></label><br>
					出差目的  <label style="color:red;font-size:12px"><b>*</b></label></td>
					<td >
						<select name="visitPurpose" data-toggle="selectpicker" onchange="visitPurposeChange();" id=j_stamp_visit_visitPurpose data-width="500px" >
                        	<option value="" selected></option>
                        	<option value="1" >Supplier Visit (供应商拜访)</option>
                        	<option value="2" >Customer Visit (客户拜访)</option>
                        	<option value="3" >New Customer Development (新客户开发)</option>
                        	<option value="4" >Local Goverment Visit (当地政府相关拜访)</option>
                        	<option value="5" >Training (培训)</option>
                        	<option value="6" >QRF Related (QRF 相关）</option>
                        	<option value="7" >New Factory Investment (新厂房投资)</option>
                        </select>
					</td>
					<td ></td>
					<td ></td>
					<td></td>				
				</tr>
				<tr>
					<td >Project No  <label style="color:red;font-size:12px"><b>*</b></label><br>
					项目号  <label style="color:red;font-size:12px"><b>*</b></label></td>
					<td ><input type="text" size="19" name="projectNo" id="j_stamp_visit_projectNo"></td>
					<td ></td>
					<td ></td>
					<td></td>				
				</tr>
				<tr>
					<td >Visit Date  <label style="color:red;font-size:12px"><b>*</b></label><br>
					出差期间  <label style="color:red;font-size:12px"><b>*</b></label></td>
					<td colspan="4"><input type="text" size="19" name="visitDateFrom" id="j_stamp_visit_visitDateFrom"  data-toggle="datepicker" placeholder="点击选择日期" data-nobtn="true" id="j_stamp_lendDate" value="" data-pattern="yyyy-MM-dd HH:mm:ss" />
					TO:&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="text" size="19" name="visitDateTo" id="j_stamp_visit_visitDateTo"  data-toggle="datepicker" placeholder="点击选择日期" data-nobtn="true" id="j_stamp_lendDate" value="" data-pattern="yyyy-MM-dd HH:mm:ss" />				
					</td>	
				</tr>
				<tr>
					<td >Total Leave Work Hours  <label style="color:red;font-size:12px"><b>*</b></label><br>
					总共出差工作天数时数  <label style="color:red;font-size:12px"><b>*</b></label></td>
					<td colspan="4"><input type="text" size="19" name="totalLevelWorkHours" id="j_stamp_visit_totalLevelWorkHours" onblur="checkTotalLevelWorkHours()" >&nbsp;Hours</td>		
				</tr>
				<tr>
					<td >Domestic/Oversea  <label style="color:red;font-size:12px"><b>*</b></label><br>
					国内国外  <label style="color:red;font-size:12px"><b>*</b></label></td>
					<td colspan="4">
					<input type="radio" name="businessTrip" data-toggle="icheck" id="j_stamp_visit_domestic" value="Domestic 国内" data-label="Domestic 国内">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="businessTrip" data-toggle="icheck" id="j_stamp_visit_oversea" value="Oversea 国外" data-label="Oversea 国外">
					</td>					
				</tr>
				<tr>
					<td>
						Visit Detail Place<label style="color:red;font-size:12px"><b>*:</b></label>:<br>出差具体目的地 <label style="color:red;font-size:12px"><b>*</b></label>:
					</td>
					<td colspan="4">
						<textarea cols="50" rows="3" id="j_stamp_visitDetailPlace"  name="visitDetailPlace" data-toggle="autoheight"></textarea>
					</td>
				</tr>
				<tr>
					<td>
						Visit Detail Purpose<label style="color:red;font-size:12px"><b>*:</b></label>:<br>出差具体事由<label style="color:red;font-size:12px"><b>*</b></label>:
					</td>
					<td colspan="4">
						<textarea cols="50" rows="3" id="j_stamp_visitDetailPurpose"  name="visitDetailPurpose" data-toggle="autoheight"></textarea>
					</td>
				</tr>
				<tr height="400px">
					<td colspan="5" >
						   <table class="table table-bordered" id="datagrid-payment-visit" data-toggle="datagrid" data-options="{
						        height: '100%',
						        gridTitle : '出差人员',
						        showToolbar: true,
						        local: 'local',
						        toolbarItem: 'add,edit,del',
						        dataUrl: 'getPaymentVisitEmployee.action?visitID=${param.id}',
						        delUrl:'json/ajaxDone.json',
						        editUrl: 'sign/payment/visit/payment_visit_edit.jsp',
						        editMode: {dialog:{width:'800',height:430,title:'Edit Employee',mask:true}},
						        paging: false,
						        showCheckboxcol: true,
						        linenumberAll: true,
						        filterThead: false,
						        contextMenuB: true,
						        hScrollbar: true,
						        columnShowhide:false,
						        columnFilter:false,
						        columnMenu:false,
						        fieldSortable:false
						    }">
						        <thead>
						            <tr>
						            	<th data-options="{name:'employeeNo',width:150,align:'center',finalWidth:'true'}" >Visit Employee No.*<br>出差人员</th>
						            	<th data-options="{name:'employeeBUNo',width:150,align:'center',finalWidth:'true'}" >Visit Employee BU No.<br>出差人员部门代码</th>
										<th data-options="{name:'employeeName',width:150,align:'center',finalWidth:'true'}">Visit Employee Name<br>出差人员姓名</th>
										<th data-options="{name:'advanceAmount',width:120,align:'center',finalWidth:'true'}">Advance Amount<br>预付款金额</th>
										<th data-options="{name:'hotelBookingByHR',width:150,align:'center',finalWidth:'true'}">A.是否HR预定酒店<br>Hotel Booking by HR</th>
										<th data-options="{name:'hotelName',width:200,align:'center',finalWidth:'true'}">酒店名称<br>Hotel Name</th>
										<th data-options="{name:'carArrangeByHR',width:120,align:'center',finalWidth:'true'}">B.是否HR派车<br>Car Arrange by HR</th>
										<th data-options="{name:'carArrangePeriod',width:150,align:'center',finalWidth:'true'}">派车时间<br>Car Arrange Period</th>
										<th data-options="{name:'airTickerBookingByHR',width:150,align:'center',finalWidth:'true'}">C.是否HR定机票<br>Air Ticket Booking by HR</th>
										<th data-options="{name:'flightNO',width:100,align:'center',finalWidth:'true'}">具体航班号<br>Flight No.</th>
										<th data-options="{name:'visarArrangeByHR',width:150,align:'center',finalWidth:'true'}">D.是否HR办理签证<br>Visar Arrange by HR</th>
						            </tr>
						        </thead>
						    </table>
					</td>
				</tr>
				<tr>
					<td colspan="5" align="center">
	            		<button type="button" id="stamp-save" class="btn-default" data-icon="save" onClick="paymentVisitSave()" >Save</button>&nbsp;&nbsp;&nbsp;&nbsp;
	            		<button type="button" id="stamp-submit" class="btn-default" data-icon="arrow-up" onClick="paymentVisitSubmit()">Submit</button>&nbsp;&nbsp;&nbsp;&nbsp;
	            		<button type="button" id="stamp-delete" class="btn-default" data-icon="close" onClick="paymentVisitDelete()" style="display:none">Delete</button>
            		</td>		
				</tr>	
				<tr>
				<td colspan="5" >
				Approval Route:<br>
				签核路径:
				</td>
				</tr>		
				<tr>
					<td colspan="5">
						<table class="table" width="100%" id="stamp_approve_his" >
							<tr name='head'>
								<th width="80px">
								
								</th>
								<th style="display:none;">
								
								</th>
								<th width="80px" >
									Cimtas ID
								</th>
								<th width="120px" >
									Approvel Name
								</th>
								<th width="80px" >
									BU NO.
								</th>
								<th width="230px" >
									Comment
								</th>
								<th width="80px" >
									Status
								</th>
								<th width="150px" >
									Operation Date
								</th>
								<th width="150px"align="center" >
									Operation
								</th>
							</tr>					
						</table>	
					</td>
				</tr>
			</table>		


        </form>
    </div>
</div>