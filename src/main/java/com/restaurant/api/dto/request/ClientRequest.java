package com.restaurant.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientRequest {
    @NotBlank(message = "First name is required")
    @Size(min = 3, max = 50, message = "First name must be between 3 and 50 characters")
    @Pattern(
            regexp = "^[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ]+$",
            message = "First name must contain only letters"
    )
    private String name;

    @NotBlank(message = "Last name is required")
    @Size(min = 3, max = 50, message = "Last name must be between 3 and 50 characters")
    @Pattern(
            regexp = "^[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ]+$",
            message = "Last name must contain only letters"
    )
    private String lastName;

    @NotBlank(message = "Phone number is required")
    @Size(min = 9, max = 15, message = "Phone number must be between 9 and 15 digits long")
    @Pattern(
            regexp = "^[0-9]+$",
            message = "Phone number must contain only digits"
    )
    private String phoneNumber;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    private String defaultDeliveryAddress;
}
