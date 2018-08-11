package cn.bill.sbjparediscache.util;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimeUtil
{
	
	/**
	 * 存到数据库使用的转换
	 * 系统默认时间转世界统一时间(UTC)
	 * @param srcDate 原时间
	 * @param srcPattern 原时间格式
	 * @return 系统默认时间，格式为srcPattern
	 */
	public static String defaultTime2Utc(String srcDate,String srcPattern) throws Exception
	{
		return switchTime(srcDate, TimeZone.getDefault().getID(), srcPattern, "UTC", srcPattern);
	}
	
	/**
	 * 从数据库读取出来使用的转换
	 * 世界统一时间(UTC)转系统默认时间
	 * @param srcDate 原时间
	 * @param srcPattern 原时间格式
	 * @return 系统默认时间，格式为srcPattern
	 */
	public static String utc2DefaultTime(String srcDate,String srcPattern) throws Exception
	{
		return switchTime(srcDate, "UTC", srcPattern, TimeZone.getDefault().getID(), srcPattern);
	}

	/**
	 * 转换时间
	 * @param srcDate 原时间
	 * @param srcTimeZoneId 原时间对应的原时区ID
	 * @param srcPattern 原时间格式
	 * @param targetTimeZoneId 目标转换时间时区
	 * @param targetPattern 目标转换时间格式
	 * @return 目标时间
	 */
	private static String switchTime(String srcDate,String srcTimeZoneId,String srcPattern,String targetTimeZoneId,String targetPattern) throws Exception
	{
		SimpleDateFormat utcFormat = new SimpleDateFormat(srcPattern);
		utcFormat.setTimeZone(TimeZone.getTimeZone(srcTimeZoneId));
		//utc时间
		Date utcDate = utcFormat.parse(srcDate);
		
		SimpleDateFormat targetFormat = new SimpleDateFormat(targetPattern);
		targetFormat.setTimeZone(TimeZone.getTimeZone(targetTimeZoneId));
		//目标时区目标格式时间
		return targetFormat.format(utcDate);
	}

	
	/**
	 * 获得当前时间
	 * @return
	 */
	public static String getCurrentTime(String pattern)
	{
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(new Date());
	}

	/**
	 * 获得当前时间
	 * 默认时间格式：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getCurrentTime() throws Exception
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(new Date());
	}
	/**
	 * 获得strDate()对应的Date对象
	 * @param pattern
	 * @param strDate yyyy-MM-dd
	 * @return
	 */
	public static Date formatDate(String pattern,String strDate) throws Exception
	{
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.parse(strDate);
	}
	
	/**
	 * 转换日期格式
	 * @param pattern	格式
	 * @param date	时间
	 * @return
	 */
	public static String formatDate(String pattern,Date date)
	{
		if(!StringUtil.isEmpty(pattern) && date != null)
		{
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			return format.format(date);
		}
		
		return null;
	}
	
	/**
	 * 转换日期格式
	 * @param newPattern
	 * @param oldPattern
	 * @param oldDate
	 * @return
	 */
	public static String formatDate(String newPattern,String oldPattern,String oldDate) throws Exception
	{
		if(!StringUtil.isEmpty(newPattern) && !StringUtil.isEmpty(oldPattern) && !StringUtil.isEmpty(oldDate))
		{
			SimpleDateFormat oldFormat = new SimpleDateFormat(oldPattern);
			SimpleDateFormat newFormat = new SimpleDateFormat(newPattern);
			Date newDate = oldFormat.parse(oldDate);
			return newFormat.format(newDate);
		}
		return null;
	}
	
	/**
	 * 将任一毫秒数都当作UTC毫秒数来转化为pattern格式的String时间
	 * 与formatByUTCStrTime()相对应
	 * @param pattern
	 * @param timeMillis 任一毫秒数都当作UTC毫秒数来转换
	 * @return
	 */
	public static String formatByUTCTimeMillis(String pattern,long timeMillis) throws Exception
	{
		if(!StringUtil.isEmpty(pattern))
		{
			Date d = new Date(timeMillis);
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			format.setTimeZone(TimeZone.getTimeZone("UTC"));
			return format.format(d);
		}
		return null;
	}
	
	/**
	 * 将任一时间都当作UTC时间来转化为世界时间的毫秒数时间
	 * 与formatByUTCTimeMillis()相对应
	 * @param pattern strTime的日期格式
	 * @param strTime 任一时间都当作UTC时间来转换
	 * @return
	 */
	public static long formatByUTCStrTime(String pattern,String strTime) throws Exception
	{
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		format.setTimeZone(TimeZone.getTimeZone("UTC"));
		return format.parse(strTime).getTime();
	}
	
	/**
	 * 获取date对应的星期几
	 * 0日,1一,2二,3三,4四,5五,6六
	 * @param date
	 * @return
	 */
	public static int getDayOfWeek(Date date)
	{
		Calendar c = Calendar.getInstance();
		if(date != null) c.setTime(date);
		return c.get(Calendar.DAY_OF_WEEK)-1;
	}
	
	/**
	 * 按天数+-时间
	 * @param date	pattern格式的时间字符串
	 * @param pattern	格式
	 * @param amount +-天数
	 * @return
	 */
	public static String calcByDay(String date,String pattern,int amount) throws Exception
	{
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date newDate = format.parse(date);
		Calendar c = Calendar.getInstance();
		c.setTime(newDate);
		c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH)+amount);
		return format.format(c.getTime());
	}


	/**
	 * 获取上个月相同日期
	 * @param currentDate
	 * @param pattern
	 * @return
	 */
	public static String getLastMonthSameDate(String currentDate,String pattern) throws Exception
	{
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date newDate = format.parse(currentDate);
		Calendar c = Calendar.getInstance();
		c.setTime(newDate);
		c.set(Calendar.MONTH, c.get(Calendar.MONTH)-1);
		return format.format(c.getTime());
	}
	
	/**
	 * 判断是否是同一天
	 */
	public static boolean isSameDay(String date1, String date2)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try
		{
			Date date1D = sdf.parse(date1);
			Date date2D = sdf.parse(date2);
			if (date1D.getTime() == date2D.getTime())
			{
				return true;
			}
		} catch (Exception e)
		{
			return false;
		}
		return false;
	}
	
}
