package com.github.gdiazs.sqlinjection.controllers;

import com.github.gdiazs.sqlinjection.dao.UserDao;
import com.github.gdiazs.sqlinjection.models.UserModel;
import com.github.gdiazs.sqlinjection.models.UserSessionModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
@RequestMapping("/")
public class LoginController {

    private final UserDao userDao;

    public LoginController(UserDao userDao) {
        this.userDao = userDao;
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserModel userModel,
                        HttpServletRequest request,
                        RedirectAttributes redirectAttr) {

        if (this.userDao.userExists(userModel.getUsername(), userModel.getPassword())) {

            var session = request.getSession(true);
            var userSession = new UserSessionModel(userModel.getUsername(), new HashMap<>());
            session.setAttribute("userSession", userSession);

            return "redirect:/dashboard/";
        }

        redirectAttr.addFlashAttribute("error", "Usuario o contraseña inválidos");
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession(true).invalidate();
        return "redirect:/";
    }
}
