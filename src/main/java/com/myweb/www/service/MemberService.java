package com.myweb.www.service;

import java.util.List;

import com.myweb.www.security.AuthVO;
import com.myweb.www.security.MemberVO;

public interface MemberService {

	void register(MemberVO mvo);

	boolean updateLastLogin(String authEmail);

	MemberVO detail(String email);

	List<MemberVO> getList();

	void modify(MemberVO mvo);


	List<AuthVO> getAuth(String email);

}
