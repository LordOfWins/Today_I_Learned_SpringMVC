package kr.co.softcampus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.softcampus.beans.DataBean;

@Controller
public class TestController {

	@GetMapping("/input_data")
	public String input_data() {
		return "input_data";
	}

	@PostMapping("/input_pro")
	public String input_pro(DataBean dataBean) {
		
	}

}
