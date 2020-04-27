package com.example.chat.service;

import com.example.chat.dao.InviteDao;
import com.example.chat.dao.UserDao;
import com.example.chat.model.Invite;
import com.example.chat.model.User;
import com.example.chat.other.builder.UserBuilder;
import com.example.chat.other.dto.FriendDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class FriendServiceTest {

    @Mock
    private InviteDao inviteDao;

    @Mock
    private UserDao userDao;

    @InjectMocks
    private FriendService friendService;

    private User woody;
    private User buzzLightYear;

    @Before
    public void init(){
        woody = UserBuilder.builder()
                .username("Woody")
                .build();
        buzzLightYear = UserBuilder.builder()
                .username("BuzzLightYear")
                .id(4L)
                .build();
    }

    @Test
    public void shouldReturnList(){
        //given
        woody.addFriend(buzzLightYear);

        //when
        List<FriendDto> friends = friendService.findFriends(woody);

        //then
        assertEquals("BuzzLightYear", friends.get(0).getUsername());
        assertEquals(4L, friends.get(0).getId());
    }

    @Test
    public void shouldRemoveFriend(){
        //given
        woody.addFriend(buzzLightYear);
        given(userDao.findById(Mockito.any())).willReturn(Optional.of(buzzLightYear));

        //when
        friendService.removeFriend(4L, woody);

        //then
        assertFalse(woody.getFriends().contains(buzzLightYear));
    }

    @Test
    public void shouldAddFriend(){
        //given
        Invite invite = Invite.builder()
                .inviting(buzzLightYear)
                .invited(woody)
                .build();
        given(inviteDao.findByInvitingIdAndInvited(Mockito.any(), Mockito.any())).willReturn(Optional.of(invite));

        //when
        friendService.addFriend(4L, woody);

        //then
        assertTrue(woody.getFriends().contains(buzzLightYear));
    }
}