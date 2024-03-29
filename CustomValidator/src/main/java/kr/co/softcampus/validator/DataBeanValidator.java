package kr.co.softcampus.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import kr.co.softcampus.beans.DataBean1;

public class DataBeanValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// 유효성 검사하려는 클래스 타입이 맞는지 확인
		return DataBean1.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "data2", "error2");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "data3", "error3");

		DataBean1 bean1 = (DataBean1) target;
		String data2 = bean1.getData2();
		String data3 = bean1.getData3();

		if (data2.length() > 10) {
			errors.rejectValue("data2", "error4");
		}
		if (data3.contains("@") == false) {
			errors.rejectValue("data3", "error5");
		}
	}

}
