package com.sdhcompany.home.test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.sdhcompany.home.entity.AnswerBoard;
import com.sdhcompany.home.entity.QuestionBoard;
import com.sdhcompany.home.repository.ABoardRepository;
import com.sdhcompany.home.repository.QboardRepository;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class TestABoard {

	@Autowired
	private QboardRepository qboardRepository;
	
	@Autowired
	private ABoardRepository aBoardRepository;
	
	@Test
	@DisplayName("답변 저장 테스트")
	public void AnswerCreatTest() {
		
		Optional<QuestionBoard> oQboard = qboardRepository.findById(4);
		assertTrue(oQboard.isPresent()); //7번 질문글이 존재하는지 테스트
		
		QuestionBoard qBoard = oQboard.get();
		AnswerBoard aBoard = new AnswerBoard();
		aBoard.setContent("4번글 답변입니다.");
		aBoard.setQuestionBoard(qBoard);
		aBoardRepository.save(aBoard);
		
	}
	
	@Test
	@DisplayName("답변 조회 테스트")
	public void AnswerSearch() {
		
		Optional<AnswerBoard> oAboard = aBoardRepository.findById(6);
		assertTrue(oAboard.isPresent());
		AnswerBoard aBoard = oAboard.get();
		
		
		assertEquals(4, aBoard.getQuestionBoard().getId());
		//질문글의 아이디를 가져와서 확인
		
	}
	@Transactional
	@Test
	@DisplayName("답변/질문 조회 테스트")
	public void AnswerQuestionSearch() {
		
		Optional<QuestionBoard> oQboard = qboardRepository.findById(4);
		assertTrue(oQboard.isPresent()); //7번 질문글이 존재하는지 테스트
		
		QuestionBoard qBoard = oQboard.get();
		
		List<AnswerBoard> aBoards = qBoard.getAnswerList();// 답변글 리스트 가져오기
		
		
		assertEquals(4, aBoards.size());
		//질문 4번글에 달린 답변 글의 개수가 총 4개인지 확인
		
	}
}
