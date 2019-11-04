package com.marcin.Facebook.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marcin.Facebook.notification.Notification;
import com.marcin.Facebook.user.model.Sex;
import com.marcin.Facebook.message.model.Message;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column
    private Sex sex;

    @Column
    private String password;

    @Column(unique = true)
    @Email
    private String email;

    @OneToMany(mappedBy = "author")
    private List<Message> messages;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "friends",
        joinColumns = {@JoinColumn(name = "friends")})
    private List<User> friends;

    @Column
    private List<Notification> notifications;
}