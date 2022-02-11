package com.aus.controllers;

import com.aus.connections.ExchangeForm;
import com.aus.model.XUser;
import com.aus.model.exchangeEntity.BinanceAccount;
import com.aus.model.exchangeEntity.BitMaxAccount;
import com.aus.repository.BinanceRepository;
import com.aus.repository.BitMaxRepository;
import com.aus.repository.UserRepositoryJPA;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AddApiController {

    private static final String ADDAPI_PAGE = "addapi";

    private final BitMaxRepository bitMaxRepository;
    private final BinanceRepository binanceRepository;
    private final UserRepositoryJPA userRepositoryJPA;


    public AddApiController(BitMaxRepository bitMaxRepository,
                            BinanceRepository binanceRepository, UserRepositoryJPA userRepositoryJPA) {

        this.bitMaxRepository = bitMaxRepository;
        this.binanceRepository = binanceRepository;
        this.userRepositoryJPA = userRepositoryJPA;
    }

    static List<String> list = null;

    static {
        list = new ArrayList<>();
        list.add("ERC-20");
        list.add("BitMax");
        list.add("Binance");
        list.add("CoinbasePro");
        list.add("Kukoin");
        list.add("Huobi");
    }

    /**
     * отрисовать таблицы на HTML-странице, а в POST-методе сделать проверку на уникальность значений,
     * уникальность акаунтов, если регистрируемый акаунт УЖЕ существует, выбргосить на экран ИСКЛЮЧЕНИЕ
     * + подсветить/выделить цветом этот акаунт в таблице. Если таблица будет с вкладками или очень большая,
     * подсвечивать нецелесообразно.
     *
     * @author Anthony Kozinau
     */
    @GetMapping("/addapipage")
    public String toAddApiPage(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        XUser xUser = userRepositoryJPA.findByEmail(user.getUsername());
        BitMaxAccount bitMaxAccount = bitMaxRepository.findByEmail(xUser.getEmail());

        model.addAttribute("bitMaxAccount", bitMaxAccount);
        model.addAttribute("list", list);
        model.addAttribute("xUser", xUser);
        return ADDAPI_PAGE;
    }

    @PostMapping("/doaddapi")
    public String addApiToDataBaseBitMax(@ModelAttribute("exchangeForm") ExchangeForm exchangeForm,
                                         HttpServletRequest request, BitMaxAccount bitMaxAccount,
                                         BinanceAccount binanceAccount) {
        String exch = request.getParameter("des");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String apikey = request.getParameter("apikey");
        String secret = request.getParameter("secret");
        return "redirect:/addapi";
    }

    @PostMapping("/addexchange")
    public String addExchange(HttpServletRequest request, BinanceAccount binanceAccount) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        XUser xUser = userRepositoryJPA.findByEmail(user.getUsername());

        String publicKey = request.getParameter("public-binance");
        String secretKey = request.getParameter("secret-binance");
        String binanceEmail = request.getParameter("email-binance");


        binanceAccount.setUsersId(xUser.getId());
        binanceAccount.setBinance_email(binanceEmail);
        binanceAccount.setPublic_key(publicKey);
        binanceAccount.setSecret(secretKey);


        return "redirect:/myaccounts";
    }
}
