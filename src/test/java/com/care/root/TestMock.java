package com.care.root;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.care.root.member.controller.MemberController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@RunWith(SpringRunner.class) //@RunWith: 어떤걸로 테스트를 구동할건지 //SpringRunner로 테스트 구동
@ContextConfiguration(locations = {"classpath:testMember.xml", "file:src/main/webapp/WEB-INF/spring/root-context.xml"}) //classpath: resources로 가져옴 //file: 파일을 읽어옴
public class TestMock { //컨트롤러와 연결해서 테스트 진행
	
	@Autowired
	MemberController mc;
	
	MockMvc mock; //mock객체 생성(컨트롤러에 대한 경로 매핑 연결해줌)
	
	@Before //@Test 전에 실행된다(주로 초기화 용도로 사용)
	public void setUp() {
		System.out.println("test 실행전--------");
		mock = MockMvcBuilders.standaloneSetup(mc).build(); //(연결하고자 하는 컨트롤러).컨트롤러를 빌드해줌();
	}
	
	@Test
	public void testIndex() throws Exception {
		System.out.println("----testindex 실행");
		//경로를 요청하는 값 (요청방식("/경로")
		mock.perform( get("/index") )
			//print:현재에 대한 상태를 출력해줌
			.andDo(print())
			//andExpect: 상황확인(현재에 대한 상태().웹통신상태(연결 성공 = 200)
			.andExpect(status().isOk())
			//forwardedUrl: 리턴 경로 확인
			.andExpect(forwardedUrl("member/index"));
	}
	
	@Test
	@Transactional(transactionManager = "txMgr") //DB입력값 Rollback
	public void testInsert() throws Exception {
		//post방식으로 insert 요청
		mock.perform( post("/insert")
				//파라미터값 설정(키, 값)
				.param("id", "987").param("name", "연습987") )
				//현재 상태 출력
				.andDo(print())
				//redirect는 302를 돌려 준다. 302면 다른 위치로 이동
				.andExpect(status().is3xxRedirection());
	}
	
	@Test
	public void testMemberview() throws Exception {
		//get방식으로 요청했을때
		mock.perform(get("/memberview")).andDo(print() )
			//통신이 성공했는지 확인
			.andExpect(status().isOk() )
			//리턴 돌아가는거 경로 확인
			.andExpect(forwardedUrl("member/memberview") )
			//model에 저장된 값이 list인지
			.andExpect(model().attributeExists("list") );
	}
}
