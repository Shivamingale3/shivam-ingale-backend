package com.shivamingale.backend.dto;


import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HomeDto {
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
    @Indexed(unique = true)
    private String firstName;

    @Size(min = 20, max = 200, message = "Title must be between 20 and 200 characters")
    private String Title;

    @Size(min = 20, max = 200, message = "Title must be between 20 and 200 characters")
    private String heading;

    @Size(min = 20, max = 200, message = "Title must be between 20 and 200 characters")
    private String subTitle;

    @Size(min = 0, max = 50)
    private Short experience;

    @Size(min = 0, max = 50)
    private Short projects;

}
