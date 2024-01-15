package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;

public interface BoardService {

	int insert(BoardDTO boardDTO);

	BoardDTO detail(int bno);

	void readCount(int bno);

	int update(BoardVO bvo);

	int remove(int bno);

	List<BoardVO> getList(PagingVO pgvo);

	int getTotalCount(PagingVO pgvo);

	int deleteFile(String uuid);

	int update(BoardDTO boardDTO);

}
