package cn.itcast.core.util;

import java.util.ArrayList;
import java.util.List;


public class QueryHelper {
	// from 子句
	private String fromClause = "";
	// where 子句
	private String whereClause = "";
	// by 子句
	private String orderByClause = "";

	private List<Object> parameters;
	//排序属性
	public static String ORDER_BY_DESC="DESC";
	public static String ORDER_BY_AEC="AEC";

	/**
	 * 构造from 子句
	 * 
	 * @param clazz
	 *            实体类
	 * @param alias
	 *            实体类对应的别名
	 */
	public QueryHelper(Class clazz, String alias) {
		if (alias != null) {
			fromClause = "FROM " + clazz.getSimpleName() + " " + alias;
		}
	}

	/**
	 * 构造where子句
	 * 
	 * @param condition
	 *            查询条件语句
	 * @param params
	 *            查询条件语句中？代表的查询条件值
	 */
	public void addCondition(String condition, Object... params) {
		if (whereClause.length() > 1) {// 非第一个查询条件
			whereClause += " AND " + condition;
		} else {// 第一个查询条件
			whereClause += " WHERE " + condition;
		}
	   if(parameters ==null){
		   parameters =new ArrayList<Object>();
	   }	
	   if(params != null){
		   for(Object param :params){
			   parameters.add(param);
		   }
	   }
	}
	
	/**
	 * 构造order by子句
	 * @param property 
	 * @param order
	 */
  public void  addOrderByProperty(String property,String order){
	  if (orderByClause.length() > 1) {// 非第一排序条件
		  orderByClause += " , " + property+ " "+ order;
		} else {// 第一个查询条件
			orderByClause = " ORDER BY " + property+ " "+ order;
		}
  }
   //查询SQL语句
	public String getQueryListHql() {
		return fromClause + whereClause + orderByClause;
	}
	//查询统计数的SQL语句
	public String getQueryCountHql() {
		return "SELECT COUNT(*) " +fromClause + whereClause ;
	}
	
	public List<Object> getParameters(){
		return parameters;
	}
}
