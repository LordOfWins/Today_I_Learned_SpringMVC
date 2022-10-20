package kr.co.softcampus.database;

import org.apache.ibatis.annotations.Insert;

import kr.co.softcampus.beans.DataBean;

public interface MapperInterface {

	@Insert("insert into spring_mvc_table(data1, data2, data3) values (#{data1}, #{data2}, #{data3})")
	void insert_data(DataBean dataBean);
}
