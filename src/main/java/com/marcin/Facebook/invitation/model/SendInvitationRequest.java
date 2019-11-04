package com.marcin.Facebook.invitation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendInvitationRequest {

    @NotNull
    private Integer invitationSenderId;

    @NotNull
    private Integer invitationRecieverId;
}
