package com.myweb.www.service;

import com.myweb.www.security.MemberVO;

public interface MemberService {

	void register(MemberVO mvo);

	boolean updateLastLogin(String authEmail);

}