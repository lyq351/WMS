package com.jk.wms.invoice.orderdetail.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import com.jk.wms.invoice.orderdetail.vo.OrderDetailModel;
import com.jk.wms.util.base.BaseEbi;

@Transactional
public interface OrderDetailEbi extends BaseEbi<OrderDetailModel>{

}
