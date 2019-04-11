package com.jk.wms.auth.dep.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.jk.wms.auth.dep.dao.dao.DepDao;
import com.jk.wms.auth.dep.vo.DepModel;
import com.jk.wms.auth.dep.vo.DepQueryModel;
import com.jk.wms.util.base.BaseImpl;
import com.jk.wms.util.base.BaseQueryModel;

public class DepImpl extends BaseImpl<DepModel> implements DepDao {

	public void doQbc(DetachedCriteria dc, BaseQueryModel qm) {
		DepQueryModel dqm = (DepQueryModel)qm;
		if(dqm.getName()!=null && dqm.getName().trim().length() > 0 ){
			dc.add(Restrictions.like("name", "%"+dqm.getName().trim()+"%"));
		}
		if(dqm.getTele()!=null && dqm.getTele().trim().length() > 0 ){
			dc.add(Restrictions.like("tele", "%"+dqm.getTele().trim()+"%"));
		}		
	}

	

}
