package com.coolgua.signup.util;



import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmsUtil {
	private static final String USER_AGENT = "CoolguaCRM Client/1.0";
//	private static final Logger log = Logger.getLogger(SmsUtil.class);
	private final static Logger log = LoggerFactory.getLogger(SmsUtil.class);
	public static final String split = ",";

	public static String sendSmsCodeNum(String url, String msg) throws IOException {

		String result = null;
		PostMethod postMethod = new PostMethod(url);
		// 参数组合
		{
			String v = KL(msg);
			postMethod.addParameter("v", v);
		}
		HttpClient client = new HttpClient();
		client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		client.getParams().setParameter(HttpMethodParams.USER_AGENT, USER_AGENT);
		int status = 0;
		try {
			status = client.executeMethod(postMethod);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 结果处理
		if (status == HttpStatus.SC_OK) {
			result = postMethod.getResponseBodyAsString();
		}
		postMethod.releaseConnection();
		return result;
	}

	// 可逆的加密算法
	public static String KL(String inStr) {
		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;
	}

	/**
	 * 创建指定数量的随机字符串
	 * 
	 * @param numberFlag
	 *            是否是数字
	 * @param length
	 * @return
	 */
	public static String createRandom(boolean numberFlag, int length) {
		String retStr = "";
		String strTable = numberFlag ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";
		int len = strTable.length();
		boolean bDone = true;
		do {
			retStr = "";
			int count = 0;
			for (int i = 0; i < length; i++) {
				double dblR = Math.random() * len;
				int intR = (int) Math.floor(dblR);
				char c = strTable.charAt(intR);
				if (('0' <= c) && (c <= '9')) {
					count++;
				}
				retStr += strTable.charAt(intR);
			}
			if (count >= 2) {
				bDone = false;
			}
		} while (bDone);

		return retStr;
	}

	public static String formatString(String template, Map<String, String> data) throws Exception {
		String regex = "\\$\\{(.+?)\\}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(template);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			String name = matcher.group(1);
			String value = (String) data.get(name);
			if (value == null) {
				value = "";
			} else {
				value = value.replaceAll("\\$", "\\\\\\$");
			}
			matcher.appendReplacement(sb, value);
		}
		matcher.appendTail(sb);
		return sb.toString();
	}
}
