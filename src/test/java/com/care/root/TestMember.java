package com.care.root;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.care.root.member.controller.MemberController;
import com.care.root.member.dao.MemberDAO;
import com.care.root.member.dto.MemberDTO;
import com.care.root.member.service.MemberService;

@RunWith(SpringRunner.class) //@RunWith: 어떤걸로 테스트를 구동할건지 //SpringRunner로 테스트 구동
@ContextConfiguration(locations = {"classpath:testMember.xml", "file:src/main/webapp/WEB-INF/spring/root-context.xml"}) //classpath: resources로 가져옴 //file: 파일을 읽어옴
public class TestMember { //테스트는 서버 구동없이 테스트 진행 //클래스 메소드 테스트
	@Autowired
	MemberController mc; //MemberController가 객체 빈으로 등록되어 잘들어오는지 먼저 확인
	//단위 테스트(하나씩 테스트)
	@Test //해당 메소드 테스트 설정
	public void testMc() { //Junit로 메소드 테스트 실행 //빨간색 상태창: error, 초록색 상태창: 정상 실행
		System.out.println("--- mc : " + mc);
		assertNotNull( mc ); //해당 객체가 null값이 아니면 해당 테스트 성공
	}
	
	@Autowired
	MemberService ms;
	@Test
	public void testMs() {
		assertNotNull( ms );
		MemberDTO dto = new MemberDTO();
		dto.setId(3333);
		dto.setName("홍길동");
		
		ms.insert(dto);
	}
	
	@Autowired
	MemberDAO dao;
	@Test
	public void testDao() {
		assertNotNull( dao );
		MemberDTO dto = new MemberDTO();
		dto.setId(1111);
		dto.setName("홍길동");
		
		dao.insert(dto);
	}
	
}
