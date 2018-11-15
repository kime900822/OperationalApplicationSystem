<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<div class="bjui-pageContent" id="div-user">
    <table class="table table-bordered" id="datagrid-user-CustomsRecords-filter" data-toggle="datagrid" data-options="{
        height: '100%',
        gridTitle : 'CustomsRecords Import',
        showToolbar: true,
        toolbarItem: 'refresh,|,import,export',
        dataUrl: 'queryCustomsRecords.action',
        fieldSortable: false,
        filterThead: false,
        linenumberAll: true,
        paging: false,
        contextMenuB: true,
        hScrollbar: true,
        importOption: {type:'dialog', options:{url:'data/customsrecords/customsrecords-import.html', width:500, height:300, title:'Import CustomsRecords'}},
        exportOption: {type:'file', options:{url:'exportCustomsrecordsExcel.action', loadingmask:true}}
    }">
        <thead>
            <tr>
            	<th data-options="{name:'date',width:200,align:'left',finalWidth:'true'}" >出货日期</th>
				<th data-options="{name:'shipments',width:200,align:'left',finalWidth:'true'}">海关出货项目</th>
				<th data-options="{name:'longProjectNo',width:200,align:'left',finalWidth:'true'}">Long Project No</th>
				<th data-options="{name:'cimtasNo',width:200,align:'left',finalWidth:'true'}">Cimtas No</th>
				<th data-options="{name:'poseLongItemNo',width:200,align:'left',finalWidth:'true'}">Pose Long Item No</th>
				<th data-options="{name:'oldItemNo',width:200,align:'left',finalWidth:'true'}">Old Item No</th>
				<th data-options="{name:'materialDescription',width:200,align:'left',finalWidth:'true'}">Material Description</th>
				<th data-options="{name:'dia',width:200,align:'left',finalWidth:'true'}">Dia-1 OD (mm)</th>
				<th data-options="{name:'sch',width:200,align:'left',finalWidth:'true'}">Sch(mm)-1</th>
				<th data-options="{name:'quantityOrdered',width:200,align:'left',finalWidth:'true'}">Quantity Ordered</th>
				<th data-options="{name:'quantityIssued',width:200,align:'left',finalWidth:'true'}">Quantity Issued</th>
				<th data-options="{name:'weight',width:200,align:'left',finalWidth:'true'}">Weight(kg) 1</th>
				<th data-options="{name:'scn',width:200,align:'left',finalWidth:'true'}">SCN</th>
				<th data-options="{name:'orTy',width:200,align:'left',finalWidth:'true'}">Or Ty</th>
				<th data-options="{name:'orderNumber',width:200,align:'left',finalWidth:'true'}">Order Number</th>
				<th data-options="{name:'manufacName',width:200,align:'left',finalWidth:'true'}">Manufac. Name</th>
				<th data-options="{name:'lotSerialNumber',width:200,align:'left',finalWidth:'true'}">Lot Serial Number</th>
				<th data-options="{name:'operator',width:200,align:'left',finalWidth:'true'}">操作员</th>
				<th data-options="{name:'createDate',width:200,align:'left',finalWidth:'true'}">操作日期</th>
            </tr>
        </thead>
    </table>
</div>