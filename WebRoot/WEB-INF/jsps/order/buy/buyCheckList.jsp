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
	});
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">进货审核</span>
		</div>
	</div>
	<div class="content-text">
		<s:form action="order_buyCheckList" method="post"> 
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(images/table_bg.gif) repeat-x;">
						<td width="20%" height="30">订单号</td>
						<td width="22%">供应商</td>
						<td width="10%">制单人</td>
						<td width="20%">制单时间</td>
						<td width="10%">订单商品总量</td>
						<td width="12%">订单总金额</td>
						<td width="6%">审核</td>
					</tr>
					<s:iterator value="orderList">
						<tr align="center" bgcolor="#FFFFFF">
							<td width="13%" height="30">${orderNum}</td>
							<td>${sm.name }</td>
							<td>${creater.name }</td>
							<td>${createTimeView }</td>
							<td>${totalNum }</td>
							<td align="right">${totalPriceView}  元</td>
							<td>
								<s:if test=" type == @com.jk.wms.invoice.order.vo.OrderModel@ORDER_TYPE_OF_BUY_NO_CHECK">
									<s:a action="order_buyCheckDetail" cssClass="xiu">
										<s:param name="om.uuid" value="uuid"/>
										审核
									</s:a>
								</s:if>
								<s:else>
									<span style="color:red">${typeView}</span>
								</s:else>
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
