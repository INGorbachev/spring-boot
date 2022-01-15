package com.example.springboot.controller;

import com.example.springboot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.springboot.model.User;
import com.example.springboot.service.UserService;

import java.util.ArrayList;
import java.util.stream.Collectors;


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

	@GetMapping("/login")
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
//
	public String addUser(@ModelAttribute("user") @Validated User user, BindingResult bindingResult,
						  @RequestParam("role") ArrayList<Long> role) {
		if (bindingResult.hasErrors()) {
			return "/admin";
		}
		user.setRoles(role.stream().map(roleService::getRoleById).collect(Collectors.toSet()));
		userService.add(user);
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
	public String updateUser(@ModelAttribute("user") @Validated User user, BindingResult bindingResult,
						 @RequestParam("role") ArrayList<Long> role) {
		if (bindingResult.hasErrors()) {
			return "/admin";
		}
		user.setRoles(role.stream().map(roleService::getRoleById).collect(Collectors.toSet()));
		userService.update(user);

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