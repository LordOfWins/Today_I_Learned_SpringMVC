package kr.co.softcampus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
	@GetMapping("/test1")
	public String test1() {
		// sub1을 요청하라고 전달
		return "redirect:/sub1";

	}

	@GetMapping("/sub1")
	public String sub1() {
		return "sub1";
	}

	@GetMapping("/test2")
	public String test2() {
		// 서버 상에서만 이동
		return "forward:/sub2";
	}

	@GetMapping("/sub2")
	public String sub2() {
		return "sub2";
	}
}
