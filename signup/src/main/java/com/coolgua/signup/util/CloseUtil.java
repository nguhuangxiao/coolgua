package com.coolgua.signup.util;

import java.io.Closeable;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class CloseUtil {
	public static void close(Object... obj) {

		try {
			if (obj != null) {
				for (int i = 0; i < obj.length; i++) {
					if (obj[i] != null) {
						if (obj[i] instanceof Closeable) {
							Closeable c = (Closeable) obj[i];
							c.close();
						}
						if (obj[i] instanceof Socket) {
							Socket s = (Socket) obj[i];
							s.close();
						}
						if (obj[i] instanceof ServerSocket) {
							ServerSocket ss = (ServerSocket) obj[i];
							ss.close();
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void close(HSSFWorkbook workbook) {
		try {
			if (null != workbook) {
				workbook.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(OutputStream output) {
		try {
			if (null != output) {
				output.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
