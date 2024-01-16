package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.security.AuthVO;
import com.myweb.www.security.MemberVO;

public interface MemberDAO {

	void register(MemberVO mvo);

	void insertAuthInit(String email);

	MemberVO selectEmail(String username);

	List<AuthVO> selectAuths(String username);

	int updateLastLogin(String authEmail);

	List<MemberVO> selectList();

	MemberVO getUser(String email);

	void update(MemberVO mvo);

	List<AuthVO> selectAuth(String email);



}
