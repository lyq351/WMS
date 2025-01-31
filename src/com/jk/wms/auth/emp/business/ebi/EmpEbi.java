package com.jk.wms.auth.emp.business.ebi;


import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.jk.wms.auth.emp.vo.EmpModel;
import com.jk.wms.util.base.BaseEbi;


@Transactional
public interface EmpEbi extends BaseEbi<EmpModel>{
	/**
	 * 根据用户名密码登录
	 * @param userName 用户名
	 * @param pwd 密码
	 * @param loginIp 登录IP地址
	 * @return 登录用户信息。如果返回null,表示登录失败。
	 */
	public EmpModel login(String userName, String pwd, String loginIp);
	
	/**
	 * 修改密码
	 * @param userName 用户名
	 * @param pwd 旧密码
	 * @param newPwd 新密码
	 * @return 修改是否成功
	 */
	public boolean changePwd(String userName, String pwd, String newPwd);

	public void save(EmpModel em, Long[] roleUuids);

	public void update(EmpModel em, Long[] roleUuids);

	/**
	 * 获取指定部门所有员工信息
	 * @param uuid 部门uuid
	 * @return
	 */
	public List<EmpModel> getByDep(Long uuid);
}
