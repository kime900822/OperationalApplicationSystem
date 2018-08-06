<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">
$(function(){
	
	if('${param.visitId}'!=null&&'${param.visitId}'!=''&&'${param.visitId}'!=undefined){
		paymentVisitViewDateToFace('${param.visitId}');
	}
	payment_visit_bind_change();
	
	if('${param.state}'==''||'${param.state}'==null||'${param.state}'==undefined||'${param.state}'=='0'){
		$.CurrentDialog.find("#tr-business-trip-user").show();
		$.CurrentDialog.find("#tr-business-trip-finance").hide();
		$.CurrentDialog.find("#payment-visit-view-save").show();
		$.CurrentDialog.find("#payment-visit-view-subjcode").hide();
		$.CurrentDialog.find("#payment-visit-view-export").hide();
	}else{
		$.CurrentDialog.find("#tr-business-trip-user").hide();
		$.CurrentDialog.find("#tr-business-trip-finance").show();
		$.CurrentDialog.find("#payment-visit-view-save").hide();
		$.CurrentDialog.find("#payment-visit-view-subjcode").show();
		$.CurrentDialog.find("#payment-visit-view-export").show();
	}		
	
	
})




function paymentVisitViewCheckSave(o){
	var err="";

	return err;
	
}

function paymentVisitViewFaceToDate(){
	var o=[];
	var t={};
	var trs=$.CurrentDialog.find("#table-business-trip-user").children().eq(0).children();
	for(var i=4;i<10;i++){
		var tds=trs.get(i).children;	
		t.visitId=$.CurrentDialog.find("#j_payment_visit_view_id").val();
		if(tds[4].children[0].value!=''){
			t.rowNum=i;
			t.currency=tds[4].children[0].value;
			t.metro=parseFloat(tds[5].children[0].value);
			t.taxi=parseFloat(tds[6].children[0].value);
			t.train=parseFloat(tds[7].children[0].value);
			t.bus=parseFloat(tds[8].children[0].value);
			t.rentalCar=parseFloat(tds[9].children[0].value);
			t.roadToil=parseFloat(tds[10].children[0].value);
			t.roadToilWithoutVAT=parseFloat(tds[11].innerHTML);
			t.roadToilVAT=parseFloat(tds[12].innerHTML);
			t.selfDriver=parseFloat(tds[13].children[0].value);
			t.airTicket=parseFloat(tds[14].children[0].value);
			t.landwayTotal=toNumber(t.metro)+toNumber(t.taxi)+toNumber(t.train)+toNumber(t.bus)+toNumber(t.rentalCar)+toNumber(t.roadToilWithoutVAT)+toNumber(t.selfDriver);
			t.transportationTotal=parseFloat(tds[15].innerHTML);
			t.hotelWithoutVAT=parseFloat(tds[16].innerHTML);
			t.hotelTaxRate=tds[17].children[0].value;
			t.hotelVAT=parseFloat(tds[18].innerHTML);
			t.hotel=parseFloat(tds[19].children[0].value);
			t.breakfast=parseFloat(tds[20].children[0].value);
			t.lunch=parseFloat(tds[21].children[0].value);
			t.dinner=parseFloat(tds[22].children[0].value);
			t.mealTotal=parseFloat(tds[23].innerHTML);
			t.other=toNumber(tds[24].children[0].value);
			t.originalCurrencyTotal=toNumber(tds[25].innerHTML);
			t.RMBExchangeRate=toNumber(tds[26].children[0].value);
			t.total=toNumber(tds[27].innerHTML);
			o.push(t);
		}
		
	}
		
	return o;
}

function paymentVisitViewDateToFace(id){
	
	$.CurrentDialog.find('form:eq(0)').trigger('bjui.ajaxStart')
	BJUI.ajax('doajax', {
	    url: 'getPaymentVisitByID.action',
	    loadingmask: true,
	    data:{id:id},	    
	    okCallback: function(json, options) {
	    	if(json.status='200'){
	    		$.CurrentDialog.find("#j_payment_visit_view_visitDateFrom").val(json.visitDateFrom);
	    		$.CurrentDialog.find("#j_payment_visit_view_visitDateTo").val(json.visitDateTo);
	    		$.CurrentDialog.find("#j_payment_visit_view_totalLevelWorkHours").val(json.totalLevelWorkHours);
	    		$.CurrentDialog.find("#j_payment_visit_view_applicantDate").val(json.applicantDate);
	    		$.CurrentDialog.find("#j_payment_visit_view_visitPurpose").val(getVisitViewVisitPurpose(json.visitPurpose));
	    		$.CurrentDialog.find("#j_payment_visit_view_projectNo").val(json.projectNo);
	    		if(json.businessTrip=='Domestic 国内')
	    		{
	    			$.CurrentDialog.find("#j_payment_visit_view_domestic").iCheck('check'); 
	    		}else if(json.businessTrip=='Oversea 国外'){
	    			$.CurrentDialog.find("#j_payment_visit_view_oversea").iCheck('check'); 
	    		}
	    		
	    		$.CurrentDialog.find("#j_payment_visit_view_DetailPlace").val(json.visitDetailPlace);
	    		$.CurrentDialog.find("#j_payment_visit_view_DetailPurpose").val(json.visitDetailPurpose);
	    		
	    		
	    		if(json.employees!=undefined&&json.employees!=""){
	    			var table=$.CurrentDialog.find("#table-payment-visit-employee")
        			$.each(json.employees,function(i,item){	  		
        				table.append("<tr><td>"+item.employeeNo+"</td><td>"+item.employeeBUNo+"</td><td>"+item.employeeName+"</td><td>"+item.advanceAmount+"</td><td>"+item.hotelBookingByHR+"</td><td>"+item.hotelName+"</td><td>"+item.carArrangeByHR+"</td>><td>"+item.carArrangePeriod+"</td>><td>"+item.airTickerBookingByHR+"</td>><td>"+item.flightNO+"</td>><td>"+item.visarArrangeByHR+"</td></tr>");	    				
        			})
        		}
		
	    		var trs=$.CurrentDialog.find("#table-business-trip-finance").children().eq(0).children();
	    		if(json.businessTrips!=undefined&&json.businessTrips!=""){
	    			$.each(json.businessTrips,function(i,item){	  	
	    				trs[item.rowNum].children[4].innerHTML=item.currency;
	    				trs[item.rowNum].children[5].innerHTML=toDecimal2(item.metro);
	    				trs[item.rowNum].children[6].innerHTML=toDecimal2(item.taxi);
	    				trs[item.rowNum].children[7].innerHTML=toDecimal2(item.train);
	    				trs[item.rowNum].children[8].innerHTML=toDecimal2(item.bus);
	    				trs[item.rowNum].children[9].innerHTML=toDecimal2(item.rentalCar);
	    				trs[item.rowNum].children[10].innerHTML=toDecimal2(item.roadToil);
	    				trs[item.rowNum].children[11].innerHTML=toDecimal2(item.roadToilWithoutVAT);
	    				trs[item.rowNum].children[12].innerHTML=toDecimal2(item.roadToilVAT);
	    				trs[item.rowNum].children[13].innerHTML=toDecimal2(item.selfDriver);
	    				trs[item.rowNum].children[14].innerHTML=toDecimal2(item.airTicket);
	    				trs[item.rowNum].children[15].innerHTML=toDecimal2(item.landwayTotal);
	    				trs[item.rowNum].children[16].innerHTML=toDecimal2(item.transportationTotal);
	    				trs[item.rowNum].children[17].innerHTML=toDecimal2(item.hotelWithoutVAT);
	    				trs[item.rowNum].children[18].innerHTML=item.hotelTaxRate;
	    				trs[item.rowNum].children[19].innerHTML=toDecimal2(item.hotelVAT);
	    				trs[item.rowNum].children[20].innerHTML=toDecimal2(item.hotel);
	    				trs[item.rowNum].children[21].innerHTML=toDecimal2(item.breakfast);
	    				trs[item.rowNum].children[22].innerHTML=toDecimal2(item.lunch);
	    				trs[item.rowNum].children[23].innerHTML=toDecimal2(item.dinner);
	    				trs[item.rowNum].children[24].innerHTML=toDecimal2(item.mealTotal);
	    				trs[item.rowNum].children[25].innerHTML=toDecimal2(item.other);
	    				trs[item.rowNum].children[26].innerHTML=toDecimal2(item.originalCurrencyTotal);
	    				trs[item.rowNum].children[27].innerHTML=toDecimal2(item.RMBExchangeRate);
	    				trs[item.rowNum].children[28].innerHTML=toDecimal2(item.total);
        			})
	    			
	        		for(var j=5;j<29;j++){
	        			total=0;
		    			total+=toNumber(trs[4].children[j].innerHTML);
		    			total+=toNumber(trs[5].children[j].innerHTML);
		    			total+=toNumber(trs[6].children[j].innerHTML);
		    			total+=toNumber(trs[7].children[j].innerHTML);
		    			total+=toNumber(trs[8].children[j].innerHTML);
		    			total+=toNumber(trs[9].children[j].innerHTML);
		    			trs[10].children[j-1].innerHTML=toDecimal2(total);
		    		}
	    		}
	    		
	    		$.CurrentDialog.find('form:eq(0)').trigger('bjui.ajaxStop')
	    		
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
            	 $.CurrentDialog.find("#j_payment_visit_view_id").val(json.params.id);
            	 $.CurrentDialog.find("#j_payment_visit_view_object").val(o);
            	 $.CurrentDialog.find("input").attr('disabled','disabled');
            	 $.CurrentDialog.find("select").attr('disabled','disabled');	
            }else{
            	 BJUI.alertmsg('error', json.message); 
            }
	    }
	});		
	
	
	
}

function changeReferenceNo(){
	var referenceNo=$.CurrentDialog.find("#j_payment_visit_view_referenceNo").val();
	BJUI.ajax('doajax', {
	    url: 'checkPaymentVisit.action',
	    loadingmask: true,
	    data:{referenceNo:referenceNo},	    
	    okCallback: function(json, options) {
            if(json.status='200'){
            	paymentVisitViewDateToFace(json.params.id);
            	 $.CurrentDialog.find("#j_payment_visit_view_id").val(json.params.id);
            }else{
            	 BJUI.alertmsg('error', json.message); 
            }
	    }
	});	
	
	
}


function payment_visit_bind_change(){
	var trs=$.CurrentDialog.find("#table-business-trip-user").children().eq(0).children();
	for(var i=4;i<trs.length;i++){
		var tds=trs.get(i).children;	
		for(var j=4;j<tds.length;j++){
			var td=tds[j];
			if(td.children[0]!=null && td.children[0]!=undefined){
				$(td.children[0]).attr('onchange','payment_visit_change_txt('+i+','+j+')');
			}
		}
	}
} 

function payment_visit_change_txt(i,j){
	var trs=$.CurrentDialog.find("#table-business-trip-user").children().eq(0).children();
	var tds=trs.get(i).children;
	
	var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
	var txt=tds[j].children[0];
	if(j!=4&&j!=17){
		if(tds[4].children[0].value==''){
			BJUI.alertmsg('warn', 'Place select Currency Used first!'); 
			txt.value='';
			return false;
		}
		
		if (!reg.test(txt.value)){
			txt.value='';
		}else{
			txt.value=toDecimal2(txt.value);
		}
		
		//交通费合计					
		if(j>4 && j<15){
			var total=0;
			total+=toNumber(tds[5].children[0].value);
			total+=toNumber(tds[6].children[0].value);
			total+=toNumber(tds[7].children[0].value);
			total+=toNumber(tds[8].children[0].value);
			total+=toNumber(tds[9].children[0].value);
			total+=toNumber(tds[10].children[0].value);
			total+=toNumber(tds[13].children[0].value);
			total+=toNumber(tds[14].children[0].value);
			tds[15].innerHTML=toDecimal2(total);
		}
		//过路费税额
		if(j==10){
			tds[11].innerHTML=toDecimal2(txt.value/1.03);
			tds[12].innerHTML=toDecimal2(txt.value/1.03*0.03);
		}
		//住宿费
		if(j==19){
			if(tds[19].children[0].value>0){
				if(tds[17].children[0].value=='V0'){
					tds[16].innerHTML=toDecimal2(tds[19].children[0].value)
					tds[18].innerHTML='-';
				}else if(tds[17].children[0].value=='V6'){
					tds[16].innerHTML=toDecimal2(tds[19].children[0].value/1.06)
					tds[18].innerHTML=toDecimal2(tds[19].children[0].value/1.06*0.06)
				}							
			}
		}
		//误餐费
		if(j>19&&j<23){
			var total=0;
			total+=toNumber(tds[20].children[0].value);
			total+=toNumber(tds[21].children[0].value);
			total+=toNumber(tds[22].children[0].value);
			tds[23].innerHTML=toDecimal2(total);
		}
		//总计部分
		var total=0;
		total+=toNumber(tds[15].innerHTML);
		total+=toNumber(tds[19].children[0].value);
		total+=toNumber(tds[23].innerHTML);
		total+=toNumber(tds[24].children[0].value);
		tds[25].innerHTML=toDecimal2(total);
		
		if(tds[26].children[0].value==''){
			tds[27].innerHTML='-';
		}else{
			tds[27].innerHTML=toDecimal2(toNumber(tds[25].innerHTML)*toNumber(tds[26].children[0].value));
		}

		if(j==17||j==26){
			total=0
		}else{
			total=0;
			total+=toNumber(trs[4].children[j].children[0].value);
			total+=toNumber(trs[5].children[j].children[0].value);
			total+=toNumber(trs[6].children[j].children[0].value);
			total+=toNumber(trs[7].children[j].children[0].value);
			total+=toNumber(trs[8].children[j].children[0].value);
			total+=toNumber(trs[9].children[j].children[0].value);
		}
		
		trs[10].children[j-1].innerHTML=toDecimal2(total);
		
		
		}
		//币种选择
		if(j==4){
			if(tds[4].children[0].value=='RMB'){
				tds[26].children[0].value='1.00';
			}else{
				tds[26].children[0].value='';
			}
			
			if(tds[26].children[0].value==''){
				tds[27].innerHTML='-';
			}else{
				tds[27].innerHTML=toDecimal2(toNumber(tds[25].innerHTML)*toNumber(tds[26].children[0].value));
			}
			
		}
		
		if(j==17){
			if(tds[19].children[0].value>0){
				if(tds[17].children[0].value=='V0'){
					tds[16].innerHTML=toDecimal2(tds[19].children[0].value)
					tds[18].innerHTML='-';
				}else if(tds[17].children[0].value=='V6'){
					tds[16].innerHTML=toDecimal2(tds[19].children[0].value/1.06)
					tds[18].innerHTML=toDecimal2(tds[19].children[0].value/1.06*0.06)
				}else{
					tds[16].innerHTML=''
					tds[18].innerHTML='';
				}							
			}
		}
		
		var t=[11,12,15,16,18,23,25,27];
		t.forEach(function(k,n){
			total=0;
			total+=toNumber(trs[4].children[k].innerHTML);
			total+=toNumber(trs[5].children[k].innerHTML);
			total+=toNumber(trs[6].children[k].innerHTML);
			total+=toNumber(trs[7].children[k].innerHTML);
			total+=toNumber(trs[8].children[k].innerHTML);
			total+=toNumber(trs[9].children[k].innerHTML);
			trs[10].children[k-1].innerHTML=toDecimal2(total);

			
		})

	
}



</script>




<div class="bjui-pageContent">
    <div class="bs-example" style="width:2100px">
        <form id="j_payment_visit_view_form" data-toggle="ajaxform">
			<input type="hidden" name="id" id="j_payment_visit_view_id" value="${param.visitId}">
			<input type="hidden" name="id" id="j_payment_visit_view_status" value="${param.status}">
			<input type="hidden" id="j_payment_visit_view_object" >
            <div class="bjui-row-0" align="center">
            <h2 class="row-label">出差单申请</h2><br> 
            </div>
			<table class="table" style="font-size:12px" >
				<tr>
					<td width="200px">Reference No.<br>单号</td>
					<td width="200px"><input type="text" size="19" name="referenceNo" onchange="changeReferenceNo();" data-nobtn="true" id="j_payment_visit_view_referenceNo"  ></td>					
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
						<input type="text" size="25" id="j_payment_visit_view_visitPurpose"  data-nobtn="true" disabled="disabled">
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
						<textarea cols="50" rows="3" id="j_payment_visit_view_DetailPlace"  name="visitDetailPlace" data-toggle="autoheight"   disabled="disabled" ></textarea>
					</td>
				</tr>
				<tr>
					<td>
						Visit Detail Purpose<label style="color:red;font-size:12px"><b>*:</b></label>:<br>出差具体事由<label style="color:red;font-size:12px"><b>*</b></label>:
					</td>
					<td colspan="4">
						<textarea cols="50" rows="3" id="j_payment_visit_view_DetailPurpose"  name="visitDetailPurpose" data-toggle="autoheight"   disabled="disabled" ></textarea>
					</td>
				</tr>
				<tr >
					<td colspan="5"  >
						   <table id="table-payment-visit-employee" border="1" cellspacing="0">
						            <tr >
						            	<th width="150" align="center">Visit Employee No.*<br>出差人员</th>
						            	<th width="150" align="center">Visit Employee BU No.<br>出差人员部门代码</th>
										<th width="150" align="center">Visit Employee Name<br>出差人员姓名</th>
										<th width="120" align="center">Advance Amount<br>预付款金额</th>
										<th width="150" align="center">A.是否HR预定酒店<br>Hotel Booking by HR</th>
										<th width="200" align="center">酒店名称<br>Hotel Name</th>
										<th width="120" align="center">B.是否HR派车<br>Car Arrange by HR</th>
										<th width="150" align="center">派车时间<br>Car Arrange Period</th>
										<th width="150" align="center">C.是否HR定机票<br>Air Ticket Booking by HR</th>
										<th width="100" align="center">具体航班号<br>Flight No.</th>
										<th width="150" align="center">D.是否HR办理签证<br>Visar Arrange by HR</th>
						            </tr>
						    </table>
					</td>
				</tr>
				<tr id="tr-business-trip-user">
					<td colspan="5" >
						<table border="1" cellspacing="0" width="2000px" id="table-business-trip-user" >
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
								<select data-toggle="selectpicker" name="usedCurrency" data-width="75px"  >
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
							<td bgcolor="#ADADAD" id='table-business-trip-total-metro'></td>
							<td bgcolor="#ADADAD" id='table-business-trip-total-taxi'></td>
							<td bgcolor="#ADADAD" id='table-business-trip-total-train'></td>
							<td bgcolor="#ADADAD" id='table-business-trip-total-bus'></td>
							<td bgcolor="#ADADAD" id='table-business-trip-total-rentalCar'></td>
							<td bgcolor="#ADADAD" id='table-business-trip-total-roadToil'></td>
							<td bgcolor="#ADADAD" id='table-business-trip-total-roadToilWithoutVAT'></td>
							<td bgcolor="#ADADAD" id='table-business-trip-total-roadToilVAT'></td>
							<td bgcolor="#ADADAD" id='table-business-trip-total-selfDriver'></td>
							<td bgcolor="#ADADAD" id='table-business-trip-total-airTicket'></td>
							<td bgcolor="#ADADAD" id='table-business-trip-total-transportationTotal'></td>
							<td bgcolor="#ADADAD" id='table-business-trip-total-hotelWithoutVAT'></td>
							<td bgcolor="#ADADAD" id='table-business-trip-total-hotelTaxRate'></td>
							<td bgcolor="#ADADAD" id='table-business-trip-total-hotelVAT'></td>
							<td bgcolor="#ADADAD" id='table-business-trip-total-hotel'></td>
							<td bgcolor="#ADADAD" id='table-business-trip-total-breakfast'></td>
							<td bgcolor="#ADADAD" id='table-business-trip-total-lunch'></td>
							<td bgcolor="#ADADAD" id='table-business-trip-total-dinner'></td>
							<td bgcolor="#ADADAD" id='table-business-trip-total-mealTotal'></td>
							<td bgcolor="#ADADAD" id='table-business-trip-total-other'></td>
							<td bgcolor="#ADADAD" id='table-business-trip-total-originalCurrencyTotal'></td>
							<td bgcolor="#ADADAD" id='table-business-trip-total-RMBExchangeRate'></td>
							<td bgcolor="#ADADAD" id='table-business-trip-total-total'></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr id="tr-business-trip-finance">
					<td colspan="5">
						<table border="1" cellspacing="0" width="2080px" id="table-business-trip-finance" >
			    			<tr>
			    				<th colspan="29" align="center">
									<h5>Business Trip Expense Application Form<br>差旅费申请单</h5>    				
			    				</th>
			    			</tr>
							<tr height="40xp">
								<th rowspan="3" width="100px" align="center">Date<br>费用发生日期</th>
								<th rowspan="3" width="100px" align="center">Start from where<br>出发地点</th>
								<th rowspan="3" width="100px" align="center">End to where<br>到达地点</th>
								<th rowspan="3" width="100px" align="center">Description<br>摘要</th>
								<th colspan="25" align="center">Expense Description 费用分类</th>
							</tr>
							<tr>
								<th rowspan="2" align="center">Currency Used<br>消费币种</th>
								<th colspan="12" align="center">Transportation 交通费</th>
								<th colspan="4" align="center">Hotel 住宿</th>
								<th colspan="4" align="center">Meal 误餐费</th>
								<th rowspan="2" align="center" width="80px">Other<br>其他<br>PO6</th>
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
								<th align="center" width="80px">Road Toll Tax<br>过路费税金<br>PO2</th>
								<th align="center" width="80px">Self-Driving<br>自驾车</th>
								<th align="center" width="80px">Air Ticket<br>机票<br>PO6</th>
								<th align="center" width="80px">Landway Total<br>陆路合计<br>PO1</th>
								<th align="center" width="80px">Transportation Total<br>交通费合计</th>
								<th align="center" width="80px">Hotel Without VAT<br>不含税金额<br>PO3</th>
								<th align="center" width="80px">Hotel Tax Rate<br>税率</th>
								<th align="center" width="80px">Hotel VAT<br>税金<br>PO4</th>
								<th align="center" width="80px">Hotel Total<br>住宿合计</th>
								<th align="center" width="80px">Breakfast<br>早餐</th>
								<th align="center" width="80px">Lunch<br>午餐</th>
								<th align="center" width="80px">Dinner<br>晚餐</th>
								<th align="center" width="80px">Meal Total<br>餐费合计<br>PO5</th>
							</tr>
							<tr height="20xp">
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td> </td>
								<td> </td>
								<td> </td>
								<td> </td>
								<td> </td>
								<td> </td>
								<td bgcolor="#ADADAD"></td>
								<td bgcolor="#ADADAD"></td>
								<td bgcolor="#ADADAD"></td>
								<td> </td>
								<td> </td>
								<td bgcolor="#ADADAD"></td>
								<td bgcolor="#ADADAD"></td>
								<td> </td>
								<td bgcolor="#ADADAD"></td>
								<td> </td>
								<td> </td>
								<td> </td>
								<td> </td>
								<td bgcolor="#ADADAD"></td>
								<td> </td>
								<td bgcolor="#ADADAD"></td>
								<td> </td>
								<td bgcolor="#ADADAD"></td>
							</tr>
							<tr height="20xp">
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td> </td>
								<td> </td>
								<td> </td>
								<td> </td>
								<td> </td>
								<td> </td>
								<td bgcolor="#ADADAD"></td>
								<td bgcolor="#ADADAD"></td>
								<td bgcolor="#ADADAD"></td>
								<td> </td>
								<td> </td>
								<td bgcolor="#ADADAD"></td>
								<td bgcolor="#ADADAD"></td>
								<td> </td>
								<td bgcolor="#ADADAD"></td>
								<td> </td>
								<td> </td>
								<td> </td>
								<td> </td>
								<td bgcolor="#ADADAD"></td>
								<td> </td>
								<td bgcolor="#ADADAD"></td>
								<td> </td>
								<td bgcolor="#ADADAD"></td>
							</tr>
							<tr height="20xp">
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td> </td>
								<td> </td>
								<td> </td>
								<td> </td>
								<td> </td>
								<td> </td>
								<td bgcolor="#ADADAD"></td>
								<td bgcolor="#ADADAD"></td>
								<td bgcolor="#ADADAD"></td>
								<td> </td>
								<td> </td>
								<td bgcolor="#ADADAD"></td>
								<td bgcolor="#ADADAD"></td>
								<td> </td>
								<td bgcolor="#ADADAD"></td>
								<td> </td>
								<td> </td>
								<td> </td>
								<td> </td>
								<td bgcolor="#ADADAD"></td>
								<td> </td>
								<td bgcolor="#ADADAD"></td>
								<td> </td>
								<td bgcolor="#ADADAD"></td>
							</tr>
							<tr height="20xp">
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td> </td>
								<td> </td>
								<td> </td>
								<td> </td>
								<td> </td>
								<td> </td>
								<td bgcolor="#ADADAD"></td>
								<td bgcolor="#ADADAD"></td>
								<td bgcolor="#ADADAD"></td>
								<td> </td>
								<td> </td>
								<td bgcolor="#ADADAD"></td>
								<td bgcolor="#ADADAD"></td>
								<td> </td>
								<td bgcolor="#ADADAD"></td>
								<td> </td>
								<td> </td>
								<td> </td>
								<td> </td>
								<td bgcolor="#ADADAD"></td>
								<td> </td>
								<td bgcolor="#ADADAD"></td>
								<td> </td>
								<td bgcolor="#ADADAD"></td>
							</tr>
							<tr height="20xp">
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td> </td>
								<td> </td>
								<td> </td>
								<td> </td>
								<td> </td>
								<td> </td>
								<td bgcolor="#ADADAD"></td>
								<td bgcolor="#ADADAD"></td>
								<td bgcolor="#ADADAD"></td>
								<td> </td>
								<td> </td>
								<td bgcolor="#ADADAD"></td>
								<td bgcolor="#ADADAD"></td>
								<td> </td>
								<td bgcolor="#ADADAD"></td>
								<td> </td>
								<td> </td>
								<td> </td>
								<td> </td>
								<td bgcolor="#ADADAD"></td>
								<td> </td>
								<td bgcolor="#ADADAD"></td>
								<td> </td>
								<td bgcolor="#ADADAD"></td>
							</tr>
							<tr height="20xp">
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td> </td>
								<td> </td>
								<td> </td>
								<td> </td>
								<td> </td>
								<td> </td>
								<td bgcolor="#ADADAD"></td>
								<td bgcolor="#ADADAD"></td>
								<td bgcolor="#ADADAD"></td>
								<td> </td>
								<td> </td>
								<td bgcolor="#ADADAD"></td>
								<td bgcolor="#ADADAD"></td>
								<td> </td>
								<td bgcolor="#ADADAD"></td>
								<td> </td>
								<td> </td>
								<td> </td>
								<td> </td>
								<td bgcolor="#ADADAD"></td>
								<td> </td>
								<td bgcolor="#ADADAD"></td>
								<td> </td>
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
								<td bgcolor="#ADADAD"></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="5" align='center'>
						<button type="button" id="payment-visit-view-save" class="btn-default" data-icon="save" onClick="paymentVisitViewSave()" >Save</button>&nbsp;&nbsp;&nbsp;&nbsp;
	            		<button type="button" id="payment-visit-view-subjcode" class="btn-default" data-icon="print" onClick="" >生成科目</button>
	            		<button type="button" id="payment-visit-view-export" class="btn-default" data-icon="print"  onClick="" >导出</button>            			
					
					</td>
				</tr>
			</table>	
        </form>
    </div>
  </div>
 