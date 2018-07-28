<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">
$(function(){

		
	
})




function paymentVisitViewCheckSave(o){
	var err="";

	return err;
	
}

function paymentVisitViewFaceToDate(){
	var o=$.CurrentNavtab.find("#j_payment_visit_view_form").serializeJson();
	o.employees=$("#datagrid-payment-visit").data('allData');
	o.uId='${user.uid}';
	o.uName='${user.name}';
	
	return o;
}

function paymentVisitViewDateToFace(o){
	
	var id = $(o).text();
	
	BJUI.ajax('doajax', {
	    url: 'getpaymentVisitByID.action',
	    loadingmask: true,
	    data:{id:id},	    
	    okCallback: function(json, options) {
	    	if(json.status='200'){
	    		$.CurrentNavtab.find("#j_payment_visit_view_visitDateFrom").val(json.visitDateFrom);
	    		$.CurrentNavtab.find("#j_payment_visit_view_visitDateTo").val(json.visitDateTo);
	    		$.CurrentNavtab.find("#j_payment_visit_view_totalLevelWorkHours").val(json.totalLevelWorkHours);
	    		$.CurrentNavtab.find("#j_payment_visit_view_applicantDate").val(json.applicantDate);
	    		$.CurrentNavtab.find("#j_payment_visit_view_visitPurpose").selectpicker().selectpicker('val',json.visitPurpose).selectpicker('refresh');
	    		$.CurrentNavtab.find("#j_payment_visit_view_projectNo").val(json.projectNo);
	    		if(json.businessTrip=='Domestic 国内')
	    		{
	    			$.CurrentNavtab.find("#j_payment_visit_view_domestic").iCheck('check'); 
	    		}else if(json.businessTrip=='Oversea 国外'){
	    			$.CurrentNavtab.find("#j_payment_visit_view_oversea").iCheck('check'); 
	    		}
	    		
	    		$.CurrentNavtab.find("#j_payment_visit_view_DetailPlace").val(json.visitDetailPlace);
	    		$.CurrentNavtab.find("#j_payment_visit_view_DetailPurpose").val(json.visitDetailPurpose);
	    	}
	    }
	})
	
	
	
	
	
	
}

function paymentVisitViewSave(){
	var o=paymentVisitViewFaceToDate();	
	var err=paymentVisitViewCheckSave(o);
	if(err!=''){
		BJUI.alertmsg('warn', err); 
		return false;
	}
	

		
	BJUI.ajax('doajax', {
	    url: 'savepaymentVisitView.action',
	    loadingmask: true,
	    data:{json:JSON.stringify(o)},	    
	    okCallback: function(json, options) {
            if(json.status='200'){
            	 BJUI.alertmsg('info', json.message); 
            	 $.CurrentNavtab.find("#j_payment_visit_view_id").val(json.params.id);
            }else{
            	 BJUI.alertmsg('error', json.message); 
            }
	    }
	});		
	
	
	
}



</script>




<div class="bjui-pageContent">
    <div class="bs-example" style="width:1700px">
        <form id="j_payment_visit_view_form" data-toggle="ajaxform">
			<input type="hidden" name="id" id="j_payment_id" value="${param.id}">
            <div class="bjui-row-0" align="center">
            <h2 class="row-label">出差单申请</h2><br> 
            </div>
			<table class="table" style="font-size:12px;">
				<tr>
					<td width="200px">Reference No.<br>单号</td>
					<td width="200px"><input type="text" size="19" name="referenceNo" data-nobtn="true" id="j_payment_visit_view_referenceNo"  ></td>					
					<td width="200px"></td>
					<td width="200px"></td>	
					<td width="900px"></td>				
				</tr>
				<tr>
					<td >Applicant Date<br>申请日期</td>
					<td ><input type="text" size="19" id="j_payment_visit_view_applicantDate"  data-nobtn="true" disabled="disabled"></td>
					<td ></td>
					<td ></td>
					<td></td>				
				</tr>
				<tr>
					<td >Visit Purpose <label style="color:red;font-size:12px"><b>*</b></label><br>
					出差目的  <label style="color:red;font-size:12px"><b>*</b></label></td>
					<td >
						<input type="text" size="19" id="j_payment_visit_view_visitPurpose"  data-nobtn="true" disabled="disabled">
					</td>
					<td ></td>
					<td ></td>
					<td></td>				
				</tr>
				<tr>
					<td >Project No  <label style="color:red;font-size:12px"><b>*</b></label><br>
					项目号  <label style="color:red;font-size:12px"><b>*</b></label></td>
					<td ><input type="text" size="19" name="projectNo" id="j_payment_visit_view_projectNo" disabled="disabled"></td>
					<td ></td>
					<td ></td>
					<td></td>				
				</tr>
				<tr>
					<td >Visit Date  <label style="color:red;font-size:12px"><b>*</b></label><br>
					出差期间  <label style="color:red;font-size:12px"><b>*</b></label></td>
					<td colspan="4"><input type="text" size="19" name="visitDateFrom" id="j_payment_visit_view_visitDateFrom"   disabled="disabled" />
					TO:&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="text" size="19" name="visitDateTo" id="j_payment_visit_view_visitDateTo"  disabled="disabled" />				
					</td>	
				</tr>
				<tr>
					<td >Total Leave Work Hours  <label style="color:red;font-size:12px"><b>*</b></label><br>
					总共出差工作天数时数  <label style="color:red;font-size:12px"><b>*</b></label></td>
					<td colspan="4"><input type="text" size="19" name="totalLevelWorkHours" id="j_payment_visit_view_totalLevelWorkHours" disabled="disabled">&nbsp;Hours</td>		
				</tr>
				<tr>
					<td >Domestic/Oversea  <label style="color:red;font-size:12px"><b>*</b></label><br>
					国内国外  <label style="color:red;font-size:12px"><b>*</b></label></td>
					<td colspan="4">
					<input type="radio" name="businessTrip" data-toggle="icheck" id="j_payment_visit_view_domestic" value="Domestic 国内" data-label="Domestic 国内"  disabled="disabled">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="businessTrip" data-toggle="icheck" id="j_payment_visit_view_oversea" value="Oversea 国外" data-label="Oversea 国外"  disabled="disabled">
					</td>					
				</tr>
				<tr>
					<td>
						Visit Detail Place<label style="color:red;font-size:12px"><b>*:</b></label>:<br>出差具体目的地 <label style="color:red;font-size:12px"><b>*</b></label>:
					</td>
					<td colspan="4">
						<textarea cols="50" rows="3" id="j_payment_visit_view_DetailPlace"  name="visitDetailPlace" data-toggle="autoheight"></textarea>
					</td>
				</tr>
				<tr>
					<td>
						Visit Detail Purpose<label style="color:red;font-size:12px"><b>*:</b></label>:<br>出差具体事由<label style="color:red;font-size:12px"><b>*</b></label>:
					</td>
					<td colspan="4">
						<textarea cols="50" rows="3" id="j_payment_visit_view_DetailPurpose"  name="visitDetailPurpose" data-toggle="autoheight"></textarea>
					</td>
				</tr>
				<tr height="400px">
					<td colspan="5" >
						   <table class="table table-bordered" id="datagrid-payment-visit" data-toggle="datagrid" data-options="{
						        height: '100%',
						        gridTitle : '出差人员',
						        showToolbar: true,
						        local: 'local',
						        dataUrl: 'getpaymentVisitEmployee.action?visitId=${param.id}',
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
			</table>		
        </form>
    </div>
    <div>
    	<table border="1" cellspacing="0" width="2000px">
    			<tr>
    				<th colspan="28" align="center">
						<h5>Business Trip Expense Application Form<br>差旅费申请单</h5>    				
    				</th>
    			</tr>
				<tr height="40xp">
					<th rowspan="3" width="100px" align="center">Date<br>费用发生日期</th>
					<th rowspan="3" width="100px" align="center">Start from where<br>出发地点</th>
					<th rowspan="3" width="100px" align="center">End to where<br>到达地点</th>
					<th rowspan="3" width="100px" align="center">Description<br>摘要</th>
					<th colspan="24" align="center">Expense Description 费用分类</th>
				</tr>
				<tr>
					<th rowspan="2" align="center">Currency Used<br>消费币种</th>
					<th colspan="11" align="center">Transportation 交通费</th>
					<th colspan="4" align="center">Hotel 住宿</th>
					<th colspan="4" align="center">Meal 误餐费</th>
					<th rowspan="2" align="center" width="80px">Other<br>其他</th>
					<th rowspan="2" align="center" width="80px">Original Currency Total<br>原币汇总金额</th>
					<th rowspan="2" align="center" width="80px">RMB exchange rate<br>人民币折算汇率</th>
					<th rowspan="2" align="center" width="80px">RMB Total<br>人民币金额</th>
				</tr>
				<tr height="40xp">
					<th align="center" width="80px">Metro<br>地铁</th>
					<th align="center" width="80px">Taxi / Didi Chuxing car<br>出租车/滴滴打车</th>
					<th align="center" width="80px">Train<br>火车</th>
					<th align="center" width="80px">Bus<br>汽车</th>
					<th align="center" width="80px">Rental Car<br>租车</th>
					<th align="center" width="80px">Road Toll<br>过路费</th>
					<th align="center" width="80px">Road Toll<br>过路费不含税金额</th>
					<th align="center" width="80px">Road Toll Tax<br>过路费税金</th>
					<th align="center" width="80px">Self-Driving<br>自驾车</th>
					<th align="center" width="80px">Air Ticket<br>机票</th>
					<th align="center" width="80px">Transportation Total<br>交通费合计</th>
					<th align="center" width="80px">Hotel Without VAT<br>不含税金额</th>
					<th align="center" width="80px">Hotel Tax Rate<br>税率</th>
					<th align="center" width="80px">Hotel VAT<br>税金</th>
					<th align="center" width="80px">Hotel Total<br>住宿合计</th>
					<th align="center" width="80px">Breakfast<br>早餐</th>
					<th align="center" width="80px">Lunch<br>午餐</th>
					<th align="center" width="80px">Dinner<br>晚餐</th>
					<th align="center" width="80px">Meal Total<br>餐费合计</th>
				</tr>
				<tr height="20xp">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td>
						<select data-toggle="selectpicker" name="usedCurrency" data-width="75px" >
							<option value=""></option>
							<option value="RMB">RMB</option>
							<option value="USD">USD</option>
							<option value="TRY">TRY</option>
							<option value="GBP">GBP</option>
							<option value="EUR">EUR</option>
							<option value="JPY">JPY</option>
						</select>
					</td>
					<td>
						<input type="text" name="metro" width="100%"/>
					</td>
					<td>
						<input type="text" name="taxi" width="100%"/>
					</td>
					<td>
						<input type="text" name="train" width="100%"/>
					</td>
					<td>
						<input type="text" name="bus" width="100%"/>
					</td>
					<td>
						<input type="text" name="rentalCar" width="100%"/>
					</td>
					<td>
						<input type="text" name="roadTail" width="100%"/>
					</td>
					<td bgcolor="#ADADAD"></td>
					<td bgcolor="#ADADAD"></td>
					<td>
						<input type="text" name="selfDriver" width="100%"/>
					</td>
					<td>
						<input type="text" name="airTicket" width="100%"/>
					</td>
					<td bgcolor="#ADADAD"></td>
					<td bgcolor="#ADADAD"></td>
					<td>
						<select name="hotelTaxRate" data-toggle="selectpicker" data-width="80px">
							<option value=""></option>
							<option value="V0">V0</option>
							<option value="V6">V6</option>
						</select>
					</td>
					<td bgcolor="#ADADAD"></td>
					<td>
						<input type="text" name="hotel" width="100%"/>
					</td>
					<td>
						<input type="text" name="breakfast" width="100%"/>
					</td>
					<td>
						<input type="text" name="lunch" width="100%"/>
					</td>
					<td>
						<input type="text" name="dinner" width="100%"/>
					</td>
					<td bgcolor="#ADADAD"></td>
					<td>
						<input type="text" name="other" width="100%"/>
					</td>
					<td bgcolor="#ADADAD"></td>
					<td>
						<input type="text" name="RMBExchangeRate" width="100%"/>
					</td>
					<td bgcolor="#ADADAD"></td>
				</tr>
				<tr height="20xp">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td>
						<select data-toggle="selectpicker" name="usedCurrency" data-width="75px" >
							<option value=""></option>
							<option value="RMB">RMB</option>
							<option value="USD">USD</option>
							<option value="TRY">TRY</option>
							<option value="GBP">GBP</option>
							<option value="EUR">EUR</option>
							<option value="JPY">JPY</option>
						</select>
					</td>
					<td>
						<input type="text" name="metro" width="100%"/>
					</td>
					<td>
						<input type="text" name="taxi" width="100%"/>
					</td>
					<td>
						<input type="text" name="train" width="100%"/>
					</td>
					<td>
						<input type="text" name="bus" width="100%"/>
					</td>
					<td>
						<input type="text" name="rentalCar" width="100%"/>
					</td>
					<td>
						<input type="text" name="roadTail" width="100%"/>
					</td>
					<td bgcolor="#ADADAD"></td>
					<td bgcolor="#ADADAD"></td>
					<td>
						<input type="text" name="selfDriver" width="100%"/>
					</td>
					<td>
						<input type="text" name="airTicket" width="100%"/>
					</td>
					<td bgcolor="#ADADAD"></td>
					<td bgcolor="#ADADAD"></td>
					<td>
						<select name="hotelTaxRate" data-toggle="selectpicker" data-width="80px">
							<option value=""></option>
							<option value="V0">V0</option>
							<option value="V6">V6</option>
						</select>
					</td>
					<td bgcolor="#ADADAD"></td>
					<td>
						<input type="text" name="hotel" width="100%"/>
					</td>
					<td>
						<input type="text" name="breakfast" width="100%"/>
					</td>
					<td>
						<input type="text" name="lunch" width="100%"/>
					</td>
					<td>
						<input type="text" name="dinner" width="100%"/>
					</td>
					<td bgcolor="#ADADAD"></td>
					<td>
						<input type="text" name="other" width="100%"/>
					</td>
					<td bgcolor="#ADADAD"></td>
					<td>
						<input type="text" name="RMBExchangeRate" width="100%"/>
					</td>
					<td bgcolor="#ADADAD"></td>
				</tr>
				<tr height="20xp">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td>
						<select data-toggle="selectpicker" name="usedCurrency" data-width="75px" >
							<option value=""></option>
							<option value="RMB">RMB</option>
							<option value="USD">USD</option>
							<option value="TRY">TRY</option>
							<option value="GBP">GBP</option>
							<option value="EUR">EUR</option>
							<option value="JPY">JPY</option>
						</select>
					</td>
					<td>
						<input type="text" name="metro" width="100%"/>
					</td>
					<td>
						<input type="text" name="taxi" width="100%"/>
					</td>
					<td>
						<input type="text" name="train" width="100%"/>
					</td>
					<td>
						<input type="text" name="bus" width="100%"/>
					</td>
					<td>
						<input type="text" name="rentalCar" width="100%"/>
					</td>
					<td>
						<input type="text" name="roadTail" width="100%"/>
					</td>
					<td bgcolor="#ADADAD"></td>
					<td bgcolor="#ADADAD"></td>
					<td>
						<input type="text" name="selfDriver" width="100%"/>
					</td>
					<td>
						<input type="text" name="airTicket" width="100%"/>
					</td>
					<td bgcolor="#ADADAD"></td>
					<td bgcolor="#ADADAD"></td>
					<td>
						<select name="hotelTaxRate" data-toggle="selectpicker" data-width="80px">
							<option value=""></option>
							<option value="V0">V0</option>
							<option value="V6">V6</option>
						</select>
					</td>
					<td bgcolor="#ADADAD"></td>
					<td>
						<input type="text" name="hotel" width="100%"/>
					</td>
					<td>
						<input type="text" name="breakfast" width="100%"/>
					</td>
					<td>
						<input type="text" name="lunch" width="100%"/>
					</td>
					<td>
						<input type="text" name="dinner" width="100%"/>
					</td>
					<td bgcolor="#ADADAD"></td>
					<td>
						<input type="text" name="other" width="100%"/>
					</td>
					<td bgcolor="#ADADAD"></td>
					<td>
						<input type="text" name="RMBExchangeRate" width="100%"/>
					</td>
					<td bgcolor="#ADADAD"></td>
				</tr>
				<tr height="20xp">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td>
						<select data-toggle="selectpicker" name="usedCurrency" data-width="75px" >
							<option value=""></option>
							<option value="RMB">RMB</option>
							<option value="USD">USD</option>
							<option value="TRY">TRY</option>
							<option value="GBP">GBP</option>
							<option value="EUR">EUR</option>
							<option value="JPY">JPY</option>
						</select>
					</td>
					<td>
						<input type="text" name="metro" width="100%"/>
					</td>
					<td>
						<input type="text" name="taxi" width="100%"/>
					</td>
					<td>
						<input type="text" name="train" width="100%"/>
					</td>
					<td>
						<input type="text" name="bus" width="100%"/>
					</td>
					<td>
						<input type="text" name="rentalCar" width="100%"/>
					</td>
					<td>
						<input type="text" name="roadTail" width="100%"/>
					</td>
					<td bgcolor="#ADADAD"></td>
					<td bgcolor="#ADADAD"></td>
					<td>
						<input type="text" name="selfDriver" width="100%"/>
					</td>
					<td>
						<input type="text" name="airTicket" width="100%"/>
					</td>
					<td bgcolor="#ADADAD"></td>
					<td bgcolor="#ADADAD"></td>
					<td>
						<select name="hotelTaxRate" data-toggle="selectpicker" data-width="80px">
							<option value=""></option>
							<option value="V0">V0</option>
							<option value="V6">V6</option>
						</select>
					</td>
					<td bgcolor="#ADADAD"></td>
					<td>
						<input type="text" name="hotel" width="100%"/>
					</td>
					<td>
						<input type="text" name="breakfast" width="100%"/>
					</td>
					<td>
						<input type="text" name="lunch" width="100%"/>
					</td>
					<td>
						<input type="text" name="dinner" width="100%"/>
					</td>
					<td bgcolor="#ADADAD"></td>
					<td>
						<input type="text" name="other" width="100%"/>
					</td>
					<td bgcolor="#ADADAD"></td>
					<td>
						<input type="text" name="RMBExchangeRate" width="100%"/>
					</td>
					<td bgcolor="#ADADAD"></td>
				</tr>
				<tr height="20xp">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td>
						<select data-toggle="selectpicker" name="usedCurrency" data-width="75px" >
							<option value=""></option>
							<option value="RMB">RMB</option>
							<option value="USD">USD</option>
							<option value="TRY">TRY</option>
							<option value="GBP">GBP</option>
							<option value="EUR">EUR</option>
							<option value="JPY">JPY</option>
						</select>
					</td>
					<td>
						<input type="text" name="metro" width="100%"/>
					</td>
					<td>
						<input type="text" name="taxi" width="100%"/>
					</td>
					<td>
						<input type="text" name="train" width="100%"/>
					</td>
					<td>
						<input type="text" name="bus" width="100%"/>
					</td>
					<td>
						<input type="text" name="rentalCar" width="100%"/>
					</td>
					<td>
						<input type="text" name="roadTail" width="100%"/>
					</td>
					<td bgcolor="#ADADAD"></td>
					<td bgcolor="#ADADAD"></td>
					<td>
						<input type="text" name="selfDriver" width="100%"/>
					</td>
					<td>
						<input type="text" name="airTicket" width="100%"/>
					</td>
					<td bgcolor="#ADADAD"></td>
					<td bgcolor="#ADADAD"></td>
					<td>
						<select name="hotelTaxRate" data-toggle="selectpicker" data-width="80px">
							<option value=""></option>
							<option value="V0">V0</option>
							<option value="V6">V6</option>
						</select>
					</td>
					<td bgcolor="#ADADAD"></td>
					<td>
						<input type="text" name="hotel" width="100%"/>
					</td>
					<td>
						<input type="text" name="breakfast" width="100%"/>
					</td>
					<td>
						<input type="text" name="lunch" width="100%"/>
					</td>
					<td>
						<input type="text" name="dinner" width="100%"/>
					</td>
					<td bgcolor="#ADADAD"></td>
					<td>
						<input type="text" name="other" width="100%"/>
					</td>
					<td bgcolor="#ADADAD"></td>
					<td>
						<input type="text" name="RMBExchangeRate" width="100%"/>
					</td>
					<td bgcolor="#ADADAD"></td>
				</tr>
				<tr height="20xp">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td>
						<select data-toggle="selectpicker" name="usedCurrency" data-width="75px" >
							<option value=""></option>
							<option value="RMB">RMB</option>
							<option value="USD">USD</option>
							<option value="TRY">TRY</option>
							<option value="GBP">GBP</option>
							<option value="EUR">EUR</option>
							<option value="JPY">JPY</option>
						</select>
					</td>
					<td>
						<input type="text" name="metro" width="100%"/>
					</td>
					<td>
						<input type="text" name="taxi" width="100%"/>
					</td>
					<td>
						<input type="text" name="train" width="100%"/>
					</td>
					<td>
						<input type="text" name="bus" width="100%"/>
					</td>
					<td>
						<input type="text" name="rentalCar" width="100%"/>
					</td>
					<td>
						<input type="text" name="roadTail" width="100%"/>
					</td>
					<td bgcolor="#ADADAD"></td>
					<td bgcolor="#ADADAD"></td>
					<td>
						<input type="text" name="selfDriver" width="100%"/>
					</td>
					<td>
						<input type="text" name="airTicket" width="100%"/>
					</td>
					<td bgcolor="#ADADAD"></td>
					<td bgcolor="#ADADAD"></td>
					<td>
						<select name="hotelTaxRate" data-toggle="selectpicker" data-width="80px">
							<option value=""></option>
							<option value="V0">V0</option>
							<option value="V6">V6</option>
						</select>
					</td>
					<td bgcolor="#ADADAD"></td>
					<td>
						<input type="text" name="hotel" width="100%"/>
					</td>
					<td>
						<input type="text" name="breakfast" width="100%"/>
					</td>
					<td>
						<input type="text" name="lunch" width="100%"/>
					</td>
					<td>
						<input type="text" name="dinner" width="100%"/>
					</td>
					<td bgcolor="#ADADAD"></td>
					<td>
						<input type="text" name="other" width="100%"/>
					</td>
					<td bgcolor="#ADADAD"></td>
					<td>
						<input type="text" name="RMBExchangeRate" width="100%"/>
					</td>
					<td bgcolor="#ADADAD"></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td colspan="2">Subtotal in figures 小计: </td>
					<td bgcolor="#ADADAD"></td>
					<td bgcolor="#ADADAD"></td>
					<td bgcolor="#ADADAD"></td>
					<td bgcolor="#ADADAD"></td>
					<td bgcolor="#ADADAD"></td>
					<td bgcolor="#ADADAD"></td>
					<td bgcolor="#ADADAD"></td>
					<td bgcolor="#ADADAD"></td>
					<td bgcolor="#ADADAD"></td>
					<td bgcolor="#ADADAD"></td>
					<td bgcolor="#ADADAD"></td>
					<td bgcolor="#ADADAD"></td>
					<td bgcolor="#ADADAD"></td>
					<td bgcolor="#ADADAD"></td>
					<td bgcolor="#ADADAD"></td>
					<td bgcolor="#ADADAD"></td>
					<td bgcolor="#ADADAD"></td>
					<td bgcolor="#ADADAD"></td>
					<td bgcolor="#ADADAD"></td>
					<td bgcolor="#ADADAD"></td>
					<td bgcolor="#ADADAD"></td>
					<td bgcolor="#ADADAD"></td>
					<td bgcolor="#ADADAD"></td>
				</tr>
			</table>
    
    </div>
</div>