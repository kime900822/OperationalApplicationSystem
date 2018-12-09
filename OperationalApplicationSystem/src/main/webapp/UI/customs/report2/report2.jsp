<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript">



</script>

<div class="bjui-pageContent" id="div-user">
    <table class="table table-bordered" id="datagrid-customs-report2-filter" data-toggle="datagrid" data-options="{
        height: '100%',
        gridTitle : '表2',
        dataType: 'jsonp',
        showToolbar: true,
        toolbarItem: 'refresh',
        dataUrl: 'customs/queryCustomsReport2.action',
        fieldSortable: false,
        filterThead: false,
        linenumberAll: true,
        paging: true,
        contextMenuB: true,
        hScrollbar: true
    }">
        <thead>
            <tr>
				<th data-options="{name:'orderNumber',width:120,align:'left',finalWidth:'true'}">Order Number</th>
				<th data-options="{name:'cimtasLongItemNo',width:160,align:'left',finalWidth:'true'}">Cimtas Long Item No</th>
				<th data-options="{name:'quantity',width:120,align:'left',finalWidth:'true'}">Quantity</th>
				<th data-options="{name:'transQTY',width:200,align:'left',finalWidth:'true'}">Trans QTY</th>
				<th data-options="{name:'DValue',width:200,align:'left',finalWidth:'true'}">数量差异</th>
            </tr>
        </thead>
    </table>
</div>