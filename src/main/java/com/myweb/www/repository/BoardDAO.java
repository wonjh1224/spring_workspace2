package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;

public interface BoardDAO {


	int insert(BoardVO bvo);

	BoardVO selectOne(int bno);

	void updateReadCount(int bno);

	int update(BoardVO bvo);

	int delete(int bno);

	List<BoardVO> selectList(PagingVO pgvo);

	int getTotalCount(PagingVO pgvo);

	long selectOneBno();




}
