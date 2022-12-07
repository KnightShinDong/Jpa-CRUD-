package com.sdhcompany.home.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

@SequenceGenerator(name = "qboard00_seq_gengerator", 
					sequenceName = "qboard00_seq",
					allocationSize = 1)  //시퀀스 만들기

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionBoard {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(length = 200)
	private String subject;
	
	@Column(length = 1000)//255자 제한 해제
	private String content;
	
	@CreationTimestamp	
	@Column(updatable = false)
	private LocalDateTime createTime;
	
	@OneToMany(mappedBy = "questionBoard", cascade = CascadeType.REMOVE)
											//질문삭제시 답변들도 모두삭제
//	private AnswerBoard answerBoard;
	private List<AnswerBoard> answerList;
}
