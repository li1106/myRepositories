package com.li.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	/*
	 * 方法1：(10分) 给一个时间对象，返回该时间所在月的1日0时0分0秒。例如一个Date对象的值是2019-05-18 11:37:22
	 * 则返回的结果为2019-05-01 00:00:00
	 */
	public static Date getMonthBegin(Date data) {
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		// 设置为1号,当前日期既为本月第一天
		c.set(Calendar.DAY_OF_MONTH, 1);
		// 将小时至0
		c.set(Calendar.HOUR_OF_DAY, 0);
		// 将分钟至0
		c.set(Calendar.MINUTE, 0);
		// 将秒至0
		c.set(Calendar.SECOND, 0);
		// 将毫秒至0
		c.set(Calendar.MILLISECOND, 0);
		Date time = c.getTime();
		return time;
	}

	/*
	 * 方法2：(10分) 给任意一个时间对象，返回该时间所在月的最后日23时59分59秒，需要考虑月大月小和二月特殊情况。
	 * 例如一个Date对象的值是2019-05-18 11:37:22，则返回的时间为2019-05-31 23:59:59
	 * 例如一个Date对象的值是2019-02-05 15:42:18，则返回的时间为2019-02-28 23:59:59
	 */
	public static Date getMonthEnd(Date data) {
		Calendar c = Calendar.getInstance();
		c.setTime(data);

		// 设置为当月最后一天
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		// 将小时至23
		c.set(Calendar.HOUR_OF_DAY, 23);
		// 将分钟至59
		c.set(Calendar.MINUTE, 59);
		// 将秒至59
		c.set(Calendar.SECOND, 59);
		// 将毫秒至999
		c.set(Calendar.MILLISECOND, 999);
		Date time = c.getTime();
		return time;
	}
}
