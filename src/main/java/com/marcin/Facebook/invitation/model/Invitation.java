package com.marcin.Facebook.invitation.model;

import com.marcin.Facebook.user.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Invitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private User invitationSender;

    @OneToOne
    private User invitationReciever;

    @Column
    private boolean isAccepted;

}
