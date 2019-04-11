package com.jk.wms.auth.dep.business.ebi;


import org.springframework.transaction.annotation.Transactional;

import com.jk.wms.auth.dep.vo.DepModel;
import com.jk.wms.util.base.BaseEbi;

//为什么事务加在接口上。方便以后的维护。
@Transactional
//切面：spring内置
//切入点：定义了该注解所在的类或接口中的所有方法
public interface DepEbi extends BaseEbi<DepModel> {

	

}
