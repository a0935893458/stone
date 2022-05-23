package com.stone.service;

import com.stone.domain.Sell;
import com.stone.domain.Stone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface StoneService {
    Stone saveStone(Stone stone);

    public Stone postStone(Stone stone,Stone stone1);

    public Stone saveSellToStone(Stone stone, Sell sell);

    Page<Stone> findAllByPage(Pageable pageable);

    Stone getStone(Long id);

    void deleteStone(Long id);

    List<Stone> listAll();

    Stone findOne(Long id);
}
