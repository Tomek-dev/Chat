package com.example.chat.model;

import com.example.chat.other.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user_model")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    private String password;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name="friends",
    joinColumns = @JoinColumn(name="person_A_id", referencedColumnName="id"), inverseJoinColumns = @JoinColumn(name="person_B_id", referencedColumnName="id"))
    private Set<User> friends = new HashSet<>();

    @OneToMany(mappedBy = "invited", orphanRemoval = true)
    private Set<Invite> invites = new HashSet<>();

    @OneToMany(mappedBy = "inviting", orphanRemoval = true)
    private Set<Invite> invitations = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void addFriend(User user){
        this.friends.add(user);
        user.getFriends().add(this);
    }

    public void removeFriend(User user){
        this.friends.remove(user);
        user.getFriends().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
