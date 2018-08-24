package com.coolgua.signup.util.image;


import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageUtils {
	private static final Logger logger = LoggerFactory.getLogger(ImageUtils.class);

	/**
	 * 判断path是否是web请求
	 * 
	 * @param path
	 * @return
	 */
	private static boolean isHttpFile(String path) {
		if (!StringUtils.isEmpty(path) && path.toLowerCase().startsWith("http")) {
			return true;
		}
		return false;
	}
	
	public static boolean getCircleImage(String sourcePath, String destPath) {
		CircleImageParam circleParam = new CircleImageParam();
		circleParam.setSourcePath(sourcePath);// 可以是网络图片,可是是本地图片
		circleParam.setDestinyPath(destPath);// 必须是本地图片,且图片格式必须是png
		return getCircleImage(circleParam);
	}

	/**
	 * 画圆形图片,sourcePath是原图片路径,radius是圆形图片的半径
	 * 
	 * @param sourcePath
	 *            原图片地址,不能为空
	 * @param destinyPath
	 *            目的图片地址,不能为空
	 * @param radius,不能为负数,为0或者null表示用图片的宽、高最小值做直径
	 * @return
	 */
	public static boolean getCircleImage(CircleImageParam param) {
		if (StringUtils.isEmpty(param.getSourcePath()) || StringUtils.isEmpty(param.getDestinyPath())) {
			return false;
		}

		long start = System.currentTimeMillis();
		// 获取原图片
		BufferedImage waterImg = null;
		try {

			if (isHttpFile(param.getSourcePath())) {
				InputStream sis = null;
				try {
					URL sourceUrl = new URL(param.getSourcePath());
					sis = sourceUrl.openConnection().getInputStream();
					waterImg = ImageIO.read(sis);
				} catch (Exception e) {
					logger.error("读取原图片异常,{}", e);
				} finally {
					if (sis != null) {
						sis.close();
					}
				}
			} else {
				File file = null;
				try {
					file = new File(param.getSourcePath());
					waterImg = ImageIO.read(file);
				} catch (Exception e) {
					logger.error("读取原图片异常,{}", e);
				}
			}
		} catch (Exception e) {
			logger.error("图片转换成圆形失败", e);
			return false;
		}

		// 半径有设置时,以设置的半径为主
		int width = waterImg.getWidth();
		int height = waterImg.getHeight();
		int getDiameter = (width < height) ? width : height;
		if (param.getDiameter() != null && param.getDiameter() > 0) {
			getDiameter = param.getDiameter();
		}

		// 按照要求缩放图片
		BufferedImage tag = new BufferedImage(getDiameter, getDiameter, BufferedImage.TYPE_INT_RGB);
		tag.getGraphics().drawImage(waterImg, 0, 0, getDiameter, getDiameter, null);
		waterImg = tag;

		// 生成最终的图片
		boolean rs = false;
		Graphics2D g2 = null;
		try {
			tag = new BufferedImage(getDiameter, getDiameter, BufferedImage.TYPE_INT_ARGB);
			g2 = tag.createGraphics();
			g2.setComposite(AlphaComposite.Src);
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setColor(Color.WHITE);
			g2.fill(new RoundRectangle2D.Float(0, 0, getDiameter, getDiameter, getDiameter, getDiameter));
			g2.setComposite(AlphaComposite.SrcAtop);
			g2.drawImage(waterImg, 0, 0, null);
			int temp = param.getDestinyPath().lastIndexOf(".") + 1;
			rs = ImageIO.write(tag, param.getDestinyPath().substring(temp), new File(param.getDestinyPath()));
		} catch (IOException e) {
			logger.error("合成图片错误", e);
			return false;
		} finally {
			if (g2 != null) {
				g2.dispose();
			}
		}

		long end = System.currentTimeMillis();
		logger.info("生成圆角图片耗时:{},图片地址:{}", (end - start), param.getDestinyPath());
		return rs;
	}
	
	public static void download(String imageUrl, String destPath) {
		DataInputStream dis = null;
		FileOutputStream fos = null;
		try {
			URL url = new URL(imageUrl);
			// 打开网络输入流
			dis = new DataInputStream(url.openStream());
			// 建立一个新的文件
			fos = new FileOutputStream(new File(destPath));
			byte[] buffer = new byte[1024];
			int length;
			// 开始填充数据
			while ((length = dis.read(buffer)) > 0) {
				fos.write(buffer, 0, length);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(dis != null) {
				try {
					dis.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(fos != null) {
				try {
					fos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		/*
		String sourceFilePath = "e://tmp/wd.jpg";
		String saveFilePath = "e://tmp/wd-out232.png";

		CircleImageParam circleParam = new CircleImageParam();
		circleParam.setSourcePath(sourceFilePath);// 可以是网络图片,可是是本地图片
		circleParam.setDestinyPath(saveFilePath);// 必须是本地图片,且图片格式必须是png
//		circleParam.setDiameter(300);// 图片直径,不指定,则是原图片宽高的最小值
		boolean r = ImageUtils.getCircleImage(circleParam);
		System.out.println("r:" + r);
		
		for (int i = 0; i < 10; i++) {
			System.out.println(RandomUtil.getString(4));
		}
		download("https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqDCwuxhMM3fxQsxQZ9OELI3mO5ziaxhcEAicnMdkfXibZn0zWX6y4ptKiaHmL8A7aXPpkiaU8Q4MCvia2g/132"
				, "e:/tmp/aa.png");
		String image = "https://reg.coolgua.net/xiaogua/193/20180712/20180712205530752.jpg";

		String photo = image.toString();
		int index = photo.lastIndexOf(".");
		if(index > 0) {
			System.out.println(photo.substring(0, index) + "_c.png");
		}
		*/

		String[] urls = {"https://reg.coolgua.net/attachment/3ddb4c7595cb42dcbf8c2a15f1af36b7/201807/27/20180727204859557.jpg"
				, "https://reg.coolgua.net/attachment/3ddb4c7595cb42dcbf8c2a15f1af36b7/201807/27/20180727181538667.jpg"
				, "https://reg.coolgua.net/attachment/3ddb4c7595cb42dcbf8c2a15f1af36b7/201807/27/20180727175605652.jpg"
				, "https://reg.coolgua.net/attachment/3ddb4c7595cb42dcbf8c2a15f1af36b7/201807/27/20180727205631683.jpg"
				, "https://reg.coolgua.net/attachment/3ddb4c7595cb42dcbf8c2a15f1af36b7/201807/27/20180727205204034.jpg"
				, "https://reg.coolgua.net/attachment/3ddb4c7595cb42dcbf8c2a15f1af36b7/201807/27/20180727195738366.jpg"
		};
		for (String url : urls) {
			int begin = url.lastIndexOf("/") + 1;
			int end = url.lastIndexOf(".");
			String name = url.substring(begin, end);
			String dir1 = "e:/tmp/src/" + name + ".jpg";
			String dir2 = "e:/tmp/out/" + name + "_c.png";
			download(url, dir1);
			getCircleImage(dir1, dir2);
		}
	}

}
