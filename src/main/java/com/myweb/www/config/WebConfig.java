package com.myweb.www.config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		// SecurityConfig.class 추가 2024-01-12
		return new Class[] {RootConfig.class, SecurityConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {ServletConfiguration.class};
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] {"/"};
	}

	//EncodingFilter 설정
	
	@Override
	protected Filter[] getServletFilters() {
		// filter 설정
		CharacterEncodingFilter encoding = new CharacterEncodingFilter();
		encoding.setEncoding("UTF-8");
		encoding.setForceEncoding(true); //외부로 나가는 데이터도 인코딩 설정
		
		return new Filter[] {encoding};
	}

	@Override
	protected void customizeRegistration(Dynamic registration) {
		// 그 외 기타 사용자 설정
		// 사용자 지정 익셉션 처리 지정
		
		// 파일 업로드 설정 2024-01-10
		String uploadLocation = "C:\\_myProject\\_java\\_fileUpload";
		int MaxFileSize = 1024*1024*20; //20M
		int MaxReqSize = MaxFileSize*2; //40M
		int fileSizeThreshold = MaxFileSize; //20M
		
		// mutipartConfig 설정
		MultipartConfigElement multipartConfig = 
				new MultipartConfigElement(uploadLocation, MaxFileSize, MaxReqSize, fileSizeThreshold);
		
		registration.setMultipartConfig(multipartConfig);
		
		
	}
	
	

}
