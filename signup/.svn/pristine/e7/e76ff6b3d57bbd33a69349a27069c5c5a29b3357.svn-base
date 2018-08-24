package com.coolgua.signup.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.servlet.http.Cookie;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;



public class HttpClientUtil {
	 private static final String SSL_KEY_STORE_PATH = "./conf/coolgua.keystore";
	    private static final String SSL_KEY_STORE_PASS = "coolgua";
	    
		public static String post(String url, List<NameValuePair> params,
				Header header) {

			CloseableHttpClient client = null;
			CloseableHttpResponse response = null;
			String message = "";
			try {
				client = HttpClients.createDefault();
				HttpPost post = new HttpPost(url);
				if (null != header) {
					post.setHeader(header);
				}
				if (null != params) {
					UrlEncodedFormEntity form = new UrlEncodedFormEntity(params,
							"UTF-8");
					post.setEntity(form);
				}
				response = client.execute(post);
				HttpEntity entity = response.getEntity();
				if (null != entity) {
					message = EntityUtils.toString(entity, "UTF-8");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseUtil.close(response, client);
			}
			return message;

		}

		public static String get(String url, List<NameValuePair> params,
				Header header) {

			CloseableHttpClient client = null;
			CloseableHttpResponse response = null;
			String message = "";
			try {
				client = HttpClients.createDefault();
				String extra = "?";
				if (null != params && params.size() > 0) {
					for (NameValuePair param : params) {
						extra += param.getName() + "=" + param.getValue() + "&";
					}
				}
				HttpGet get = new HttpGet(url
						+ extra.substring(0, extra.length() - 1));
				if (null != header) {
					get.setHeader(header);
				}
				response = client.execute(get);
				HttpEntity entity = response.getEntity();
				if (null != entity) {
					message = EntityUtils.toString(entity, "UTF-8");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseUtil.close(response, client);
			}
			return message;

		}
		
		public static String doGet(String url, Map<String, String> param) {
			return doGetCookie(url, param, null);
		}
		
		public static String doGetCookie(String url, Map<String, String> param, List<Cookie> cookies) {
			// 创建Httpclient对象
			CloseableHttpClient httpClient = null;
			String resultString = "";
			CloseableHttpResponse response = null;
			try {
				// 创建uri
				URIBuilder builder = new URIBuilder(url);
				if (param != null) {
					for (String key : param.keySet()) {
						builder.addParameter(key, param.get(key));
					}
				}
				if (CollectionUtils.isNotEmpty(cookies)) {
					CookieStore cookieStore = new BasicCookieStore();
					for (Cookie obj : cookies) {
						BasicClientCookie cookie = new BasicClientCookie(obj.getName(), obj.getValue());
						cookie.setVersion(obj.getVersion());
						cookie.setDomain(obj.getDomain());
						cookie.setAttribute(ClientCookie.DOMAIN_ATTR, "true");
						cookie.setPath(StringUtils.isBlank(obj.getPath()) ? "/" : obj.getPath());
//						System.out.println("Cookie:" + cookie.getName() +";"+ cookie.getValue()+";"+ cookie.getVersion()+";"+ cookie.getDomain()+";"+ cookie.getPath());
						cookieStore.addCookie(cookie);
					}
					httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
				}else{
					httpClient = HttpClients.createDefault();
				}
				URI uri = builder.build();
				// 创建http GET请求
				HttpGet httpGet = new HttpGet(uri);
				// 执行请求
				response = httpClient.execute(httpGet);
				// 判断返回状态是否为200
				if (response.getStatusLine().getStatusCode() == 200) {
					resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (response != null) {
						response.close();
					}
					if (httpClient != null) {
						httpClient.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return resultString;
		}

		public static String doGet(String url) {
			if(url.startsWith("https")) return sslGet(url);
			return doGet(url, null);
		}
		
		public static String doGetCookie(String url, List<Cookie> cookies) {
			if(url.startsWith("https")) return sslGet(url, cookies);
			return doGetCookie(url, null, cookies);
		}

		public static HostnameVerifier hv = new HostnameVerifier() {
	        public boolean verify(String urlHostName, SSLSession session) {
	            System.out.println("Warning: URL Host: " + urlHostName + " vs. " + session.getPeerHost());
	            return true;
	        }
	    };
	    
	    public static String sslPost(final String urlstr, String params) {
			HttpURLConnection connection = null;
			try {
				URL url = new URL(urlstr);
				javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
				javax.net.ssl.TrustManager tm = new myTM();
				trustAllCerts[0] = tm;
				javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext.getInstance("SSL");
				sc.init(null, trustAllCerts, null);
				javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
				HttpsURLConnection.setDefaultHostnameVerifier(hv);
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("POST");
				connection.setDoOutput(true);
				connection.setDoInput(true);
				connection.setUseCaches(false);
				if(StringUtils.isNotBlank(params)){
					OutputStream outputStream = connection.getOutputStream();
					outputStream.write(params.getBytes("UTF-8"));
					outputStream.close();
				}
				final BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
				String line;
				final StringBuffer stringBuffer = new StringBuffer(255);
				synchronized (stringBuffer) {
					while ((line = in.readLine()) != null) {
						stringBuffer.append(line);
						stringBuffer.append("\n");
					}
					return stringBuffer.toString();
				}
			} catch (final IOException e) {
				e.printStackTrace();
				return null;
			} catch (final Exception e1){
				e1.printStackTrace();
				return null;
			}finally {
				if (connection != null) {
					connection.disconnect();
				}
			}
		}
	    
	    public static String sslGet(final String string) {
	    	return sslGet(string, null);
	    }
	    
		public static String sslGet(final String string, List<Cookie> cookies) {
			HttpURLConnection connection = null;
			try {
				URL url = new URL(string);
				javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
				javax.net.ssl.TrustManager tm = new myTM();
				trustAllCerts[0] = tm;
				javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext.getInstance("SSL");
				sc.init(null, trustAllCerts, null);
				javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
				
				HttpsURLConnection.setDefaultHostnameVerifier(hv);
				connection = (HttpURLConnection) url.openConnection();
				if(CollectionUtils.isNotEmpty(cookies)){
					StringBuilder sb = new StringBuilder();
					for (Cookie cookie : cookies) {
						if(StringUtils.isNotBlank(sb.toString())){
							sb.append(";");
						}
						sb.append(cookie.getName()).append("=").append(cookie.getValue());
					}
					connection.setRequestProperty("Cookie", sb.toString());// 给服务器送登录后的cookie
				}
				final BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
				String line;
				final StringBuffer stringBuffer = new StringBuffer(255);
				synchronized (stringBuffer) {
					while ((line = in.readLine()) != null) {
						stringBuffer.append(line);
						stringBuffer.append("\n");
					}
					return stringBuffer.toString();
				}
			} catch (final Exception e1){
				e1.printStackTrace();
				return null;
			}finally {
				if (connection != null) {
					connection.disconnect();
				}
			}
		}
		
		static class myTM implements javax.net.ssl.TrustManager, javax.net.ssl.X509TrustManager {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public boolean isServerTrusted(
					java.security.cert.X509Certificate[] certs) {
				return true;
			}

			public boolean isClientTrusted(
					java.security.cert.X509Certificate[] certs) {
				return true;
			}

			public void checkServerTrusted(
					java.security.cert.X509Certificate[] certs, String authType)
					throws java.security.cert.CertificateException {
				return;
			}

			public void checkClientTrusted(
					java.security.cert.X509Certificate[] certs, String authType)
					throws java.security.cert.CertificateException {
				return;
			}
		}
		
		public static String doPost(String url, Map<String, String> param) {
			if(url.startsWith("https")){
				StringBuffer sb = new StringBuffer();
				if(param != null && param.size() > 0){
					Set<Entry<String, String>> entrySet = param.entrySet();
					for (Entry<String, String> entry : entrySet) {
						String key = entry.getKey();
						String value = entry.getValue();
						if(StringUtils.isNotBlank(sb.toString())){
							sb.append("&").append(key).append("=").append(value);
						}else{
							sb.append(key).append("=").append(value);
						}
					}
				}
				return sslPost(url, sb.toString());
			}
			// 创建Httpclient对象
			CloseableHttpClient httpClient = HttpClients.createDefault();
			CloseableHttpResponse response = null;
			String resultString = "";
			try {
				// 创建Http Post请求
				HttpPost httpPost = new HttpPost(url);
				// 创建参数列表
				if (param != null) {
					List<NameValuePair> paramList = new ArrayList<NameValuePair>();
					for (String key : param.keySet()) {
						paramList.add(new BasicNameValuePair(key, param.get(key)));
					}
					// 模拟表单
					UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, "utf-8");
					httpPost.setEntity(entity);
				}
				// 执行http请求
				response = httpClient.execute(httpPost);
				resultString = EntityUtils.toString(response.getEntity(), "utf-8");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return resultString;
		}

		public static String doPost(String url) {
			return doPost(url, null);
		}

		public static String doSSLPost(String url, Map<String, String> param) {
		    // 允许 自定义添加的证书, 以及自签名证书
	        SSLContext sslcontext;
	        CloseableHttpClient httpclient;
	        CloseableHttpResponse response = null;
	        String resultString = "";
	        try {
	            // load from ssl key store, enable self-signed certification
	            sslcontext = SSLContexts.custom().loadTrustMaterial(new File(SSL_KEY_STORE_PATH),
	                    SSL_KEY_STORE_PASS.toCharArray(), new TrustSelfSignedStrategy()).build();
	            
	            // get ssl socket factory using ssl context
	            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" },
	                    null, SSLConnectionSocketFactory.getDefaultHostnameVerifier());
	            httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
	            
	            HttpPost httpPost = new HttpPost(url);
	            // 创建参数列表
	            if (param != null) {
	                List<NameValuePair> paramList = new ArrayList<NameValuePair>();
	                for (String key : param.keySet()) {
	                    paramList.add(new BasicNameValuePair(key, param.get(key)));
	                }
	                // 模拟表单
	                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
	                httpPost.setEntity(entity);
	            }
	            // 执行http请求
	            response = httpclient.execute(httpPost);
	            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
	            
	        } catch (Exception e1) {
	        }   
	        return resultString;
	    }
		
		public static String doPostJson(String url, String json) {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			CloseableHttpResponse response = null;
			try {
				HttpPost httpPost = new HttpPost(url);
				/*RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(30000).build();
				httpPost.setConfig(requestConfig);*/
				StringEntity entity = new StringEntity(json, "UTF-8");
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/json");
				//此处跳过
				httpPost.setEntity(entity);
				response = httpClient.execute(httpPost);	
				if(response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
					HttpEntity entity2 = response.getEntity();
					return EntityUtils.toString(entity2, "UTF-8");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return null;
		}
		
		public static String postJson(String url, String json) {
			if(url.startsWith("https")){
				return sslPost(url, json);
			}else{
				return doPostJson(url, json);
			}
		}
		
		
		public static void main(String[] args) {
			try {
				List<Cookie> cookies = new ArrayList<Cookie>();
				Cookie cookie = new Cookie("castgc", "a6e446b91390f5703779d00aba9f1845c4939b10");
				cookie.setDomain(".coolgua.com");
				cookie.setPath("/");
				cookies.add(cookie);
				String resultString = doGetCookie("http://usso.coolgua.com:202/oauth2/userinfo?access_token=de2008cf0f4f56594ebb882923821eb4", cookies);
				System.out.println(resultString);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public static String doPostMultipartFile(String url, File file, Map<String, String> param){
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String resultString = "";
		CloseableHttpResponse response = null;
		try {
			HttpPost httppost = new HttpPost(url);
			FileBody binFileBody = new FileBody(file);
			MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
			multipartEntityBuilder.addPart("file", binFileBody);
			if (param != null && param.size() > 0) {
				Set<String> keys = param.keySet();
				for (String key : keys) {
					multipartEntityBuilder.addPart(key, new StringBody(param.get(key), ContentType.APPLICATION_JSON));
				}
			}
			HttpEntity reqEntity = multipartEntityBuilder.build();
			httppost.setEntity(reqEntity);
			response = httpclient.execute(httppost);
			HttpEntity resEntity = response.getEntity();
			if (response.getStatusLine().getStatusCode() == 200) {
				resultString = EntityUtils.toString(resEntity, "UTF-8");
			}
			EntityUtils.consume(resEntity);
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			try {
				if (response != null) {
					response.close();
				}
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return resultString;
	}
}
