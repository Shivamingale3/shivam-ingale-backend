package com.shivamingale.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {

    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;

    @Email(message = "Invalid email format")
    private String email;

    @Pattern(
            regexp = "^\\+?[1-9]\\d{1,14}$",
            message = "Invalid mobile number format. Must be in international format (e.g., +919876543210)"
    )
    private String mobile;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 50, message = "Password must be between 8 and 50 characters")
    private String password;

/*
    @NotBlank(message = "Role is required")
    @Pattern(
            regexp = "SUPER_ADMIN|RESELLER_OWNER|RESELLER_ADMIN|ORGANIZATION_OWNER|ORGANIZATION_ADMIN|ORGANIZATION_USER",
            message = "Invalid role. Allowed roles: SUPER_ADMIN, RESELLER_OWNER, RESELLER_ADMIN, ORGANIZATION_OWNER, ORGANIZATION_ADMIN, ORGANIZATION_USER"
    )
    private String role;
*/

}
