package com.care.root.member.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.care.root.member.dao.MemberDAO;
import com.care.root.member.dto.MemberDTO;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberDAO mapper;
	public void insert(MemberDTO dto) { //오버라이딩
		mapper.insert(dto); //데이터가 전달되면서 데이터베이스에 저장
	}
	
	public void getMember(Model model) {
		//ArrayList<MemberDTO> list =  mapper.getMember();
		//model.addAttribute("list", list);
		model.addAttribute("list", mapper.getMember());
	}

}
