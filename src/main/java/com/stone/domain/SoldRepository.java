package com.stone.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SoldRepository extends JpaRepository<Sold,Long> {
    List<Sold> findAllByUid(Long id);


    Sold findSoldBySellGroupAndUid(Long sellGroup, Long uid);
}
