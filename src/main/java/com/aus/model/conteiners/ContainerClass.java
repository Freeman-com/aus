package com.aus.model.conteiners;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
public class ContainerClass {

    @Id
    public String asset;
    public String totalBalance;
    public String availableBalance;
    public String tiker;
    public String tikerbalanceinUSDT;

    public ContainerClass() {}
}
