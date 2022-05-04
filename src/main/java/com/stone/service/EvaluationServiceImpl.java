package com.stone.service;

import com.stone.domain.Evaluation;
import com.stone.domain.EvaluationRepository;
import com.stone.domain.Stone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;

@Service
public class EvaluationServiceImpl implements EvaluationService{

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Override
    public Evaluation saveEvaluationToDB(MultipartFile file, String comment, int stars, Date evaluationDate){
        Evaluation evaluation = new Evaluation();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains("..")){
            System.out.println("not a valid file");
        }
        try {
            evaluation.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        evaluation.setComment(comment);
        evaluation.setStars(stars);
        evaluation.setEvaluationDate(evaluationDate);

        return evaluationRepository.save(evaluation);
    }

    @Override
    public Page<Evaluation> findAllByPage(Pageable pageable){
        return evaluationRepository.findAll(pageable );
    }

}
