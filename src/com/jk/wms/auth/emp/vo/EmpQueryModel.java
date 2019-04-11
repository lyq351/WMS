package com.jk.wms.auth.emp.vo;

import com.jk.wms.util.base.BaseQueryModel;
import com.jk.wms.util.format.FormatUtil;



public class EmpQueryModel extends EmpModel implements BaseQueryModel{
	//追加生日作为查询条件的最大值字段
	private Long birthday2;
	private String birthday2View;
	
	public String getBirthday2View() {
		return birthday2View;
	}
	public Long getBirthday2() {
		return birthday2;
	}
	public void setBirthday2(Long birthday2) {
		this.birthday2 = birthday2;
		this.birthday2View = FormatUtil.formatDate(birthday2);
	}
	
}
