<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="js/Calendar.js"></script>
<script type="text/javascript">
	$(function() {
		$("#query").click(function() {
			$("[name='pageNum']").val(1);
			$("form:first").submit();
		});
		
		$(".unit").click(function(){
			//1.获取点击的链接值
			//2.将该值赋值到查询条件上
			$("[name='gqm.unit']").val($(this).html());
			$("[name='pageNum']").val(1);
			$("form:first").submit();
		});
		
		
	});
	function showMsg(msg,uuid){
		//top.document.getElementById("context-msg").style.display = "block";
		top.$('context-msg').style.display = "block";
		top.$('context-msg-text').innerHTML=msg;
		top.$('hid-action').value="goods_delete.action?gm.uuid="+uuid;
		top.lock.show();
	}
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">商品管理</span>
		</div>
	</div>
	<div class="content-text">
		<s:form action="goods_list" method="post"> 
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td>供应商:</td>
						<td>
							<s:select name="gqm.gtm.sm.uuid" list="supplierList" listKey="uuid" listValue="name" headerKey="-1" headerValue="---请-选-择---" cssStyle="width:113px"></s:select>
						</td>
						<td>单&nbsp;&nbsp;&nbsp;&nbsp;位</td>
						<td><s:textfield name="gqm.unit" size="14"/></td>
						<td><a id="query"> <img src="images/can_b_01.gif" border="0" /> </a></td>
						<td width="70"><a href="goods_input.action"><img src="images/can_b_02.gif" border="0" /> </a></td>
					
						
					</tr>
				</table>
			</div>
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(images/table_bg.gif) repeat-x;">
						<td width="18%" height="30">供应商</td>
						<td width="12%">商品名</td>
						<td width="12%">生产厂家</td>
						<td width="12%">产地</td>
						<td width="12%">进货价格</td>
						<td width="12%">销售价格</td>
						<td width="6%">单位</td>
						<td width="16%">操作</td>
					</tr>
					<s:iterator value="goodsList">
						<tr align="center" bgcolor="#FFFFFF">
							<td width="13%" height="30">${gtm.sm.name}</td>
							<td>${name}</td>
							<td>${producer }</td>
							<td>${origin }</td>
							<td align="right">${inPriceView }&nbsp;元&nbsp;</td>
							<td align="right">${outPriceView }&nbsp;元&nbsp;</td>
							<td><a href="javascript:void(0)" class="unit">${unit}</a></td>
							<td>
								<img src="images/icon_3.gif" /> 
								<span style="line-height:12px; text-align:center;">
									<s:a action="goods_input" cssClass="xiu">
										<s:param name="gm.uuid" value="uuid"/>
										修改
									</s:a> 
								</span> 
								<img src="images/icon_04.gif" /> 
								<span style="line-height:12px; text-align:center;"> 
									<a href="javascript:void(0)" class="xiu" onclick="showMsg('是否删除该项数据？',${uuid})">删除</a>
								</span>
							</td>
						</tr>
					</s:iterator>
				</table>
				<%@ include file="/WEB-INF/jsps/tools/paging.jsp" %>
			</div>
		</s:form>
	</div>
	<div class="content-bbg"></div>
</div>
