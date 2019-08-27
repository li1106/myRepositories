package com.ldd.cms.utils;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

/**
 * 
 * @ClassName: AssertUtil
 * @Description: 断言工具类
 * @author: charles
 * @date: 2019年7月12日 下午3:37:54
 */
public class AssertUtil {

	/**
	 * 
	 * @Title: isTrue
	 * @Description: 断言为真（就是表达结果或参数必须为真， 否则抛出自己的自定义 异常）；
	 * @param exp
	 * @param message
	 * @return: void
	 */
	public static void isTrue(boolean exp, String message) {

		if (!exp)
			throw new CMSRuntimeException(message);

	}

	/**
	 * 断言为假；
	 * 
	 * @Title: isFalse
	 * @Description: TODO
	 * @param exp
	 * @param message
	 * @return: void
	 */
	public static void isFalse(boolean exp, String message) {

		if (exp)
			throw new CMSRuntimeException(message);

	}

	/**
	 * 
	 * @Title: isNotNull
	 * @Description: // 断言对象不为空；
	 * @param obj
	 * @param message
	 * @return: void
	 */

	public static void isNotNull(Object obj, String message) {

		if (obj == null)
			throw new CMSRuntimeException(message);

	}

	/**
	 * 
	 * @Title: isNotNull
	 * @Description: // 断言对象必须空；；
	 * @param obj
	 * @param message
	 * @return: void
	 */

	public static void isNull(Object obj, String message) {

		if (obj != null)
			throw new CMSRuntimeException(message);

	}

	/**
	 * 断言 List 或 Set 集合不为空，没有元素也算空； (6)
	 * 
	 * (8
	 */

	public static void isNotEmpty(Collection c, String message) {

		if (c == null || c.size() == 0) {
			throw new CMSRuntimeException(message);
		}
	}

	/**
	 * 断言 Map 集合不为空，没有元素也算空；
	 * 
	 * @Title: isNotEmpty
	 * @Description: TODO
	 * @param c
	 * @param message
	 * @return: void
	 */
	public static void isNotEmpty(Map<?, ?> map, String message) {

		if (map == null || map.isEmpty()) {
			throw new CMSRuntimeException(message);
		}
	}

	/**
	 * (7) 断言字符串必须有值，去掉空格后，长度必须大于 0；
	 * 
	 * @Title: isNotEmpty
	 * @Description: TODO
	 * @param map
	 * @param message
	 * @return: void
	 */
	public static void hasText(String src, String message) {
		if (null == src || src.trim().length() == 0) {
			throw new CMSRuntimeException(message);
		}
	}

	/**
	 * ) 断言值必须是正数(值大于 0)；
	 * 
	 * @Title: hasText
	 * @Description: TODO
	 * @param src
	 * @param message
	 * @return: void
	 */
	public static void isPostive(boolean exp, String message) {
		if (!exp)
			throw new CMSRuntimeException(message);
	}

	// 方法8：断言值必须大于0，如果小于或等于0，则抛出自定义异常，异常消息为第2个参数默认消息。 (5分)
	public static void greaterThanZero(BigDecimal value, String message) {
		if (value.doubleValue() <= 0)
			throw new CMSRuntimeException(message);
	}
}
