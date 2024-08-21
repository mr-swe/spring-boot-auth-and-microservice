package com.example.family_authorization.infrastructurer.security;

import com.example.family_authorization.domain.repository.UserProfileRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserProfileRepository userProfileRepository;

    public CustomUserDetailsService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

//        if (userName.equals("admin")) {
//            return getDefaultUser();
//        }
        final var user = userProfileRepository.findByUserNameAndIsDeletedFalse(userName).orElseThrow(() -> new UsernameNotFoundException("No User Found"));
        return new User(
                user.getUserName(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                getAuthorities(user.getUserName())
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String roles) {

        Set<Long> ids = Arrays.stream(roles.split(",")).map(Long::parseLong).collect(Collectors.toSet());

        List<GrantedAuthority> authorities = new ArrayList<>();
//        List<RoleInfo> roleInfos = roleInfoRepository.findAByIdInAndIsDeletedFalseAndOrganizationId(ids, CurrentUser.getOrgId());
//
//        for (RoleInfo role : roleInfos) {
//            authorities.add(new SimpleGrantedAuthority(role.getName()));
//        }

        authorities.add(new SimpleGrantedAuthority("TEST"));
        return authorities;
    }

    private User getDefaultUser() {
        final var simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_" + "READ");
        return new User(
                "admin",
                ("$2a$10$lgSXFVwZB6qhwD1Ep1YJLuyTNAkM0VUODi5VmRm3r20JXSMx0XZdq"),
                List.of(simpleGrantedAuthority)
        );
    }

}
