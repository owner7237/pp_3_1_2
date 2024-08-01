package com.example.pp_3_1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.pp_3_1.model.User;
import com.example.pp_3_1.service.UserService;

@Controller
@RequestMapping(value = "/")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public String listUsers(ModelMap modelMap) {
        modelMap.addAttribute("userlist", userService.listUsers());
        return "users";
    }

    @GetMapping(value = "/delete")
    public String deleteUser(@RequestParam("id") long id, ModelMap modelMap) {
        userService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping(value = "/add")
    public String addUser(@RequestParam(name = "firstname") String firstname,
                          @RequestParam(name = "lastname") String lastname,
                          @RequestParam(name = "age") byte age,
                          ModelMap modelMap) {
        User user = new User(firstname, lastname, age);
        userService.save(user);
        System.out.println(user);
        return "redirect:/";
    }

    @GetMapping(value = "/edit")
    public String editUserPage(@RequestParam("id") long id, ModelMap modelMap) {
        modelMap.addAttribute("user", userService.getUser(id));
        return "users-edit";
    }

    @GetMapping(value = "/edit/submit")
    public String editUser(@RequestParam("id") long id,
                           @RequestParam(name = "firstname") String firstname,
                           @RequestParam(name = "lastname") String lastname,
                           @RequestParam(name = "age") byte age,
                           ModelMap modelMap) {
        User user = userService.getUser(id);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setAge(age);
        userService.update(user);
        return "redirect:/";
    }

    @GetMapping(value = "/clear")
    public String clear(ModelMap modelMap) {
        userService.clearTable();
        return "redirect:/";
    }
}
