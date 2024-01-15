package com.myweb.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;


import com.myweb.www.domain.CommentVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.PagingHandler;

public interface CommentDAO {

	int insert(CommentVO cvo);

	List<PagingHandler> selectList(long bno);

	List<CommentVO> selectList(@Param("bno") long bno, @Param("pgvo") PagingVO pgvo);

	int selectOneBnoTotalCount(long bno);

	int update(CommentVO cvo);

	int delete(int cno);

	void upCmtCount(long bno);

}
