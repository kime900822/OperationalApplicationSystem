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
    <div class="bs-example" style="width:1500px">
        <form id="j_stamp_visit_form" data-toggle="ajaxform">
			<input type="hidden" name="id" id="j_stamp_visit_id" value="${param.id}">
			<input type="hidden" name="state" id="j_stamp_visit_state" value="">
            <div class="bjui-row-0" align="center">
            <h2 class="row-label">出差单申请</h2><br> 
            </div>
			<table class="table" style="font-size:12px;">
				<tr>
					<td width="200px">Reference No.<br>单号:</td>
					<td width="200px"><input type="text" size="19" name="applicationCode" data-nobtn="true" id="j_stamp_visit_applicationCode" value="" placeholder="保存或者送审后生成"  readonly=""></td>					
					<td width="200px"></td>
					<td width="200px"></td>	
					<td width="700px"></td>				
				</tr>
				<tr>
					<td >Application Date<br>申请日期:</td>
					<td ><input type="text" size="19" name="applicationDate" id="j_stamp_visit_applicationDate"  data-toggle="datepicker" placeholder="点击选择日期" data-nobtn="true" ></td>
					<td ></td>
					<td ></td>
					<td></td>				
				</tr>
				<tr>
					<td >Visit Date<br>出差日期:</td>
					<td colspan="4"><input type="text" size="19" name="lendDate" data-toggle="datepicker" placeholder="点击选择日期" data-nobtn="true" id="j_stamp_lendDate" value=""  />
					TO:&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="text" size="19" name="lendDate" data-toggle="datepicker" placeholder="点击选择日期" data-nobtn="true" id="j_stamp_lendDate" value=""  />				
					</td>	
				</tr>
				<tr>
					<td >Total Leave Work Hours<br>总共出差工作天数时数:</td>
					<td colspan="4"><input type="text" size="19" name="applicationDate" id="j_stamp_visit_applicationDate" >&nbsp;Hours</td>		
				</tr>
				<tr>
					<td >Domestic/Oversea<br>国内国外</td>
					<td colspan="4">
					<input type="checkbox" name="stampType" data-toggle="icheck" id="j_stamp_companyChop" value="Company Chop" data-label="Domestic 国内">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="checkbox" name="stampType" data-toggle="icheck" id="j_stamp_companyChop" value="Company Chop" data-label="Oversea 国外">
					</td>					
				</tr>
				<tr>
					<td>
						Visit Detail Place<label style="color:red;font-size:12px"><b>*:</b></label>:<br>出差具体目的地 <label style="color:red;font-size:12px"><b>*</b></label>:
					</td>
					<td colspan="4">
						<textarea cols="50" rows="3" id="j_stamp_chopObject"  name="chopObject" data-toggle="autoheight"></textarea>
					</td>
				</tr>
				<tr>
					<td>
						Visit Detail Purpose<label style="color:red;font-size:12px"><b>*:</b></label>:<br>出差具体事由<label style="color:red;font-size:12px"><b>*</b></label>:
					</td>
					<td colspan="4">
						<textarea cols="50" rows="3" id="j_stamp_chopObject"  name="chopObject" data-toggle="autoheight"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="5">
					    <table id="j_datagrid_payment_visit" data-toggle="datagrid" data-options="{
				        height: '100%',
				        gridTitle : 'Employee',
				        showToolbar: true,
				        toolbarItem: 'add,edit,del,save,cancel,|,refresh',
				        dataType: 'jsonp',
				        dataUrl: 'getDict.action',
				        editUrl: 'modeDict.action',
				        delUrl: 'deleteDict.action',
				        paging: {pageSize:30, pageCurrent:1},
				        linenumberAll: true,
				        hScrollbar: true,
				        inlineEditMult: false,
				        addLocation:'last'
				    }">
				        <thead>
				            <tr>
				           		<th data-options="{name:'id', align:'center',finalWidth:'true', width:200, edit:false,hide:'true'}">ID</th>
				            	<th data-options="{name:'type', align:'center',finalWidth:'true', width:200, edit:false}">Visit Employee No.*<br>出差人员</th>
				                <th data-options="{name:'key', align:'center',finalWidth:'true', width:200}">Visit Employee BU No.<br>出差人员部门代码</th>
				                <th data-options="{name:'keyExplain', align:'center',finalWidth:'true', width:200}">keyExplain</th>
				                <th data-options="{name:'value', align:'center',finalWidth:'true',width:200}">Value</th>
				                <th data-options="{name:'valueExplain', align:'center',finalWidth:'true',width:200}">valueExplain</th>
				            </tr>
				        </thead>
				    </table>

					</td>
				</tr>
				<tr>
					<td colspan="5" align="center">
	            		<button type="button" id="stamp-save" class="btn-default" data-icon="save" onClick="saveStamp()" >Save</button>&nbsp;&nbsp;&nbsp;&nbsp;
	            		<button type="button" id="stamp-submit" class="btn-default" data-icon="arrow-up" onClick="submitStamp()">Submit</button>&nbsp;&nbsp;&nbsp;&nbsp;
	            		<button type="button" id="stamp-delete" class="btn-default" data-icon="close" onClick="deleteStamp()" style="display:none">Delete</button>
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