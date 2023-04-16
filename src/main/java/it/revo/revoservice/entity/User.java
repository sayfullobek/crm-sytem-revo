package it.revo.revoservice.entity;

import it.revo.revoservice.entity.enums.LidStatus;
import it.revo.revoservice.entity.template.AbsEntity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
@Builder
public class User extends AbsEntity implements UserDetails {
    @Column(nullable = false)
    private String firstName;

    private String lastName;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    private String parentNumber;

    //    @Column(nullable = false)
    private String password;

    private String code;

    @ManyToOne(optional = false)
    private Role role;

    @ManyToMany
    private List<Course> courses;

    private String birthDate;

    @OneToMany
    private List<Statistics> statistics;

    @OneToOne
    private Contact contact;

    @OneToMany
    private List<Accounts> accaunts;

    @OneToMany
    private List<WorkProgress> workProgresses;

    @Enumerated(value = EnumType.STRING)
    private LidStatus lidStatus;

    private boolean enabled = true;

    private boolean credentialsNonExpired = true;

    private boolean accountNonLocked = true;
    private boolean accountNonExpired = true;

    public User(String firstName, String lastName, String phoneNumber, String password, Role roles, String code) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role = roles;
        this.code = code;
    }

    public User(String firstName, String lastName, String phoneNumber, Role roles, List<Course> courses, String birthDate, String parentNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.role = roles;
        this.courses = courses;
        this.birthDate = birthDate;
        this.parentNumber = parentNumber;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(role);
    }

    @Override
    public String getUsername() {
        return phoneNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
