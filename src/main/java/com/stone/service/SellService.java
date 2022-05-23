package com.stone.service;

import com.stone.domain.Evaluation;
import com.stone.domain.Sell;
import com.stone.domain.Stone;
import com.stone.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SellService {
    Sell saveSell(Sell sell, MultipartFile file);

    Page<Sell> findAllByPage(Pageable pageable);

    Sell findOne(Long id);

    Sell saveSell(Sell sell);

    Sell saveUidToSell(Sell sell, Long sellGroup, User user);

    Page<Sell> findSellBySellStatusNotNull(Pageable pageable);

    Page<Sell> findSellBySellStatusEquals(int sellStatus,Pageable pageable);

    Page<Sell> findSellByStatus(int status, Pageable pageable);

    List<Sell> findSellByUid(long id);
}
