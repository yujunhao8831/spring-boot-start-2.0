package com.aidijing.controller.security;

import com.aidijing.common.ResponseEntity;
import com.aidijing.domain.User;
import com.aidijing.model.JwtUser;
import com.aidijing.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthenticationController {

    @Value( "${jwt.header}" )
    private String tokenHeaderKey;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 创建身份验证token
     *
     * @param user
     * @param device
     * @return
     * @throws AuthenticationException
     */
    @PostMapping( value = "${jwt.route.authentication.path}" )
    public ResponseEntity createAuthenticationToken ( @RequestBody User user,
                                                      Device device ) throws AuthenticationException {
        // 执行安全
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication( authentication );

        /**
         * Reload password post-security so we can generate token
         * 重新加载密码安全后我们可以生成令牌 {@link org.zerhusen.security.service.JwtUserDetailsServiceImpl#loadUserByUsername(String)}
         * old : final UserDetails userDetails = userDetailsService.loadUserByUsername( authenticationRequest.getUsername() );
         */
        final UserDetails userDetails = ( UserDetails ) authentication.getPrincipal();
        final String      token       = jwtTokenUtil.generateToken( userDetails, device );
        // 返回
        return ResponseEntity.ok().add( "token", token );
    }

    /**
     * 刷新token
     *
     * @param request
     * @return
     */
    @GetMapping( value = "${jwt.route.authentication.refresh}" )
    public ResponseEntity< ? > refreshAndGetAuthenticationToken ( HttpServletRequest request ) {
        String  token    = request.getHeader( tokenHeaderKey );
        String  username = jwtTokenUtil.getUsernameFromToken( token );
        JwtUser user     = ( JwtUser ) userDetailsService.loadUserByUsername( username );

        if ( jwtTokenUtil.canTokenBeRefreshed( token, user.getLastPasswordResetDate() ) ) {
            String refreshedToken = jwtTokenUtil.refreshToken( token );
            return ResponseEntity.ok().add( "token", token );
        } else {
            return ResponseEntity.fail( "token 无法刷新,请重新获取" );
        }
    }

}
