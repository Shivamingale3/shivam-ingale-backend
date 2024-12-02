package com.shivamingale.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "skills")
public class Skills {
    @Id
    private String id;
    private String name;
    private String iconLink;
    private Short score;
    private String domain;
}
