package kr.co.softcampus.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.softcampus.beans.DataBean;

@Controller
public class TestController {
	@GetMapping("/test1")
	public String test1(@RequestParam Map<String, String> map, @RequestParam(value = "data3") List<String> list) {
		// jsp에서 주는 이름과 동일해야 함 그리고 자동 형변환 되지 못 해 문자열로 밖에 받지 못함.
		String data1 = map.get("data1");
		String data2 = map.get("data2");
		String data3 = map.get("data3");
		System.out.printf("data1 : %s\n", data1);
		System.out.printf("data2 : %s\n", data2);
		// 동일명으로 전달되는 경우 2개 이상의 파라미터는 하나만 남기게 됨
		System.out.printf("data3 : %s\n", data3);
		for (String string : list) {
			System.out.printf("data3: %s\n", string);
		}
		return "result";
	}

	@GetMapping("/test2")
	// @ModelAttribute 여러개를 사용하면 여러 군데에서 주입 받을 수 있다.
//	public String test2(@ModelAttribute DataBean bean1, @ModelAttribute DataBean bean2) {
	// @ModelAttribute 생략가능
	public String test2(DataBean bean1, DataBean bean2) {
		System.out.printf("data1 : %d\n", bean1.getData1());
		System.out.printf("data2 : %d\n", bean1.getData2());
		for (int number : bean1.getData3()) {
			System.out.printf("data3 : %d\n", number);
		}
		System.out.printf("data1 : %d\n", bean2.getData1());
		System.out.printf("data2 : %d\n", bean2.getData2());
		return "result";
	}
}
