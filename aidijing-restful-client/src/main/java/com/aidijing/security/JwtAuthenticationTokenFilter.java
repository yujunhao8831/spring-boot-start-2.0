package com.aidijing.security;

import com.aidijing.common.util.LogUtils;
import com.aidijing.model.JwtUser;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    ///////////////////////////////////////////////////////////////////////////
    // 在log4j2.xml配置文件通过 %X{xxx} 获取 
    ///////////////////////////////////////////////////////////////////////////
    /** 用户ID */
    private static final String USER_ID   = "userId";
    /** 用户姓名 */
    private static final String USER_NAME = "userName";
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil       jwtTokenUtil;
    @Value( "${jwt.header}" )
    private String             tokenHeader;


    @Override
    protected void doFilterInternal ( HttpServletRequest request,
                                      HttpServletResponse response,
                                      FilterChain chain ) throws ServletException, IOException {
        final String authToken = request.getHeader( this.tokenHeader );
        final String username  = jwtTokenUtil.getUsernameFromToken( authToken );

        if ( LogUtils.getLogger().isDebugEnabled() ) {
            LogUtils.getLogger().debug( "authToken : {},username : {}", authToken, username );
        }

        if ( username != null && SecurityContextHolder.getContext().getAuthentication() == null ) {
            // It is not compelling necessary to load the use details from the database. You could also store the information
            // in the token and read it from it. It's up to you ;)
            UserDetails userDetails = this.userDetailsService.loadUserByUsername( username );
            // For simple validation it is completely sufficient to just check the token integrity. You don't have to call the database compellingly. Again it's up to you ;)
            // 对于简单的验证是完全足够的只是检查令牌的完整性。你不需要调用数据库校验。这取决于你.)
            if ( jwtTokenUtil.validateToken( authToken, userDetails ) ) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                ThreadContext.put( USER_ID, String.valueOf( ( ( JwtUser ) userDetails ).getId() ) );
                ThreadContext.put( USER_NAME, username );

                authentication.setDetails( new WebAuthenticationDetailsSource().buildDetails( request ) );


                if ( LogUtils.getLogger().isDebugEnabled() ) {
                    LogUtils.getLogger().debug( "authToken : {},username : {}", authToken, username );
                }
                if ( LogUtils.getLogger().isDebugEnabled() ) {
                    LogUtils.getLogger().debug( "该 " + username + "用户已认证, 设置安全上下文" );
                }
                SecurityContextHolder.getContext().setAuthentication( authentication );
            }
        }
        chain.doFilter( request, response );
    }
}