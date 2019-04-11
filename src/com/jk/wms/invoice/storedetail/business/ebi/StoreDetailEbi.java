package com.jk.wms.invoice.storedetail.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import com.jk.wms.invoice.storedetail.vo.StoreDetailModel;
import com.jk.wms.util.base.BaseEbi;

@Transactional
public interface StoreDetailEbi extends BaseEbi<StoreDetailModel>{

}
