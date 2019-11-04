package com.marcin.Facebook.invitation.controller;

import com.marcin.Facebook.invitation.model.InvitationAnswerRequest;
import com.marcin.Facebook.invitation.model.SendInvitationRequest;
import com.marcin.Facebook.invitation.service.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invite")
public class InvitationController {

    private final InvitationService invitationService;

    @Autowired
    public InvitationController(InvitationService invitationService) {
        this.invitationService = invitationService;
    }

    @PostMapping
    public void sendInvitation(@RequestBody SendInvitationRequest request){
        invitationService.sendInvitation(request);
    }

    @PostMapping("/answer")
    public void invitationAnswer(@RequestBody InvitationAnswerRequest request) {
        invitationService.invitationAnswer(request);
    }
}
