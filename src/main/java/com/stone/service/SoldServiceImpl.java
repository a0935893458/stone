package com.stone.service;

import com.stone.domain.Sold;
import com.stone.domain.SoldRepository;
import com.stone.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoldServiceImpl implements SoldService{

    @Autowired
    private SoldRepository soldRepository;

    @Override
    public Sold saveSoldPrice(User user){
        Sold sold = new Sold();
        sold.setPrice(user.getTotalPrice());
        sold.setUid(user.getId());
        user.setTotalPrice(0);
        List<Sold> soldList = soldRepository.findAllByUid(user.getId());
        Long sellGroup = Long.valueOf(soldList.size())+1;
        sold.setSellGroup(sellGroup);
        sold.setSellStatus(0);

        return soldRepository.save(sold);
    }

    @Override
    public List<Sold> findAllByUid(long uid){
        return soldRepository.findAllByUid(uid);
    }

}
