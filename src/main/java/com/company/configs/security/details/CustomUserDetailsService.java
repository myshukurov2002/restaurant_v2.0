package com.company.configs.security.details;

import com.company.auth.entity.ProfileEntity;
import com.company.expection.exp.AppBadRequestException;
import com.company.auth.ProfileRepository;
import com.company.auth.ProfileRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final ProfileRepository profileRepository;
    private final ProfileRoleRepository profileRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        ProfileEntity profile = profileRepository
                .findById(UUID.fromString(username))
                .orElseThrow(() -> new AppBadRequestException("login.or.password.wrong"));

        return new CustomUserDetails(profile, profileRoleRepository.findAllRoleList(profile.getId()));
    }
}
