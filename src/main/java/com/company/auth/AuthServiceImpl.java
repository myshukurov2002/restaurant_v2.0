package com.company.auth;

import com.company.config.i18n.MessageService;
import com.company.auth.entity.ProfileEntity;
import com.company.auth.entity.ProfileRoleEntity;
import com.company.auth.enums.ProfileRole;
import com.company.auth.enums.ProfileStatus;
import com.company.expection.exp.AppBadRequestException;
import com.company.expection.exp.ItemNotFoundException;
import com.company.auth.dto.AuthDto;
import com.company.auth.dto.RegistrationDto;
import com.company.base.ApiResponse;
import com.company.auth.dto.JwtResponse;
import com.company.util.JWTUtil;
import com.company.util.MD5Util;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final ProfileRoleRepository profileRoleRepository;
    private final ProfileRepository profileRepository;
    private final MessageService messageService;


    @Override
    public ApiResponse<JwtResponse> login(AuthDto auth) {

        ProfileEntity profileEntity = profileRepository
                .findByPhoneAndVisibilityTrue(auth.phone())
                .orElseThrow(ItemNotFoundException::new);

        if (Objects.equals(profileEntity.getPassword(),
                MD5Util.encode(auth.password()))) {

            log.warn("login: " + auth.password());
            return new ApiResponse<>(true, toDto(profileEntity));
        }
        return new ApiResponse<>(getMessage("wrong.password"), 400, false);
    }

    @Override
    @Transactional
    public ApiResponse<JwtResponse> registration(RegistrationDto reg) {

        profileRepository
                .findByPhoneAndVisibilityTrue(reg.phone())
                .ifPresent(profile -> {
                    throw new AppBadRequestException("phone.already.exists");
                });

        ProfileEntity profile = toEntity(reg);

        ProfileEntity savedProfile = profileRepository.save(profile);
        assignRole(savedProfile.getId());// giving the default role USER

        log.info("registration " + reg.phone());

        return new ApiResponse<>(true, getMessage("success.registration"), toDto(profile));
    }

    private void assignRole(UUID profileId) {

        ProfileRoleEntity role = ProfileRoleEntity.builder()
                .profileRole(ProfileRole.USER)
                .profileId(profileId)
                .build();

        profileRoleRepository.save(role);
    }

    private JwtResponse toDto(ProfileEntity entity) {

        List<ProfileRole> roles = profileRoleRepository
                .findAllRoleList(entity.getId());

        return JwtResponse.builder()
                .id(entity.getId())
                .roles(roles)
                .jwt(JWTUtil.encode(entity.getId(), roles))
                .build();
    }

    private ProfileEntity toEntity(RegistrationDto reg) {

        return ProfileEntity.builder()
                .firstName(reg.firstName())
                .lastName(reg.lastName())
                .phone(reg.phone())
                .password(MD5Util.encode(reg.password()))
                .status(ProfileStatus.ACTIVE)
                .build();
    }

    private String getMessage(String msg) {

        return messageService.getMessage(msg, LocaleContextHolder.getLocale());
    }
}
