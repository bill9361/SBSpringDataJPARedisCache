package cn.bill.sbjparediscache.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cn.bill.sbjparediscache.entity.Person;

/**
 * Description: <br/>
 * Date:2018年8月7日 下午1:32:49 <br/>
 *
 * @author fengminbiao@126.com
 * @version
 */
public interface PersonDao extends JpaRepository<Person, String>
{
	/**
	 * JPA方法命名规则方式
	 * 根据name模糊查询人员信息
	 * @param name
	 * @return
	 */
	public List<Person> findByNameLike(String name);
	
	/**
	 * 自己写SQL方式(但是这里指的是hql而不是纯sql,比如表名对应的是实体类名，而不是真正的数据库表名，否则报错)
	 * 根据name模糊查询人员信息
	 * @param name
	 * @return
	 */
	@Query(value="select p from Person p where p.name like ?1 and age=?2")
	public List<Person> findByNameAndAge(String name,Integer age);
	

	/**
	 * JPA方法命名规则方式
	 * 根据name模糊查询人员信息+分页
	 * @param name
	 * @return
	 */
	public Page<Person> findByNameLike(String name,Pageable pageable);
	

	/**
	 * 自己写SQL方式(但是这里指的是hql而不是纯sql,比如表名对应的是实体类名，而不是真正的数据库表名，否则报错)
	 * 根据id查找人员
	 * @return
	 */
	@Query(value="select p from Person p where p.id=?1")
	public Person queryById(String id);


}
