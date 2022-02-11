package com.aus.controllers;

import com.aus.model.XUser;

import com.aus.repository.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Controller
public class MyassetController {

    private static final String MYASSET_BASE = "myasset";
    private final UserRepositoryJPA userRepositoryJPA;
    private final LeftTableRepository leftTableRepository;
    private final BinanceRepository binanceRepository;
    private final CompoundRepository compoundRepository;
    private final BalanceRepository balanceRepository;

    public MyassetController(UserRepositoryJPA userRepositoryJPA,
                             LeftTableRepository leftTableRepository, BinanceRepository binanceRepository,
                             CompoundRepository compoundRepository, BalanceRepository balanceRepository) {
        this.userRepositoryJPA = userRepositoryJPA;
        this.leftTableRepository = leftTableRepository;
        this.binanceRepository = binanceRepository;
        this.compoundRepository = compoundRepository;
        this.balanceRepository = balanceRepository;
    }

    @GetMapping("/myasset")
    public String mainPage(Model model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        XUser xUser = userRepositoryJPA.findByEmail(user.getUsername());


        List<String> list = Arrays.asList("Ticker", "Market Price", "Quantity", "NetCost", "Total");
        List<Map<String, Object>> columns = new ArrayList<>();

        var erc20List = leftTableRepository.findByUsersId(xUser.getId());
        var binanceList = binanceRepository.findByUsersId(xUser.getId());

        for (var zxc : erc20List) {

            columns.add(Map.of("Ticker", zxc.getTicker(), "Market Price", zxc.getMarketprice(), "Quantity",
                    zxc.getQuantity(), "NetCost", zxc.getNetcost(), "Total", zxc.getQuantity()));
        }

        List<String> listCompound = Arrays.asList("Name", "NetCost (usd)", "Quantity", "Interest Rate", "Tithe", "Tithe(usd)", "ROI");
        List<Map<String, Object>> columnsCompound = new ArrayList<>();

        var compoundList = compoundRepository.findByCompoundActive("active");


        for (var x : compoundList) {
            columnsCompound.add(Map.of("Name", x.getName(), "NetCost (usd)", x.getNetcost(), "Quantity", x.getQuantity(),
                    "Interest Rate", x.getInterestrate(), "Tithe", x.getTithe(), "Tithe(usd)", x.getTitheusd(),
                    "ROI", x.getRoi()));
        }


        var accBalance = balanceRepository.findByTotalBalance(xUser.getId());
        String i = accBalance.toString();


        model.addAttribute("xUser", xUser);
        model.addAttribute("list", list);
        model.addAttribute("columns", columns);
        model.addAttribute("listCompound", listCompound);
        model.addAttribute("columnsCompound", columnsCompound);
        model.addAttribute("i", i);
        return MYASSET_BASE;
    }
}