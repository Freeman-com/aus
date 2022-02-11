package com.aus.connections;

import lombok.Data;

@Data
public class ExchangeForm {
    private String id;
    private String name;
    private String des;

    public ExchangeForm(String id, String name, String des) {
        this.id = id;
        this.name = name;
        this.des = des;
    }

    public ExchangeForm(){super();}


}
