package com.myweb.www.controller;


import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.FileVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.FileHandler;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//@RequiredArgsConstructor 선언 후 private final로 객체 등록 => 생성자 주입

@Controller
@RequestMapping("/board/*")
@Slf4j
@RequiredArgsConstructor
public class BoardController {

	
	private final BoardService bsv;
	
	private boolean isDetail = true;
	
	private final FileHandler fh;
	
	@ModelAttribute("serverTime")
	public String serverTime(Locale locale) {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		return formattedDate;
	}
	
	@GetMapping("/register")
	public void register() {}
	
	@PostMapping("/register")
	public String insert(BoardVO bvo, RedirectAttributes re, 
			@RequestParam(name="files", required = false)MultipartFile[] files) {
		log.info("bvo : {}", bvo);
		//파일 업로드
		List<FileVO> flist = null;
		
		//fileHandler 생성 mulipartfile -> flist
		if(files[0].getSize() > 0) {
			flist = fh.uploadFiles(files);
		}
		
		int isOk = bsv.insert(new BoardDTO(bvo, flist));
		re.addAttribute("isOk", isOk);
		
		return "index";
	}
	
	@GetMapping("/list")
	public String list(Model m, PagingVO pgvo) {
		log.info("pgvo >>> {}", pgvo);
		
		//페이징 처리
		List<BoardVO> list = bsv.getList(pgvo);
		
		//totalCount 구하기
		int totalCount = bsv.getTotalCount(pgvo);
		
		PagingHandler ph = new PagingHandler(pgvo, totalCount);
		
		m.addAttribute("list", list);
		m.addAttribute("ph", ph);
		
		return "/board/list";
	}
	
	@GetMapping("/detail/{bno}")
	public String detail(@PathVariable int bno, Model m) {
		
		if(isDetail) {
			bsv.readCount(bno);			
		}
		
		BoardDTO bdto = bsv.detail(bno);
		
		m.addAttribute("BoardDTO", bdto);
		
		isDetail = true;
		
		return "/board/detail";
	}
	
	@GetMapping("/modify/{bno}")
	public String modify(@PathVariable int bno, Model m) {
		
		BoardDTO bdto = bsv.detail(bno);
		
		m.addAttribute("BoardDTO", bdto);
		
		return "/board/modify";
	}
	
	@PostMapping("/modify")
	public String update(BoardVO bvo, RedirectAttributes re,
			@RequestParam(name="files", required=false)MultipartFile[] files) {
		
		log.info("bvo {}", bvo);
		log.info("BNO {}", bvo.getBno());
		
		
		
		List<FileVO> flist = null;
		
		if(files[0].getSize() > 0) {
			flist = fh.uploadFiles(files);
		}
		
		int isOk = bsv.update(new BoardDTO(bvo, flist));
		
		re.addFlashAttribute("isMod", isOk);
		
		isDetail = false;
		
		return "redirect:/board/detail/"+ bvo.getBno();
	}
	
	@GetMapping("/remove/{bno}")
	public String remove(@PathVariable int bno, RedirectAttributes re) {
		log.info("{}", bno);
		
		int isOk = bsv.remove(bno);
		log.info(isOk > 0 ? "삭제 성공" : "삭제 실패");
		
		re.addFlashAttribute("isDel", isOk);
		
		return "redirect:/board/list";
	}
	
	@ResponseBody
	@DeleteMapping("/{uuid}")
	public String deleteFile(@PathVariable("uuid") String uuid) {
		
		int isOk = bsv.deleteFile(uuid);
				
		return isOk > 0 ? "1":"0";
	}
	

	
	
	
}
