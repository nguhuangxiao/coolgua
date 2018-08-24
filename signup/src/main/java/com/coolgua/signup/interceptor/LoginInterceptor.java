package com.coolgua.signup.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.coolgua.common.constant.OperatorMsg;
import com.coolgua.common.domain.User;
import com.coolgua.common.service.RedisService;
import com.coolgua.common.util.JsonUtils;
import com.coolgua.common.util.SessionUtil;
import com.coolgua.signup.constant.JSON_RESULT;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private RedisService redisService;
	
	@Value("${REDIS_USER_SESSION_KEY:REDIS_USER_SESSION}")
	private String REDIS_USER_SESSION_KEY;
	
	@Value("${SSO_BASE_URL}")
	private String SSO_BASE_URL;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String path = request.getRequestURI();
		String[] params = path.split("/");
		if(params.length > 4 && (params[1].equalsIgnoreCase("common") || params[1].equalsIgnoreCase("interface"))){
			String fp = params[3];
			String token = SessionUtil.createToken(request, fp);
			String userJson = redisService.get(REDIS_USER_SESSION_KEY + ":" + token);
			if (StringUtils.isBlank(userJson)) {
				User user = SessionUtil.getUserByToken(request, SSO_BASE_URL, token);
				if(user == null){
					out(response);
					return false;
				}
				userJson = JsonUtils.objectToJson(user);
				redisService.set(REDIS_USER_SESSION_KEY + ":" + token, userJson);
			}
			redisService.expire(REDIS_USER_SESSION_KEY + ":" + token, 1800);
			return true;
		}else{
			out(response);
			return false;
		}
	}
	
	private static final void out(HttpServletResponse response) {
        ObjectMapper objectMapper = new ObjectMapper();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            Map<String, Object> result = new HashMap<String, Object>();
            result.put(JSON_RESULT.MSG, OperatorMsg.FAILED.getMsg());
			result.put(JSON_RESULT.CODE, OperatorMsg.FAILED.getCode());
			result.put(JSON_RESULT.SUCCESS, Boolean.FALSE);
            out.append(objectMapper.writeValueAsString(result));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

}
