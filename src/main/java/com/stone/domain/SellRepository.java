package com.stone.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SellRepository extends JpaRepository<Sell,Long> {
    List<Sell> findByStatus(int status);

    Page<Sell> findSellByStatus(int status, Pageable pageable);


    List<Sell> findSellByUid(long id);
}
