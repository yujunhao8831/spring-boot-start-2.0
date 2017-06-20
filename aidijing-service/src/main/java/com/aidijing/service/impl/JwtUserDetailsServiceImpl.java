package com.aidijing.service.impl;

import com.aidijing.domain.Role;
import com.aidijing.domain.User;
import com.aidijing.model.JwtUser;
import com.aidijing.service.RoleService;
import com.aidijing.service.UserService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername ( String username ) throws UsernameNotFoundException {
        User user = userService.findByUsername( username );
        if ( user == null ) {
            throw new UsernameNotFoundException( String.format( "该'%s'用户名不存在.", username ) );
        } else {
            return new JwtUser(
                    user.getId(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getPassword(),
                    mapToGrantedAuthorities( user.getId() ),
                    user.isEnabled(),
                    user.getLastPasswordResetDate()
            );
        }
    }

    private List< GrantedAuthority > mapToGrantedAuthorities ( Long userId ) {
        List< Role > roles = roleService.getByUserId( userId );
        if ( CollectionUtils.isEmpty( roles ) ) {
            return null;
        }
        return roles.parallelStream()
                    .map( role -> new SimpleGrantedAuthority( role.getRoleNameCode() ) )
                    .collect( Collectors.toList() );
    }
}










