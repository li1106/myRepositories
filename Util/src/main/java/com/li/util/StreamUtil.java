package com.li.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.FileInputStream;

public class StreamUtil {
	/*
	 * 方法1：批量关闭流，参数能传入无限个。(10分)
	 * 例如传入FileInputStream对象、JDBC中Connection对象都可以关闭，并且参数个数不限。
	 */
	public static void close(Closeable... io) {
		for (Closeable temp : io) {
			try {
				if (temp != null) {
					temp.close();
				}
			} catch (Exception e) {
			}
		}
	}

	/*
	 * 方法2：传入一个文本文件对象，默认为UTF-8编码，返回该文件内容(10分)，要求方法内部调用上面第1个方法关闭流(5分)
	 */
	private static String ReadTxtFile(String FileName) throws Exception {
		BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(FileName));
		ByteArrayOutputStream memStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		/**
		 * 不理解
		 */
		while ((len = bufferedInputStream.read(buffer)) != -1) {
			memStream.write(buffer, 0, len);
		}
		byte[] data = memStream.toByteArray();
		close(bufferedInputStream, memStream, bufferedInputStream);
		return new String(data);
	}

	public static void main(String[] args) throws Exception {
		String readTxtFile = ReadTxtFile("C:\\Users\\李大大\\Desktop\\考前准备.txt");
		System.out.println(readTxtFile);
	}
}
