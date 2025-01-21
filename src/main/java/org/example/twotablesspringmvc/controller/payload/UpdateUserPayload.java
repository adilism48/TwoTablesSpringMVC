package org.example.twotablesspringmvc.controller.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UpdateUserPayload(
        @NotBlank(message = "Name is required")
        String name,
        @NotBlank(message = "Email is required")
        @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "Invalid email, must be format: test@example.com")
        String email,
        @Pattern(regexp = "^(20[0-9]{2}|19[0-9]{2})-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$", message = "Must be formatted YYYY-MM-DD")
        @NotBlank(message = "Date of birth is required")
        String dateOfBirth
) {
}
