package com.example.berychc.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cars")
@Schema(description = "Данные машин")
public class Cars {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "id", nullable = true)
    @Min(0)
    private Integer id;

    @NotNull
    @Size(min = 3, max = 10)
    @Column(name = "brand")
    @Schema(name = "brand", nullable = true)
    private String brand;

    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "series")
    @Schema(name = "series" ,nullable = true)
    private String series;

    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "chassisNumber")
    @Schema(name = "chassisNumber", nullable = true)
    private String chassisNumber;

    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "horsePower")
    @Schema(name = "horsePower", nullable = true)
    @Min(0)
    @Max(2000)
    private Short horsePower;

//    @ManyToOne
//    @JoinColumn(name = "person_id", nullable = false)
//    private Person person;
}
