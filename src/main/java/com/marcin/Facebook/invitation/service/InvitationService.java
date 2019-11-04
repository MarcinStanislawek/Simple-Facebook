package com.marcin.Facebook.invitation.service;

import com.marcin.Facebook.event.notification.NotificationEvent;
import com.marcin.Facebook.invitation.model.Invitation;
import com.marcin.Facebook.invitation.model.InvitationRepository;
import com.marcin.Facebook.invitation.model.InvitationAnswerRequest;
import com.marcin.Facebook.invitation.model.SendInvitationRequest;
import com.marcin.Facebook.user.model.User;
import com.marcin.Facebook.user.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class InvitationService {

    private final UserRepository userRepository;
    private final InvitationRepository invitationRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public InvitationService(UserRepository userRepository, InvitationRepository invitationRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.userRepository = userRepository;
        this.invitationRepository = invitationRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void sendInvitation(SendInvitationRequest sendInvitationRequest) {
        Invitation invitation = new Invitation();
        User invitationSender = userRepository.findById(sendInvitationRequest.getInvitationSenderId()).get();
        User invitationReciever = userRepository.findById(sendInvitationRequest.getInvitationRecieverId()).get();
        invitation.setInvitationSender(invitationSender);
        invitation.setInvitationReciever(invitationReciever);
        invitationRepository.save(invitation);
    }

    public void invitationAnswer(InvitationAnswerRequest request) {
        Invitation invitationToAccept = invitationRepository.findById(request.getInvitationId()).get();
        invitationToAccept.setAccepted(request.getInvitationAnswer());
        if(request.getInvitationAnswer().equals(Boolean.TRUE)) {
            User invitationSender = userRepository.findById(invitationToAccept.getInvitationSender().getId()).get();
            User invitationReciever = userRepository.findById(invitationToAccept.getInvitationReciever().getId()).get();
            invitationSender.getFriends().add(invitationReciever);
            invitationReciever.getFriends().add(invitationSender);
            userRepository.save(invitationReciever);
            userRepository.save(invitationSender);
            acceptInvitationEvent(invitationSender.getId());
        }
    }

    public void acceptInvitationEvent(Integer userId) {
        NotificationEvent event = new NotificationEvent(this, userId);
        applicationEventPublisher.publishEvent(event);
    }
}
