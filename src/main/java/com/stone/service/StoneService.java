package com.stone.service;

import com.stone.domain.Stone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StoneService {
    Stone saveStone(Stone stone);



    Page<Stone> findAllByPage(Pageable pageable);

    Stone getStone(Long id);

    void deleteStone(Long id);

    List<Stone> listAll();

    Stone findOne(Long id);
}
