package cn.bill.sbjparediscache.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.bill.sbjparediscache.entity.Person;

/**
 * Description: <br/>
 * Date:2018年8月7日 下午5:14:46 <br/>
 *
 * @author fengminbiao@126.com
 * @version
 */

public interface PersonService
{

	List<Person> findByNameLike(String name);

	Person addOrUpdate(Person p);

	List<Person> addOrUpdateAll(List<Person> pList);

	List<Person> findByNameAndAge(String name, Integer age);

	void deleteById(String id);

	Page<Person> findByNameLike(String name, Pageable pageable);

	Person queryById(String id);

}