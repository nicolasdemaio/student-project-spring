package com.ndemaio.studentprojectspring.professor;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "professor")
public class Professor {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @Temporal (TemporalType.DATE)
    private Date birthDate;

    @Column (columnDefinition = "boolean default true") // Por defecto es verdadero
    private boolean isActive;

}
