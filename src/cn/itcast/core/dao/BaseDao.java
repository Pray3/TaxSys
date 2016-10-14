package cn.itcast.core.dao;

import java.io.Serializable;
import java.util.List;

import cn.itcast.core.page.PageResult;
import cn.itcast.core.util.QueryHelper;
import cn.itcast.nsfw.info.entity.Info;

public interface BaseDao<T> {
	
	//新增
	public void save(T entity);
	//更新
	public void update(T entity);
	//根据id删除
	public void delete(Serializable id);
	//根据id查找
	public T findObjectById(Serializable id);
	//查找列表
	public List<T> findObjects();
	//根据条件查找列表
	public List<T> findObjects(String hql,List<Object> parameters);
	//根据条件查找列表 - 查询助手
	public List<T> findObjects(QueryHelper queryHelper);
	//分页查找列表 - 查询助手
	public PageResult getPageResult(QueryHelper queryHelper, int pageNo,
			int pageSize);

}
