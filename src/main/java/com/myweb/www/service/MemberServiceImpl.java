package com.myweb.www.service;



import java.util.List;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.www.repository.MemberDAO;
import com.myweb.www.security.AuthVO;
import com.myweb.www.security.MemberVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

	private final MemberDAO mdao;
	

	
	@Transactional
	@Override
	public void register(MemberVO mvo) {
		log.info("register service in!");
		
		mdao.register(mvo);
		mdao.insertAuthInit(mvo.getEmail());

	}


	@Override
	public boolean updateLastLogin(String authEmail) {
		// TODO Auto-generated method stub
		return mdao.updateLastLogin(authEmail) > 0 ? true : false;
	}


	@Override
	public MemberVO detail(String email) {

		List<AuthVO> authList = mdao.selectAuths(email);
		MemberVO mvo = mdao.selectEmail(email);
		mvo.setAuthList(authList);
		
		return mvo;
	}


	@Override
	public List<MemberVO> getList() {
		// TODO Auto-generated method stub
		
		return mdao.selectList();
	}


	@Override
	public void modify(MemberVO mvo) {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		if(mvo.getPwd() == null || mvo.getPwd().length() == 0) {
			MemberVO tmpMvo = mdao.getUser(mvo.getEmail());
			mvo.setPwd(tmpMvo.getPwd());
			mdao.update(mvo);
		}else {
			String pwd = mvo.getPwd();
			String encodepwd = passwordEncoder.encode(pwd);
			mvo.setPwd(encodepwd);
			mdao.update(mvo);
		}
		
		
	}


	@Override
	public List<AuthVO> getAuth(String email) {
		// TODO Auto-generated method stub
		return mdao.selectAuth(email);
	}




}
