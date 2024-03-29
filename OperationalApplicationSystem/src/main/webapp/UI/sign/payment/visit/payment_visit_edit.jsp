<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <script type="text/javascript">

 $(function(){
	 if('${param.advanceAmount}'!=null&&'${param.advanceAmount}'!=''&&'${param.advanceAmount}'!=undefined){
		 $.CurrentDialog.find('#j_payment_visit_employee_advanceAmount').val('${param.advanceAmount}');
	 }
	 
	 if('${param.addFlag}'!='true'){
		 $.CurrentDialog.find('#j_payment_visit_employee_hotelBookingByHR').selectpicker().selectpicker('val','${param.hotelBookingByHR}').selectpicker('refresh');
		 $.CurrentDialog.find('#j_payment_visit_employee_carArrangeByHR').selectpicker().selectpicker('val','${param.carArrangeByHR}').selectpicker('refresh');
		 $.CurrentDialog.find('#j_payment_visit_employee_airTickerBookingByHR').selectpicker().selectpicker('val','${param.airTickerBookingByHR}').selectpicker('refresh');
		 $.CurrentDialog.find('#j_payment_visit_employee_visarArrangeByHR').selectpicker().selectpicker('val','${param.visarArrangeByHR}').selectpicker('refresh'); 
		 if('${param.hotelBookingByHR}'=='YES'){
			 $.CurrentDialog.find("#j_payment_visit_employee_hotelName").removeAttr("disabled");
		 }
		 if('${param.carArrangeByHR}'=='YES'){
			 $.CurrentDialog.find("#j_payment_visit_employee_carArrangePeriod").removeAttr("disabled");
		 }
		 if('${param.airTickerBookingByHR}'=='YES'){
			 $.CurrentDialog.find("#j_payment_visit_employee_flightNO").removeAttr("disabled");
		 }
	 }

	 
 })
 
 
 
function paymentVisitEmployeeSelected(obj,id){
	var val=$.CurrentDialog.find(obj).val();
	if(val=='YES'){		
		$.CurrentDialog.find("#"+id).removeAttr("disabled");
	}else{
		$.CurrentDialog.find("#"+id).attr("disabled","");
	}
	
	
}
 
 
function  paymentVisitEmployeeChange(){
	var id=$.CurrentDialog.find('#j_payment_visit_employee_employeeNo').val();
	if(id!=''&&id!=undefined){
		BJUI.ajax('doajax', {
			url:'getUserByID.action',
			loadingmask:true,
			data:{uid:id},
			okCallback:function(json, options) {
				if (json.length > 0) {
					$.CurrentDialog.find('#j_payment_visit_employee_employeeName').val(json[0].name);
					$.CurrentDialog.find('#j_payment_visit_employee_employeeBUNo').val(json[0].department.did);
				} else {
					BJUI.alertmsg('error', 'userid不存在');
					$.CurrentDialog.find('#j_payment_visit_employee_employeeNoy').val('');
					$.CurrentDialog.find('#j_payment_visit_employee_employeeName').val('');
					$.CurrentDialog.find('#j_payment_visit_employee_employeeBUNo').val('');
				}
			}
		})
	}
	
	
	
}
 
function paymentVisitEmployeeSaveCheck(){
	
	 if($.CurrentDialog.find("#j_payment_visit_employee_hotelBookingByHR").val()=='YES' && $.CurrentDialog.find("#j_payment_visit_employee_hotelName").val()==''){
		 BJUI.alertmsg('warn', 'HotelName Can`t Null!');
		 return false;
	 }
	 if($.CurrentDialog.find("#j_payment_visit_employee_carArrangeByHR").val()=='YES'&&$.CurrentDialog.find("#j_payment_visit_employee_carArrangePeriod")==''){
		 BJUI.alertmsg('warn', 'CarArrangePeriod Can`t Null!');
		 return false;
	 }
	 if($.CurrentDialog.find("#j_payment_visit_employee_airTickerBookingByHR").val()=='YES'&&$.CurrentDialog.find("#j_payment_visit_employee_flightNO")==''){
		 BJUI.alertmsg('warn', 'FlightNO Can`t Null!');
		 return false;
	 }
	 
	 return true;
	
}


 
</script> 
    
<div class="bjui-pageContent">
    <div class="bs-example" >
        <form action="modPaymentVisitEmployee.action?callback=?" class="datagrid-edit-form" data-toggle="validate" data-data-type="jsonp" onsubmit="return paymentVisitEmployeeSaveCheck()">
            <div class="bjui-row col-2">
                
                <label class="row-label">Visit Employee No.<label style="color:red;font-size:12px"><b>*</b></label><br> 出差人员  <label style="color:red;font-size:12px"><b>*</b></label></label>
                <div class="row-input required">
                    <input type="text" name="employeeNo" value="${param.employeeNo}" id="j_payment_visit_employee_employeeNo" data-toggle="findgrid" onchange="paymentVisitEmployeeChange()" data-options="{
			            group: '',
			            include: 'employeeNo:uid,employeeBUNo:did,employeeName:name',
			            dialogOptions: {title:'选择出差人员'},
			            empty:false,
			            gridOptions: {
			                local: 'local',
			                dataUrl: 'getUser4Find.action',
			                columns: [
			                    {name:'uid', label:'Visit Employee No.', width:150},
			                    {name:'name',width:200,label:'Visit Employee BU No.'},
			                    {name:'did',width:200,label:'Visit Employee Name'}
			                ]
			            }
			        }" placeholder="点放大镜按钮查找" data-rule="required" >
                </div>
                <label class="row-label">Visit Employee BU No.<br>出差人员部门代码</label>
                <div class="row-input required">
                    <input type="text" name="employeeBUNo" id="j_payment_visit_employee_employeeBUNo" value="${param.employeeBUNo}" readonly="" data-rule="required">
                </div>
                <label class="row-label">Visit Employee Name<br>出差人员姓名</label>
                <div class="row-input required">
					<input type="text" name="employeeName" id="j_payment_visit_employee_employeeName" readonly=""  data-rule="required" value="${param.employeeName}">
                </div>
               
                <div >
                    
                </div>
                <label class="row-label">A.是否HR预定酒店<br>Hotel Booking by HR</label>
                <div class="row-input required">
                    <select name="hotelBookingByHR" id="j_payment_visit_employee_hotelBookingByHR" onchange="paymentVisitEmployeeSelected(this,'j_payment_visit_employee_hotelName')" data-toggle="selectpicker"  data-rule="required" data-width="100%"  >
                         <option value="YES" >YES</option>
                         <option value="NO" selected>NO</option>
                    </select>
                </div>
                <label class="row-label">酒店名称<br>Hotel Name</label>
                <div class="row-input">
                    <input type="text" disabled="" name="hotelName"  id="j_payment_visit_employee_hotelName" value="${param.hotelName}">
                </div>
                <label class="row-label">B.是否HR派车<br>Car Arrange by HR</label>
                <div class="row-input required">
                    <select name="carArrangeByHR" id="j_payment_visit_employee_carArrangeByHR" onchange="paymentVisitEmployeeSelected(this,'j_payment_visit_employee_carArrangePeriod')" data-toggle="selectpicker" data-rule="required" data-width="100%"  >
                         <option value="YES" >YES</option>
                         <option value="NO" selected>NO</option>
                    </select>
                </div>
                <label class="row-label">派车时间<br>Car Arrange Period</label>
                <div class="row-input">
                    <input type="text" disabled="" name="carArrangePeriod"  id="j_payment_visit_employee_carArrangePeriod"  value="${param.carArrangePeriod}"  data-toggle="datepicker" placeholder="点击选择日期" data-nobtn="true" data-pattern="yyyy-MM-dd HH:mm:ss">
                </div>
                <label class="row-label">C.是否HR定机票<br>Air Ticket Booking by HR</label>
                <div class="row-input required">
                    <select name="airTickerBookingByHR" id="j_payment_visit_employee_airTickerBookingByHR" onchange="paymentVisitEmployeeSelected(this,'j_payment_visit_employee_flightNO')"  data-toggle="selectpicker"  data-rule="required" data-width="100%"  >
                         <option value="YES" >YES</option>
                         <option value="NO" selected>NO</option>
                    </select>
                </div>
                <label class="row-label">具体航班号<br>Flight No.</label>
                <div class="row-input">
                    <input type="text" name="flightNO" disabled="" id="j_payment_visit_employee_flightNO" value="${param.flightNO}">
                </div>
                <label class="row-label">D.是否HR办理签证<br>Visar Arrange by HR</label>
                <div class="row-input required">
                    <select name="visarArrangeByHR" id="j_payment_visit_employee_visarArrangeByHR" data-toggle="selectpicker"  data-rule="required" data-width="100%"  >
                         <option value="YES" >YES</option>
                         <option value="NO" selected>NO</option>
                    </select>
                </div>
            </div>
        </form>
    </div>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close" data-icon="close">Cancel</button></li>
        <li><button type="submit" class="btn-default" data-icon="save">Save</button></li>
    </ul>
</div>