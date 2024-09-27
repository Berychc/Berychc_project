package com.example.berychc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "person")
@Schema(description = "Данные о персоне")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "id", nullable = true)
    @Min(0)
    private Integer id;

    @NotNull
    @Email
    @Column(name = "username")
    @Schema(name = "username", nullable = true)
    private String username;

    @NotNull
    @Column(name = "password")
    @Schema(name = "password", description = " ", nullable = true)
    private String password;

    @NotNull
    @Column(name = "fullName")
    @Schema(name = "fullName", nullable = true)
    private String fullName;

    @NotNull
    @Size(min = 10, max = 11)
    @Column(name = "phoneNumber")
    @Schema(name = "phoneNumber", description = "+7", nullable = true)
    private String phoneNumber;

    @JsonIgnore // !
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Cars> cars = new ArrayList<>();
}
