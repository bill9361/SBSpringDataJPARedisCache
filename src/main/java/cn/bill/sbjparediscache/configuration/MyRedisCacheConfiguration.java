package cn.bill.sbjparediscache.configuration;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Description: Redis缓存的配置<br/>
 * Date:2018年8月10日 上午10:31:08 <br/>
 *
 * @author fengminbiao@126.com
 * @version
 */
@Configuration
public class MyRedisCacheConfiguration extends CachingConfigurerSupport
{

	/**
	 * 使用缓存注解时会使用StringRedisSerializer对Key进行序列化，使用GenericJackson2JsonRedisSerializer对Value进行反序列化。
	 * 
	 * Jackson序列化器其实有两个，Jackson2JsonRedisSerializer和我们上面使用的GenericJackson2JsonRedisSerializer。
	 * 如果使用Jackson2JsonRedisSerializer在反序列化时会遇到问题，因为没有具体泛型或泛型为Object时，会将缓存中的数据反序列化为LinkedHashMap，而我们需要的是User对象，因此就会抛出一个异常。
	 * 
	 * 而使用GenericJackson2JsonRedisSerializer就可以避免这种情况。
	 * @return
	 */
	@Bean
	public RedisCacheConfiguration redisCacheConfiguration() 
	{
	    return RedisCacheConfiguration
	            .defaultCacheConfig()
	            .serializeKeysWith(
	                    RedisSerializationContext
	                            .SerializationPair
	                            .fromSerializer(new StringRedisSerializer()))
	            .serializeValuesWith(
	                    RedisSerializationContext
	                            .SerializationPair
	                            .fromSerializer(new GenericJackson2JsonRedisSerializer()));
	}

}
