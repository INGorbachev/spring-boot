package com.example.springboot.controller;

import com.example.springboot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.springboot.model.User;
import com.example.springboot.service.UserService;


@Controller
@RequestMapping
public class AdminController {

	private final UserService userService;
	private final RoleService roleService;

	@Autowired
	public AdminController(UserService userService, RoleService roleService) {
		this.userService = userService;
		this.roleService = roleService;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

	@GetMapping("/admin")
	public String getUsers(Model model){
		model.addAttribute("users", userService.listUsers());
		model.addAttribute("roles", roleService.getRoles());
		return "admin";
	}


	@PostMapping(value = "/admin/add")
	public String addUser(@ModelAttribute User user, @RequestParam(value = "role") Long[] rolesId){
		userService.add(user, rolesId);
		return "redirect:/admin";
	}

	@PostMapping(value = "/admin/delete")
	public String deleteUser(@ModelAttribute("id") Long id){

		if (!userService.checkUserById(id)) {
			return "redirect:/admin";
		}

		userService.remove(id);
		return "redirect:/admin";
	}

	@PostMapping(value = "/admin/update")
	public String updateUser(@ModelAttribute("user") User user, @RequestParam(value = "role", required = false) Long[] rolesId){
		userService.update(user, rolesId);
		return "redirect:/admin";
	}

	@GetMapping(value = "/admin/update")
	public String updateUser(@ModelAttribute("id") Long id, Model model){

		if (userService.checkUserById(id)) {
			return "redirect:/admin";
		}

		User user = userService.getUserById(id);

		model.addAttribute("roles", roleService.getRoles());
		model.addAttribute("user",user);
		return "update";
	}
}