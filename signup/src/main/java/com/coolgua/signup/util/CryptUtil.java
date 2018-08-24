package com.coolgua.signup.util;


import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;  

public class CryptUtil {

//	private Logger log = Logger.getLogger(CryptUtil.class);
	private final static Logger log = LoggerFactory.getLogger(CryptUtil.class);
	
    private static final CryptUtil instance = new CryptUtil();
    private static final String KEY = "helloabcd";

    private CryptUtil() {
    }

	public static CryptUtil getInstance() {
		return instance;
	}

	private Key initKeyForAES(String key) throws NoSuchAlgorithmException {
		if (null == key || key.length() == 0) {
			throw new NullPointerException("key not is null");
		}
		SecretKeySpec key2 = null;
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		random.setSeed(key.getBytes());
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, random);
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			key2 = new SecretKeySpec(enCodeFormat, "AES");
		} catch (NoSuchAlgorithmException ex) {
			throw new NoSuchAlgorithmException();
		}
		return key2;
	}

    /**
     * AES加密算法，不受密钥长度限制
     * 
     * @param content
     * @param key
     * @return
     */
	public String encryptAES(String content, String key) {
		try {
			SecretKeySpec secretKey = (SecretKeySpec) initKeyForAES(key);
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);// 初始化
			byte[] result = cipher.doFinal(byteContent);
			return asHex(result); // 加密
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String encryptAES(String content) {
		return encryptAES(content, KEY);
	}

    /**
     * aes解密算法，不受密钥长度限制
     * 
     * @param content
     * @param key
     * @return
     */
	public String decryptAES(String content, String key) {
		try {
			SecretKeySpec secretKey = (SecretKeySpec) initKeyForAES(key);
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, secretKey);// 初始化
			byte[] result = cipher.doFinal(asBytes(content));
			return new String(result); // 加密
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
    
    public String decryptAES(String content) {
    	return decryptAES(content, KEY);
    }

    /**
     * 将2进制数值转换为16进制字符串
     * 
     * @param buf
     * @return
     */
	public String asHex(byte buf[]) {
		StringBuffer strbuf = new StringBuffer(buf.length * 2);
		int i;
		for (i = 0; i < buf.length; i++) {
			if (((int) buf[i] & 0xff) < 0x10)
				strbuf.append("0");
			strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
		}
		return strbuf.toString();
	}

    /**
     * 将16进制转换
     * 
     * @param hexStr
     * @return
     */
	public byte[] asBytes(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
    }

	private static byte[] base64DecodeChars = new byte[] { -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59,
			60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
			10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1,
			-1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37,
			38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1,
			-1, -1 };
	
	/**
	 * 解密
	 * @param str
	 * @return
	 */
	public byte[] decode(String str) {
		try {
			byte[] data = str.getBytes();
			int len = data.length;
			ByteArrayOutputStream buf = new ByteArrayOutputStream(len);
			int i = 0;
			int b1, b2, b3, b4;

			while (i < len) {
				do {
					b1 = base64DecodeChars[data[i++]];
				} while (i < len && b1 == -1);
				if (b1 == -1) {
					break;
				}

				do {
					b2 = base64DecodeChars[data[i++]];
				} while (i < len && b2 == -1);
				if (b2 == -1) {
					break;
				}
				buf.write((int) ((b1 << 2) | ((b2 & 0x30) >>> 4)));
				do {
					b3 = data[i++];
					if (b3 == 61) {
						return buf.toByteArray();
					}
					b3 = base64DecodeChars[b3];
				} while (i < len && b3 == -1);
				if (b3 == -1) {
					break;
				}
				buf.write((int) (((b2 & 0x0f) << 4) | ((b3 & 0x3c) >>> 2)));

				do {
					b4 = data[i++];
					if (b4 == 61) {
						return buf.toByteArray();
					}
					b4 = base64DecodeChars[b4];
				} while (i < len && b4 == -1);
				if (b4 == -1) {
					break;
				}
				buf.write((int) (((b3 & 0x03) << 6) | b4));
			}
			return buf.toByteArray();
		} catch (Exception e) {
			log.info(e.getMessage(), e);
		}
		return null;
	}
	
	public static void main(String[] args) {
		CryptUtil crypt = CryptUtil.getInstance();
		String content = "http://192.168.1.27:8080/survey/wxpayCallback.html";
		System.out.println(crypt.decryptAES("5d320335986a50f1e462f2d3bcf277b6e1a432cc1cfa52851c5bb565fa6c7cafa62ee80c9b51062c6e056d021bd75bb0"));
	}

}
