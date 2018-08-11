package cn.bill.sbjparediscache.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description: 日志工具类<br/>
 * Date:2018年7月26日 上午10:21:31 <br/>
 *
 * @author fengminbiao@126.com
 * @version
 */
public class LoggerUtil
{
	private static Logger logger = LoggerFactory.getLogger(LoggerUtil.class);
	
	private LoggerUtil(){}
	
	/**
	 * 获取Log4j
	 * @return
	 */
	public static Logger getLogger()
	{
		return logger;
	}
	
}
