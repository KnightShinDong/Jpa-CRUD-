package com.sdhcompany.home.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.sdhcompany.home.entity.QuestionBoard;
import com.sdhcompany.home.repository.QboardRepository;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class TestQBoard {
	
	@Autowired
	QboardRepository qboardRepository;
	
	@Test
	@DisplayName("저장테스트")
	public void createQuestion() {
		QuestionBoard questionBoard = new QuestionBoard();
		
		questionBoard.setSubject("안녕");
		questionBoard.setContent("안녕");
		
		
		qboardRepository.save(questionBoard);
		
	}
	@Test
	@DisplayName("조회테스트1")
	public void searchQuestion1() {
		List<QuestionBoard> qAll =  qboardRepository.findAll();
		assertEquals(2, qAll.size()); //실제 모든 글의 개수와 기대한 값인 2개가 일치하는지 여부 확인
		
		QuestionBoard q1 = qAll.get(1);
		assertEquals("질문받아라", q1.getSubject()); 
	}
	
	@Test
	@DisplayName("조회테스트2")
	public void searchQuestion2() {
		Optional<QuestionBoard> qBoard =  qboardRepository.findById(2);
		
		if(qBoard.isPresent()) {
		//isEmpty(),isPresent()
		QuestionBoard q1 = qBoard.get();
		assertEquals("질문받아라", q1.getSubject()); 
		}
	}
	
	@Test
	@DisplayName("조회테스트3")
	public void searchQuestion3() {
		List<QuestionBoard> sub = qboardRepository.findBySubject("질문");
	
		QuestionBoard qid= sub.get(0);
		assertEquals(5,  qid.getId());
	}
	
	@Test
	@DisplayName("조회테스트4")
	public void searchQuestion4() {
		List<QuestionBoard> sub = qboardRepository.findBySubjectAndContent("안녕", "안녕");
		QuestionBoard qid= sub.get(0);
		assertEquals(6,  qid.getId());
	}
	
	@Test
	@DisplayName("조회테스트5")
	public void searchQuestion5() {
		List<QuestionBoard> subAll = qboardRepository.findBySubjectLike( "%질문%");
		
		assertEquals(5,  subAll.size());
		
	}
	
	@Test
	@DisplayName("수정테스트1")
	public void modifyQuestion() {
		
		Optional<QuestionBoard> mid = qboardRepository.findById(2);
		
		QuestionBoard mEntity = mid.get();
		
		mEntity.setSubject("저는 2번글입니다");
		QuestionBoard qr = qboardRepository.save(mEntity);
		
		assertEquals("저는 2번글입니다" ,qr.getSubject());
		
	}
	
	@Test
	@DisplayName("삭제테스트01")
	public void deleteQuestion() {
//		List<QuestionBoard> qAll =  qboardRepository.findAll();
//		int qAllSize1 = qAll.size(); //모든글의 개수
//		
//		Optional<QuestionBoard> deleteId = qboardRepository.findById(3);
//		QuestionBoard dd= deleteId.get();
//		
//		qboardRepository.delete(dd);
//		
//		qAll =  qboardRepository.findAll();
//		int qAllSize2 = qAll.size(); //모든글의 개수
//		assertEquals(qAllSize2, qAllSize1-1);
//-----------------------------------------------------------------------------------		
		
//		List<QuestionBoard> qAll =  qboardRepository.findAll();
//		int qAllSize1 = qAll.size(); //모든글의 개수
		
		long qAllSize1 = qboardRepository.count(); //모든 데이터 개수 조회
		Optional<QuestionBoard> deleteId = qboardRepository.findById(3);
		
		assertTrue(deleteId.isPresent());
		
		QuestionBoard dd= deleteId.get();
		
		
		
		qboardRepository.deleteById(4);
		
//		qAll =  qboardRepository.findAll();
//		int qAllSize2 = qAll.size(); //모든글의 개수
		long qAllSize2 = qboardRepository.count();
		assertEquals(qAllSize2, qAllSize1-1);
		
	}
}
