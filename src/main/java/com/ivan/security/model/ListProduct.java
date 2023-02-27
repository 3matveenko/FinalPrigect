package com.ivan.security.model;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="List_products")
public class ListProduct extends BaseEntity{

     @ManyToOne
     private User nameUser;
     @ManyToMany(fetch = FetchType.EAGER)
     private List<Product> products;
     private int sum;
     private Date date;
}
