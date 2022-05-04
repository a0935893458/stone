package com.stone.service;

import com.stone.domain.Evaluation;
import com.stone.domain.Sell;
import com.stone.domain.Stone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface SellService {
    Sell saveSell(Sell sell, MultipartFile file);

    Page<Sell> findAllByPage(Pageable pageable);

    Sell findOne(Long id);

    Sell saveSell(Sell sell);

}
