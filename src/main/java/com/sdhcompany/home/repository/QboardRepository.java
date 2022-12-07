package com.sdhcompany.home.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sdhcompany.home.entity.QuestionBoard;

public interface QboardRepository extends JpaRepository<QuestionBoard, Integer >{
public List<QuestionBoard> findBySubject(String subject);
//글 제목과 일치하는 
public List<QuestionBoard> findBySubjectAndContent(String subject, String content);
public List<QuestionBoard> findBySubjectLike(String subject);
public List<QuestionBoard> findById(String id);


}
