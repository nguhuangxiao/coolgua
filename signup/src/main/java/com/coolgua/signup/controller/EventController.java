package com.coolgua.signup.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.coolgua.common.util.DateUtil;
import com.coolgua.common.util.SessionUtil;
import com.coolgua.common.util.UUIDGenerator;
import com.coolgua.signup.constant.FieldConstant;
import com.coolgua.signup.constant.JSON_RESULT;
import com.coolgua.signup.domain.Event;
import com.coolgua.signup.domain.FormField;
import com.coolgua.signup.domain.GalaxyGraph;
import com.coolgua.signup.domain.Scene;
import com.coolgua.signup.exception.FailedReqeustException;
import com.coolgua.signup.service.EventService;
import com.coolgua.signup.util.AjaxJson;
import com.coolgua.signup.util.BadRequestException;
import com.coolgua.signup.util.CloseUtil;
import com.coolgua.signup.util.GraphUtil;
import com.coolgua.signup.util.HttpClientUtil;
import com.coolgua.signup.util.QrcodeUtil;
import com.coolgua.signup.util.RandomUtil;
import com.coolgua.signup.util.image.ImageUtils;

@Controller
@RequestMapping(value = "/event")
public class EventController {
	private static Logger log = LoggerFactory.getLogger(EventController.class);

	@Value("${SSO_BASE_URL}")
	private String SSO_BASE_URL;
	
	@Value("${UPLOAD_DIR}")
	private String uploadDir;

	@Value("${appID}")
	private String appID;

	@Value("${appSecret}")
	private String appSecret;

	@Value("${XIAOGUA_URL}")
	private String xiaoguaUrl;
	
	@Autowired
	private EventService eventService;
	
	private static final String IMG_FILEEXT = "^.*?\\.(jpg|jpeg|bmp|gif|png)$";
	
	@ResponseBody
	@RequestMapping(value = "/uploadBanner/{orgId}/{fp}", method = RequestMethod.POST)
	public AjaxJson uploadBanner(@RequestParam(value = "file", required = true) MultipartFile file
			, @PathVariable String fp, @PathVariable Integer orgId, @RequestParam(required=false) String eventId) {
		StringBuilder sb = new StringBuilder();
		AjaxJson result = new AjaxJson(JSON_RESULT.OK);
		try {
			String filename = file.getOriginalFilename();
			if (!isValidFileExt(filename, IMG_FILEEXT)) {
				throw new BadRequestException("不支持的文件格式");
			}
			String ext = filename.substring(filename.lastIndexOf(".")); // 文件扩展名

			Date timestamp = new Date();
			String split = "/";
			StringBuilder pathSb = new StringBuilder();
			pathSb.append("banner").append(split)
					.append(orgId).append(split)
					.append(DateUtil.formatDate(timestamp, "yyyyMM")).append(split)
					.append(DateUtil.formatDate(timestamp, "dd")).append(split)
					.append(createFileName(timestamp, file.getOriginalFilename()));
			String path = pathSb.toString();
			convertFile(file, path);
			Map<String, Object> map = new HashMap<>();
			String logoUrl = xiaoguaUrl + path;
			map.put("path", logoUrl);
			map.put("uploadExt", ext);
			result.setMap(map);
			
			//如果eventId不为空，则是上传小程序logo
			if(!StringUtils.isEmpty(eventId)) {
				eventService.updateLogo(logoUrl, eventId);
			}
		} catch (Exception e) {
			if (sb.toString() == null || sb.toString() == "") {
				sb.append("上传文件出错");
			}
			throw new FailedReqeustException(sb.toString());
		} finally {
			sb = null;
		}
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/uploadFile/{eventId}", method = RequestMethod.POST)
	public AjaxJson uploadFile(@RequestParam(value = "file", required = true) MultipartFile file
			, @RequestParam String eventId) {
		StringBuilder sb = new StringBuilder();
		AjaxJson result = new AjaxJson(JSON_RESULT.OK);
		try {
			String filename = file.getOriginalFilename();
			if (!isValidFileExt(filename, IMG_FILEEXT)) {
				throw new BadRequestException("不支持的文件格式");
			}
			String ext = filename.substring(filename.lastIndexOf(".")); // 文件扩展名

			Date timestamp = new Date();
			String split = "/";
			StringBuilder pathSb = new StringBuilder();
			String randomName = getRandomName();
			pathSb.append("attachment").append(split)
					.append(eventId).append(split)
					.append(DateUtil.formatDate(timestamp, "yyyyMM")).append(split)
					.append(DateUtil.formatDate(timestamp, "dd")).append(split)
					.append(randomName);
			String path = pathSb + ext;
			convertFile(file, path);
			Map<String, Object> map = new HashMap<>();
			String logoUrl = xiaoguaUrl + path;
			map.put("path", logoUrl);
			map.put("uploadExt", ext);
			try {
				ImageUtils.getCircleImage(uploadDir + path, pathSb + "_c.png");
			} catch (Exception e) {
				e.printStackTrace();
			}
			result.setMap(map);
		} catch (Exception e) {
			if (sb.toString() == null || sb.toString() == "") {
				sb.append("上传文件出错");
			}
			throw new FailedReqeustException(sb.toString());
		} finally {
			sb = null;
		}
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/getEvents/{orgId}", method = RequestMethod.POST)
	public Map<String, Object> getEvents(@PathVariable Integer orgId) {
		Map<String, Object> map = new HashMap<>();
		try {
			List<Event> events = eventService.getEvents(orgId);
			map.put("events", events);
			map.put("code", JSON_RESULT.OK);
		} catch (Exception e) {
			map.put("code", JSON_RESULT.FAILED);
			map.put("msg", e.getMessage());
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/getEventById/{eventId}", method = RequestMethod.POST)
	public Map<String, Object> getEventById(@PathVariable(value = "eventId") String eventId) {
		Map<String, Object> map = new HashMap<>();
		try {
			Event event = eventService.getEventById(eventId);
			map.put("event", event);
			map.put("code", JSON_RESULT.OK);
		} catch (Exception e) {
			map.put("code", JSON_RESULT.FAILED);
			map.put("msg", e.getMessage());
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/saveEvent", method = RequestMethod.POST)
	public Map<String, Object> saveEvent(Event event) {
		System.out.println("save event=====" + event);
		Map<String, Object> map = new HashMap<>();
		try {
			String eventId = event.getId();
			if(StringUtils.isEmpty(eventId)) {
				eventId = UUIDGenerator.getUUID();
				event.setId(eventId);
				String acode = xiaoguaUrl + getAcode(eventId);
				String qrcode = xiaoguaUrl + getQrcode(eventId);
				event.setAcode(acode);
				event.setQrcode(qrcode);
				eventService.addEvent(event);	
			} else {
				eventService.updateEvent(event);
			}
			map.put("code", JSON_RESULT.OK);
		} catch (Exception e) {
			map.put("code", JSON_RESULT.FAILED);
			map.put("msg", e.getMessage());
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		return map;
	}
	
	// 获取小程序码
	private String getAcode(String eventId) {
		return QrcodeUtil.getAcode(appID, appSecret, eventId, uploadDir);
	}

	// 获取小程序二维码
	private String getQrcode(String eventId) {
		return QrcodeUtil.getQrcode(appID, appSecret, eventId, uploadDir);
	}

	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		//转换日期
		DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器
	}

	@ResponseBody
	@RequestMapping(value = "/registers/{eventId}", method = RequestMethod.POST)
	public AjaxJson listRegister(@PathVariable String eventId
			, @RequestParam(required=false) Integer page, @RequestParam(required=false) Integer size) {
		try {
			Map<String, Object> result = new ConcurrentHashMap<String, Object>();
			result.put("page", eventService.findPageRegisters(eventId, page, size));
			return new AjaxJson(JSON_RESULT.OK, result);
		} catch (Exception e) {
			String message = "获取报名列表失败,请重试";
			log.error(message, e);
			throw new FailedReqeustException(message);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/register/{eventId}/{openId}", method = RequestMethod.POST)
	public AjaxJson getRegister(@PathVariable(value = "eventId") String eventId, @PathVariable(value = "openId") String openId) {
		try {
			Map<String, Object> result = eventService.getRegister(eventId, openId);
			return new AjaxJson(JSON_RESULT.OK, result);
		} catch (Exception e) {
			String message = "获取报名详情失败,请重试";
			log.error(message, e);
			throw new FailedReqeustException(message);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/reg-simple/{eventId}/{openId}", method = RequestMethod.POST)
	public AjaxJson getRegisterSimple(@PathVariable(value = "eventId") String eventId, @PathVariable(value = "openId") String openId) {
		try {
			Map<String, Object> result = eventService.getRegisterSimple(eventId, openId);
			return new AjaxJson(JSON_RESULT.OK, result);
		} catch (Exception e) {
			String message = "获取报名弹窗数据失败,请重试";
			log.error(message, e);
			throw new FailedReqeustException(message);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/exportReg/{eventId}", method = RequestMethod.POST)
	public AjaxJson exportReg(@PathVariable String eventId, @RequestParam(required=false) String ids
			, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("ids===" + ids);
		Map<String, Object> paramters = new HashMap<String, Object>();
		paramters.put("eventId", eventId);
		if (!StringUtils.isEmpty(ids)) {
			try {
				List<String> regIds = JSONArray.parseArray(ids, String.class);
				System.out.println("regIdssize===" + regIds.size());
				if(!CollectionUtils.isEmpty(regIds)) {
					paramters.put("ids", regIds);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		Map<String, Object> result = eventService.getRegisters(paramters, uploadDir);
		List<Map<String, Object>> datas = (List<Map<String, Object>>) result.get("datas");
		if (!CollectionUtils.isEmpty(datas)) {
			List<File> files = new ArrayList<File>();
			List<FormField> fields = (List<FormField>) result.get("fields");
			paramters = new HashMap<String, Object>();
			paramters.put("eventId", eventId);
			List<String> fieldNames = new ArrayList<String>();
			List<List<String>> dataList = new ArrayList<List<String>>();
			for (FormField field : fields) {
				fieldNames.add(field.getName());
				System.out.println("fields======" + field);
			}
			if (!CollectionUtils.isEmpty(datas)) {
				String format = "yyyy-MM-dd HH:mm";
				for (Map<String, Object> map : datas) {
					List<String> data = new ArrayList<String>();
					data.add(map.get("id").toString());  //data存id
					for (FormField field : fields) {
						Object obj = map.get(field.getEnglish());
						if(obj == null) {
							data.add("");
						} else if (FieldConstant.IMAGE.intValue() == field.getTypeId()) {
							String val = obj.toString();
							if(StringUtils.isEmpty(val)) {
								data.add(val);
							} else {
								try {
									String path = uploadDir + obj.toString().substring(xiaoguaUrl.length());
									File file = new File(path);
									if (file.exists()) {
										files.add(file);
									}
									data.add(file.getName());
								} catch (Exception e) {
									e.printStackTrace();
									data.add(obj.toString());
								}
							}
						} else if(obj instanceof Date) {
							data.add(DateUtil.formatDate(((Date)obj), format));
						} else {
							data.add(obj.toString());
						}
					}
					dataList.add(data);
				}
			}
			ZipOutputStream output = null;
			ByteArrayOutputStream out = null;
			// 产生工作簿对象
			HSSFWorkbook workbook = new HSSFWorkbook();
			try {
				String fileName = "报名列表_" + System.currentTimeMillis() + ".zip";
				response.reset();
				response.setCharacterEncoding("UTF-8");
				if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
					fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
				} else {
					fileName = URLEncoder.encode(fileName, "UTF-8");
				}
				response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
				response.setContentType("application/octet-stream");

				// 产生工作表对象
				HSSFSheet sheet = workbook.createSheet();
				// 第一行是字段名
				HSSFRow firstRow = sheet.createRow(0);
				int colSize = fieldNames.size();
				for (int i = 0; i < colSize; i++) {
					HSSFCell cell = firstRow.createCell(i);
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellValue(fieldNames.get(i));
				}

				// 数据行
				for (int j = 0; j < dataList.size(); j++) {
					HSSFRow row = sheet.createRow(j + 1); // 创建一行
					List<String> rowData = dataList.get(j);
					int size = rowData.size();
					for (int k = 1; k < size; k++) {
						HSSFCell cell = row.createCell(k - 1);// 创建一列
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						String values = rowData.get(k);
						cell.setCellValue(values == null ? "" : values);
					}
				}
				output = new ZipOutputStream(response.getOutputStream());
				byte[] buffer = new byte[1024];
				if (!CollectionUtils.isEmpty(files)) {
					for (File image : files) {
						FileInputStream input = null;
						try {
							input = new FileInputStream(image);
							output.putNextEntry(new ZipEntry(image.getName()));
//							output.putNextEntry(new ZipEntry(FileUtil.getFileNameNoEx(image.getName())));
							int length = 0;
							while ((length = input.read(buffer)) > 0) {
								output.write(buffer, 0, length);
							}
							output.flush();
							output.closeEntry();
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							if (null != input) {
								input.close();
							}
						}
					}
				}
				out = new ByteArrayOutputStream();
				workbook.write(out);
				byte[] bytes = out.toByteArray();
				output.putNextEntry(new ZipEntry("报名列表.xls"));
				output.write(bytes);
				output.flush();
				output.closeEntry();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				CloseUtil.close(out);
				CloseUtil.close(workbook);
				CloseUtil.close(output);
			}
			return null;
		} else {
			return new AjaxJson(JSON_RESULT.OK, "没有数据可以导出");
		}
	}

	/**
	 * 获取当前登录用户所在组织机构
	 * 
	 * @param ssoUserId
	 * @return
	 */
	@RequestMapping(value = "/getUserOrgs", method = RequestMethod.GET)
	public AjaxJson getUserOrgs(@PathVariable(value = "ssoUserId") String ssoUserId) {
		String json = HttpClientUtil.doGet(SSO_BASE_URL + "/getUserOrgs/" + ssoUserId + ".json");
		Map<String, Object> map = (Map) JSON.parse(json);
		return new AjaxJson(JSON_RESULT.OK, map);
	}

	@RequestMapping(value = "/saveCurOrg/{fp}/{orgId}", method = RequestMethod.POST)
	public AjaxJson saveCurOrg(@PathVariable(value = "fp") String fingerprint, @PathVariable String orgId, HttpServletRequest request) {
		String token = SessionUtil.createToken(request, fingerprint);
		String json = HttpClientUtil.doGet(SSO_BASE_URL + "/saveCurOrg/" + token + "/" + orgId + ".json");
		Map<String, Object> map = JSON.parseObject(json);
		return new AjaxJson(JSON_RESULT.OK, map);
	}

	private boolean isValidFileExt(String fileName, String reg) {
		boolean flag = false;
		try {
			Pattern pattern = Pattern.compile(reg);
			Matcher matcher = pattern.matcher(fileName.toLowerCase());
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	private File convertFile(MultipartFile file, String path) {
		File convFile = new File(uploadDir + path);
		try {
			File parentFile = convFile.getParentFile();
			if (!parentFile.exists()) {
				parentFile.mkdirs(); // 如果目录不存在则创建
			}
			convFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(convFile);
			fos.write(file.getBytes());
			fos.close();
		} catch (IOException e) {
			log.error("文件转换错误", e);
			e.printStackTrace();
		}
		return convFile;
	}

	private String createFileName(Date timestamp, String fileName) {
		return DateUtil.formatDate(timestamp, "yyyyMMddHHmmssSSS")
				+ fileName.substring(fileName.lastIndexOf(".")
						- (fileName.contains("tar.gz") ? 4 : 0));
	}
	
	private String getRandomName() {
		Date timestamp = new Date();
		return DateUtil.formatDate(timestamp, "yyyyMMddHHmmssSSS")+RandomUtil.getString(4);
	}

	@ResponseBody
	@RequestMapping(value = "/scene/{id}", method = RequestMethod.POST)
	public AjaxJson getScene(@PathVariable String id) {
		System.out.println("=========getScene==========" + id);
		try {
			Scene scene = eventService.getScene(id);
			if(scene == null) {
				scene = new Scene();
				scene.setEventId(id);
			}
			System.out.println("=========getScenereturn==========" + JSONObject.toJSONString(scene));
			return new AjaxJson(JSON_RESULT.OK, scene);
		} catch (Exception e) {
			String message = "获取报名列表失败,请重试";
			log.error(message, e);
			throw new FailedReqeustException(message);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/report/{eventId}", method = RequestMethod.POST)
	public AjaxJson report(@PathVariable String eventId, @RequestParam String type, @RequestParam(required=false) String dim
			, @RequestParam(required=false) String startTime, @RequestParam(required=false) String endTime) {
		try {
			Object result = null;
			if("basic".equals(type)) {
				result = basicReport(eventId, dim, startTime, endTime);
			} else if("effect".equals(type)) {
				result = effectReport(eventId);
			} else if("invite".equals(type)) {
				result = inviteReport(eventId);
			}
			return new AjaxJson(JSON_RESULT.OK, result);
		} catch (Exception e) {
			String message = "统计分析失败,请重试";
			log.error(message, e);
			throw new FailedReqeustException(message);
		}
	}

	private Map<String, Object> basicReport(String eventId, String dim, String startTime, String endTime) {
		Map<String, Object> basicReport = eventService.basicReport(eventId, dim, startTime, endTime);
		return basicReport;
	}

	private List<Map<String, Object>> effectReport(String eventId) {
		List<Map<String, Object>> result = eventService.effectReport(eventId);
		filterPhoto(result, eventId);
		return result;
	}

	private List<Map<String, Object>> inviteReport(String eventId) {
		List<Map<String, Object>> result = eventService.inviteReport(eventId);
		filterPhoto(result, eventId);
		return result;
	}
	
	private void filterPhoto(List<Map<String, Object>> result, String eventId) {
		// 处理images
		for (Map<String, Object> map : result) {
			Object image = map.get("photo");
			// 返回圆形图像
			if(image != null && image.toString().trim().length() > 0) { // 有报名的照片
				System.out.println("===imagenotnull===");
				String photo = image.toString();
				int index = photo.lastIndexOf(".");
				if(index > 0) {
					String url = photo.substring(0, index) + "_c.png";
					map.put("photo", url);
					String circlePath = uploadDir + url.substring(xiaoguaUrl.length());
					File path = new File(circlePath);
					if(!path.exists()) {
						getCircleHeadImage(photo, eventId, circlePath);
					}
					map.put("head_imgurl", url);
				}
			}
		}
	}

	@ResponseBody
	@RequestMapping(value = "/galaxy/{eventId}", method = RequestMethod.POST)
	public AjaxJson galaxy(@PathVariable String eventId, @RequestParam(required=false) String openId
	        , @RequestParam(required=false) Integer width, @RequestParam(required=false) Integer height) {
		try {
			List<Map<String, Object>> result = eventService.findGalaxyRank(eventId, openId);
			
            if(width == null) {
                width = 1024;
            }
            if(height == null) {
                height = 600;
            }
			List<GalaxyGraph> graphs = GraphUtil.getGalaxyGraphs(width, height, result.size(), StringUtils.isEmpty(openId));
			// 处理images
			int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE, maxX = 0, maxY = 0; // 坐标范围
			int radius = 0;
			for (int i = 0; i < result.size(); i++) {
			    Map<String, Object> map = result.get(i);
				GalaxyGraph graph = graphs.get(i);
				int x = graph.getX();
				int y = graph.getY();
				radius = graph.getDiameter()/2 + 10;
				map.put("x", x);
				map.put("y", y);
				map.put("diameter", graph.getDiameter());
				
				if(x < minX) minX = x;
				if(x > maxX) maxX = x;
				if(y < minY) minY = y;
				if(y > maxY) maxY = y;
				
				Object image = map.get("photo");
				System.out.println(image + "===image===");
				// 返回圆形图像
				if(image != null && image.toString().trim().length() > 0) { // 有报名的照片
					System.out.println("===imagenotnull===");
					String photo = image.toString();
					int index = photo.lastIndexOf(".");
					if(index > 0) {
						String url = photo.substring(0, index) + "_c.png";
						map.put("photo", url);
						String circlePath = uploadDir + url.substring(xiaoguaUrl.length());
						File path = new File(circlePath);
						if(!path.exists()) {
							getCircleHeadImage(photo, eventId, circlePath);
						}
					}
				} else { // 没有报名的照片，取微信头像，将其处理成圆形
					Object head = map.get("head_imgurl");
					Object cricleHead = map.get("head_img_circle");
					System.out.println(head + "===head===" + cricleHead);
					if(head != null && head.toString().trim().length() > 0 && cricleHead == null) {
						System.out.println("====1111==");
						String headUrl = (String)head;
						String openIdTmp = (String)map.get("open_id");
						String split = "/";
						String path = new StringBuilder("attachment/wxhead/").append(appID).append(split).append(openIdTmp).toString();
						String circleHeadImgPath = uploadDir + path + "_c.png";
						File circleHeadFile = new File(circleHeadImgPath);
						if(!circleHeadFile.exists()) {
							String headImgPath = uploadDir + path + ".jpg";
							File downDir = new File(uploadDir + path);
							if(!downDir.exists()) {
								downDir.mkdirs();
							}
							
							ImageUtils.download(headUrl, headImgPath);
							ImageUtils.getCircleImage(headImgPath, circleHeadImgPath);
						}
						
						map.put("head_imgurl", xiaoguaUrl + path + "_c.png");
					} else if(cricleHead != null) {
						System.out.println("====1111xxx222==");
						map.put("head_imgurl", cricleHead);
					}
				}
			}
			System.out.println("galaxyresult====" + result.size());

			AjaxJson json = new AjaxJson(JSON_RESULT.OK, result);
			Map<String, Object> map = new HashMap<>();
			map.put("minX", minX-radius);
			map.put("maxX", maxX+radius);
			map.put("minY", minY-radius);
			map.put("maxY", maxY+radius);
			json.setMap(map);
			return json;
		} catch (Exception e) {
			String message = "统计分析失败,请重试";
			log.error(message, e);
			throw new FailedReqeustException(message);
		}
	}
	
	private void getCircleHeadImage(String headImgUrl, String eventId, String circleImgPath) {
		System.out.println(headImgUrl + "====circleImgPath===" + circleImgPath);
		if(StringUtils.isEmpty(headImgUrl)) {
			return;
		}
		
		try {
			Date timestamp = new Date();
			String split = "/";
			StringBuilder pathSb = new StringBuilder();
			pathSb.append("attachment/")
					.append(eventId).append(split)
					.append(DateUtil.formatDate(timestamp, "yyyyMM")).append(split)
					.append(DateUtil.formatDate(timestamp, "dd")).append(split)
					.append(getRandomName(timestamp));
			
			String destPath = uploadDir + pathSb + ".jpg";
			File downDir = new File(destPath).getParentFile();
			if(!downDir.exists()) {
				downDir.mkdirs();
			}
			ImageUtils.download(headImgUrl, destPath);
//			String circleImgPath = uploadDir + pathSb + "_c.png";
			ImageUtils.getCircleImage(destPath, circleImgPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String getRandomName(Date timestamp) {
		return DateUtil.formatDate(timestamp, "yyyyMMddHHmmssSSS")+RandomUtil.getString(4);
	}
}
