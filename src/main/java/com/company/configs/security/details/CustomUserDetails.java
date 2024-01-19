package com.company.configs.security.details;

import com.company.auth.entity.ProfileEntity;
import com.company.auth.enums.ProfileRole;
import com.company.auth.enums.ProfileStatus;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CustomUserDetails implements UserDetails {

    private final ProfileEntity profile;
    private final List<GrantedAuthority> profileRoles;

    public CustomUserDetails(ProfileEntity profile, List<ProfileRole> roleList) {

        this.profile = profile;
        this.profileRoles = roleList.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return profileRoles;
    }

    @Override
    public String getPassword() {
        return profile.getPassword();
    }

    @Override
    public String getUsername() {
        return profile.getPhone();
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
        return profile.getStatus().equals(ProfileStatus.ACTIVE);
    }
}
