package com.myweb.www;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.repository.BoardDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.myweb.www.config.RootConfig.class})
public class BoardTest {

	@Inject
	private BoardDAO bdao;
	
	@Test
	public void insertBoard() {
		for(int i=0; i<300; i++) {
			BoardVO bvo = new BoardVO();
			bvo.setTitle("Test Title " + i);
			bvo.setWriter("Test Writer " + i);
			bvo.setContent("Test Content " + i);
			bdao.insert(bvo);
		}
	}
	
//	@Test
//	public void deleteBoard() {
//		for(int i=0; i<500; i++) {
//			bdao.delete(i);
//		}
//	}

	
}
