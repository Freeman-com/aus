package com.aus.controllers;

import com.aus.model.ICO;
import com.aus.model.XUser;
import com.aus.repository.IcoRepository;
import com.aus.repository.UserRepositoryJPA;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IcoController {

    private static final String MYASSET_BASE = "ico";
    private final UserRepositoryJPA userRepositoryJPA;
    private final IcoRepository icoRepository;

    public IcoController(UserRepositoryJPA userRepositoryJPA, IcoRepository icoRepository) {
        this.userRepositoryJPA = userRepositoryJPA;
        this.icoRepository = icoRepository;
    }

    @GetMapping("/ico")
    public String icoPage(Model model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        XUser xUser = userRepositoryJPA.findByEmail(user.getUsername());

        ICO ico = icoRepository.findByName(user.getUsername());

        model.addAttribute("xUser", xUser);
        model.addAttribute("ico", ico);

        return MYASSET_BASE;
    }
}
