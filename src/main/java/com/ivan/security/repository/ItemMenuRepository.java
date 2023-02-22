package com.ivan.security.repository;

import com.ivan.security.model.ItemMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface ItemMenuRepository extends JpaRepository<ItemMenu, Long> {

    ItemMenu findByItemMenuName(String name);


}
