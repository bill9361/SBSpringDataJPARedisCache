package cn.bill.sbjparediscache.entity;

import javax.persistence.Column;
import javax.persistence.Entity;


/**
 * Description: <br/>
 * Date:2018年8月7日 下午1:25:31 <br/>
 *
 * @author fengminbiao@126.com
 * @version
 */

@SuppressWarnings("serial")
//继承父实体，父实体需要用@MappedSuperclass注解标明
@Entity
public class Person extends BaseEntity
{
	@Column
	private String name;		//姓名
	@Column
	private String idCardNo;	//身份证号
	@Column
	private String phoneNo;		//电话号码
	
	@Column
	private Integer age;		//年龄
	@Column
	private String email;		//邮箱

	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}

	public String getIdCardNo()
	{
		return idCardNo;
	}
	public void setIdCardNo(String idCardNo)
	{
		this.idCardNo = idCardNo;
	}

	public String getPhoneNo()
	{
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo)
	{
		this.phoneNo = phoneNo;
	}
	
	public Integer getAge()
	{
		return age;
	}
	public void setAge(Integer age)
	{
		this.age = age;
	}
	
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	@Override
	public String toString()
	{
		return "Person [name=" + name + ", idCardNo=" + idCardNo + ", phoneNo=" + phoneNo + ", age=" + age + ", email="
				+ email + ", getId()=" + getId() + ", getCreateTime()=" + getCreateTime() + ", getCreateBy()="
				+ getCreateBy() + ", getUpdateTime()=" + getUpdateTime() + ", getUpdateBy()=" + getUpdateBy() + "]";
	}
	
}
