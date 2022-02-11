package com.aus.controllers;

import com.aus.model.XUser;
import com.aus.repository.BinanceRepository;
import com.aus.repository.BitMaxRepository;
import com.aus.repository.LeftTableRepository;
import com.aus.repository.UserRepositoryJPA;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class MyAccountsController {

    private static final String MYASSET_BASE = "myaccounts";
    private final BitMaxRepository bitMaxRepository;
    private final UserRepositoryJPA userRepositoryJPA;
    private final LeftTableRepository leftTableRepository;
    private final BinanceRepository binanceRepository;

    public MyAccountsController(BitMaxRepository bitMaxRepository, UserRepositoryJPA userRepositoryJPA,
                                LeftTableRepository leftTableRepository, BinanceRepository binanceRepository) {
        this.bitMaxRepository = bitMaxRepository;
        this.userRepositoryJPA = userRepositoryJPA;
        this.leftTableRepository = leftTableRepository;
        this.binanceRepository = binanceRepository;
    }

    @GetMapping("/myaccounts")
    public String mainPage(Model model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        XUser xUser = userRepositoryJPA.findByEmail(user.getUsername());


        List<String> headers = Arrays.asList("Exchange", "Email exchange", "Country", "User Data", "Status", "Balance");
        List<Map<String, Object>> rows = new ArrayList<>();

        var bitmaxList = bitMaxRepository.findByUsersId(xUser.getId());
        var binanceList = binanceRepository.findByUsersId(xUser.getId());


        for (var bitmax : bitmaxList) {
            //API call
            rows.add(Map.of("Exchange", "BitMax", "Email exchange", bitmax.getEmail(), "Country", "Russia",
                    "User Data", bitmax.getEmail(), "Status", "active", "Balance", bitmax.getMarketvalue()));
        }
        for (var binance : binanceList) {
            //API call
            rows.add(Map.of("Exchange", "Binance", "Email exchange", binance.getBinance_email(), "Country",
                    "Russia",
                    "User Data", binance.getBinance_email(), "Status", "active", "Balance",
                    "3245"));
        }


        List<String> headers2 = Arrays.asList("Tiker", "Name", "PublicKey", "Balance ETH", "NetCost USD", "Profit USD",
                "LockUp");
        List<Map<String, Object>> rows2 = new ArrayList<>();

        var erc20List = leftTableRepository.findByUsersId(xUser.getId());

        for (var erc : erc20List) {
            //API call
            rows2.add(Map.of("Tiker", "ETH", "Name", erc.getQuantity(), "PublicKey", erc.getQuantity(),
                    "Balance ETH", erc.getQuantity(),
                    "NetCost USD", erc.getQuantity(), "Profit USD", "0", "LockUp", "0"));
        }

        model.addAttribute("xUser", xUser);
        model.addAttribute("headers", headers);
        model.addAttribute("headers2", headers2);
        model.addAttribute("rows", rows);
        model.addAttribute("rows2", rows2);


        return MYASSET_BASE;
    }
}
