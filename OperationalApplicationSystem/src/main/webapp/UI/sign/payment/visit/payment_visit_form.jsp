<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">
$(function(){
	var today = new Date().formatDate('yyyy-MM-dd');
	$.CurrentNavtab.find('#j_payment_visit_applicantDate').val(today);
	$.CurrentNavtab.find("#j_payment_visit_visitDateFrom").val(today+' 08:00:00');
	$.CurrentNavtab.find("#j_payment_visit_visitDateTo").val(today+' 17:00:00');
	$.CurrentNavtab.find("#j_payment_visit_totalLeaveWorkHours").val('8.0');

	if('${param.id}'!=null&&'${param.id}'!=''){
		paymentVisitDateToFace('${param.id}');
	}
	
	paymentVisitShowButton('');
	
	
	
	//单元格点击编辑
	
	
	
	
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
	if(o.totalLeaveWorkHours==null||o.totalLeaveWorkHours==''){
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
	
	if(((o.currency!=null&&o.currency!='')&&(o.advanceAmount==null||o.advanceAmount=='0.00')) || ((o.currency==null||o.currency=='')&&(o.advanceAmount!='0.00'&&o.advanceAmount!='0'))){
		err+=' Currency and amount must be both input or empty. !<br>'
	}
	
	if(o.currency!=null&&o.currency!=''&&o.currency!='0.00'&&o.currency=='RMB'&&parseFloat(o.advanceAmount)<3000){
		err+=' RMB must be greater than 3000 !<br>'
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

function paymentVisitDateToFace(id){
	
	$.CurrentNavtab.find('form:eq(0)').trigger('bjui.ajaxStart')
	
	BJUI.ajax('doajax', {
	    url: 'getPaymentVisitByID.action',
	    loadingmask: true,
	    data:{id:id},	    
	    okCallback: function(json, options) {
	    	if(json.status='200'){
	    		$.CurrentNavtab.find("#j_payment_visit_referenceNo").val(json.referenceNo);
	    		$.CurrentNavtab.find("#j_payment_visit_visitDateFrom").val(json.visitDateFrom);
	    		$.CurrentNavtab.find("#j_payment_visit_visitDateTo").val(json.visitDateTo);
	    		$.CurrentNavtab.find("#j_payment_visit_totalLeaveWorkHours").val(json.totalLeaveWorkHours);
	    		$.CurrentNavtab.find("#j_payment_visit_applicantDate").val(json.applicantDate);
	    		$.CurrentNavtab.find("#j_payment_visit_visitPurpose").selectpicker().selectpicker('val',json.visitPurpose).selectpicker('refresh');
	    		$.CurrentNavtab.find("#j_payment_visit_projectNo").val(json.projectNo);
	    		if(json.currency!=undefined){
	    			$.CurrentNavtab.find("#j_payment_visit_currency").selectpicker().selectpicker('val',json.currency).selectpicker('refresh');
	    		}
	    		$.CurrentNavtab.find("#j_payment_visit_advanceAmount").val(json.advanceAmount);
	    		if(json.businessTrip=='Domestic 国内')
	    		{
	    			$.CurrentNavtab.find("#j_payment_visit_domestic").iCheck('check'); 
	    		}else if(json.businessTrip=='Oversea 国外'){
	    			$.CurrentNavtab.find("#j_payment_visit_oversea").iCheck('check'); 
	    		}
	    		
	    		$.CurrentNavtab.find("#j_payment_visitDetailPlace").val(json.visitDetailPlace);
	    		$.CurrentNavtab.find("#j_payment_visitDetailPurpose").val(json.visitDetailPurpose);
	    		
	    		var obj=$.CurrentNavtab.find('#paymentVisit_approve_his');  
	    		obj.children().children().eq(0).siblings().remove();
	    		
        		var maxLevel=-1;
        		if(json.approveHis!=undefined&&json.approveHis!=""){
        			$.each(json.approveHis,function(i,item){	  		
	    				obj.append("<tr><td>"+item.name+"</td><td style='display:none'></td><td>"+item.uId+"</td><td>"+item.uName+"</td><td>"+item.dId+"</td><td>"+item.comment+"</td><td>"+item.status+"</td><td>"+item.date+"</td><td></td></tr>");	    				
	    				maxLevel=parseInt(item.level);
        			})
        						
        		}
        		
	    		if(json.approveList!=undefined&&json.approveList!=''&&json.state.indexOf('Rejected')<0){	    
	    			
	    			$.each(json.approveList,function(i,item){	
	    				if(i>maxLevel){
    						if(i==maxLevel+1&&'${user.uid}'==item.uid&&json.state!='0'&&json.state!='-1'&&json.state!='-2'&&json.state!='9'){
			    				obj.append("<tr><td>"+item.name+"</td><td style='display:none'>"+item.level+"</td><td>"+item.uid+"</td><td>"+item.uname+"</td><td>"+item.did+"</td><td></td><td></td><td></td><td><button type='button' id='payment-visit-approve' class='btn btn-success' style='width:50px;' name='Approved' onclick='paymentVisitApprove(this)'   >√</button>&nbsp;&nbsp;<button type='button' id='payment-reject'  style='width:50px;' class='btn btn-danger' name='Rejected' onclick='paymentVisitApprove(this)' >×</button></td></tr>");	    				
	    					}else{
			    				obj.append("<tr><td>"+item.name+"</td><td style='display:none'>"+item.level+"</td><td>"+item.uid+"</td><td>"+item.uname+"</td><td>"+item.did+"</td><td></td><td></td><td></td><td></td></tr>");	    				
	    					}
	    				}
	    				
	    			})	    			
	    		}
	    		
	    		paymentVisitShowButton(json.state)
	    		$.CurrentNavtab.find('form:eq(0)').trigger('bjui.ajaxStop')
	    	
	    	}
	    }
	})
	
	
	
	
	
	
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
            	 $.CurrentNavtab.find("#j_payment_visit_id").val(json.params.id);
            	 $.CurrentNavtab.find("#j_payment_visit_referenceNo").val(json.params.referenceNO);
            	 paymentVisitShowButton('SAVE')

            }else{
            	 BJUI.alertmsg('error', json.message); 
            }
	    }
	});		
	
	
	
}

function paymentVisitSubmit(){
	
	var o=paymentVisitFaceToDate();	
	var err=paymentVisitCheckSave(o);
	if(err!=''){
		BJUI.alertmsg('warn', err); 
		return false;
	}
	
	BJUI.ajax('doajax', {
	    url: 'submitPaymentVisit.action',
	    loadingmask: true,
	    data:{id:$.CurrentNavtab.find("#j_payment_visit_id").val()},	    
	    okCallback: function(json, options) {
            if(json.status='200'){
            	BJUI.alertmsg('info', json.message); 
            	paymentVisitDateToFace(json.params.id)          		 
            }else{
            	 BJUI.alertmsg('error', json.message); 
            }
	    }
	})	
	
}

function paymentVisitDelete(){
	BJUI.alertmsg('confirm', 'Delete?', {
		   okCall: function() {
				BJUI.ajax('doajax', {
				    url: 'deletePaymentVisit.action',
				    loadingmask: true,
				    data:{id:$.CurrentNavtab.find("#j_payment_visit_id").val()},	    
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

function paymentVisitApprove(o){

	
	bootbox.prompt({
		size:"small",
		title:"Comment?", 
		callback:function (result) {
		if(result!=null){
			$.CurrentNavtab.find('form:eq(0)').trigger('bjui.ajaxStart')
			approveState=$(o).attr('name')
			if(approveState=='Rejected'&&(result==''||result==undefined)){
				bootbox.alert("Comment can`t empty!");
				 return false;
			}
			level = $(o).parent().siblings().eq(1).html();
			
			BJUI.ajax('doajax', {
		    url: 'approvePaymentVisit.action',
		    loadingmask: true,
		    data:{approveState:approveState,comment:result,tradeId:$.CurrentNavtab.find("#j_payment_visit_id").val(),level:level},	    
		    okCallback: function(json, options) {
		           if(json.status='200'){
		           	 BJUI.alertmsg('info', json.message); 
		           	 $(o).parent().siblings().eq(5).html(json.params.data.comment);
		           	 $(o).parent().siblings().eq(6).html(json.params.data.status);
		           	 $(o).parent().siblings().eq(7).html(json.params.data.date);
		           	 $(o).hide().siblings().hide();
		           }else{
		           	 BJUI.alertmsg('error', json.message); 
		           }
		           $.CurrentNavtab.find('form:eq(0)').trigger('bjui.ajaxStop')
		    }
		 	})
	 	
			}
		}
	})
}


function visitPurposeChange(){
	var val=$.CurrentNavtab.find("#j_payment_visit_visitPurpose").val();
	if(val=='3'){
		$.CurrentNavtab.find("#j_payment_visit_projectNo").val('8200-1');
	}else if (val=='4'){
		$.CurrentNavtab.find("#j_payment_visit_projectNo").val('8200-1');
	}else if (val=='5'){
		$.CurrentNavtab.find("#j_payment_visit_projectNo").val('8302-1');
	}else if (val=='6'){
		$.CurrentNavtab.find("#j_payment_visit_projectNo").val('61721-999');
	}else if (val=='7'){
		$.CurrentNavtab.find("#j_payment_visit_projectNo").val('8400-1');
	}else if (val=='8'){
		$.CurrentNavtab.find("#j_payment_visit_projectNo").val('8200-1');
	}else{
		$.CurrentNavtab.find("#j_payment_visit_projectNo").val('');
	}
	
}

function paymentVisitShowButton(state){
	var viewType='${param.viewtype}';
	if(viewType == ''){
		if(state==''){
			 $.CurrentNavtab.find('#payment-visit-save').show();
			 $.CurrentNavtab.find('#payment-visit-delete').hide();
			 $.CurrentNavtab.find('#payment-visit-submit').hide();
			 $.CurrentNavtab.find('#payment-visit-cancel').hide();
		}else if(state=='SAVE'||state.indexOf('Rejected')>0){
			 $.CurrentNavtab.find('#payment-visit-save').show();
			 $.CurrentNavtab.find('#payment-visit-delete').show();
			 $.CurrentNavtab.find('#payment-visit-submit').show();
			 $.CurrentNavtab.find('#payment-visit-cancel').hide();
			 $("input[id*='j_payment_visit']").removeAttr('disabled');
			 $("select[id*='j_payment_visit']").removeAttr('disabled');
			 $("textarea[id*='j_payment_visit']").removeAttr('disabled');
			 $.CurrentNavtab.find('#j_payment_visit_form').find(".btn-group[role='group']").show();
		}else{
			 $.CurrentNavtab.find('#payment-visit-save').hide();
			 $.CurrentNavtab.find('#payment-visit-delete').hide();
			 $.CurrentNavtab.find('#payment-visit-submit').hide();
			 $.CurrentNavtab.find('#payment-visit-cancel').hide();
			 $("input[id*='j_payment_visit']").attr('disabled','disabled');
			 $("select[id*='j_payment_visit']").attr('disabled','disabled');
			 $("textarea[id*='j_payment_visit']").attr('disabled','disabled');
			 $.CurrentNavtab.find('#j_payment_visit_form').find(".btn-group[role='group']").hide();
		}
		if(state=='END APPROVAL'){
			$.CurrentNavtab.find('#payment-visit-cancel').show();
		}
	}else{
		 $.CurrentNavtab.find('#payment-visit-delete').hide();
		 $.CurrentNavtab.find('#payment-visit-submit').hide();
		 $.CurrentNavtab.find('#payment-visit-save').hide();
		 $.CurrentNavtab.find('#payment-visit-cancel').hide();
		 $.CurrentNavtab.find('#j_payment_visit_form').find(".btn-group[role='group']").hide();
		 $("input[id*='j_payment_visit']").attr('disabled','disabled');
		 $("select[id*='j_payment_visit']").attr('disabled','disabled');
		 $("textarea[id*='j_payment_visit']").attr('disabled','disabled');
	}
	
	if(state==''||state=='SAVE'||state=='SUBMIT'||state.indexOf('Rejected')>0){
		 $.CurrentNavtab.find('#payment-visit-print-business').hide();
		 $.CurrentNavtab.find('#payment-visit-print-travel').hide();
	}else{
		 $.CurrentNavtab.find('#payment-visit-print-business').show();
		 $.CurrentNavtab.find('#payment-visit-print-travel').show();
	}
	
}


function checkTotalLeaveWorkHours(){
	
	var o = $.CurrentNavtab.find("#j_payment_visit_totalLeaveWorkHours");
	var ret =  /^\d+(\.\d+)?$/;
	if(!ret.test(o.val())&&o.val()!=''){
	  	 o.val("");
	  	 BJUI.alertmsg('error', 'Plese Enter Right Type'); 
	   }
	
	
}


function paymentVisitPrintBusiness(){
	BJUI.ajax('ajaxdownload', {
		 url: 'paymentVisitPrintBusiness.action',
		 loadingmask: true,
		 data:{id:$.CurrentNavtab.find("#j_payment_visit_id").val(),printUrl:'/templet/LeaveRequisition.pdf'}
	})
	
}

function paymentVisitPrintTravel(){
	BJUI.ajax('ajaxdownload', {
		 url: 'paymentVisitPrintTravel.action',
		 loadingmask: true,
		 data:{id:$.CurrentNavtab.find("#j_payment_visit_id").val()}
	})
}



function paymentVisitEmployeeLevelTime(){
	var startTime=$.CurrentNavtab.find("#j_payment_visit_visitDateFrom").val();
	var endTime=$.CurrentNavtab.find("#j_payment_visit_visitDateTo").val();
	var datetime1 = new Date(startTime), datetime2 = new Date(endTime);
	if(datetime1>=datetime2){
		BJUI.alertmsg('error', 'The end date must be greater than the start date.');
		$.CurrentNavtab.find("#j_payment_visit_totalLeaveWorkHours").val('');
		return false;
	}
	
	if(startTime!=''&& endTime!=''){
		$.CurrentNavtab.find("#j_payment_visit_totalLeaveWorkHours").val(leaveTime(startTime,endTime));
	}
	
}


function paymentVisitViewCancel(){
	BJUI.ajax('doajax', {
	    url: 'cancelPaymentVisit.action',
	    loadingmask: true,
	    data:{id:$.CurrentNavtab.find("#j_payment_visit_id").val()},	    
	    okCallback: function(json, options) {
            if(json.status='200'){
            	BJUI.alertmsg('info', json.message); 
            }else{
            	BJUI.alertmsg('error', json.message); 
            }
	    }
	});	
}


function paymengVisitCheckAdvanceAmount(o){
	var obj=$(o).val();
	if(checkNumber(obj)){
		$(o).val(toFiexd(obj,2));
	}else{
		$(o).val('0.00')
	}
	
	
}

</script>




<div class="bjui-pageContent">
    <div class="bs-example" style="width:1700px">
        <form id="j_payment_visit_form" data-toggle="ajaxform">
			<input type="hidden" name="id" id="j_payment_visit_id" value="${param.id}">
            <div class="bjui-row-0" align="center">
            <h2 class="row-label">出差单申请</h2>
            <h2 class="row-label">Business Travel Application</h2><br>
            </div>
			<table class="table" style="font-size:12px;">
				<tr>
					<td width="200px">Reference No.<br>单号</td>
					<td width="200px"><input type="text" size="19" name="referenceNo" data-nobtn="true" id="j_payment_visit_referenceNo" value="" placeholder="保存或者送审后生成"  readonly=""></td>					
					<td width="200px"></td>
					<td width="200px"></td>	
					<td width="900px"></td>				
				</tr>
				<tr>
					<td >Applicant Date<br>申请日期</td>
					<td ><input type="text" size="19" name="applicantDate" id="j_payment_visit_applicantDate"  data-toggle="datepicker" placeholder="点击选择日期" data-nobtn="true" ></td>
					<td ></td>
					<td ></td>
					<td></td>				
				</tr>
				<tr>
					<td >Visit Purpose <label style="color:red;font-size:12px"><b>*</b></label><br>
					出差目的  <label style="color:red;font-size:12px"><b>*</b></label></td>
					<td colspan="4">
						<select name="visitPurpose" data-toggle="selectpicker" onchange="visitPurposeChange();" id="j_payment_visit_visitPurpose" data-width="500px" >
                        	<option value="" selected></option>
                        	<option value="1" >Supplier Visit (供应商拜访)</option>
                        	<option value="2" >Customer Visit (客户拜访)</option>
                        	<option value="3" >New Customer Development (新客户开发)</option>
                        	<option value="4" >New Facility purchase (新设备采购)</option>
                        	<option value="5" >Training (培训)</option>
                        	<option value="6" >QRF Related (QRF 相关)</option>
                        	<option value="7" >New Factory Investment (新厂房投资)</option>
                        	<option value="8" >Exhibition (展会)</option>
                        </select>
					</td>			
				</tr>
				<tr>
					<td >Project No  <label style="color:red;font-size:12px"><b>*</b></label><br>
					项目号  <label style="color:red;font-size:12px"><b>*</b></label></td>
					<td ><input type="text" size="19" name="projectNo" id="j_payment_visit_projectNo"></td>
					<td ></td>
					<td ></td>
					<td></td>				
				</tr>
				<tr>
					<td >Currency <br> 币种  </td>
					<td >
					<select name="currency" data-toggle="selectpicker"   id="j_payment_visit_currency" data-width="190px" >
							<option value=""></option>
							<option value="RMB">RMB</option>
							<option value="USD">USD</option>
							<option value="EUR">EUR</option>
					</select>
					</td>
					<td ></td>
					<td ></td>
					<td></td>				
				</tr>
				<tr>
					<td >Advance Amount<br>预付款金额</td>
					<td ><input type="text" name="advanceAmount" id="j_payment_visit_advanceAmount"  onchange="paymengVisitCheckAdvanceAmount(this)" value="0.00"></td>
					<td></td>
					<td></td>
					<td></td>				
				</tr>
				<tr>
					<td >Visit Date  <label style="color:red;font-size:12px"><b>*</b></label><br>
					出差期间  <label style="color:red;font-size:12px"><b>*</b></label></td>
					<td colspan="4"><input type="text" size="19" name="visitDateFrom" id="j_payment_visit_visitDateFrom" onchange="paymentVisitEmployeeLevelTime()" data-toggle="datepicker" placeholder="点击选择日期" data-nobtn="true" id="j_payment_lendDate" value="" data-pattern="yyyy-MM-dd HH:mm:ss" />
					TO:&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="text" size="19" name="visitDateTo" id="j_payment_visit_visitDateTo"  onchange="paymentVisitEmployeeLevelTime()"  data-toggle="datepicker" placeholder="点击选择日期" data-nobtn="true" id="j_payment_lendDate" value="" data-pattern="yyyy-MM-dd HH:mm:ss" />				
					</td>	
				</tr>
				<tr>
					<td >Total Leave Work Hours  <label style="color:red;font-size:12px"><b>*</b></label><br>
					总共出差工作天数时数  <label style="color:red;font-size:12px"><b>*</b></label></td>
					<td colspan="4"><input type="text" size="19" name="totalLeaveWorkHours" id="j_payment_visit_totalLeaveWorkHours" onblur="checkTotalLeaveWorkHours()" >&nbsp;Hours</td>		
				</tr>
				<tr>
					<td >Domestic/Oversea  <label style="color:red;font-size:12px"><b>*</b></label><br>
					国内国外  <label style="color:red;font-size:12px"><b>*</b></label></td>
					<td colspan="4">
					<input type="radio" name="businessTrip" data-toggle="icheck" id="j_payment_visit_domestic" value="Domestic 国内" data-label="Domestic 国内">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="businessTrip" data-toggle="icheck" id="j_payment_visit_oversea" value="Oversea 国外" data-label="Oversea 国外">
					</td>					
				</tr>
				<tr>
					<td>
						Visit Detail Place<label style="color:red;font-size:12px"><b>*:</b></label>:<br>出差具体目的地 <label style="color:red;font-size:12px"><b>*</b></label>:
					</td>
					<td colspan="4">
						<textarea cols="50" rows="3" id="j_payment_visitDetailPlace"  name="visitDetailPlace" data-toggle="autoheight"></textarea>
					</td>
				</tr>
				<tr>
					<td>
						Visit Detail Purpose<label style="color:red;font-size:12px"><b>*:</b></label>:<br>出差具体事由<label style="color:red;font-size:12px"><b>*</b></label>:
					</td>
					<td colspan="4">
						<textarea cols="50" rows="3" id="j_payment_visitDetailPurpose"  name="visitDetailPurpose" data-toggle="autoheight"></textarea>
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
						        dataUrl: 'getPaymentVisitEmployee.action?visitId=${param.id}',
						        delUrl:'json/ajaxDone.json',
						        editUrl: 'sign/payment/visit/payment_visit_edit.jsp',
						        editMode: {dialog:{width:'800',height:430,title:'Edit Employee',mask:true}},
						        paging: false,
						        showCheckboxcol: true,
						        linenumberAll: true,
						        filterThead: false,
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
										<th data-options="{name:'hotelBookingByHR',width:150,align:'center',finalWidth:'true'}">A.是否HR预定酒店<br>Hotel Booking by HR</th>
										<th data-options="{name:'hotelName',width:200,align:'center',finalWidth:'true'}">酒店名称<br>Hotel Name</th>
										<th data-options="{name:'carArrangeByHR',width:120,align:'center',finalWidth:'true'}">B.是否HR派车<br>Car Arrange by HR</th>
										<th data-options="{name:'carArrangePeriod',width:150,align:'center',finalWidth:'true'}">派车时间<br>Car Arrange Period</th>
										<th data-options="{name:'airTickerBookingByHR',width:150,align:'center',finalWidth:'true'}">C.是否HR定机票<br>Air Ticket Booking by HR</th>
										<th data-options="{name:'flightNO',width:100,align:'center',finalWidth:'true'}">具体航班号<br>Flight No.</th>
										<th data-options="{name:'visarArrangeByHR',width:150,align:'center',finalWidth:'true'}">D.是否HR办理签证<br>Visa Arrange by HR</th>
						            </tr>
						        </thead>
						    </table>
					</td>
				</tr>
				<tr>
					<td colspan="5" align="center">
	            		<button type="button" id="payment-visit-save" class="btn-default" data-icon="save" onClick="paymentVisitSave()" >Save</button>&nbsp;&nbsp;&nbsp;&nbsp;
	            		<button type="button" id="payment-visit-submit" class="btn-default" data-icon="arrow-up" onClick="paymentVisitSubmit()">Submit</button>&nbsp;&nbsp;&nbsp;&nbsp;
	            		<button type="button" id="payment-visit-delete" class="btn-default" data-icon="close" onClick="paymentVisitDelete()" >Delete</button>&nbsp;&nbsp;&nbsp;&nbsp;
	            		<button type="button" id="payment-visit-cancel" class="btn-default" data-icon="arrow-down" onClick="paymentVisitViewCancel()" style="height:50px" >Cancel this travel business<br>取消预申请</button>&nbsp;&nbsp;&nbsp;&nbsp;
	            		<button type="button" id="payment-visit-print-business" class="btn-default" data-icon="print" style="height:50px" onClick="paymentVisitPrintBusiness()" >Print Out  business leave paper<br>打印请假单</button>
	            		<button type="button" id="payment-visit-print-travel" class="btn-default" data-icon="print" style="height:50px" onClick="paymentVisitPrintTravel()" >Print Out Pre-Travel Expense <br>打印出差预申请单</button>            			
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
						<table class="table" width="100%" id="paymentVisit_approve_his" >
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