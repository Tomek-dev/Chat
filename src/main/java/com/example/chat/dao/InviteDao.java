package com.example.chat.dao;

import com.example.chat.model.Invite;
import com.example.chat.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InviteDao extends JpaRepository<Invite, Long> {
    void deleteByInvitedIdAndInviting(Long invited, User inviting);
    List<Invite> findByInvited(User invites);
    List<Invite> findByInviting(User invitations);
}
