package com.myweb.www.handler;

import java.util.List;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.domain.PagingVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class PagingHandler {

	private int startPage; // 페이지네이션 시작번호  1, 11, 21
	private int endPage;   // 끝 번호           10, 20, 30 
	private boolean prev, next;
	
	private int totalCount; // 총 게시글 수 
	private PagingVO pgvo;  // 매개변수로 전달 
	
	private List<CommentVO> cmtList;
	
	public PagingHandler(PagingVO pgvo, int totalCount, List<CommentVO> cmtList) {
		this(pgvo, totalCount);
		this.cmtList = cmtList;
	}
	
	//생성자에서 모든 값들이 설정되야 함
	public PagingHandler(PagingVO pgvo, int totalCount) {
		this.pgvo = pgvo;
		this.totalCount = totalCount;
		
		this.endPage = (int)Math.ceil(pgvo.getPageNo() / 10.0)*10;
		
		this.startPage = endPage - 9;
		
		//실제 마지막 페이지
		//전체 글 수 / 한 페이지에 표시되는 게시글 수 => 올림
		int realEndPage = (int)Math.ceil( totalCount / (double)pgvo.getQty());
		
		if(realEndPage < endPage) {
			endPage = realEndPage;
		}
		
		this.prev = this.startPage > 1; 
		this.next = this.endPage < realEndPage;
		
	}
	
}
