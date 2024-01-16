package com.myweb.www.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.www.security.AuthVO;
import com.myweb.www.security.MemberVO;
import com.myweb.www.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/member/*")
@Slf4j
@RequiredArgsConstructor
@Controller
public class MemberController {

	private final MemberService msv;
	private final BCryptPasswordEncoder bcEncoder;
	
	
	@GetMapping("/register")
	public void register() {}
	
	@PostMapping("/register")
	public String regitser(MemberVO mvo) {
		
		mvo.setPwd(bcEncoder.encode(mvo.getPwd()));
		log.info("mvo >>> {}", mvo);
		msv.register(mvo);
		
		return "index";
	}
	
	@GetMapping("/login")
	public void login() {}
	
	@PostMapping("/login")
	public String login(HttpServletRequest request, RedirectAttributes re) {
		//로그인 실패시 다시 로그인 페이지로 돌아와 오류 메시지 전송
		//다시 로그인 유도
		re.addAttribute("email", request.getAttribute("email"));
		re.addAttribute("errorMsg", request.getAttribute("errorMsg"));
		
		return "redirect:/member/login";
	}
	
	@GetMapping("/list")
	public String list(Model m) {
		List<MemberVO> list = msv.getList();

		for(MemberVO mvo : list) {
			mvo.setAuthList(msv.getAuth(mvo.getEmail()));
		}
		
		m.addAttribute("list", list);

		
		return "/member/list";
	}
	
//	@RequestParam("email") String email : 쿼리스트링 (파라미터 받기)
//	@GetMapping("/modify") 
//	public void modify(Principal p, Model m) {
//		log.info(">>> Principle >> email > {}", p);
//		String email = p.getName();
//		m.addAttribute("mvo", msv.detail(email));
//	}
//	
	@GetMapping("/modify/{email}")
	public String modifyMember(Principal p, Model m, @PathVariable String email) {
		log.info(">>> Principle >> email > {}", email);
		log.info("p.getName {}", p.getName());
		m.addAttribute("mvo", msv.detail(email));
		if(p.getName().equals(email)) {
			return "/member/modify";			
		}else {
			return "index";			
		}
		

	}
	
	@PostMapping("/modify")
	public String modify(MemberVO mvo, HttpServletRequest request, HttpServletResponse response) {
		
		log.info("modify >> >> mvo {}", mvo);
		msv.modify(mvo);

		//로그아웃 진행
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		new SecurityContextLogoutHandler().logout(request, response, authentication);
			
		return "/member/login";
	}
	
	
	
	
}
