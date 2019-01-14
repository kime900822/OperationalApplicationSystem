<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript">

$(function(){
	init();
})

function init(){
	var tbody=$.CurrentNavtab.find('#datagrid-customs-report1-filter').children().eq(1).children();
	tbody.forEach(function(i,index){
		alert(i)
		var num=parseFloat(i.children().eq(14).children().html());
		if(num>0){
			i.attr('style','background-color:red');			
		}else if(num>0 && num <1){
			i.attr('style','background-color:yellow');		
		}
	})
	
	
}

</script>
<div class="bjui-pageHeader" style="background-color:#fefefe; border-bottom:none;">
<form data-toggle="ajaxsearch" data-options="{searchDatagrid:$.CurrentNavtab.find('#datagrid-customs-report1-filter')}" id="datagrid-customs-report1-query">
    <fieldset>
        <legend style="font-weight:normal;">Search：</legend>
        <div style="margin:0; padding:1px 5px 5px;">
        <table	>
        	<tr>
        		<td width="80px">
        		<span>料件序号：</span>
        		</td>
        		<td width="150px">
            	<input type="text" name="materialNo"  value="" size="9" data-rule="number">
        		</td>
        		<td width="80px">
        		<span>成品序号：</span>
        		</td>
        		<td width="150px">
            	<input type="text" name="productNo" value=""  size="9" data-rule="number">
        		</td>
	        	<td width="80px">
        		<span>上传批次号:</span>
        		</td>
        		<td width="180px">
            	<input type="text" name="batchNumber" value="" size="15">
        		</td>
        		<td width="160px">
	        		<div class="btn-group">
	                <button type="submit" class="btn-green" data-icon="search">Search</button>
	                <button type="reset" class="btn-orange" data-icon="times">Reset</button>
	            	</div>
         		</td>
        	</tr>    
        </table>
        </div>

    </fieldset>
</form>
</div>
<div class="bjui-pageContent" id="div-user">
    <table class="table table-bordered" id="datagrid-customs-report1-filter" data-toggle="datagrid" data-options="{
        height: '100%',
        gridTitle : '表1',
        dataType: 'jsonp',
        showToolbar: true,
        toolbarItem: 'refresh,|,export',
        dataUrl: 'customs/queryCustomsReport1.action',
        fieldSortable: false,
        filterThead: false,
        linenumberAll: true,
        paging: true,
        contextMenuB: true,
        hScrollbar: true,
        exportOption: {type:'file', options:{url:'customs/exportCustomsReport1.action', loadingmask:true,queryForm:$.CurrentNavtab.find('#datagrid-customs-report1-query')}}
    }">
        <thead>
            <tr>
				<th data-options="{name:'columen1',width:120,align:'left',finalWidth:'true'}">成品序号</th>
				<th data-options="{name:'columen2',width:160,align:'left',finalWidth:'true'}">料件序号</th>
				<th data-options="{name:'columen3',width:120,align:'left',finalWidth:'true'}">单耗版本号</th>
				<th data-options="{name:'columen4',width:200,align:'left',finalWidth:'true'}">净耗</th>
				<th data-options="{name:'columen5',width:200,align:'left',finalWidth:'true'}">有形损耗率(%)</th>
				<th data-options="{name:'columen6',width:200,align:'left',finalWidth:'true'}">无形损耗率(%)</th>
				<th data-options="{name:'columen7',width:200,align:'left',finalWidth:'true'}">单耗申报状态代码</th>
				<th data-options="{name:'columen8',width:200,align:'left',finalWidth:'true'}">保税料件比例(%)</th>
				<th data-options="{name:'columen9',width:200,align:'left',finalWidth:'true'}">修改标志</th>
				<th data-options="{name:'columen10',width:200,align:'left',finalWidth:'true'}">单耗有效期</th>
				<th data-options="{name:'columen11',width:200,align:'left',finalWidth:'true'}">备注</th>
				<th data-options="{name:'columen12',width:200,align:'left',finalWidth:'true'}">上传批次号</th>
				<th data-options="{name:'columen13',width:200,align:'left',finalWidth:'true'}">备案数量</th>
				<th data-options="{name:'columen14',width:200,align:'left',finalWidth:'true'}">结存数</th>
            </tr>
        </thead>
    </table>
</div>