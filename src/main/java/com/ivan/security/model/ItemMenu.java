package com.ivan.security.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="t_items_menu")
public class ItemMenu extends BaseEntity{

    @Column(name = "item_menu_name")
    private String itemMenuName;
    private int price;

}
