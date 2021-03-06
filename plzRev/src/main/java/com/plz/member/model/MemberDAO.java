package com.plz.member.model;

import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO implements IMemberDAO {

	@Autowired
	private SqlSessionTemplate sqlsession;
	
	// ====  로그인여부 알아오기====
	@Override
	public int loginEnd(HashMap<String, String> map) {

		/*
		 	1:일치
		 	0:암호틀림
		 	-1:아이디없음
		 */
		int result = sqlsession.selectOne("plz.loginEnd",map);
		
		return result;
	}

	// ====  로그인한 사용자정보 알아오기====
	@Override
	public MemberVO getLoginMember(String userid) {

		MemberVO mvo = sqlsession.selectOne("plz.getLoginMember",userid);
		return mvo;
	}

	@Override
	public int isUseuserid(String email) {
		int isUseuserid = sqlsession.selectOne("plz.isUseuserid",email);
		return isUseuserid;
	}

	@Override
	public int registerMember(HashMap<String, String> map) {
		int registerEmail = sqlsession.insert("plz.registerEmail",map);
		int registerMember = 0;
		
		if(registerEmail == 1){
			registerMember = sqlsession.insert("plz.registerMember",map);
		}
		
		return registerMember*registerEmail;
	}
}






















