package com.aus.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "lefttable")
public class LeftTable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "users_id")
    private long usersId;


    @Column(name = "ticker")
    private String ticker;

    @Column(name = "marketprice")
    private BigDecimal marketprice;

    @Column(name = "quantity")
    private BigDecimal quantity;

    @Column(name = "netcost")
    private BigDecimal netcost;

    @Column(name = "total")
    private BigDecimal total;
}
