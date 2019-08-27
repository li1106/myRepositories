package com.ldd.cms.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.ldd.cms.utils.IKWord;

public class IKDemo {
	public static void main(String[] args) throws IOException {
		String content="李大大大大一个李中华民共和国人小狗小猫小猪小鸡";
		//统计词的结果
		HashMap<String, Integer> frequencies = new HashMap<>();
		// 统计词
		Map<String, Integer> context = IKWord.count(frequencies, content);
		Set<Entry<String,Integer>> set = frequencies.entrySet();
		Iterator<Entry<String, Integer>> iterator = set.iterator();
		System.out.println("---------------------");
		while(iterator.hasNext()){
			Entry<String, Integer> entrty = iterator.next();
			System.out.println(entrty.getKey()+"&&&"+entrty.getValue());
		}
		// 对分词结果的出现次数进行排序
		List<Entry<String, Integer>> list = IKWord.order(frequencies);
		System.out.println(list.size()+"**************");
		System.out.println("%%%%%%%%%%%%%%%%%%%%");
		list.forEach(System.out::println);
		System.out.println("=================");
		List<Entry<String,Integer>> order = IKWord.order(IKWord.count(frequencies, "八串亩维教育中华人民共和国串亩"));
		order.forEach(System.out::println);
	}
}
