package com.jk.wms.invoice.order.business.ebo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.jk.wms.auth.emp.vo.EmpModel;
import com.jk.wms.invoice.goods.vo.GoodsModel;
import com.jk.wms.invoice.operdetail.dao.dao.OperDetailDao;
import com.jk.wms.invoice.operdetail.vo.OperDetailModel;
import com.jk.wms.invoice.order.business.ebi.OrderEbi;
import com.jk.wms.invoice.order.dao.dao.OrderDao;
import com.jk.wms.invoice.order.vo.OrderModel;
import com.jk.wms.invoice.order.vo.OrderQueryModel;
import com.jk.wms.invoice.orderdetail.dao.dao.OrderDetailDao;
import com.jk.wms.invoice.orderdetail.vo.OrderDetailModel;
import com.jk.wms.invoice.store.vo.StoreModel;
import com.jk.wms.invoice.storedetail.dao.dao.StoreDetailDao;
import com.jk.wms.invoice.storedetail.vo.StoreDetailModel;
import com.jk.wms.util.base.BaseQueryModel;
import com.jk.wms.util.exception.AppException;
import com.jk.wms.util.num.NumUtil;

public class OrderEbo implements OrderEbi{
	private OrderDao orderDao;
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public void save(OrderModel om) {
		orderDao.save(om);
	}

	public void update(OrderModel om) {
		orderDao.update(om);
	}

	public void delete(OrderModel om) {
		orderDao.delete(om);
	}

	public OrderModel get(Serializable uuid) {
		return orderDao.get(uuid);
	}

	public List<OrderModel> getAll() {
		return orderDao.getAll();
	}

	public List<OrderModel> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {
		return orderDao.getAll(qm,pageNum,pageCount);
	}

	public Integer getCount(BaseQueryModel qm) {
		return orderDao.getCount(qm);
	}

	public void saveBuyOrder(OrderModel om, Long[] goodsUuids, Integer[] nums,
			Double[] prices, EmpModel creater) {
		//保存订单
		//设置订单号:订单号唯一
		String orderNum = NumUtil.generatorOrderNum();
		om.setOrderNum(orderNum);
		//订单创建时间是当前系统时间
		om.setCreateTime(System.currentTimeMillis());
		//当前保存的是采购订单
		om.setOrderType(OrderModel.ORDER_ORDERTYPE_OF_BUY);
		//新保存的订单的状态是未审核
		om.setType(OrderModel.ORDER_TYPE_OF_BUY_NO_CHECK);
		//制单人
		om.setCreater(creater);
		//对应的供应商（已经封装在了om）
		
		Integer totalNum = 0;
		Double totalPrice = 0.0d;
		Set<OrderDetailModel> odms = new HashSet<OrderDetailModel>();
		for(int i = 0;i<goodsUuids.length;i++){
			//创建订单明细的对象并添加到集合中
			OrderDetailModel odm = new OrderDetailModel();
			//设置订单明细数量
			odm.setNum(nums[i]);
			//设置订单剩余未入库数量值
			odm.setSurplus(nums[i]);			
			//设置订单明细单价
			odm.setPrice(prices[i]);
			//设置订单明细的商品
			GoodsModel gm = new GoodsModel();
			gm.setUuid(goodsUuids[i]);
			odm.setGm(gm);
			//设置所属的订单
			odm.setOm(om);
			//将明细对象加入集合
			odms.add(odm);
			
			totalNum += nums[i];
			totalPrice += nums[i]*prices[i];
		}
		//设置订单中对应的所有明细数据
		om.setOdms(odms);
		//设置订单总数量 
		om.setTotalNum(totalNum);
		//设置订单总价值
		om.setTotalPrice(totalPrice);
		
		orderDao.save(om);		
	}

	public List<OrderModel> getAllBuy(OrderQueryModel oqm, Integer pageNum,
			Integer pageCount) {
		//设置一个固定的条件，订单类别为采购
		oqm.setOrderType(OrderModel.ORDER_ORDERTYPE_OF_BUY);
		return orderDao.getAll(oqm, pageNum, pageCount);
	}

	public Integer getCountInStore(OrderQueryModel oqm) {
		oqm.setType(OrderModel.ORDER_TYPE_OF_BUY_CHECK_PASS);
		return orderDao.getCount(oqm);
	}

	public List<OrderModel> getAllInStore(OrderQueryModel oqm, Integer pageNum,
			Integer pageCount) {
		oqm.setType(OrderModel.ORDER_TYPE_OF_BUY_CHECK_PASS);
		return orderDao.getAll(oqm, pageNum, pageCount);
	}
	
	private Integer[] buyCheckOrderTypes = new Integer[]{
			OrderModel.ORDER_ORDERTYPE_OF_BUY,
			OrderModel.ORDER_ORDERTYPE_OF_RETURN_BUY
		};	

	public Integer getCountBuyCheck(OrderQueryModel oqm) {
		return orderDao.getCountOrderTypes(oqm,buyCheckOrderTypes);
	}

	public List<OrderModel> getAllBuyCheck(OrderQueryModel oqm,
			Integer pageNum, Integer pageCount) {
		return orderDao.getAllOrderTypes(oqm, pageNum, pageCount,buyCheckOrderTypes);
	}

	public void buyCheckPass(Long uuid, EmpModel checker) {
		//所谓审核实际上是修改业务
		//快照更新
		OrderModel temp = orderDao.get(uuid);
		
		//逻辑校验：比对的数据必须是从数据库中取出的数据，而不能使用页面传递的数据
		if(!temp.getType().equals(OrderModel.ORDER_TYPE_OF_BUY_NO_CHECK)){
			throw new AppException("对不起，请不要进行非法操作！");
		}
		
		//type
		temp.setType(OrderModel.ORDER_TYPE_OF_BUY_CHECK_PASS);
		//审核时间
		temp.setCheckTime(System.currentTimeMillis());
		//审核人
		temp.setChecker(checker);		
	}

	public void buyCheckNoPass(Long uuid, EmpModel checker) {
		OrderModel temp = orderDao.get(uuid);
		//逻辑校验
		if(!temp.getType().equals(OrderModel.ORDER_TYPE_OF_BUY_NO_CHECK)){
			throw new AppException("对不起，请不要进行非法操作！");
		}
		temp.setType(OrderModel.ORDER_TYPE_OF_BUY_CHECK_NO_PASS);
		temp.setCheckTime(System.currentTimeMillis());
		temp.setChecker(checker);
	}

	private OrderDetailDao orderDetailDao;
	private StoreDetailDao storeDetailDao;
	private OperDetailDao operDetailDao;
	public void setOperDetailDao(OperDetailDao operDetailDao) {
		this.operDetailDao = operDetailDao;
	}
	public void setStoreDetailDao(StoreDetailDao storeDetailDao) {
		this.storeDetailDao = storeDetailDao;
	}
	public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
	}

	public OrderDetailModel inGoods(Long storeUuid, Long odmUuid, Integer num,
			EmpModel login) {
		//入库
		//1.订单明细中的剩余数量要更新
		//快照
		OrderDetailModel odm = orderDetailDao.get(odmUuid);
		OrderModel om = odm.getOm();
		
		if(!om.getType().equals(OrderModel.ORDER_TYPE_OF_BUY_CHECK_PASS)){
			throw new AppException("数据错误！");
		}
		if(odm.getSurplus()<num){
			throw new AppException("数据错误！");
		}
		
		//更新订单明细的剩余数量
		odm.setSurplus(odm.getSurplus()-num);
		
		//货物信息需要使用
		GoodsModel gm = odm.getGm();
		StoreModel sm = new StoreModel();
		sm.setUuid(storeUuid);
		
		//2.库存数量要发生变化
		//使用快照更新数量
		//查询按照仓库与货物查询
		StoreDetailModel sdm = storeDetailDao.getBySmAndGm(storeUuid,gm.getUuid());
		//判断该货物在指定仓库中有没有存储过
		if(sdm != null){
			//如果存储过，快照更新
			//修改当前库存数量
			sdm.setNum(sdm.getNum()+num);
		}else{
			//如果没有存储过，新增数据
			sdm = new StoreDetailModel();
			sdm.setNum(num);
			sdm.setGm(gm);
			sdm.setSm(sm);
			storeDetailDao.save(sdm);
		}
		
		//3.数据要求可追踪，记录操作日志
		OperDetailModel opdm = new OperDetailModel();
		opdm.setNum(num);
		opdm.setOperTime(System.currentTimeMillis());
		opdm.setType(OperDetailModel.OPER_TYPE_OF_IN);
		opdm.setGm(gm);
		opdm.setSm(sm);
		opdm.setEm(login);
		operDetailDao.save(opdm);
		
		//4.设置订单的状态为入库完毕
		Integer sum = 0;
		for(OrderDetailModel temp : om.getOdms()){
			sum += temp.getSurplus();
		}
		if(sum == 0 ){
			//全部入库完毕
			om.setType(OrderModel.ORDER_TYPE_OF_BUY_COMPLETE);
			om.setEndTime(System.currentTimeMillis());
		}
		return odm;
	}

}
