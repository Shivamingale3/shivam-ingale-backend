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
@Document(collection = "experience")
public class Experience {
    @Id
    private String id;

    private String company;
    private String position;
    private String from;
    private String to;
    private String address;
    private String description;
    private String domain;
}
