package org.example.twotablesspringmvc.controller.payload;

import org.example.twotablesspringmvc.model.User;

public record UpdatePhonePayload(
        String phoneNumber,
        User user
) {
}
