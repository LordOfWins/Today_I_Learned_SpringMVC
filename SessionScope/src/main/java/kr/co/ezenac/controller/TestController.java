package kr.co.ezenac.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import kr.co.ezenac.beans.DataBean1;

@Controller
/* 저장하려는 객체가 여러개일 때 - 배열로 만든다. */
@SessionAttributes({ "sessionBean1", "sessionBean2" }) // sessionBean1은 session에 저장되어있다는 것을 명시해줌
public class TestController {

	@ModelAttribute("sessionBean1")
	// session객체에 저장할 수 있도록 ModelAttribute정의 그리고 호출되고 반환하는 주소값을 sessionBean1이름으로 세션에
	// 저장
	public DataBean1 sessionBean1() {
		return new DataBean1();
	}

	@ModelAttribute("sessionBean2")
	public DataBean1 sessionBean2() {
		return new DataBean1();
	}

	@GetMapping("/test1") // session영역은 request에서 추출
//	public String test1(HttpServletRequest request) {
	public String test1(HttpSession session) {
//		HttpSession session = request.getSession();
		session.setAttribute("data1", "문자열1");

		return "test1";
	}

	@GetMapping("/test2")
	public String test2(HttpSession session) {
		session.setAttribute("data1", "문자열2");
		return "redirect:/result1";
	}

	@GetMapping("/test3")
	public String test3(HttpSession session) {
		session.setAttribute("data1", "문자열3");
		return "forward:/result1";
	}

	@GetMapping("/result1")
//	public String result1(HttpServletRequest request) {
	/* Spring MVC에서는 Session을 바로 주입 받아서 사용가능 */
	public String result1(HttpSession session) {
//		HttpSession session = request.getSession();
		String data1 = (String) session.getAttribute("data1");
		System.out.printf("data1 : %s\n", data1);
		return "result1";
	}

	@GetMapping("test4")
	public String test4(HttpSession session) {
		/* 이미 객체가 존재할 때 주입 받을 수 있지, 객체를 생성해서 주입할 수는 없다. */
//	public String test4(@SessionAttribute("bean1") DataBean1 bean1) {
		DataBean1 bean1 = new DataBean1();
		bean1.setData1("문자열4");
		bean1.setData2("문자열5");
		/* 최초로 주입할 때는 직접 주입 해야 한다. */
		session.setAttribute("bean1", bean1);
		return "test4";
	}

	@GetMapping("/result4")
//	public String result4(HttpSession session) {
	public String result4(@SessionAttribute("bean1") DataBean1 bean1) {
//		DataBean1 bean1 = (DataBean1) session.getAttribute("bean1");
		System.out.printf("bean1.data1 : %s\n", bean1.getData1());
		System.out.printf("bean1.data2 : %s\n", bean1.getData2());
		return "result4";
	}

	@GetMapping("/test5")
	// sessionBean1이라는 이름으로 request영역에 저장이 됨
	public String test5(@ModelAttribute("sessionBean1") DataBean1 sessionBean1,
			@ModelAttribute("sessionBean2") DataBean1 sessionBean2) {
		sessionBean1.setData1("문자열6");
		sessionBean1.setData2("문자열7");

		sessionBean2.setData1("문자열8");
		sessionBean2.setData2("문자열9");
		return "test5";
	}

	@GetMapping("/result5")
	public String result5(@ModelAttribute("sessionBean1") DataBean1 sessionBean1,
			@ModelAttribute("sessionBean2") DataBean1 sessionBean2) {
		System.out.printf("sessionBean1.data1 : %s\n", sessionBean1.getData1());
		System.out.printf("sessionBean1.data2 : %s\n", sessionBean1.getData2());

		System.out.printf("sessionBean2.data1 : %s\n", sessionBean2.getData1());
		System.out.printf("sessionBean2.data2 : %s\n", sessionBean2.getData2());
		return "result5";
	}
}
