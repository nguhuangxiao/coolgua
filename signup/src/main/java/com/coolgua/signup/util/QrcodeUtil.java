package com.coolgua.signup.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.coolgua.signup.domain.Layout;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class QrcodeUtil {
	private static final String ACODE_URL = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=ACCESS_TOKEN";
	private static final String QRCODE_URL = "https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode?access_token=ACCESS_TOKEN";
	
	/**
	 * 根据参数eventId获取小程序码
	 * @param appId
	 * @param appSecret
	 * @param sceneId, 场景Id
	 * @return
	 */
	public static String getAcode(String appId, String appSecret, String sceneId, String prefixDir) {
		// 获取token
		String webTokenUrl = UserInfoUtil.getWebToken(appId, appSecret);
		String tokenRes = HttpsUtil.httpsRequestToString(webTokenUrl, "GET", null);

		JSONObject webTokenUrlJsonObject = JSONObject.parseObject(tokenRes);
		String access_token = webTokenUrlJsonObject.getString("access_token");
		String qrCodeurl = ACODE_URL.replace("ACCESS_TOKEN", access_token);

		RestTemplate rest = new RestTemplate();
		InputStream is = null;
		OutputStream os = null;
		try {
			Map<String, Object> param = new HashMap<>();
			param.put("scene", sceneId);
			param.put("page", "pages/index/index");
			param.put("width", 300);
			param.put("auto_color", false);
			param.put("is_hyaline", true);
			Map<String, Object> line_color = new HashMap<>();
			line_color.put("r", 0);
			line_color.put("g", 0);
			line_color.put("b", 0);
			param.put("line_color", line_color);
			System.out.println("调用生成微信URL接口传参:" + param);
			MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
			HttpEntity requestEntity = new HttpEntity(param, headers);
			ResponseEntity<byte[]> entity = rest.exchange(qrCodeurl, HttpMethod.POST, requestEntity,
					byte[].class, new Object[0]);
			System.out.println("调用小程序生成微信永久小程序码URL接口返回结果:" + entity.getBody());
			byte[] result = entity.getBody();
			is = new ByteArrayInputStream(result);
			Date timestamp = new Date();
			StringBuilder sb = new StringBuilder();
			sb.append("qrcode/").append(DateUtil.formatDate(timestamp, "yyyyMM")).append("/")
			.append(DateUtil.formatDate(timestamp, "dd")).append("/").append(UUID.randomUUID().toString().replace("-", "")).append(".jpg");
			String picUrl = sb.toString();
			String QrCodeurl = prefixDir + picUrl;
			System.out.println("QrCodeurl========" + QrCodeurl);
			File file = new File(QrCodeurl);
			File parentFile = file.getParentFile();
			if (!parentFile.exists()) {
				parentFile.mkdirs(); // 如果目录不存在则创建
			}
			if (!file.exists()) {
				file.createNewFile();
			}
			os = new FileOutputStream(file);
			int len = 0;
			byte[] buf = new byte[1024];
			while ((len = is.read(buf, 0, 1024)) != -1) {
				os.write(buf, 0, len);
			}
			os.flush();
			return picUrl;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * 根据参数eventId获取小程序二维码
	 * @param appId
	 * @param appSecret
	 * @param eventId
	 * @return
	 */
	public static String getQrcode(String appId, String appSecret, String eventId, String prefixDir) {
		// 获取token
		String webTokenUrl = UserInfoUtil.getWebToken(appId, appSecret);
		String tokenRes = HttpsUtil.httpsRequestToString(webTokenUrl, "GET", null);

		JSONObject webTokenUrlJsonObject = JSONObject.parseObject(tokenRes);
		String access_token = webTokenUrlJsonObject.getString("access_token");
		String qrCodeurl = QRCODE_URL.replace("ACCESS_TOKEN", access_token);

		RestTemplate rest = new RestTemplate();
		InputStream is = null;
		OutputStream os = null;
		try {
			Map<String, Object> param = new HashMap<>();
			param.put("path", "/pages/index/index?eventId=" + eventId);
			param.put("width", 300);
			System.out.println("调用生成微信URL接口传参:" + param);
			MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
			HttpEntity requestEntity = new HttpEntity(param, headers);
			ResponseEntity<byte[]> entity = rest.exchange(qrCodeurl, HttpMethod.POST, requestEntity,
					byte[].class, new Object[0]);
			System.out.println("调用小程序生成微信永久小程序码URL接口返回结果:" + entity.getBody());
			byte[] result = entity.getBody();
			is = new ByteArrayInputStream(result);
			Date timestamp = new Date();
			StringBuilder sb = new StringBuilder();
			sb.append("qrcode/").append(DateUtil.formatDate(timestamp, "yyyyMM")).append("/")
			.append(DateUtil.formatDate(timestamp, "dd")).append("/").append(UUID.randomUUID().toString().replace("-", "")).append(".jpg");
			String picUrl = sb.toString();
			String QrCodeurl = prefixDir + picUrl;
			System.out.println("QrCodeurl========" + QrCodeurl);
			File file = new File(QrCodeurl);
			File parentFile = file.getParentFile();
			if (!parentFile.exists()) {
				parentFile.mkdirs(); // 如果目录不存在则创建
			}
			if (!file.exists()) {
				file.createNewFile();
			}
			os = new FileOutputStream(file);
			int len = 0;
			byte[] buf = new byte[1024];
			while ((len = is.read(buf, 0, 1024)) != -1) {
				os.write(buf, 0, len);
			}
			os.flush();
			return picUrl;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public static void encode(String content, Integer width, Integer height, File file,
			String format) {
		try {
			Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			BitMatrix matrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
//			File file = new File(filePath);
			MatrixToImageWriter.writeToPath(matrix, format, file.toPath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
//		encode("932078", 430, 430, new File("d:/tmp/1.png"), "png");
/*		for (int i = 0; i < 20; i++) {
			String id = getUUId();
			System.out.println(id);
		}*/
		/*
		String a = getLayoutStr();
		System.out.println(a);
		List<Layout> layouts = JSONArray.parseArray(a, Layout.class);
		System.out.println(layouts);
		*/
		String a = "https://reg.coolgua.net/attachment/ce70d2d482104fac8033a46ca94aae61/201807/21/20180721113309741.jpg";
		String xiaoguaUrl = "https://reg.coolgua.net/";
		System.out.println(a.substring(xiaoguaUrl.length()));
	}

	private static String getLayoutStr() {
		List<Layout> l = new ArrayList<>();
		Layout l1 = new Layout();
		l1.setId("0");
		l1.setName("手机");
		l1.setEnglish("cellphone");
		l1.setCustom(0);
		l1.setTypeId(0);
		l1.setDictionary("");
		l.add(l1);
		
		l1 = new Layout();
		l1.setId("1");
		l1.setName("姓名");
		l1.setEnglish("name");
		l1.setCustom(0);
		l1.setTypeId(0);
		l1.setDictionary("");
		l.add(l1);
		
		l1 = new Layout();
		l1.setId("2");
		l1.setName("单位");
		l1.setEnglish("company");
		l1.setCustom(0);
		l1.setTypeId(0);
		l1.setDictionary("");
		l.add(l1);
		
		l1 = new Layout();
		l1.setId("6");
		l1.setName("邮箱");
		l1.setEnglish("email");
		l1.setCustom(0);
		l1.setTypeId(0);
		l1.setDictionary("");
		l.add(l1);
		
		l1 = new Layout();
		l1.setName("场馆");
		l1.setCustom(1);
		l1.setTypeId(4);
		l1.setDictionary("白酒馆##@@##葡萄酒馆##@@##食品饮料馆");
		l.add(l1);
		
		l1 = new Layout();
		l1.setName("产品");
		l1.setCustom(1);
		l1.setTypeId(0);
		l.add(l1);
		
		JSONArray jsonObject1 = (JSONArray) JSONObject.toJSON(l);
		return jsonObject1.toJSONString();
	}
	
	public static String getUUId() {
        int machineId = 1;//最大支持1-9个集群机器部署
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if(hashCodeV < 0) {//有可能是负数
            hashCodeV = - hashCodeV;
        }
        // 0 代表前面补充0     
        // 4 代表长度为4     
        // d 代表参数为正数型
        return machineId + String.format("%015d", hashCodeV);
    }


}
