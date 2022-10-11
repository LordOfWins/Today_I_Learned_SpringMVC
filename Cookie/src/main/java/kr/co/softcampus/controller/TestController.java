package kr.co.softcampus.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

	@GetMapping("/save_cookie")
	public String save_cookie(HttpServletResponse response) {
		try {
			String data1 = URLEncoder.encode("문자열1", "utf-8");
			String data2 = URLEncoder.encode("문자열2", "utf-8");
			Cookie cookie1 = new Cookie("cookie1", data1);
			Cookie cookie2 = new Cookie("cookie2", data2);

			// 쿠키 수명 설정
			cookie1.setMaxAge(365 * 24 * 60 * 60);
			cookie2.setMaxAge(365 * 24 * 60 * 60);
			// 응답 정보를 담을 response에 cookie를 담음
			response.addCookie(cookie1);
			response.addCookie(cookie2);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "save_cookie";
	}

	@GetMapping("/load_cookie")
	public String load_cookie(HttpServletRequest request) {
		try {
			// Servlet은 쿠키 배열로 받아 사용 가능
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				String str = URLDecoder.decode(cookie.getValue(), "utf-8");
				if (cookie.getName().equals("cookie1")) {
					System.out.printf("cookie1 : %s\n", str);
				} else if (cookie.getName().equals("cookie2")) {
					System.out.printf("cookie2 : %s\n", str);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "load_cookie";
	}

	@GetMapping("/load_cookie2")
	// 쿠키를 주입 받아 사용 가능
	public String load_cookie2(@CookieValue("cookie1") String cookie1, @CookieValue("cookie2") String cookie2) {
		System.out.printf("cookie1 : %s\n", cookie1);
		System.out.printf("cookie2 : %s\n", cookie2);
		return "load_cookie2";
	}

}
