package org.example.twotablesspringmvc.controller;

import lombok.RequiredArgsConstructor;
import org.example.twotablesspringmvc.controller.payload.NewUserPayload;
import org.example.twotablesspringmvc.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserListController {

    private final UserService userService;

    @GetMapping("/list")
    public String getUsersListPage(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "user_list";
    }

    @GetMapping("/create")
    public String getNewUserPage() {
        return "new_user";
    }

    @PostMapping("/create")
    public String createUser(Model model, NewUserPayload payload, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("payload", payload);
            model.addAttribute("errors", bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .toList());
            return "new_user";
        } else {
            var product = this.userService.createUser(payload.name(), payload.email(), payload.dateOfBirth());
            return "redirect:/user/%d".formatted(product.getId());
        }
    }
}
