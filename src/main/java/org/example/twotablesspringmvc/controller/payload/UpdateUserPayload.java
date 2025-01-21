package org.example.twotablesspringmvc.controller.payload;

import java.sql.Date;

public record UpdateUserPayload(
        String name,
        String email,
        Date dateOfBirth
) {
}
