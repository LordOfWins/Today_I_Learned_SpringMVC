package kr.co.softcampus.model;

import javax.servlet.http.HttpServletRequest;

//모델이 되는 클래스
public class Test2Service {
	public static int minus(HttpServletRequest request) {
		String str1 = request.getParameter("data1");
		String str2 = request.getParameter("data2");
		int number1 = Integer.parseInt(str1);
		int number2 = Integer.parseInt(str2);
		int result = number1 - number2;
		return result;
	}
}
