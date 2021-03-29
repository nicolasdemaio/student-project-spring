package com.ndemaio.studentprojectspring.professor;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table (name = "professor")
public class Professor {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    @Column (columnDefinition = "boolean default true") // Por defecto es verdadero
    private boolean isActive;

}
