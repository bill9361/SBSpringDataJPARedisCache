package cn.bill.sbjparediscache.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Description: 异常处理工具<br/>
 * Date:2018年8月6日 下午3:46:58 <br/>
 *
 * @author fengminbiao@126.com
 * @version
 */
public class ExceptionUtil
{
	
	private ExceptionUtil(){}
	
	/**
	 * 获取异常内容
	 * @param ex
	 * @return
	 */
	public static String getCrashContent(Throwable ex) 
	{
		if(ex != null)
		{
			//可以获取到 Cause By的内容
			Writer writer = new StringWriter();  
			if(writer != null)
			{
				PrintWriter printWriter = new PrintWriter(writer);  
				ex.printStackTrace(printWriter);  
				Throwable cause = ex.getCause();  
				while (cause != null) 
				{  
					cause.printStackTrace(printWriter);  
					cause = cause.getCause();  
				}  
				
				printWriter.close();  
				return writer.toString();
			}
		}
		
		return null;
	}

}
