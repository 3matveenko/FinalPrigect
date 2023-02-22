package com.ivan.security.model;


import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="t_orders")
public class Order extends BaseEntity{


    @ManyToOne
    private User username;
    private int sum;
    private Date date;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<ItemMenu> itemsMenu;


}
