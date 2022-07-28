package com.care.root.member.dao;

import java.util.List;

import com.care.root.member.dto.MemberDTO;

public interface MemberDAO {
	public void insert(MemberDTO dto); //연결되어 있는 xml을 찾아가 insert와 같은 mapper를 찾음
	public List<MemberDTO> getMember();
}
