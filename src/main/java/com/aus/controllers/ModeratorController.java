package com.aus.controllers;

import com.aus.model.XUser;
import com.aus.repository.UserRepositoryJPA;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ModeratorController {

    private static final String MYASSET_BASE = "moderator";
    private final UserRepositoryJPA userRepositoryJPA;

    public ModeratorController(UserRepositoryJPA userRepositoryJPA) {
        this.userRepositoryJPA = userRepositoryJPA;
    }


    @GetMapping("/moderator")
    public String icoPage(Model model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        XUser xUser = userRepositoryJPA.findByEmail(user.getUsername());


        model.addAttribute("xUser", xUser);

        return MYASSET_BASE;
    }
}
