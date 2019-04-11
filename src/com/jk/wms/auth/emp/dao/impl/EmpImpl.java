package com.jk.wms.auth.emp.dao.impl;


import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.jk.wms.auth.emp.dao.dao.EmpDao;
import com.jk.wms.auth.emp.vo.EmpModel;
import com.jk.wms.auth.emp.vo.EmpQueryModel;
import com.jk.wms.util.base.BaseImpl;
import com.jk.wms.util.base.BaseQueryModel;

public class EmpImpl extends BaseImpl<EmpModel> implements EmpDao{

	public void doQbc(DetachedCriteria dc,BaseQueryModel qm){
		EmpQueryModel eqm = (EmpQueryModel)qm;
		if(eqm.getUserName()!=null && eqm.getUserName().trim().length()>0){
			dc.add(Restrictions.eq("userName", eqm.getUserName().trim()));
		}
		if(eqm.getName()!=null && eqm.getName().trim().length()>0){
			dc.add(Restrictions.like("name", "%"+eqm.getName().trim()+"%"));
		}
		if(eqm.getTele()!=null && eqm.getTele().trim().length()>0){
			dc.add(Restrictions.like("tele", "%"+eqm.getTele().trim()+"%"));
		}
		if(eqm.getGender()!=null && eqm.getGender()!= -1){
			dc.add(Restrictions.eq("gender", eqm.getGender()));
		}
		if(eqm.getEmail()!=null && eqm.getEmail().trim().length()>0){
			dc.add(Restrictions.like("email", "%"+eqm.getEmail().trim()+"%"));
		}
		//eqm.dm.uuid
		if(eqm.getDm()!=null && eqm.getDm().getUuid()!=null && eqm.getDm().getUuid()!=-1){
			//如果查询的条件是封装对象的uuid属性，可以使用两种格式构造
			//1.直接使用对象做查询条件:默认使用该对象的uuid作为查询条件(推荐)
			dc.add(Restrictions.eq("dm", eqm.getDm()));
			//2.使用对象的uuiid作为查询条件:仅支持使用uuid作为查询条件的格式
			//dc.add(Restrictions.eq("dm.uuid",eqm.getDm().getUuid()));
		}
		//birthday
		if(eqm.getBirthday()!=null){
			dc.add(Restrictions.ge("birthday", eqm.getBirthday()));
		}
		//birthday2
		if(eqm.getBirthday2()!=null){
			dc.add(Restrictions.le("birthday", eqm.getBirthday2()+86400000-1));
		}
	}

	public static void main(String[] args) {
		System.out.println(new Date(1422230400000L));
		System.out.println(new Date(1422230400000L+86400000-1));
	}
	
	public EmpModel getByUserNameAndPwd(String userName, String pwd) {
		String hql ="from EmpModel where userName = ? and pwd = ?";
		List<EmpModel> temp = this.getHibernateTemplate().find(hql,userName,pwd);
		return	temp.size()>0 ? temp.get(0):null; 
	}

	public boolean updatePwdByUserNameAndPwd(String userName, String pwd,String newPwd) {
		String hql = " update EmpModel set pwd = ? where userName = ? and pwd = ?";
		int row = this.getHibernateTemplate().bulkUpdate(hql,newPwd,userName,pwd);
		return row > 0;
	}

	public List<EmpModel> getAllByDepUuid(Long uuid) {
		String hql = " from EmpModel where dm.uuid = ?";
		return this.getHibernateTemplate().find(hql,uuid);
	}
}




