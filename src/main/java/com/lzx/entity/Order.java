package com.lzx.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tbl_order")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_number")
    private String orderNumber;
}
