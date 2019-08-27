package com.li.test;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

public class JsoupTest {

	@Test
	public void test_jsoup_get() throws IOException {
		// 获取文档对象
		Connection connect = Jsoup.connect("https://www.qb5200.tw/xiaoshuo/2/2906/1668346.html");
		for (int i = 1; i <= 2; i++) {
			Document document = null;
			try {
				document = connect.get();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (document != null) {

				Elements class1 = document.getElementsByClass("content");
				Elements select = class1.get(0).select("h1");
				String title = select.get(0).text();

				Element element = document.getElementById("content");
				String text = element.text();
				System.out.println(title);
				System.out.println(text);
				System.out.println("======================================================================");
				writeFile("青铜剑客  " + title, text);
			}
			Elements els = document.getElementsByClass("page_chapter");
			Elements select = els.get(0).select("a");
			String attr = select.get(2).attr("href");
			connect = Jsoup.connect("https://www.qb5200.tw" + attr);
		}
	}

	private void writeFile(String title, String text) throws IOException {
		title = title.replace("?", "").replace(" ", "").replace("|", "").replace(",", "").replace("\"", "");
		// 创建文件对象
		File file = new File("D:\\1703ajsoup", title + ".txt");
		// 创建文件的输出流
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf8"));
		bw.write(text);
		bw.flush();
		bw.close();
	}

}
