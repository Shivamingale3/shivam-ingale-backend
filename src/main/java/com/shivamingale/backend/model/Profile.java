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
@Document(collection = "profile")
public class Profile {
    @Id
    private String id;

    @Indexed(unique = true)
    private String firstName;

    private String avatarLink;

    private String basedIn;

    private String jobTitle;

    private String instagramLink;

    private String gitHubLink;

    private String linkedInLink;

    private String email;

    private String resumeLink;

    private String hireMeLink;
    @Indexed(unique = true)
    private String domain;
}
