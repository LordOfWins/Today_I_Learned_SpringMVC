package kr.co.softcampus.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import kr.co.softcampus.beans.DataBean1;

@Controller
public class TestController {
	// 1. jsp에 데이터를 사용하려는 것
	// 2. forwarding 했을 때 다른 메서드로 가져가려는 것
	// forward해서 추출할 때는 받을 메서드 HttpServletRequest 객체여야 한다.
	@GetMapping("/test1")
	public String test1(HttpServletRequest request) {
		request.setAttribute("data1", "문자열1");
		// redirect: result1을 요청하라고 전달 그리고 request는 소멸
		// forward: 서버 내에서 이동하기 때문에 request는 소멸 되지 않는다. jsp에서도 사용 가능
		return "forward:/result1";
	}

	@GetMapping("/result1")
	public String result1(HttpServletRequest request) {
		// redirect: 새로운 데이터가 생성돼 data1에 null이 뜬다.
		// forward: 삭제되지 않고 서버 상에서 이동하기 때문에 문자열1이 대입된다.
		String data1 = (String) request.getAttribute("data1");
		System.out.printf("data1: %s\n", data1);
		return "result1";
	}

	@GetMapping("/test2")
	public String test2(Model model) {
		model.addAttribute("data2", "문자열2");
		// request 객체에 저장
		return "forward:/result2";
	}

	@GetMapping("/result2")
	// model에 저장되지 않아서 가져올 수 없기 때문에 request를 주입 받아야 한다.
	public String result2(HttpServletRequest request) {
		String data2 = (String) request.getAttribute("data2");
		System.out.printf("data2 : %s\n", data2);
		return "result2";
	}

	@GetMapping("/test3")
	public ModelAndView test3(ModelAndView mv) {
		mv.addObject("data3", "문자열3");
		// request영역에 저장됨
		mv.setViewName("forward:/result3");
		return mv;
	}

	@GetMapping("/result3")
	public String result3(HttpServletRequest request) {
		String data3 = (String) request.getAttribute("data3");
		System.out.printf("data3 : %s\n", data3);
		return "result3";
	}

	@GetMapping("/test4")
	public String test4(Model model) {
		DataBean1 bean1 = new DataBean1();
		bean1.setData1("문자열4");
		bean1.setData2("문자열5");
		model.addAttribute("bean1", bean1);
		// request에 저장
		return "forward:/result4";
	}

	@GetMapping("/result4")
	public String result4(HttpServletRequest request) {
		DataBean1 bean1 = (DataBean1) request.getAttribute("bean1");
		System.out.printf("bean1.data1 : %s\n", bean1.getData1());
		System.out.printf("bean1.data2 : %s\n", bean1.getData2());
		return "result4";
	}

	@GetMapping("/test5")
	// bean1 이름으로 request에 저장이 된다.
	public String test5(@ModelAttribute("bean1") DataBean1 bean1) {
		bean1.setData1("문자열6");
		bean1.setData2("문자열7");
		return "forward:/result5";
	}

	@GetMapping("/result5")
	// 다른 bean객체로 저장되어 null 값이 뜬다.
//	public String result5(@ModelAttribute("bean1") DataBean1 bean1) {
	public String result5(HttpServletRequest request) {
		DataBean1 bean1 = (DataBean1) request.getAttribute("bean1");
		System.out.println("bean1.data1 : " + bean1.getData1());
		System.out.print("bean1.data2 : " + bean1.getData2());
		return "result5";
	}
}
