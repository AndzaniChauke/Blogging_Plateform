package com.example.Blogging.Plateform2.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Blogger {


    @SequenceGenerator(
            name = "driver_sequence",
            sequenceName = "driver_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "driver_sequence"
    )
    private Long id;

    @Lob
    private String profilePicture;

    private String bio;

    private String about;

    private String blogType;

    private Boolean submitted;

    public Blogger( Boolean submitted) {



        this.submitted = submitted;
    }
}
