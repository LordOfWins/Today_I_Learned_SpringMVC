package kr.co.softcampus.controller;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.softcampus.beans.DataBean;

@RestController
public class RestTestController {

	@GetMapping("/test2")
	public ResponseEntity<ArrayList<DataBean>> test2() {

		DataBean bean1 = new DataBean("문자열1", 100, 11.11, false);
		DataBean bean2 = new DataBean("문자열2", 200, 22.22, true);
		DataBean bean3 = new DataBean("문자열3", 300, 33.33, false);
		ArrayList<DataBean> list = new ArrayList<DataBean>();
		list.add(bean1);
		list.add(bean2);
		list.add(bean3);

		ResponseEntity<ArrayList<DataBean>> entry = new ResponseEntity<ArrayList<DataBean>>(list, HttpStatus.OK);
		/*
		 * ㅣist: 객체를 담기 위해서 httpStatus: 안전하다는 걸 증명하기 위해서
		 */
		return entry; // 데이터로 보내기 때문에 문자열 자체가 보인다.
		// 데이터의 총량을 줄이려고 한다.
	}

}
