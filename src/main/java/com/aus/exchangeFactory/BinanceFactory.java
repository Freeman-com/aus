package com.aus.exchangeFactory;

import com.aus.repository.BinanceRepository;
import com.aus.repository.UserRepositoryJPA;
import com.binance.connector.client.impl.SpotClientImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class BinanceFactory {

    private final BinanceRepository binanceRepository;
    private final UserRepositoryJPA userRepository;

    @Autowired
    public BinanceFactory(BinanceRepository binanceRepository, UserRepositoryJPA userRepository) {
        this.binanceRepository = binanceRepository;
        this.userRepository = userRepository;
    }

    List<String> list = new ArrayList<>();

    public String senderMethod(long userId) {

        var keyList2 = binanceRepository.findByUsersId(userId);
        /* Account Snapshot - Binance */
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("type", "SPOT");

        for (var s : keyList2) {
            SpotClientImpl client = new SpotClientImpl(s.getPublic_key(), s.getSecret());
            String result = client.createWallet().accountSnapshot(parameters);
            list.add(result);
        }

        return String.valueOf(list);
    }
}
