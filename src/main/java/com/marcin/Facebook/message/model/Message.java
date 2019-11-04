package com.marcin.Facebook.message.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marcin.Facebook.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private String content;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User author;

    @Column
    private ReactionType reaction;

    @Column
    private Integer userReactionId;

    @Column
    private Boolean isActive;
}
