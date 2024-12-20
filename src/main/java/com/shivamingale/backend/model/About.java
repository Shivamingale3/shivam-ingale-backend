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
@Document(collection = "about")
public class About {
    @Id
    private String id;
    @Indexed(unique = true)
    private String domain;
    private String title;
    private String heading;
    private String subHeading;
}
