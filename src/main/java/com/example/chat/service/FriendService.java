package com.example.chat.service;

import com.example.chat.dao.InviteDao;
import com.example.chat.dao.UserDao;
import com.example.chat.model.Invite;
import com.example.chat.model.User;
import com.example.chat.other.dto.FriendDto;
import com.example.chat.other.exceptions.InviteNotFoundException;
import com.example.chat.other.exceptions.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FriendService {

    private final ModelMapper mapper = new ModelMapper();

    private UserDao userDao;
    private InviteDao inviteDao;

    @Autowired
    public FriendService(UserDao userDao, InviteDao inviteDao) {
        this.userDao = userDao;
        this.inviteDao = inviteDao;
    }

    public List<FriendDto> findFriends(User user){
        return user.getFriends().stream()
                .map(friend -> mapper.map(friend, FriendDto.class))
                .collect(Collectors.toList());
    }

    public void removeFriend(Long id, User user){
        Optional<User> friendOptional = userDao.findById(id);
        User friend = friendOptional.orElseThrow(UserNotFoundException::new);
        user.removeFriend(friend);
        userDao.save(user);
    }

    public void addFriend(Long id, User user){
        Optional<Invite> inviteOptional = inviteDao.findByInvitingIdAndInvited(id, user);
        Invite invite = inviteOptional.orElseThrow(InviteNotFoundException::new);
        user.addFriend(invite.getInviting());
        userDao.save(user);
    }
}
