<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript">



</script>

<div class="bjui-pageContent" id="div-user">
    <table class="table table-bordered" id="datagrid-customs-report1-filter" data-toggle="datagrid" data-options="{
        height: '100%',
        gridTitle : '表1',
        dataType: 'jsonp',
        showToolbar: true,
        toolbarItem: 'refresh',
        dataUrl: 'customs/queryCustomsReport1.action',
        fieldSortable: false,
        filterThead: false,
        linenumberAll: true,
        paging: true,
        contextMenuB: true,
        hScrollbar: true
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
				<th data-options="{name:'columen13',width:200,align:'left',finalWidth:'true'}">结存数</th>
            </tr>
        </thead>
    </table>
</div>