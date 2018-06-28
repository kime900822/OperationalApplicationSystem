<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">
$(function(){
	var today = new Date().formatDate('yyyy-MM-dd');
	var chopDate=new Date(new Date().setDate(new Date().getDate()+5)).formatDate('yyyy-MM-dd');
	$.CurrentNavtab.find('#j_stamp_visit_applicationDate').val(today);

	
})



</script>




<div class="bjui-pageContent">
    <div class="bs-example" style="width:1700px">
        <form id="j_stamp_visit_form" data-toggle="ajaxform">
			<input type="hidden" name="id" id="j_stamp_visit_id" value="${param.id}">
			<input type="hidden" name="state" id="j_stamp_visit_state" value="">
            <div class="bjui-row-0" align="center">
            <h2 class="row-label">出差单申请</h2><br> 
            </div>
			<table class="table" style="font-size:12px;">
				<tr>
					<td width="200px">Reference No.<br>单号</td>
					<td width="200px"><input type="text" size="19" name="applicationCode" data-nobtn="true" id="j_stamp_visit_applicationCode" value="" placeholder="保存或者送审后生成"  readonly=""></td>					
					<td width="200px"></td>
					<td width="200px"></td>	
					<td width="900px"></td>				
				</tr>
				<tr>
					<td >Application Date<br>申请日期</td>
					<td ><input type="text" size="19" name="applicationDate" id="j_stamp_visit_applicationDate"  data-toggle="datepicker" placeholder="点击选择日期" data-nobtn="true" ></td>
					<td ></td>
					<td ></td>
					<td></td>				
				</tr>
				<tr>
					<td >Visit Purpose <label style="color:red;font-size:12px"><b>*</b></label><br>
					出差目的  <label style="color:red;font-size:12px"><b>*</b></label></td>
					<td ><input type="text" size="19" name="visitPurpose" id="j_stamp_visit_visitPurpose"  data-toggle="datepicker" placeholder="点击选择日期" data-nobtn="true" >
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
					<td colspan="4"><input type="text" size="19" name="visitDateFrom" id="j_stamp_visit_visitDateFrom"  data-toggle="datepicker" placeholder="点击选择日期" data-nobtn="true" id="j_stamp_lendDate" value=""  />
					TO:&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="text" size="19" name="visitDateTo" id="j_stamp_visit_visitDateTo"  data-toggle="datepicker" placeholder="点击选择日期" data-nobtn="true" id="j_stamp_lendDate" value=""  />				
					</td>	
				</tr>
				<tr>
					<td >Total Leave Work Hours  <label style="color:red;font-size:12px"><b>*</b></label><br>
					总共出差工作天数时数  <label style="color:red;font-size:12px"><b>*</b></label></td>
					<td colspan="4"><input type="text" size="19" name="totalLevelWorkHours" id="j_stamp_visit_totalLevelWorkHours" >&nbsp;Hours</td>		
				</tr>
				<tr>
					<td >Domestic/Oversea  <label style="color:red;font-size:12px"><b>*</b></label><br>
					国内国外  <label style="color:red;font-size:12px"><b>*</b></label></td>
					<td colspan="4">
					<input type="checkbox" name="businessTrip" data-toggle="icheck" id="j_stamp_visit_domestic" value="Domestic 国内" data-label="Domestic 国内">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="checkbox" name="businessTrip" data-toggle="icheck" id="j_stamp_visit_oversea" value="Oversea 国外" data-label="Oversea 国外">
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
						   <table class="table table-bordered" id="datagrid-edit-filter" data-toggle="datagrid" data-options="{
						        height: '100%',
						        gridTitle : '出差人员',
						        showToolbar: true,
						        dataType: 'jsonp',
						        toolbarItem: 'add,edit,del',
						        dataUrl: 'getPyamentVisitEmployee.action?visitID=${param.id}',
						        delUrl:'json/ajaxDone.json',
						        editUrl: 'sign/payment/visit/payment_visit_edit.jsp',
						        editMode: {dialog:{width:'800',height:430,title:'Edit Employee',mask:true}},
						        paging: {pageSize:5, pageCurrent:1},
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
	            		<button type="button" id="stamp-save" class="btn-default" data-icon="save" onClick="savePaymentVisit()" >Save</button>&nbsp;&nbsp;&nbsp;&nbsp;
	            		<button type="button" id="stamp-submit" class="btn-default" data-icon="arrow-up" onClick="submitPaymentVisit()">Submit</button>&nbsp;&nbsp;&nbsp;&nbsp;
	            		<button type="button" id="stamp-delete" class="btn-default" data-icon="close" onClick="deletePaymentVisit()" style="display:none">Delete</button>
            		</td>				
				</tr>	
				<tr>
				<td colspan="5" >
				Approval Route:<br>
				签核路径:
				</td>
				</tr>		
				<tr>
					<td colspan="4">
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
					<td></td>
				</tr>
			</table>		


        </form>
    </div>
</div>