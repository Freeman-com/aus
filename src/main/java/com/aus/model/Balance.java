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
@Table(name = "balance")
public class Balance implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "users_id")
    private long usersId;

    @Column(name = "total_balance")
    private double totalBalance;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Balance balance = (Balance) o;
        return Objects.equals(id, balance.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
