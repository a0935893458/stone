package com.stone.service;

import com.stone.domain.Evaluation;
import com.stone.domain.Sell;
import com.stone.domain.Stone;
import com.stone.domain.StoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class StoneServiceImpl implements StoneService {

    @Autowired
    private StoneRepository stoneRepository;

    @Override
    public Stone saveStone(Stone stone){
       return  stoneRepository.save(stone);
    }


    @Override
    public Page<Stone> findAllByPage(Pageable pageable){
        return stoneRepository.findAll(pageable );
    }

    @Override
    public Stone getStone(Long id){
        return stoneRepository.findById(id).get();
    }

    @Override
    public void deleteStone(Long id){
        stoneRepository.deleteById(id);
    }

    @Override
    public List<Stone> listAll() {
        return stoneRepository.findAll();
    }

    @Override
    public Stone findOne(Long id){
        return stoneRepository.findById(id).orElse(null);
    }

}
