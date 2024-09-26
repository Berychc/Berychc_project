package com.example.berychc.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Cars {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "id", nullable = true)
    @Min(0)
    private Integer id;

    @Column(name = "brand")
    @Schema(name = "brand", nullable = true)
    private String brand;

    @Column(name = "series")
    @Schema(name = "series" ,nullable = true)
    private String series;

    @Column(name = "chassisNumber")
    @Schema(name = "chassisNumber", nullable = true)
    private String chassisNumber;

    @Column(name = "horsePower")
    @Schema(name = "horsePower", nullable = true)
    @Min(0)
    @Max(2000)
    private Short horsePower;
}
