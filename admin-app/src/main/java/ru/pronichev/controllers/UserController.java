package ru.pronichev.controllers;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.pronichev.dto.UserDto;
import ru.pronichev.dto.params.UserListParams;
import ru.pronichev.services.RoleService;
import ru.pronichev.services.UserService;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @Value("roles")
    private String rolesAttr;

    @Value("user_form")
    private String userForm;

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("activePage", "User");
    }

    @GetMapping
    public String listPage(Model model,
                           UserListParams userListParams) {

        model.addAttribute("users",
            userService.findWithFilter(userListParams)
                .map(UserDto::toDto)
        );
        return "users";
    }

    @GetMapping("/new")
    public String newUserForm(Model model) {

        model.addAttribute("user", UserDto.empty());
        model.addAttribute(rolesAttr, roleService.findAllDto());
        return userForm;
    }

    @GetMapping("/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {

        model.addAttribute("user", UserDto.toDto(userService.findById(id)));
        model.addAttribute(rolesAttr, roleService.findAllDto());
        return userForm;
    }

    @PostMapping
    public String update(@Valid @ModelAttribute("user") UserDto user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute(rolesAttr, roleService.findAllDto());
            return userForm;
        }
        if (!user.getPassword().equals(user.getRepeatPassword())) {
            model.addAttribute(rolesAttr, roleService.findAllDto());
            result.rejectValue("password", "", "Repeated password is not correct");
            return userForm;
        }
        userService.save(user);
        return "redirect:/user";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/user";
    }
}