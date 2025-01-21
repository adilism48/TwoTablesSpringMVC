package org.example.twotablesspringmvc.controller.payload;

import java.sql.Date;

public record NewUserPayload(
        String name,
        String email,
        Date dateOfBirth
) {
}
