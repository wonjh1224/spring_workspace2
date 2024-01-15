package com.myweb.www.service;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.FileVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.repository.BoardDAO;
import com.myweb.www.repository.FileDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

	private final BoardDAO bdao;

	private final FileDAO fdao;

	@Transactional
	@Override
	public int insert(BoardDTO bdto) {
		log.info("insert service in");

		// bvo boardMapper / flist fileMapper
		int isOk = bdao.insert(bdto.getBvo());
		if (bdto.getFlist() == null) {
			return isOk;
		}

		// bvo insert 후 파일도 있다면
		if (isOk > 0 && bdto.getFlist().size() > 0) {
			// bno setting
			long bno = bdao.selectOneBno(); // 가장 마지막에 등록된 bno
			for (FileVO fvo : bdto.getFlist()) {
				fvo.setBno(bno);
				isOk *= fdao.insertFile(fvo);
			}
		}

		return isOk;
	}

	@Override
	public List<BoardVO> getList(PagingVO pgvo) {
		log.info("list service in");
		return bdao.selectList(pgvo);
	}

	@Transactional
	@Override
	public BoardDTO detail(int bno) {
		log.info("detail service in");
		BoardVO bvo = bdao.selectOne(bno);
		List<FileVO> flist = fdao.getFileList(bno);
		BoardDTO bdto = new BoardDTO(bvo, flist);
		return bdto;
	}

	@Override
	public void readCount(int bno) {
		// TODO Auto-generated method stub
		bdao.updateReadCount(bno);
	}

	@Override
	public int update(BoardVO bvo) {
		// TODO Auto-generated method stub
		return bdao.update(bvo);

	}

	@Override
	public int remove(int bno) {
		// TODO Auto-generated method stub
		return bdao.delete(bno);

	}

	@Override
	public int getTotalCount(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return bdao.getTotalCount(pgvo);
	}

	@Override
	public int deleteFile(String uuid) {
		// TODO Auto-generated method stub
		return fdao.deleteFile(uuid);
	}

	@Transactional
	@Override
	public int update(BoardDTO boardDTO) {
		// TODO Auto-generated method stub
		int isOk = bdao.update(boardDTO.getBvo());
		if(boardDTO.getFlist() == null) {
			return isOk;
		}
		
		//bvo insert 후 파일도 있다면
		if(isOk > 0 && boardDTO.getFlist().size() > 0) {
			//bno setting
			long bno = bdao.selectOneBno(); // 가장 마지막에 등록된 bno
			for(FileVO fvo : boardDTO.getFlist()) {
				fvo.setBno(boardDTO.getBvo().getBno());
				isOk *= fdao.insertFile(fvo);
			}
		}
		
		return isOk;
	}


	
}
