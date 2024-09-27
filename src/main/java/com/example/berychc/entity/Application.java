package com.example.berychc.entity;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class Application {

    private Integer id;
    private String name;
    private String author;
    private String version;
}
