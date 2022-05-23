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
        stone.setNtdPrice(stone.getChPrice()*6);
        stone.setSell(new Sell());
        Sell sell = stone.getSell();
        sell.setStoneId(stone.getStoneId());
        sell.setOrigin(stone.getOrigin());
        sell.setName(stone.getName());
        sell.setPrice(stone.getChPrice()*6);
        sell.setStatus(stone.getStatus());

        return  stoneRepository.save(stone);
    }

    @Override
    public Stone postStone(Stone stone,Stone stone1){
        stone.setNtdPrice(stone.getChPrice()*6);
        Sell sell = stone1.getSell();
        sell.setStoneId(stone.getStoneId());
        sell.setOrigin(stone.getOrigin());
        sell.setName(stone.getName());
        sell.setPrice(stone.getChPrice()*6);
        sell.setStatus(stone.getStatus());
        stone.setSell(stone1.getSell());

        return  stoneRepository.save(stone);
    }

    @Override
    public Stone saveSellToStone(Stone stone,Sell sell){
        stone.setName(sell.getName());
        stone.setOrigin(sell.getOrigin());
        stone.setNtdPrice(sell.getPrice());
        stone.setChPrice(sell.getPrice()/6);
        stone.setStatus(sell.getStatus());
        stone.setStoneId(sell.getStoneId());

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
