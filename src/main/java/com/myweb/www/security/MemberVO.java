package com.myweb.www.security;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MemberVO {

	private String email;
	private String pwd;
	private String nickName;
	private String lastLogin;
	private String regAt;
	
	private List<AuthVO> authList;
	
}
