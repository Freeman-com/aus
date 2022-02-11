package com.aus.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "compound")
public class Compound implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "users_id")
    private long usersId;

    @Column(name = "compound_active")
    private String compoundActive;

    @Column(name = "name")
    private String name;

    @Column(name = "netcost")
    private double netcost;

    @Column(name = "quantity")
    private double quantity;

    @Column(name = "roi")
    private String roi;

    @Column(name = "interest_rate")
    private double interestrate;

    @Column(name = "tithe")
    private double tithe;

    @Column(name = "tithe_usd")
    private double titheusd;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Compound compound = (Compound) o;
        return Objects.equals(id, compound.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
