package org.example.twotablesspringmvc.controller.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.example.twotablesspringmvc.model.User;

public record UpdatePhonePayload(
        @Pattern(regexp = "^0[357]\\d{8}$", message = "Invalid number, example: 0555998877")
        @NotBlank(message = "Phone Number is required")
        String phoneNumber,
        User user
) {
}
