package com.example.demosecurity.config;

import com.example.demosecurity.security.FailureAuthenticationHandler;
import com.example.demosecurity.security.MyUserDetails;
import com.example.demosecurity.security.SuccessAuthenticationHandler;
import com.example.demosecurity.security.VerifyCodeFilter;
import com.example.demosecurity.web.errors.ErrorCode;
import com.example.demosecurity.web.errors.MyException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.*;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

import static org.springframework.security.config.Elements.REMEMBER_ME;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    private FailureAuthenticationHandler failureAuthenticationHandler;
    @Autowired
    private VerifyCodeFilter verifyCodeFilter;

    @Autowired SuccessAuthenticationHandler successAuthenticationHandler;
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/index").permitAll()//index路径免认证
//                .anyRequest().authenticated()
//                .and().formLogin()//httpbasic方式认证
//                .and().csrf().disable();//CSRF（Cross-site request forgery），中文名称：跨站请求伪造，也被称为：one click attack/session riding，缩写为：CSRF/XSRF。
//        super.configure(http);
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder().encode("123456")).roles("USER","ADMIN")
//                .and()
//        .withUser("user").password(passwordEncoder().encode("123456")).roles("USER");
//    }


    /**
     * 配置编码器
     * @return
     */
    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(){
            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                if (!super.matches(rawPassword, encodedPassword)){
                    throw new BadCredentialsException("用户认证失败");
                }
                return true;
            }
        };
    }


    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource ;

    @Autowired
    private UserDetailsService userService;
    @Bean
    public RememberMeServices rememberMeServices(){
        InMemoryTokenRepositoryImpl tokenRepository = new InMemoryTokenRepositoryImpl();
        PersistentTokenBasedRememberMeServices rememberMeServices =
                new PersistentTokenBasedRememberMeServices(REMEMBER_ME,userService,tokenRepository);
        //客户端cookie名
        rememberMeServices.setCookieName(REMEMBER_ME);
        rememberMeServices.setTokenValiditySeconds(10);
        return rememberMeServices;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()//配置权限
//                .antMatchers("/order/**").hasAuthority("/order") //特殊的uri才需要在此处配置
                //特殊的uri才需要在此处配置 ROLE_ADMIN
                .antMatchers("/order/**").hasRole("ADMIN")
                .antMatchers("/util/**","/favicon.ico","/login","/js/**","/css/**").permitAll()
                .anyRequest().authenticated()//任意请求需要登录
                .and()
                //开启formLogin默认配置
                .formLogin()
                //请求时未登录跳转接口  spring security 提供了默认的登录页
                .loginPage("/login")
//                .failureUrl("/login?error=true")//用户密码错误跳转接口
                .failureHandler(failureAuthenticationHandler) //如果需要复杂的业务处理失败的情况，可配置failhandle
//                .defaultSuccessUrl("/index", true)//登录成功跳转接口
                .successHandler(successAuthenticationHandler)
//                .loginProcessingUrl("/login")//post登录接口，登录验证由系统实现
//                .usernameParameter("username")    //要认证的用户参数名，自定义登录页面时参数名设置，默认username
//                .passwordParameter("password")    //要认证的密码参数名，自定义登录页面时参数名设置默认password
                .permitAll()
                .and()
                //开启记住我的拦截器
                .rememberMe()
                .rememberMeServices(rememberMeServices())
                .and()
                .logout()//配置注销
                .logoutUrl("/logout")//注销接口
                .logoutSuccessUrl("/login").permitAll()//注销成功跳转接口 注销后返回首页或者登录页
                .and()
                .csrf().disable()//禁用csrf
                .exceptionHandling().accessDeniedPage("/error403");//拒绝访问时页面跳转


                //session管理,失效后跳转
                http.sessionManagement().invalidSessionUrl("/login");
                //单用户登录，如果有一个登录了，同一个用户在其他地方登录将前一个剔除下线
                //http.sessionManagement().maximumSessions(1).expiredSessionStrategy(expiredSessionStrategy());
                //单用户登录，如果有一个登录了，同一个用户在其他地方不能登录
                http.sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(true);
                //退出时情况cookies
                http.logout().deleteCookies("JESSIONID");//根据实际进行定义
                //解决中文乱码问题
//                CharacterEncodingFilter filter = new CharacterEncodingFilter();
//                filter.setEncoding("UTF-8"); filter.setForceEncoding(true);
                //
//                http.addFilterBefore(filter,CsrfFilter.class);
//        http.addFilterBefore(verifyCodeFilter,  UsernamePasswordAuthenticationFilter.class);
        http.headers().frameOptions().disable(); //使用 springsecurity + h2database 必须要配置csrf().disable()和 http.headers().frameOptions().disable()
    }
}
