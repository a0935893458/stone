package com.stone.service;

import com.stone.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

@Service
public class SellServiceImpl implements SellService{

    @Autowired
    private SellRepository sellRepository;

    @Autowired
    private StoneServiceImpl stoneService;

    @Autowired
    private SoldRepository soldRepository;

    /**
     * 存取上架的照片,介紹,大小
     * @param sell
     * @param file
     * @return
     */

    @Override
    public Sell saveSell(Sell sell, MultipartFile file){
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains("..")){
            System.out.println("not a valid file");
        }
        try {
            sell.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stone stone=stoneService.findOne(sell.getId()-1);
        stone.setName(sell.getName());
        stone.setOrigin(sell.getOrigin());
        stone.setNtdPrice(sell.getPrice());
        stone.setChPrice(sell.getPrice()/6);
        stone.setStatus(sell.getStatus());
        stone.setStoneId(sell.getStoneId());
        stoneService.saveStone(stone);


        return sellRepository.save(sell);
    }

    /**
     * 更改買家觀看狀態及進銷存的狀態
     * @param sell
     * @param sell1
     * @return
     */
    public Sell saveSellStatus(Sell sell,Sell sell1){
        Sold sold = soldRepository.findSoldBySellGroupAndUid(sell1.getSellGroup(),sell1.getUid());
        sold.setSellStatus(sell.getSellStatus());
        Stone stone = stoneService.findOne(sell.getId()-1);
        stone.setStatus(sell.getStatus());
        sell1.setStatus(sell.getStatus());
        sell1.setSellStatus(sell.getSellStatus());
        return sellRepository.save(sell1);
    }

    @Override
    public Page<Sell> findAllByPage(Pageable pageable) {
        return sellRepository.findAll(pageable);
    }


    @Override
    @Transactional
    public Sell findOne(Long id) {
        return sellRepository.findById(id).get();
    }

    @Override
    public Sell saveSell(Sell sell){
        return  sellRepository.save(sell);
    }

    public Sell saveUidToSell(Sell sell, Long uid, Long sellGroup, User user){
        sell.setUid(uid);
        sell.setSellStatus(0);
        sell.setSellTime(new Date());
        sell.setSellGroup(sellGroup);
        sell.setStatus(9);
        sell.setUsername(user.getName());
        sell.setPhone(user.getPhone());
        sell.setSend(user.getSend());

        return sellRepository.save(sell);
    }
}
