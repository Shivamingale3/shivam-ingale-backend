package com.shivamingale.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document("home")
public class Home {
    @Id
    private String id;

    @Indexed(unique = true)
    private String firstName;

    private String Title;

    private String heading;

    private String subTitle;

    private Short experience;

    private Short projects;
    @Indexed(unique = true)
    private String domain;

}
