<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<div class="bjui-pageContent" id="div-user">
    <table class="table table-bordered" id="datagrid-user-filter" data-toggle="datagrid" data-options="{
        height: '100%',
        gridTitle : 'Instruction Import',
        showToolbar: true,
        toolbarItem: 'refresh,|,import,export',
        dataUrl: 'getInstruction.action',
        fieldSortable: false,
        filterThead: false,
        linenumberAll: true,
        paging: false,
        contextMenuB: true,
        hScrollbar: true,
        importOption: {type:'dialog', options:{url:'data/instruction/instruction-import.html', width:500, height:300, title:'Import Instruction'}},
        exportOption: {type:'file', options:{url:'exportInstructionExcel.action', loadingmask:false}}
    }">
        <thead>
            <tr>
            	<th data-options="{name:'Frame_PO_Repore',width:200,align:'left',finalWidth:'true'}" >Frame_PO_Repore</th>
				<th data-options="{name:'Frame_Projection',width:200,align:'left',finalWidth:'true'}">Frame_Projection</th>
				<th data-options="{name:'Mix',width:200,align:'left',finalWidth:'true'}">Mix</th>
            </tr>
        </thead>
    </table>
</div>