package com.stone.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Long> {

    List<Cart> findByUid(Long uid);

    Cart findCartBySidAndUid(Long sid,Long uid);
}
