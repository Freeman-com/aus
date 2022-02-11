package com.aus.model.exchangeEntity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "bitmaxaccount")
@Data
public class BitMaxAccount implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "apikey")
    private String apikey;
    @Column(name = "secret")
    private String secret;

    @Column(name = "bitmaxemail")
    private String email;

    @Column(name = "password")
    private String password;
    @Column(name = "exptime")
    private Integer exptime;
    @Column(name = "marketvalue")
    private BigDecimal marketvalue;
    @Column(name = "netcost")
    private BigDecimal netcost;
    @Column(name = "alltimeprofit")
    private BigDecimal alltimeprofit;

    @Column(name = "users_id")
    private long usersId;

}
