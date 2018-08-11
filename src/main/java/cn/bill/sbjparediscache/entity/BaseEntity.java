package cn.bill.sbjparediscache.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 作者Email:fengminbiao@126.com
 * 创建时间：2018年7月2日下午2:46:00
 * 类说明：POJO基类
 */
@SuppressWarnings("serial")
@MappedSuperclass
public class BaseEntity implements Serializable
{
	/**
	 * 注意@Column：此标记可以标注在getter方法或属性前
	 * 
	 * JPA提供的四种标准用法为TABLE,SEQUENCE,IDENTITY,AUTO. 
	 * TABLE：使用一个特定的数据库表格来保存主键。 
	 * SEQUENCE：根据底层数据库的序列来生成主键，条件是数据库支持序列。 
	 * IDENTITY：主键由数据库自动生成（主要是自动增长型） 
	 * AUTO：主键由程序控制。 
	 */
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(length=36)
	private String id;
	@Column
	private String createTime;
	@Column
	private String createBy;
	@Column
	private String updateTime;
	@Column
	private String updateBy;

	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getCreateTime()
	{
		return createTime;
	}
	public void setCreateTime(String createTime)
	{
		this.createTime = createTime;
	}
	public String getCreateBy()
	{
		return createBy;
	}
	public void setCreateBy(String createBy)
	{
		this.createBy = createBy;
	}
	public String getUpdateTime()
	{
		return updateTime;
	}
	public void setUpdateTime(String updateTime)
	{
		this.updateTime = updateTime;
	}
	public String getUpdateBy()
	{
		return updateBy;
	}
	public void setUpdateBy(String updateBy)
	{
		this.updateBy = updateBy;
	}
	@Override
	public String toString()
	{
		return "id=" + id + ", createTime=" + createTime + ", createBy=" + createBy + ", updateTime="
				+ updateTime + ", updateBy=" + updateBy + "";
	}
	
	

}
