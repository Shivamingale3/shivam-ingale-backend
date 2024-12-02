package com.shivamingale.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {

    @NotBlank(message = "Email cannot be empty")
    private String email;

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
