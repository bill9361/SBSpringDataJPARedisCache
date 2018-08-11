package cn.bill.sbjparediscache.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cn.bill.sbjparediscache.dao.PersonDao;
import cn.bill.sbjparediscache.entity.Person;
import cn.bill.sbjparediscache.service.PersonService;

/**
 * Description: <br/>
 * Date:2018年8月7日 下午5:12:01 <br/>
 *
 * @author fengminbiao@126.com
 * @version
 */
@CacheConfig(cacheNames="personCache")
@Service
public class PersonServiceImpl implements PersonService
{
	@Autowired
	private PersonDao personDao;
	
	/**
	 * https://www.ibm.com/developerworks/cn/opensource/os-cn-spring-cache/index.html
	 * 在Service层
	 * findxx方法上加@Cacheable表示存入或读取缓存:
	 * 
	 * 释：@Cacheable:当第一次调用这个方法时，它的结果会被缓存下来，在缓存的有效时间内，以后访问这个方法都直接返回缓存结果，不再执行方法中的代码段。
	 * 				这个注解可以用condition属性来设置条件，如果不满足条件，就不使用缓存能力，直接执行方法。可以使用key属性来指定key的生成规则。
	 * key属性是用来指定Spring缓存方法的返回结果时对应的key的。该属性支持SpringEL表达式。当我们没有指定该属性时，Spring将使用默认策略生成key。
	 * 默认策略：
	 * 例如：
	 * package service;
	 * public class MemcachedService {
	 * 
	 * 		@Cacheable(cacheNames={"MyEhcache"})
	 *  	public String storeUserAddress(String accountId, String address)
	 *  	{
	 *  		return address;
	 *  	}
	 * 
	 *  }
	 *  调用时：
	 * memCachedService.storeUserAddress("user", "BeiJing");
	 * 所以对应的key为：service.MemcachedService-storeUserAddress_user_BeiJing
	 * 
	 * 
	 * 自定义策略：
	 * 自定义策略是指我们可以通过Spring的EL表达式来指定我们的key。这里的EL表达式可以使用方法参数及它们对应的属性。使用方法参数时我们可以直接使用“#参数名”或者“#p参数index”。
	 * 
	 * package service;
	 * public class MemcachedService {
	 * 
	 * 		@Cacheable(cacheNames={"MyEhcache"},key="#p0")
	 *  	public String storeUserAddress(String accountId, String address)
	 *  	{
	 *  		return address;
	 *  	}
	 * 
	 *  }
	 *  调用时：
	 * memCachedService.storeUserAddress("user", "BeiJing");
	 * 所以对应的key为：service.MemcachedService-storeUserAddress_user
	 * 则缓存与address无关
	 * 
	 * 
	 * 释：@CachePut:与@Cacheable不同，@CachePut不仅会缓存方法的结果，还会执行方法的代码段。它支持的属性和用法都与@Cacheable一致。
	 * 
	 * 释：@CacheEvict:与@Cacheable功能相反，@CacheEvict表明所修饰的方法是用来删除失效或无用的缓存数据。
	 * 注：@CacheEvict注释提供的 beforeInvocation 属性，将其设置为 true，这样，在方法执行前我们的缓存就被清空了。可以确保缓存被清空
	 * 
	 * delete**,update**上加@CacheEvict：清除缓存
	 * 释：
	 * 
	 * 参数解释：
	 * value、cacheNames：两个等同的参数（cacheNames为Spring 4新增，作为value的别名），用于指定缓存存储的集合名，不能为空，如果使用EHCache，就是ehcache.xml中声明的cache的name*
　　　   * key：缓存的key，默认为空，既表示使用方法的参数类型及参数值作为key，支持SpEL
　　　   * condition：触发条件，只有满足条件的情况才会加入缓存，默认为空，既表示全部都加入缓存，支持SpEL
　　　   * allEntries：CacheEvict参数，true表示清除value中的全部缓存，默认为false
	 */
	@Cacheable(cacheNames={"personCache"},key="#p0")
	@Override
	public List<Person> findByNameLike(String name)
	{
		name = "%"+name+"%";
		return personDao.findByNameLike(name);
	}
	
	/**
	 * 分页查询
	 * @param name
	 * @param pageable
	 * @return
	 */
	//@Cacheable(cacheNames={"personCache"})
	@Override
	public Page<Person> findByNameLike(String name,Pageable pageable)
	{
		name = "%"+name+"%";
		return personDao.findByNameLike(name, pageable);
	}
	
	//@Cacheable(cacheNames={"personCache"},key="#p0")
	@Cacheable(cacheNames={"personCache"},key="#p0.concat(#p1)")
	@Override
	public List<Person> findByNameAndAge(String name,Integer age)
	{
		name = "%"+name+"%";
		return personDao.findByNameAndAge(name,age);
	}
	

	@Cacheable(cacheNames={"personCache"},key="#p0")
	@Override
	public Person queryById(String id)
	{
		return personDao.queryById(id);
	}
	
	
	/**
	 * 添加或更新
	 * @param p
	 * @return
	 */
	@CachePut(cacheNames={"personCache"},key="#person.id")
	@Override
	public Person addOrUpdate(Person person)
	{
		Person p = personDao.save(person);
		System.out.println(p);
		return p;
	}
	
	/**
	 * 添加或更新All
	 * @param p
	 * @return
	 */
	//@CacheEvict(cacheNames={"MyEhcache"},allEntries=true,beforeInvocation=true)
	@Override
	public List<Person> addOrUpdateAll(List<Person> pList)
	{
		return personDao.saveAll(pList);
	}

	/**
	 * 根据Id删除
	 */
	//@CacheEvict(cacheNames={"MyEhcache"},allEntries=true,beforeInvocation=true)
	@Override
	public void deleteById(String id)
	{
		personDao.deleteById(id);
	}
	
}
