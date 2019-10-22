package com.marcin.Facebook.model;

import com.marcin.Facebook.model.ENUM.Sex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column()
    private Sex sex;

    @Column
    private String password;

    @Column
    @Email
    private String email;
}
