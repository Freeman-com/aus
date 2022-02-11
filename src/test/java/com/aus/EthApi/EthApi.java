package com.aus.EthApi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class EthApi {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        Logger log = LoggerFactory.getLogger(EthApi.class);

        Web3j client = Web3j.build(new HttpService("https://mainnet.infura.io/v3/cd601df89a60461f9d744777d13769a5"));

        final String ethAddress = "0xb69749D19517b0255985C1a6eCDD05a6307dC30E";
        final EthGetBalance balanceResponse = client
                .ethGetBalance(ethAddress, DefaultBlockParameter.valueOf("latest")).sendAsync()
                .get(10, TimeUnit.SECONDS);

        final BigInteger unscaledBalance = balanceResponse.getBalance();

        log.info("Some object: {}", unscaledBalance);
    }
}
