package com.ndemaio.studentprojectspring.student;

import com.ndemaio.studentprojectspring.enums.Gender;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table (name = "student")
public class Student {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY) // Genera el ID de forma autoincremental
    private Long id;

    private String firstName;
    private String lastName;

    @Column (nullable = false)
    private String email;

    @Enumerated (EnumType.STRING) // Crea una entidad Gender por nombre
    private Gender gender;

    private LocalDate birthDate;

    public int calculateAge() {

        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }
}
