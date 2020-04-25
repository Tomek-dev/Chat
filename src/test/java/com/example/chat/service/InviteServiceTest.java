package com.example.chat.service;

import com.example.chat.dao.InviteDao;
import com.example.chat.dao.UserDao;
import com.example.chat.model.Invite;
import com.example.chat.model.User;
import com.example.chat.other.builder.UserBuilder;
import com.example.chat.other.dto.InviteDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class InviteServiceTest {

    @Mock
    private InviteDao inviteDao;

    @Mock
    private UserDao userDao;

    @InjectMocks
    private InviteService inviteService;

    private User invited;
    private User inviting;
    private Invite invite;

    @Before
    public void init(){
        invited = UserBuilder.builder()
                .username("invited")
                .id(4L)
                .build();
        inviting = UserBuilder.builder()
                .username("inviting")
                .build();
        invite = Invite.builder()
                .inviting(inviting)
                .invited(invited)
                .build();
    }

    @Test
    public void shouldReturnList(){
        //given
        given(inviteDao.findByInvited(Mockito.any())).willReturn(Collections.singletonList(invite));

        //when
        List<InviteDto> invites = inviteService.findInvites(new User());

        //then
        assertEquals("invited", invites.get(0).getInvitedUsername());
        assertEquals("inviting", invites.get(0).getInvitingUsername());
        assertEquals(4L, invites.get(0).getInvitedId());
    }

    @Test
    public void shouldAdd(){
        //given
        final Invite[] saved = new Invite[1];
        given(userDao.findByUsername(Mockito.any())).willReturn(Optional.of(invited));
        given(inviteDao.save(Mockito.any(Invite.class))).willAnswer(invocationOnMock -> saved[0] = (Invite) invocationOnMock.getArguments()[0]);

        //when
        inviteService.add("invated", inviting);

        //then
        verify(inviteDao).save(any());

        assertEquals("invited", saved[0].getInvited().getUsername());
        assertEquals("inviting", saved[0].getInviting().getUsername());
    }
}