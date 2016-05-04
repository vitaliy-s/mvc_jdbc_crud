package control;

import dao.UserDAO;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by vitaliy on 04.05.16.
 */
@Controller
public class MyControl {

    @Autowired
    UserDAO userDAO;

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "alluser", method = RequestMethod.GET)
    public ModelAndView allUser(ModelAndView modelAndView) {
        List<User> userList = userDAO.allUser();
        modelAndView.addObject("listuser", userList);
        modelAndView.setViewName("alluser");
        return modelAndView;
    }

    @RequestMapping(value = "newUser", method = RequestMethod.GET)
    public ModelAndView newUser(ModelAndView modelAndView) {
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("userForm");
        return modelAndView;
    }

    @RequestMapping(value = "saveUser", method = RequestMethod.POST)
    public ModelAndView saveUser(@ModelAttribute User user){
        userDAO.saveOrUpdate(user);
        return new ModelAndView("redirect:alluser");
    }

    @RequestMapping(value = "deleteUser", method = RequestMethod.GET)
    public ModelAndView deleteUser(HttpServletRequest request){
        int userId = Integer.parseInt(request.getParameter("id"));
        userDAO.delete(userId);
        return new ModelAndView("redirect:alluser");
    }

    @RequestMapping(value = "editUser", method = RequestMethod.GET)
    public ModelAndView editUser(HttpServletRequest request){
        int userId = Integer.parseInt(request.getParameter("id"));
        User user = userDAO.get(userId);
        ModelAndView modelAndView = new ModelAndView("userForm");
        modelAndView.addObject("user", user);
        return modelAndView;
    }
}
