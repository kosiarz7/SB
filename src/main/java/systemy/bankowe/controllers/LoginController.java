package systemy.bankowe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import systemy.bankowe.security.SpringSecurityContextUtil;

/**
 * Kontroler odpowiedzialny za logowanie do systemu.
 * 
 * @author Adam Kopaczewski
 *
 *         Copyright © 2015 Adam Kopaczewski
 */
@Controller
public class LoginController {
    @Autowired
    private SpringSecurityContextUtil springSecurityUtil;
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(final Model model) {
        springSecurityUtil.logoutCurrentUser();
        return "login";
    }
}
