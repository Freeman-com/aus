package com.aus.counterBalanses;

import com.aus.exchangeFactory.AscendexFactory;
import com.aus.exchangeFactory.BinanceFactory;

import java.util.Map;

public class MainCounter {

    private final BinanceFactory binanceFactory;
    private final AscendexFactory ascendexFactory;

    public MainCounter(BinanceFactory binanceFactory, AscendexFactory ascendexFactory) {
        this.binanceFactory = binanceFactory;
        this.ascendexFactory = ascendexFactory;
    }

    /*------------------------------------------------------------------------------------*/
    /*AUS API Methods*/

    public String accountSnapshot (long userId) {
        binanceFactory.senderMethod(userId);


        return null;
    }

    /*AUS API Methods*/
    /*------------------------------------------------------------------------------------*/


}
