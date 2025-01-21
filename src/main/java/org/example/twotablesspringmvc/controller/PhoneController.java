package org.example.twotablesspringmvc.controller;

import lombok.RequiredArgsConstructor;
import org.example.twotablesspringmvc.controller.payload.UpdatePhonePayload;
import org.example.twotablesspringmvc.model.Phone;
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
@RequestMapping("/user/{userId:\\d+}/phone/{phoneId:\\d+}")
public class PhoneController {

    private final PhoneService phoneService;
    private final UserService userService;


    @ModelAttribute("user")
    public User user(@PathVariable("userId") int userId) {
        return this.userService.findUserById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    @ModelAttribute("phone")
    public Phone phone(@PathVariable("phoneId") int phoneId) {
        return this.phoneService.findPhoneById(phoneId)
                .orElseThrow(() -> new NoSuchElementException("Phone not found"));
    }

    @GetMapping
    public String getPhonePage() {
        return "phone";
    }

    @GetMapping("/edit")
    public String getPhoneEditPage() {
        return "edit_phone";
    }

    @PostMapping("/edit")
    public String editPhone(@PathVariable("phoneId") int phoneId, @PathVariable("userId") int userId,
                            Model model, UpdatePhonePayload payload, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("payload", payload);
            model.addAttribute("errors", bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .toList());
            return "edit_phone";
        } else {
            this.phoneService.updatePhone(phoneId, payload.phoneNumber(), payload.user());
            return "redirect:/user/%d/phone/%d".formatted(userId ,phoneId);
        }
    }

    @PostMapping("/delete")
    public String deletePhone(@PathVariable int phoneId, @PathVariable("userId") int userId) {
        this.phoneService.deletePhone(phoneId);
        return "redirect:/user/%d".formatted(userId);
    }
}
