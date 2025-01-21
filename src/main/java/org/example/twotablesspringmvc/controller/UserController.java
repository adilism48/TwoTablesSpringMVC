package org.example.twotablesspringmvc.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.twotablesspringmvc.controller.payload.NewPhonePayload;
import org.example.twotablesspringmvc.controller.payload.UpdateUserPayload;
import org.example.twotablesspringmvc.model.User;
import org.example.twotablesspringmvc.service.PhoneService;
import org.example.twotablesspringmvc.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user/{userId:\\d+}")
public class UserController {

    private final UserService userService;
    private final PhoneService phoneService;

    @ModelAttribute("user")
    public User user(@PathVariable("userId") int userId) {
        return this.userService.findUserById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
    }


    @GetMapping
    public String getUserPage(@PathVariable("userId") int userId, Model model) {
        model.addAttribute("phones", phoneService.findAllByUserId(userId));
        return "user";
    }

    @GetMapping("/edit")
    public String getUserEditPage() {
        return "edit_user";
    }

    @PostMapping("/edit")
    public String editUser(@PathVariable("userId") int userId, Model model, @Valid UpdateUserPayload payload, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("payload", payload);
            model.addAttribute("errors", bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .toList());
            return "edit_user";
        } else {
            this.userService.updateUser(userId, payload.name(), payload.email(), payload.dateOfBirth());
            return "redirect:/user/%d".formatted(userId);
        }
    }

    @PostMapping("/delete")
    public String deleteUser(@PathVariable int userId) {
        this.userService.deleteUser(userId);
        return "redirect:/user/list";
    }

    // Phone create

    @GetMapping("/phone/create")
    public String getPhoneCreatePage() {
        return "new_phone";
    }

    @PostMapping("/phone/create")
    public String createPhone(@PathVariable int userId, Model model, @Valid NewPhonePayload payload, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("payload", payload);
            model.addAttribute("errors", bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .toList());
            return "new_phone";
        } else {
            var product = this.phoneService.createPhone(payload.phoneNumber(), payload.user());
            return "redirect:/user/%d/phone/%d".formatted(userId, product.getId());
        }
    }
}
