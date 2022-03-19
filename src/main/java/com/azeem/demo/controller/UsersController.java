package com.azeem.demo.controller;

import com.azeem.demo.entity.Roles;
import com.azeem.demo.entity.Users;
import com.azeem.demo.repository.MyUserDetails;
import com.azeem.demo.services.RoleService;
import com.azeem.demo.services.UsersServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/api/users")
public class UsersController {
    private UsersServiceInterface usersService;
    private RoleService roleService;

    @Autowired
    public UsersController(UsersServiceInterface usersService, RoleService roleService){

        this.roleService = roleService;
        this.usersService = usersService;
    }

    @GetMapping("/list")
    public String listUsers(Model model){
        List<Users> users = usersService.listUsers();

        model.addAttribute("user", users);

        return "list-users";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){
        Users user = new Users();

        model.addAttribute("user", user);

        return "user-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("user") Users users){
        Roles userRole = roleService.getRoleById(2);
        users.addRole(userRole);

        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        users.setPassword(bcrypt.encode(users.getPassword()));

        usersService.saveUser(users);

        return "redirect:/api/users/list";
    }

    @PostMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("userId") int theId,
                                    Model theModel) {

        Users theUser = usersService.getUserById(theId);

        theModel.addAttribute("user", theUser);

        return "user-form";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("userId") int theId) {
        usersService.deleteUser(theId);

        return "redirect:/api/users/list";

    }
}
