package pl.polsl.universityfilesender.user;


import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Collections;

@Entity
@Getter
@Setter
@Table(name = "app_user")
public class User implements UserDetails {

    public enum Role {
        ROLE_STUDENT, ROLE_TEACHER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 100, unique = true, nullable = false)
    @Size(min = 8,max = 100)
    @NotBlank
    private String username;

    @Column(name = "password", nullable = false)
    @NotBlank
    private String password;


    @Column(name = "first_name", length = 100, nullable = false)
    @Size(min = 1, max = 100)
    @NotBlank
    private String firstName;


    @Column(name = "last_name", length = 100, nullable = false)
    @Size(min = 1, max = 100)
    @NotBlank
    private String lastName;


    @Column(nullable = false, name = "role")
    @Enumerated(EnumType.STRING)
    @NotNull
    private Role role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());

        return Collections.singleton(authority);
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


}
