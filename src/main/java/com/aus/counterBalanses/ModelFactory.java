package com.aus.counterBalanses;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ModelFactory {

    List<String> ticker;
    List<BigDecimal> marketPrice;
    List<BigDecimal> quantity;
    List<BigDecimal> netcost;
    List<BigDecimal> total;

}
