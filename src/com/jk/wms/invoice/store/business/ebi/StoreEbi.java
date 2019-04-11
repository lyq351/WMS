package com.jk.wms.invoice.store.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import com.jk.wms.invoice.store.vo.StoreModel;
import com.jk.wms.util.base.BaseEbi;

@Transactional
public interface StoreEbi extends BaseEbi<StoreModel>{

}
