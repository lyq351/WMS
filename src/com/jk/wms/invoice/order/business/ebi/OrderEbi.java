package com.jk.wms.invoice.order.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.jk.wms.auth.emp.vo.EmpModel;
import com.jk.wms.invoice.order.vo.OrderModel;
import com.jk.wms.invoice.order.vo.OrderQueryModel;
import com.jk.wms.invoice.orderdetail.vo.OrderDetailModel;
import com.jk.wms.util.base.BaseEbi;

@Transactional
public interface OrderEbi extends BaseEbi<OrderModel>{
	
	/**
	 * 保存采购订单
	 * @param om 订单数据模型（封装了供应商uuid）
	 * @param goodsUuids 商品UUID数组
	 * @param nums	数量数组
	 * @param prices 单价数组
	 * @param creater 制单人
	 */
	public void saveBuyOrder(OrderModel om, Long[] goodsUuids, Integer[] nums,
			Double[] prices, EmpModel creater);
	/**
	 * 获取所有采购订单数据
	 * @param oqm
	 * @param pageNum
	 * @param pageCount
	 * @return
	 */
	public List<OrderModel> getAllBuy(OrderQueryModel oqm, Integer pageNum,
			Integer pageCount);
	
	public Integer getCountInStore(OrderQueryModel oqm);
	
	public List<OrderModel> getAllInStore(OrderQueryModel oqm, Integer pageNum,
			Integer pageCount);
	
	public Integer getCountBuyCheck(OrderQueryModel oqm);
	public List<OrderModel> getAllBuyCheck(OrderQueryModel oqm,
			Integer pageNum, Integer pageCount);
	/**
	 * 采购审核通过
	 * @param uuid 被审核的uuid
	 * @param checker 审核人
	 */
	public void buyCheckPass(Long uuid, EmpModel checker);
	/**
	 * 采购审核驳回
	 * @param uuid
	 * @param checker
	 */
	public void buyCheckNoPass(Long uuid, EmpModel checker);
	/**
	 * 
	 * @param storeUuid
	 * @param odmUuid
	 * @param num
	 * @param login
	 * @return
	 */
	public OrderDetailModel inGoods(Long storeUuid, Long odmUuid, Integer num,
			EmpModel login);

}
