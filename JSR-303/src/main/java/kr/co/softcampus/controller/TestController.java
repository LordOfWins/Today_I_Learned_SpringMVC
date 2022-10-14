package kr.co.softcampus.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.softcampus.bean.DataBean1;

@Controller
public class TestController {

	@GetMapping("/input_data")
//input_data에 넣을 값이 없어서 dataBean1이라도 넣어 줘야 한다.
	public String input_data(DataBean1 dataBean1) {
		return "input_data";
	}

	@PostMapping("/input_pro")
	public String input_pro(@Valid DataBean1 dataBean1, BindingResult result) {
		if (result.hasErrors()) {
			return "input_data";
		}
		return "input_success";
	}

}
