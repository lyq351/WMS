package com.jk.wms.auth.res.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.jk.wms.auth.res.vo.ResModel;
import com.jk.wms.util.base.BaseEbi;

@Transactional
public interface ResEbi extends BaseEbi<ResModel>{
    /**
     * 获取指定用户所有可操作的资源信息
     * @param uuid 用户uuid
     * @return
     */
	public List<ResModel> getAllByEmp(Long uuid);

}
