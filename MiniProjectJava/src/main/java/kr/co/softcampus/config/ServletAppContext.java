package kr.co.softcampus.config;


import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.co.softcampus.interceptor.TopMenuInterceptor;
import kr.co.softcampus.mapper.BoardMapper;
import kr.co.softcampus.mapper.TopMenuMapper;
import kr.co.softcampus.service.TopMenuService;

// Spring MVC 프로젝트에 관련된 설정을 하는 클래스
/**
 * @author user
 *
 */
@Configuration
// Controller 어노테이션이 셋팅되어 있는 클래스를 Controller로 등록한다.
@EnableWebMvc
// 스캔할 패키지를 지정한다.
@ComponentScan("kr.co.softcampus.controller")
@ComponentScan("kr.co.softcampus.dao")
@ComponentScan("kr.co.softcampus.service")
@PropertySource("/WEB-INF/properties/db.properties")
public class ServletAppContext implements WebMvcConfigurer {
	// Controller의 메서드가 반환하는 jsp의 이름 앞뒤에 경로와 확장자를 붙혀주도록 설정한다.

	@Value("${db.classname}")
	private String db_classname;
	@Value("${db.url}")
	private String db_url;
	@Value("${db.username}")
	private String db_username;
	@Value("${db.password}")
	private String db_password;

	@Autowired
	private TopMenuService topMenuService;

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.configureViewResolvers(registry);
		registry.jsp("/WEB-INF/views/", ".jsp");
	}

	// 정적 파일의 경로를 매핑한다.
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addResourceHandlers(registry);
		registry.addResourceHandler("/**").addResourceLocations("/resources/");
	}

	// 데이터 베이스 점속 정보를 관리하는 Bean
	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource source = new BasicDataSource();
		source.setDriverClassName(db_classname);
		source.setUrl(db_url);
		source.setUsername(db_username);
		source.setPassword(db_password);
		return source;
	}

	// 쿼리문과 접속 정보를 관리하는 객체
	@Bean
	public SqlSessionFactory factory(BasicDataSource source) throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(source);
		SqlSessionFactory factory = factoryBean.getObject();
		return factory;
	}

	// 쿼리문 실행을 위한 객체(Mapper 관리)
	@Bean
	public MapperFactoryBean<BoardMapper> getBoardMapper(SqlSessionFactory factory) throws Exception {
		MapperFactoryBean<BoardMapper> factoryBean = new MapperFactoryBean<BoardMapper>(BoardMapper.class);
		factoryBean.setSqlSessionFactory(factory);
		return factoryBean;

	}

	@Bean
	public MapperFactoryBean<TopMenuMapper> getTopMenuMapper(SqlSessionFactory factory) throws Exception {
		MapperFactoryBean<TopMenuMapper> factoryBean = new MapperFactoryBean<TopMenuMapper>(TopMenuMapper.class);
		factoryBean.setSqlSessionFactory(factory);
		return factoryBean;

	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		WebMvcConfigurer.super.addInterceptors(registry);
		TopMenuInterceptor topMenuinterceptor = new TopMenuInterceptor(topMenuService);
		InterceptorRegistration reg1 = registry.addInterceptor(topMenuinterceptor);
		reg1.addPathPatterns("/**");
	}

}
