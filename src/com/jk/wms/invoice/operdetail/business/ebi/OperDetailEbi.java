package com.jk.wms.invoice.operdetail.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import com.jk.wms.invoice.operdetail.vo.OperDetailModel;
import com.jk.wms.util.base.BaseEbi;

@Transactional
public interface OperDetailEbi extends BaseEbi<OperDetailModel>{

}
