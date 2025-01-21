package org.example.twotablesspringmvc.controller.payload;

import org.example.twotablesspringmvc.model.User;

public record NewPhonePayload(
        String phoneNumber,
        User user
) {
}
