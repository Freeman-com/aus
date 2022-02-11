package com.aus.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ico")
@Data
public class ICO {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "personal_allocation")
    private String personal_allocation;

    @Column(name = "status")
    private String status;
    @Column(name = "rated")
    private int rated;

    @Column(name = "type")
    private String type;
}
