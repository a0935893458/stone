package com.stone.service;

import com.stone.domain.Evaluation;
import com.stone.domain.Stone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public interface EvaluationService {
    Evaluation saveEvaluationToDB(MultipartFile file, String comment, int stars , Date evaluationDate);

    Page<Evaluation> findAllByPage(Pageable pageable);
}
