package cn.bill.sbjparediscache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 
 * Description: <br/>
 * Date:2018年8月10日 上午10:18:46 <br/>
 *
 * @author fengminbiao@126.com
 * @version
 */
//启用缓存
@EnableCaching
@SpringBootApplication
public class SbSpringDataJpaRedisCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbSpringDataJpaRedisCacheApplication.class, args);
	}
}
