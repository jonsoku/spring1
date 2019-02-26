package com.edu.kimschool.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 로그인 안한 상태에서 다른 서비스를 이용할 시, 로그인 화면으로 다시 보내기 위한 인터셉터(Interceptor)
 */
public class LoginInterceptorUtil extends HandlerInterceptorAdapter{
	//prehand 자동 완성
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		String currentId = (String) session.getAttribute("loginId");
		
		if(currentId == null) {
			response.sendRedirect( "/kimschool/member/login");
			return false;
		}
		return super.preHandle(request, response, handler); //true 반환
	}
}
