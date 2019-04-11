package com.jk.wms.util.base;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {
    public void save(T t);
	
	public void update(T t);
	
	public void delete(T t);
	/**
	 * 查询所有数据
	 * @return
	 */
	public List<T> getAll();
	/**
	 * 按条件查询
	 * @param qm
	 * @return
	 */
	//public List<T> getAll(BaseQueryModel qm);
	/**
	 * 根据uuid查询
	 * @param uuid
	 * @return
	 */
	public T get(Serializable uuid);
	/**
	 * 按条件查询分页
	 * @param qm
	 * @param pageNum
	 * @param pageCount
	 * @return
	 */
	public List<T> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount);
     /**
      * 获取总条数
      * @param qm
      * @return
      */
	public Integer getCount(BaseQueryModel qm);

}
