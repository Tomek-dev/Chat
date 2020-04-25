package com.example.chat.service;

import com.example.chat.dao.InviteDao;
import com.example.chat.dao.UserDao;
import com.example.chat.model.Invite;
import com.example.chat.model.User;
import com.example.chat.other.dto.InviteDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InviteService {

    private final ModelMapper mapper = new ModelMapper();

    private InviteDao inviteDao;
    private UserDao userDao;

    @Autowired
    public InviteService(InviteDao inviteDao, UserDao userDao) {
        this.inviteDao = inviteDao;
        this.userDao = userDao;
    }

    public void add(String username, User inviting){
        Optional<User> invited = userDao.findByUsername(username);
        if(invited.isPresent()){
            Invite invite = Invite.builder()
                    .inviting(inviting)
                    .invited(invited.get())
                    .build();
            inviteDao.save(invite);
        }
    }

    public List<InviteDto> findInvites(User invites){
        List<Invite> inviteList = inviteDao.findByInvited(invites);
        return inviteList.stream()
                .map(invite -> mapper.map(invite, InviteDto.class))
                .collect(Collectors.toList());
    }

    public List<InviteDto> findInvitations(User invitations){
        List<Invite> inviteList = inviteDao.findByInviting(invitations);
        return inviteList.stream()
                .map(invite -> mapper.map(invite, InviteDto.class))
                .collect(Collectors.toList());
    }

    public void delete(Long invited, User inviting){
        inviteDao.deleteByInvitedIdAndInviting(invited, inviting);
    }
}
