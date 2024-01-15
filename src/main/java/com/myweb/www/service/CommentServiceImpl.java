package com.myweb.www.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.repository.BoardDAO;
import com.myweb.www.repository.CommentDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class CommentServiceImpl implements CommentService{

	private final CommentDAO cdao;
	
	private final BoardDAO bdao;

	@Override
	public int post(CommentVO cvo) {
		
		cdao.upCmtCount(cvo.getBno());
		return cdao.insert(cvo);
	}

	@Transactional
	@Override
	public PagingHandler getList(long bno, PagingVO pgvo) {
		// TODO Auto-generated method stub
		// commentList로 setting
		// ph 객체안에 cmtList 구성
		// totalCount 구해오기
		
		int totalCount = cdao.selectOneBnoTotalCount(bno);
		List<CommentVO> list = cdao.selectList(bno, pgvo);
		PagingHandler ph = new PagingHandler(pgvo, totalCount, list);
		return ph; 
	}

	@Override
	public int update(CommentVO cvo) {
		// TODO Auto-generated method stub
		return cdao.update(cvo);
	}

	@Override
	public int delete(int cno) {
		// TODO Auto-generated method stub
		
		return cdao.delete(cno);
	}
	
}
